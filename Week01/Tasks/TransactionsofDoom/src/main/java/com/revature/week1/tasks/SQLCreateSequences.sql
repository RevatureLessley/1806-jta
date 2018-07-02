create or replace procedure insertintousers(username in varchar2, userpassword in varchar2, userbalance in number, useradmin in number, userapproved in number)
is
begin
insert into users (user_name, user_password, user_balance, user_admin, user_approved)
values (username, userpassword, userbalance, useradmin, userapproved);
commit;
end;
/
create or replace procedure insertintounapprovedusers(username in varchar2, userpassword in varchar2, userbalance in number, useradmin in number, userapproved in number)
is
begin
insert into unapproved_users (user_name, user_password, user_balance, user_admin, user_approved)
values (username, userpassword, userbalance, useradmin, userapproved);
commit;
end;
/
create or replace procedure insertintoapprovedusers(username in varchar2, userpassword in varchar2, userbalance in number, useradmin in number, userapproved in number)
is
begin
insert into approved_users (user_name, user_password, user_balance, user_admin, user_approved)
values (username, userpassword, userbalance, useradmin, userapproved);
commit;
end;
/
create or replace procedure insertintoadminusers(username in varchar2, userpassword in varchar2, userbalance in number, useradmin in number, userapproved in number)
is
begin
insert into admin_users (user_name, user_password, user_balance, user_admin, user_approved)
values (username, userpassword, userbalance, useradmin, userapproved);
commit;
end;