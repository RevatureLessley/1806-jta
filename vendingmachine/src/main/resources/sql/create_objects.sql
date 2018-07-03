-- Vending Machine Project 0
-- Author Nathan Edwards

-- Drop all objects if they exist
drop table inventory;
drop trigger item_sequence_trigger;
drop sequence item_sequence;
drop table item;
drop table vending_machine_user;
drop table vending_machine_table;
drop table save_file;

-- item table
create table vending_machine_item (
    item_index number(10) primary key,
    item_name varchar2(32),
    item_value number(4),
    item_cannot_be_sold varchar2(1),
    item_description varchar2(100),
    serialized_object blob);

-- item table
create sequence vending_machine_item_sequence
start with 1
increment by 1;

create or replace trigger vending_machine_item_sequence_trigger
before insert on vending_machine_item
for each row
begin
    if :new.item_index is null then
    select item_sequence.NEXTVAL into :new.item_index from dual;
    end if;
end;

-- inventory table
create table inventory (
    uuid varchar2(32) primary key,
    serialized_object blob
);
    
-- vending_machine_user table
create table vending_machine_user (
    uuid varchar2(32) primary key,
    user_name varchar2(32),
    cash number(6),
    enabled varchar2(1),
    serialized_object blob
);

-- vending_machine table
create table vending_machine (
    vending_machine_index number(7) primary key,
    vending_machine_uuid varchar2(32),
    machine_name varchar2(32),
    admin_name varchar2(32),
    admin_uuid varchar2(32),
    serialized_object blob,
    constraint fk_admin_uuid foreign key (admin_uuid) references vending_machine_user (uuid)
);

-- save file
create table save_file (
    save_file_index number(1) primary key,
    serialized_object blob
)

create sequence save_file_sequence
start with 1
increment by 1;

create or replace trigger save_file_sequence_trigger
before insert on save_file
for each row
begin
    if :new.save_file_index is null then
        select save_file_sequence.NEXTVAL into :new.save_file_index from dual;
    end if;
end;
