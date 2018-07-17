-- currently only takes time in days
drop table project_1_time;
create table project_1_time (
    unit varchar2(30) primary key,
    urgent_request_start_of_course number(10),
    supervisor_auto_approve_limit number(10),
    dept_head_auto_approve_limit number(10)
);
/
insert into project_1_time values ('DAYS',14,14,21);
commit;
/

-- If the urgent request start of course time has been exceeded,
-- the Scheduler automatically approves the reimbursement request.
create or replace procedure autoApprovalOverride
as
    v_current_time varchar(40);
    v_approve varchar2(15) := 'Approved.';
begin
    select systimestamp  into v_current_time from dual;
    update project_1_reimbursement_form set benco_decision = v_approve,
                                            benco_decision_date = v_current_time,
                                            benco_comments = 'This entire form has been auto-approved. ',
                                            supervisor_decision = v_approve,
                                            supervisor_decision_date = v_current_time,
                                            supervisor_comments = 'This entire form has been auto-approved. ',
                                            department_head_decision = v_approve,
                                            department_head_decision_date = v_current_time,
                                            department_head_comments = 'This entire form has been auto-approved. ',
                                            status = v_approve,
                                            form_comments = 'This entire form has been auto-approved. ';
    commit;
end;
/

-- If the supervisor auto approval limit exceeds
-- the form_creation_date + the dept_head_auto_approval limit
-- and supervisor_decision is null.
-- the Scheduler automatically approves the supervisor
-- portion of th reimbursement request.
create or replace procedure supervisorAutoApproval
as
    v_current_time varchar(40);
    v_time_limit number(10);
    v_approve varchar2(15) := 'Approved.';
    v_form_comments varchar2(300);
    v_supervisor_comments varchar(300);
begin
    select systimestamp into v_current_time from dual;
    select supervisor_auto_approve_limit into v_time_limit from project_1_time where unit='DAYS';
    select form_comments into v_form_comments from project_1_reimbursement_form;
    select supervisor_comments into v_supervisor_comments from project_1_reimbursement_form;
    update project_1_reimbursement_form set supervisor_decision_date = v_current_time,
                                            supervisor_decision = v_approve,
                                            form_comments = v_form_comments || ' The supervisor decision was auto-approved. ',
                                            supervisor_comments = v_supervisor_comments || ' The supervisor decision was auto-approved. '
                                            where supervisor_decision = null and form_creation_date + v_time_limit < v_current_time;
    commit;
end;
/

-- For all forms where the form_creation has exceeded
-- 
-- If the department head auto approval limit has been exceeded,
-- the Scheduler automatically approves the department head
-- portion of the reimbursement request.
create or replace procedure deptHeadAutoApproval
as
    v_current_time varchar2(40);
    v_time_limit number(10);
    v_approve varchar2(15) := 'Approved.';
    v_form_comments varchar2(300);
    v_department_head_comments varchar2(300);
begin
    select systimestamp into v_current_time from dual;
    select dept_head_auto_approve_limit into v_time_limit from project_1_time where unit='DAYS';
    select form_comments into v_form_comments from project_1_reimbursement_form;
    select department_head_comments into v_department_head_comments from project_1_reimbursement_form;
    update project_1_reimbursement_form set department_head_decision_date = v_current_time,
                                            department_head_decision = v_approve,
                                            form_comments = v_form_comments || ' The department head decision was auto-approved. ',
                                            department_head_comments = v_department_head_comments || ' The department head decision was auto-approved. '
                                            where department_head_decision = null and form_creation_date + v_time_limit < v_current_time;
    commit;
end;
/

select systimestamp from dual;
begin
    dbms_scheduler.drop_job(job_name => 'auto_approver_supervisor');
end;
/
declare
    v_start_date date;
    v_end_date date;
    v_limit number;
begin
    select systimestamp into v_start_date from dual;
    select supervisor_auto_approve_limit into v_limit from project_1_time;
    dbms_scheduler.create_job (
    job_name            =>  'auto_approver_supervisor',
    job_type            =>  'stored_procedure',
    job_action          =>  'supervisor_auto_approval',
    start_date          =>  v_start_date,
    repeat_interval     =>  'FREQ=DAILY',
    auto_drop           =>  false,
    comments            =>  'This is the Supervisor Auto-approval Job.'
    );
end;
/

begin
    dbms_scheduler.drop_job(job_name => 'auto_approver_department_head');
end;
/
declare
    v_start_date date;
    v_end_date date;
begin
    select systimestamp into v_start_date from dual;
    dbms_scheduler.create_job (
    job_name            =>  'auto_approver_department_head',
    job_type            =>  'stored_procedure',
    job_action          =>  'deptHeadAutoApproval',
    start_date          =>  v_start_date,
    repeat_interval     =>  'FREQ=DAILY',
    auto_drop           =>  false,
    comments            =>  'This is the Department Head Auto-approval Job.'
    );
end;
/

begin
    dbms_scheduler.drop_job(job_name => 'auto_approver_urgent_override');
end;
/
declare
    v_start_date date;
    v_end_date date;
begin
    select systimestamp into v_start_date from dual;
    dbms_scheduler.create_job (
    job_name            =>  'auto_approver_urgent_override',
    job_type            =>  'stored_procedure',
    job_action          =>  'autoApprovalOverride',
    start_date          =>  v_start_date,
    repeat_interval     =>  'FREQ=DAILY',
    auto_drop           =>  false,
    comments            =>  'This is the Form Auto-approval Job for urgent course start forms.'
    );
end;
/