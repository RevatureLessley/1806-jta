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