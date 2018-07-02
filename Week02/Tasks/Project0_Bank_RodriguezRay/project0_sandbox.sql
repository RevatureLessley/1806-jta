CREATE TABLE bank (
    bank_id NUMBER(9) PRIMARY KEY,
    bank_name VARCHAR2(32),
    bank_phone VARCHAR2(12)
);

INSERT INTO bank (bank_id, bank_name, bank_phone)
VALUES(1, 'Chase', '555-555-5555');

CREATE TABLE account (
    acc_id NUMBER(9) PRIMARY KEY, 
    acc_type VARCHAR2(12) NOT NULL, 
    first_name VARCHAR2(32) NOT NULL, 
    last_name VARCHAR2(32) NOT NULL, 
    user_name VARCHAR2(32) NOT NULL, 
    user_password VARCHAR2(32) NOT NULL
);

CREATE TABLE customer(
    customer_id NUMBER(9) PRIMARY KEY,
    balance NUMBER(12),
    acc_id NUMBER(9),
    bank_id NUMBER(9),
    CONSTRAINT fk_customer_bank_id FOREIGN KEY (bank_id) REFERENCES bank(bank_id),
    CONSTRAINT fk_customer_acc_id FOREIGN KEY (acc_id) REFERENCES account(acc_id)
);

CREATE TABLE admin(
    admin_id NUMBER(9) PRIMARY KEY,
    acc_id NUMBER(9),
    bank_id NUMBER(9),
    CONSTRAINT fk_admin_bank_id FOREIGN KEY (bank_id) REFERENCES bank(bank_id),
    CONSTRAINT fk_admin_acc_id FOREIGN KEY (acc_id) REFERENCES account(acc_id)
);

CREATE TABLE banker(
    banker_id NUMBER(9) PRIMARY KEY,
    acc_id NUMBER(9),
    bank_id NUMBER(9),
    CONSTRAINT fk_banker_bank_id FOREIGN KEY (bank_id) REFERENCES bank(bank_id),
    CONSTRAINT fk_banker_acc_id FOREIGN KEY (acc_id) REFERENCES account(acc_id)
);

DROP SEQUENCE account_seq;
CREATE SEQUENCE account_seq
    START WITH 100
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER account_seq_trigger
BEFORE INSERT ON account --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.acc_id IS NULL THEN
        SELECT account_seq.NEXTVAL INTO :new.acc_id FROM dual;
    END IF;
END;
/

DROP SEQUENCE customer_seq;
CREATE SEQUENCE customer_seq
    START WITH 100
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER customer_seq_trigger
BEFORE INSERT ON customer --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.customer_id IS NULL THEN
        SELECT customer_seq.NEXTVAL INTO :new.customer_id FROM dual;
    END IF;
END;
/

DROP SEQUENCE admin_seq;
CREATE SEQUENCE admin_seq
    START WITH 100
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER admin_seq_trigger
BEFORE INSERT ON admin --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.admin_id IS NULL THEN
        SELECT admin_seq.NEXTVAL INTO :new.admin_id FROM dual;
    END IF;
END;
/

DROP SEQUENCE banker_seq;
CREATE SEQUENCE banker_seq
    START WITH 100
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER banker_seq_trigger
BEFORE INSERT ON banker --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.banker_id IS NULL THEN
        SELECT banker_seq.NEXTVAL INTO :new.banker_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE insertUser(
	accId IN NUMBER,
	accType IN VARCHAR2,
	firstName IN VARCHAR2,
	lastName IN VARCHAR2,
	customerName IN VARCHAR2,
	customerPassword IN VARCHAR2)
IS
BEGIN 
	INSERT INTO account(acc_id, acc_type, first_name, last_name, user_name, user_password)
	VALUES (accId, accType, firstName, lastName, customerName, customerPassword);
	INSERT INTO customer(acc_id, bank_id)
	VALUES(accId, 1);
	COMMIT;
END;
/

CREATE OR REPLACE FUNCTION get_customer_count
RETURN NUMBER
IS
    customer_count NUMBER(9);
BEGIN 
    customer_count := get_customer_count();
    DBMS_OUTPUT.PUT_LINE(customer_count);
END;
/

COMMIT;
