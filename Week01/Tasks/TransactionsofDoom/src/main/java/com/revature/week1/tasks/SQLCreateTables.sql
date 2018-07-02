
drop table users2;
drop table unapproved_users;
drop table approved_users;
drop table admin_users;


create table unapproved_users(
user_id number(10),
user_name  varchar2(20),
user_password  varchar2(20),
user_balance number(10),
user_admin  number(1),
user_approved  number(1)
);

create table approved_users(
user_id  number(10),
user_name  varchar2(20),
user_password  varchar2(20),
user_balance number(10),
user_admin  number(1),
user_approved  number(1)
);

create table admin_users(
user_id  number(10),
user_name  varchar2(20),
user_password  varchar2(20),
user_balance number(10),
user_admin  number(1),
user_approved  number(1)
);

create table users2(
user_id  number(10),
user_name  varchar2(20),
user_password  varchar2(20),
user_balance number(10),
user_admin  number(1),
user_approved  number(1)
);

commit;