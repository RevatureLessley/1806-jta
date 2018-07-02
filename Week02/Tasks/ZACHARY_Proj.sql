DROP SEQUENCE TRANSATIONS_seq;
CREATE SEQUENCE TRANSATIONS_seq
    START WITH 303
    INCREMENT BY 1; 
 / 
CREATE OR REPLACE TRIGGER TRANSATIONS_seq_Triger
BEFORE INSERT ON TRANSATIONS 
FOR EACH ROW
BEGIN 
    IF :new.TRAN_ID IS NULL THEN
        SELECT TRANSATIONS_seq.NEXTVAL INTO :new.TRAN_ID FROM dual;
    END IF;
END;
/
--------------------------------------------------------------------------------------
DROP SEQUENCE BankAccount_seq;
CREATE SEQUENCE BankAccount_seq
    START WITH 103
    INCREMENT BY 1; 
 / 
CREATE OR REPLACE TRIGGER BankAccount_seq_Triger
BEFORE INSERT ON BANK_ACCOUNT 
FOR EACH ROW
BEGIN 
    IF :new.ACCOUNT_ID IS NULL THEN
        SELECT BankAccount_seq.NEXTVAL INTO :new.ACCOUNT_ID FROM dual;
    END IF;
END;
/
--------------------------------------------------------------------------------------

DROP SEQUENCE CUSTOMER_seq;
CREATE SEQUENCE CUSTOMER_seq
    START WITH 103
    INCREMENT BY 1; 
/  
CREATE OR REPLACE TRIGGER CUSTOMER_seq_Triger
BEFORE INSERT ON CUSTOMER 
FOR EACH ROW
BEGIN 
    IF :new.CUSTOMER_ID IS NULL THEN
        SELECT CUSTOMER_seq.NEXTVAL INTO :new.CUSTOMER_ID FROM dual;
    END IF;
END;
/
-----------------------------------------------------------------------------

DROP SEQUENCE ADDRESS_seq;
CREATE SEQUENCE ADDRESS_seq
    START WITH 203
    INCREMENT BY 1;  
 / 
CREATE OR REPLACE TRIGGER ADDRESS_seq_Triger
BEFORE INSERT ON CUSTOMER_ADDRESS 
FOR EACH ROW
BEGIN 
    IF :new.ADRESS_ID IS NULL THEN
        SELECT ADDRESS_seq.NEXTVAL INTO :new.ADRESS_ID FROM dual;
    END IF;
END;
/
---------------------------------------------------------------

DROP SEQUENCE ACCOUNT_2_CUSTOMER_seq;
CREATE SEQUENCE ACCOUNT_2_CUSTOMER_seq
    START WITH 103
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER ACCOUNT_2_CUSTOMER_seq_Triger
BEFORE INSERT ON ACCOUNT_2_CUSTOMER 
FOR EACH ROW
BEGIN 
    IF :new.CUSTOMER_ID IS NULL THEN
        SELECT ACCOUNT_2_CUSTOMER_seq.NEXTVAL INTO :new.CUSTOMER_ID FROM dual;
    END IF;
END;
/
------------------------------------------------------------------------------------

DROP SEQUENCE CUSTOMER_2_ACCOUNT_seq;
CREATE SEQUENCE CUSTOMER_2_ACCOUNT_seq
    START WITH 103
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER CUSTOMER_2_seq_seq_Triger
BEFORE INSERT ON ACCOUNT_2_CUSTOMER 
FOR EACH ROW
BEGIN 
    IF :new.ACCOUNT_ID IS NULL THEN
        SELECT CUSTOMER_2_ACCOUNT_seq.NEXTVAL INTO :new.ACCOUNT_ID FROM dual;
    END IF;
END;
/
-------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE Transations_test(TRANID IN NUMBER, 
                                                        DEPOSITEDTEST IN NUMBER,
                                                        WITHDRAWTEST IN NUMBER)
IS
BEGIN
    INSERT INTO TRANSATIONS (TRAN_ID, DEPOSITED, WITHDRAW)
    VALUES(TRANID, DEPOSITEDTEST,  WITHDRAWTEST);
    COMMIT;
