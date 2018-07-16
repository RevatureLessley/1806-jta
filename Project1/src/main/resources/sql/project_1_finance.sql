drop table project_1_finance;
create table project_1_finance (
    employee_uuid varchar2(36) primary key,
    amount_available number(5),
    amount_awarded number(5),
    amount_pending number(5),
    amount_exceeded number(5)
);
/

create or replace procedure selectFinancialInformation(uuid in varchar2,
                                                       returnCursor out sys_refcursor)
is
begin
    open returnCursor for select * from project_1_finance where employee_uuid = uuid;
end;
/

create or replace procedure selectAmountAvailable(uuid in varchar2,
                                                  returnCursor out sys_refcursor)
is
begin
    open returnCursor for select amount_available from project_1_finance where employee_uuid = uuid;
end;
/

create or replace procedure selectAmountAwarded(uuid in varchar2,
                                                returnCursor out sys_refcursor)
is
begin
    open returnCursor for select amount_awarded from project_1_finance where employee_uuid = uuid;
end;
/

create or replace procedure selectPendingAmount(uuid in varchar2,
                                                returnCursor out sys_refcursor)
is
begin
    open returnCursor for select amount_pending from project_1_finance where employee_uuid = uuid;
end;
/

create or replace procedure selectAmountExceeded(uuid in varchar2,
                                                 returnCursor out sys_refcursor)
is
begin
    open returnCursor for select amount_exceeded from project_1_finance where employee_uuid = uuid;
end;
/

create or replace procedure updateExceededAmount(uuid in varchar2,
                                                 amount in number)
is
begin
    update project_1_finance set amount_exceeded = amount where employee_uuid = uuid;
end;
/

create or replace procedure updateAwardedAmount(uuid in varchar2,
                                                amount in number)
is
begin
    update project_1_finance set amount_awarded = amount where employee_uuid = uuid;                     
    commit;
end;
/

create or replace procedure updatePendingAmount(uuid in varchar2,
                                                amount in number)
is
begin
    update project_1_finance set amount_pending = amount where employee_uuid = uuid;
    commit;
end;
/

create or replace procedure updateAvailable(uuid in varchar2,
                                            amount in number)
is
begin
    update project_1_finance set amount_awarded = amount where employee_uuid = uuid;
    commit;
end;
/
