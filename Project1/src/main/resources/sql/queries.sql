declare
    v_supervisor_number number;
begin
     select role_number
    into v_supervisor_number
    from project_1_role
    where role_name='Supervisor';
    dbms_output.put_line('The role number for Supervisor is ' || v_supervisor_number);
 end;
 
select * from project_1_user;

select * from project_1_role;

select * from project_1_role where role_name='Supervisor';

call selectUserByUsername('user');