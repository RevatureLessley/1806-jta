
-- item table
create or replace procedure insertIntoVendingMachineItem(itemName in varchar2,
                                                         itemValue in number,
                                                         cannotBeSold in varchar2,
                                                         itemDescription in varchar2,
                                                         serializedObject in blob)
is
begin
    insert into vending_machine_item (item_index, item_name, item_value, item_cannot_be_sold, item_description, serialized_object)
    values (null, itemName,itemValue, cannotBeSold, itemDescription, serializedObject);
    commit;
end;
/

-- vending_machine_user table
create or replace procedure insertIntoVendingMachineUser(uuid in varchar2,
                                                         username in varchar2,
                                                         cashAmount in number,
                                                         isEnabled in varchar2,
                                                         serializedObject blob)
is
begin
    insert into vending_machine_user (uuid, user_name, cash, enabled, serialized_object)
    values (uuid, usernamer, cashAmount, isEnabled, serializedObject);
    commit;
end;
/

-- vending_machine table
create or replace procedure insertIntoVendingMachine(uuid in varchar2,
                                                     machineName in number,
                                                     adminName in varchar2,
                                                     adminUUID in varchar2,
                                                     serializedObject blob)
is
begin
    insert into vending_machine(vending_machine_index, vending_machine_uuid, machine_name, admin_name, admin_uuid, serialized_object)
    values (null, uuid, machineName, adminName, adminUUID, serializedObject);
    commit;
end;
/

-- inventory table
create or replace procedure insertIntoInventory(uuid in varchar2,
                                                serializedObject blob)
is
begin
    insert into inventory(uuid, serialized_object)
    values(uuid, serialized_object);
    commit;
end;
/
                                                
-- save_file table
create or replace procedure insertIntoVendingMachineItem(serializedObject blob)
is
begin
    insert into save_file(save_file_index, serialized_object)
    values(null, serializedObject);
    commit;
end;
/