END;
/
--------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE INSERTINTOACCOUNT_2_CUSTOMER(CUSID IN NUMBER, 
                                                        ACCID IN NUMBER)
IS
BEGIN
    INSERT INTO ACCOUNT_2_CUSTOMER (CUSTOMER_ID, ACCOUNT_ID)
    VALUES(CUSID, ACCID );
    COMMIT;
END;
/
------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE insertIntoBank(branchId IN NUMBER, 
                                            accountType IN NUMBER,
                                            accountBalance IN NUMBER,
                                            tranId IN NUMBER)
IS
BEGIN
    INSERT INTO BANK_ACCOUNT (BRANCH_ID, ACCOUNT_TYPE_ID, ACCOUNT_BALANCE, TRAN_ID)
    VALUES(branchId, accountType, accountBalance, tranId);
    COMMIT;
END;
/
-----------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE INSERTIntoCUSTOMER( CustomerLName IN VARCHAR2, 
                                            CustomerFName IN VARCHAR2,
                                            Customerpassword IN VARCHAR2,
                                            AddressId IN NUMBER)
IS
BEGIN
    INSERT INTO CUSTOMER (CUSTOMER_LNAME, CUSTOMER_FNAME, CUSTOMER_PASSWORD,ADRESS_ID)
    VALUES(CustomerLName, CustomerFName, Customerpassword,AddressId);
    COMMIT;
END;
 /
---------------------------------------------------------------------------------------------
 
CREATE OR REPLACE PROCEDURE INSERTIntoADDRESS( StreetNumber IN NUMBER, 
                                            StreetName IN VARCHAR2)
IS
BEGIN
    INSERT INTO CUSTOMER_ADDRESS (STREET_NUMBER, STREET_NAME)
    VALUES(StreetNumber, StreetName);
    COMMIT;
END;
/ 
---------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION GET_MAX_ADD_ID --if no parameters, then dont type parenthesis
RETURN NUMBER
IS
    MAX_CUR NUMBER(6);
BEGIN
    SELECT max(ADRESS_ID) INTO MAX_CUR FROM CUSTOMER_ADDRESS;
    RETURN MAX_CUR;
END;
/

DECLARE 
    MAX_CUR NUMBER;
BEGIN
    MAX_CUR := GET_MAX_ADD_ID();
    DBMS_OUTPUT.PUT_LINE(MAX_CUR);
END;  
/
----------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION GET_MAX_ID_account --if no parameters, then dont type parenthesis
RETURN NUMBER
IS
    MAX_ACID NUMBER(6);
BEGIN
    SELECT max(ACCOUNT_ID) INTO MAX_ACID FROM ACCOUNT_2_CUSTOMER;
    RETURN MAX_ACID;
END;
/
DECLARE 
    MAX_ACID NUMBER;
BEGIN
    MAX_ACID := GET_MAX_ID_account();
    DBMS_OUTPUT.PUT_LINE(MAX_ACID);
END;  
/
------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION GET_MAX_ID_CUSTOMER
RETURN NUMBER
IS
    MAX_CUID NUMBER(6);
BEGIN
    SELECT max(CUSTOMER_ID) INTO MAX_CUID FROM ACCOUNT_2_CUSTOMER;
    RETURN MAX_CUID;
END;
/
DECLARE 
    MAX_CUID NUMBER;
BEGIN
    MAX_CUID := GET_MAX_ID_CUSTOMER();
    DBMS_OUTPUT.PUT_LINE(MAX_CUID);
END;  
/
------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION GET_MAX_Tran_ID  
RETURN NUMBER
IS
    MAX_TRANID NUMBER(6);
BEGIN
    SELECT max(TRAN_ID) INTO MAX_TRANID FROM TRANSATIONS;
    RETURN MAX_TRANID;
END;
/

--SELECT * FROM BANK_ACCOUNT;


-- 

----
------
--SELECT * FROM CUSTOMER_ADDRESS
--ORDER BY  ADRESS_ID;
----------
--SELECT * FROM ACCOUNT_2_CUSTOMER;
--------select * from accounts;
--------select * from BANK_BRANCH;
------------
--SELECT * FROM CUSTOMER;
--------
--SELECT * FROM BANK_ACCOUNT;
------
--SELECT * FROM TRANSATIONS;



