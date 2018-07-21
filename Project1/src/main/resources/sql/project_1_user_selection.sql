create or replace procedure selectAllUsers (returnCursor out sys_refcursor)
as
begin
    open returnCursor for select * from project_1_user inner join project_1_finance on project_1_user.uuid = project_1_finance.employee_uuid;
end;
/

create or replace procedure selectAllBenCoUsers (returnCursor out sys_refcursor)
is
begin
    open returnCursor for select * from project_1_role_relationship where employee_role='Benefits Coordinator';
end;
/

create or replace procedure selectAllDeptHeadUsers (returnCursor out sys_refcursor)
is
begin
    open returnCursor for select * from project_1_role_relationship where employee_role='Department Head';
end;
/

create or replace procedure selectAllSupervisorUsers (returnCursor out sys_refcursor)
is
begin
    open returnCursor for select * from project_1_role_relationship where employee_role='Supervisor';
end;
/

create or replace procedure selectAllAdminUsers (returnCursor out sys_refcursor)
is
begin
    open returnCursor for select * from project_1_role_relationship where employee_role='Admin';
end;
/

create or replace procedure selectAllGeneralUsers (returnCursor out sys_refcursor)
is
begin
    open returnCursor for select * from project_1_user where project_1_user.uuid not in (select employee_uuid from project_1_role_relationship);
end;
/