DROP SEQUENCE counter;
CREATE SEQUENCE counter
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0;
    --This sequence will be used to handle the id field for our tables
    --In order to actually utilize it, we will need to build something that
    --reacts to situations where an employee inserted. E.G. Triggers
/ --Use forward slash to separate transactions and other parts of the script.

CREATE OR REPLACE TRIGGER project0_trigger
BEFORE INSERT ON project0_client --NOTE: you can use BEFORE or AFTER followed by a CRUD
BEGIN --This keyword signifies a block for a transaction
        UPDATE project0_numaccounts set number_of_accounts = counter.NEXTVAL WHERE COUNTER=1;
END;
/

CREATE OR REPLACE PROCEDURE delete_table
IS
BEGIN
    execute immediate 'truncate table project0_account';
    execute immediate 'truncate table project0_client';
    execute immediate 'truncate table project0_contact';
    execute immediate 'truncate table project0_personal_info';
    COMMIT;
END delete_table;
/
call delete_table;
CREATE OR REPLACE FUNCTION getAccounts --if no parameters, then dont type parenthesis
RETURN NUMBER
IS
    accounts number(38);
BEGIN
    SELECT number_of_accounts INTO accounts FROM project0_numaccounts;
    RETURN accounts;
END;
/

CREATE OR REPLACE PROCEDURE insertIntoAccount(ssnum IN VARCHAR2, 
                                            balance IN BINARY_FLOAT,
                                            pass IN VARCHAR2)
IS
BEGIN
    INSERT INTO project0_account (SS_NUM, BALANC, PASS)
    VALUES(ssnum, balance, pass);
    COMMIT;
END;
/

