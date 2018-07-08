-- Tuition Reimbursement Management System Project 1
-- Author Nathan Edwards

drop table project_1_user;
create table project_1_user (
    uuid varchar2(36) primary key,
    username varchar2(32) unique,
    login_password varchar2(50),
    first_name varchar2(100),
    last_name varchar2(100),
    email varchar2(25),
    phone_number varchar2(25)
);
/

drop table project_1_role;
create table project_1_role (
    role_number number(2) primary key,
    role_name varchar2(50)
);
/

drop sequence project_1_role_seq;
create sequence project_1_role_seq
start with 1
increment by 1;
/

drop trigger project_1_role_seq_trigger;
create or replace trigger project_1_role_seq_trigger
before insert on project_1_role
for each row
begin
    if :new.role_number is null then
    select project_1_role_seq.NEXTVAL into :new.role_number from dual;
    end if;
end;
/

insert into project_1_role
values (null, 'Supervisor');
insert into project_1_role
values (null, 'Benefits Coordinator');
insert into project_1_role
values (null, 'Admin');

create table project_1_role_relationship (
    employee_uuid varchar2(36),
    employee_role number(2),
    constraint fk_role_uuid foreign key (employee_uuid) references project_1_user (uuid),
    constraint fk_role_number foreign key (employee_role) references project_1_role
);
/

create table project_1_direct_supervisor (
    employee_uuid varchar2(36),
    direct_supervisor_uuid varchar2(36),
    constraint fk_employee_uuid foreign key (employee_uuid) references project_1_user (uuid),
    constraint fk_direct_supervisor foreign key (direct_supervisor_uuid) references project_1_user (uuid)
);
/

create or replace procedure selectAllUsers (returnCursor out sys_refcursor)
as
begin
    open returnCursor for select * from project_1_user;
end;
/
call selectUserByUsername('user');
create or replace procedure selectUserByUsername (userHandle in varchar2,
                                                   returnCursor out sys_refcursor)
is
begin
    open returnCursor for select * from project_1_user where username=userHandle;
end;
/

create or replace procedure selectWithUsername(userHandle in varchar2,
                                               userPassword in varchar2,
                                               returnCursor out sys_refcursor)
is
begin
    open returnCursor for select * from project_1_user where username = userHandle and 
                                       login_password = userPassword;
end;
/

create or replace procedure selectWithEmail(userEmail in varchar2,
                                               userPassword in varchar2,
                                               returnCursor out sys_refcursor)
is
begin
    open returnCursor for select * from project_1_user where email = userEmail and 
                                       login_password = userPassword;
end;
/

create or replace procedure insertIntoUser(userUuid in varchar2,
                                           userHandle in varchar2,
                                           loginPassword in varchar2,
                                           firstName in varchar2,
                                           lastName in varchar2,
                                           userEmail in varchar2,
                                           phoneNumber in varchar2)
is
begin
    insert into project_1_user (uuid, username, login_password, first_name, last_name, email, phone_number)
    values(userUuid, userHandle, loginPassword, firstName, lastName, userEmail, phoneNumber);
    commit;
end;
/

create or replace procedure updateUser(userUuid in varchar2,
                                       userHandle in varchar2,
                                       loginPassword in varchar2,
                                       firstName in varchar2,
                                       lastName in varchar2,
                                       userEmail in varchar2,
                                       phoneNumber in varchar2)
is
begin
    update project_1_user set username = userHandle,
                              login_password = loginPassword,
                              first_name = firstName,
                              last_name = lastName,
                              email = userEmail,
                              phone_number = phoneNumber
                          where uuid = userUuid;
    commit;
end;
/