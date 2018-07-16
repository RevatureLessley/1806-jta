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
    currentTime varchar(40);
    approve varchar2(15) := 'Approved.';
begin
    select systimestamp  into currentTime from dual;
    update project_1_reimbursement_form set benco_decision = approve,
                                            benco_decision_date = currentTime,
                                            supervisor_decision = approve,
                                            supervisor_decision_date = currentTime,
                                            department_head_decision = approve,
                                            department_head_decision_date = currentTime,
                                            status = approve,
                                            supervisor_comments = 'This entire form has been auto-approved. ';
    commit;
end;
/

-- If the supervisor auto approval limit has been exceeded,
-- the Scheduler automatically approves the supervisor
-- portion of th reimbursement request.
create or replace procedure supervisorAutoApproval
as
    currentTime varchar(40);
    approve varchar2(15) := 'Approved.';
begin
    select systimestamp into currentTime from dual;
    update project_1_reimbursement_form set supervisor_decision_date = currentTime,
                                            supervisor_decision = approve,
                                            supervisor_comments = 'Supervisor decision was auto-approved. ';
    commit;
end;
/

-- If the department head auto approval limit has been exceeded,
-- the Scheduler automatically approves the department head
-- portion of the reimbursement request.
create or replace procedure deptHeadAutoApproval()
as
    currentTime varchar(40);
    approve varchar2(15) := 'Approved.';
begin
    select systimestamp into currentTime from dual;
    update project_1_reimbursement_form set department_head_decision_date = currentTime,
                                            department_head_decision = approve,
                                            supervisor_comments = 'Supervisor decision was auto-approved. ';
    commit;
end;
/
