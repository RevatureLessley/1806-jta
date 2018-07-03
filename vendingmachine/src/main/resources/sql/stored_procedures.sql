
-- item table
create or replace procedure insertIntoVendingMachineItem(itemName in varchar2,
                                                         itemValue in number,
                                                         cannotBeSold in varchar2,
                                                         itemDescription in varchar2,
                                                         serializedObject in blob)
is
begin
    insert into vending_machine_item (item_name, item_value, item_cannot_be_sold, item_description, serialized_object)
    values (itemName, itemValue, cannotBeSold, itemDescription, serializedObject);
    commit;
end;
/

create or replace procedure updateVendingMachineItem(itemName in varchar2,
                                                     itemValue in number,
                                                     cannotBeSold in varchar2,
                                                     itemDescription in varchar2,
                                                     serializedObject in blob)
is
begin
    update vending_machine_item set item_value = itemValue,
                                    item_cannot_be_sold = cannotBeSold,
                                    item_description = itemDescription,
                                    serialized_object = serializedObject
                                where item_name = itemName;
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
    values (uuid, username, cashAmount, isEnabled, serializedObject);
    commit;
end;
/

create or replace procedure updateVendingMachineUser(vendingMachineId in varchar2,
                                                     username in varchar2,
                                                     cashAmount in number,
                                                     isEnable in varchar2,
                                                     serializedObject blob)
is
begin
    update vending_machine_user set user_name = username,
                                    cash = cashAmount,
                                    enabled = isEnable,
                                    serialized_object = serializedObject
                                where uuid = vendingMachineId;
    commit;
end;

-- vending_machine table
create or replace procedure insertIntoVendingMachine(uuid in varchar2,
                                                     machineName in number,
                                                     adminName in varchar2,
                                                     adminUUID in varchar2,
                                                     serializedObject blob)
is
begin
    insert into vending_machine(vending_machine_uuid, machine_name, admin_name, admin_uuid, serialized_object)
    values (uuid, machineName, adminName, adminUUID, serializedObject);
    commit;
end;
/

create or replace procedure updateVendingMachine(uuid in varchar2,
                                                 machineName in number,
                                                 adminName in varchar2,
                                                 adminUUID in varchar2,
                                                 serializedObject blob)
is
begin
    update vending_machine set machine_name = machineName,
                               admin_name = adminName,
                               admin_uuid = adminUUID,
                               serialized_object = serializedObject
                              where vending_machine_uuid = uuid;
    commit;
end;
/

-- inventory table
create or replace procedure insertIntoInventory(objectUUID in varchar2,
                                                serializedObject blob)
is
begin
    insert into inventory(uuid, serialized_object)
    values(objectUUID, serializedObject);
    commit;
end;
/

create or replace procedure updateInventory(objectUUID in varchar2,
                                            serializedObject blob)
is
begin
    update inventory set serialized_object = serializedObject
                     where uuid = objectUUID;
    commit;
end;

-- save_file table
create or replace procedure insertIntoVendingMachineItem(serializedObject blob)
is
begin
    insert into save_file(save_file_index, serialized_object)
    values(null, serializedObject);
    commit;
end;
/
