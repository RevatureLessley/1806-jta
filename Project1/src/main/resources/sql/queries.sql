declare
    v_supervisor_number number;
begin
     select role_number
    into v_supervisor_number
    from project_1_role
    where role_name='Supervisor';
    dbms_output.put_line('The role number for Supervisor is ' || v_supervisor_number);
 end;
 
select 
       u.username, u.first_name, u.last_name,
       u.email, u.phone_number, areimbursement.amount_available,
       f.form_uuid, f.employee_uuid, f.form_creation_date,
       f.status, f.direct_supervisor_uuid, f.department_head_uuid,
       f.benco_decision, f.supervisor_decision, f.supervisor_decision_date,
       f.supervisor_comments, f.depertment_head_decision, f.department_head_decision_date,
       rform.form_closed_date, rform.present_to_management_required, rform.completed_with_satisfaction
       from project_1_reimbursement_form f
       left outer join project_1_user u
       on rform.employee_uuid = u.uuid
       left outer join project_1_user_role_relationship rrelationship
       on rform.employee_uuid = rrelationship.employee_uuid
       left outer join project_1_available_reimbursement areimbursement
       on areimbursement.employee_uuid = u.uuid
       left outer join project_1_attachment attachment
       on attachment.employee_uuid = u.uuid
       left outer join project_1_grade grade
       on grade.form_uuid = u.uuid;
/

declare
    available number := 10000;
    pending number := 0;
    awarded number := 0;
    exceeded number := 0;
begin
    insert into project_1_finance (employee_uuid, amount_available, amount_awarded, amount_pending, amount_exceeded) select uuid, available, pending, awarded, exceeded from project_1_user;
end;
/

select * from project_1_user
inner join project_1_finance
on project_1_user.uuid = project_1_finance.employee_uuid;

select * from project_1_role;

select * from project_1_role where role_name='Supervisor';

select systimestamp from dual;

declare
    v_cur sys_refcursor;
    v_amount number;
    v_uuid varchar2(36) := '2d51a67f-fcc1-40d0-8724-f35727d4f534';
begin
    selectAmountAvailable(v_uuid, v_cur);
    loop
        fetch v_cur into v_amount;
        exit when v_cur%notfound;
        dbms_output.put_line('Amount Available: ' || v_amount);
    end loop;
    close v_cur;
end;

declare
    v_cur sys_refcursor;
    v_amount number;
begin
    selectAllUsers(v_cur);
    loop
        fetch v_cur into v_amount;
        exit when v_cur%notfound;
        dbms_output.put_line('Amount Available: ' || v_amount);
    end loop;
    close v_cur;
end;

select * from project_1_user;

declare
    v_current_time date;
begin
    select systimestamp into v_current_time from dual;
    insert into project_1_reimbursement_form
    values ('d80afbaa-d6be-4720-bda8-e249a3d8c79b', '2d51a67f-fcc1-40d0-8724-f35727d4f534', v_current_time, 'Form comments', 'Approved', 'd014c686-ddb7-44b8-bd92-68661fd25afd', '0807dbf4-0a3a-4123-b8c2-3b60e3216cb4', '4a2b19a2-4830-4e65-9cbe-3fbf6bffa42a', 'YES', v_current_time, 'Approved.', 'YES', v_current_time, 'No comments.', 'YES', v_current_time, 'No comments.', v_current_time, 'YES', 'YES');
end;
/
select * from project_1_reimbursement_form;


declare
    v_cursor sys_refcursor;
    v_uuid varchar2(36);
begin
    selectAllforms(v_cursor);
    loop
    exit when v_cursor%NOTFOUND;
    fetch v_cursor into v_uuid;
        select * from table(v_uuid);
    end loop;
end;
/
select * from project_1_role;
select role_name from (select * from project_1_role inner join project_1_role_relationship on employee_role = role_number);
exec insertAdmin('80d60e04-bcd0-42be-a5d8-b9a4ff4d12c4');
exec insertSupervisor('80d60e04-bcd0-42be-a5d8-b9a4ff4d12c4');
exec insertDepartmentHead('80d60e04-bcd0-42be-a5d8-b9a4ff4d12c4');
exec insertBenefitsCoordinator('80d60e04-bcd0-42be-a5d8-b9a4ff4d12c4');
select * from project_1_reimbursement_form;
select * from project_1_user;
alter table project_1_user modify email varchar2(100);
delete from project_1_user where username is null;
commit;