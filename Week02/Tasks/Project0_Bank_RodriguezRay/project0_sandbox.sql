--CREATE USER project0 IDENTIFIED BY password;
--GRANT DBA TO project0;
SELECT * FROM admin JOIN account ON admin.acc_id = account.acc_id;

SELECT account.ACC_ID, account.ACC_TYPE, account.FIRST_NAME, account.LAST_NAME, account.USER_NAME,
account.USER_PASSWORD, customer.BALANCE, customer.APPROVED, customer.BANNED 
FROM customer JOIN account ON customer.acc_id = account.acc_id 
WHERE account.user_name='rrod' AND account.user_password='cards';

SELECT * FROM loan;
SELECT * FROM admin;
SELECT * FROM account;

SELECT ACC_ID, ACC_TYPE, FIRST_NAME, LAST_NAME, USER_NAME, USER_PASSWORD, BALANCE, APPROVED, BANNED 
FROM account a, customer c
WHERE a.user_name = 'rrod' AND a.user_password = 'cards' AND a.acc_id = c.acc_id;

TRUNCATE TABLE customer;

DROP TABLE customer;

DELETE FROM customer WHERE acc_id=120;
DELETE FROM account  WHERE acc_id=120;

commit;

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
    approved VARCHAR2(6),
    banned VARCHAR2(6),
    acc_id NUMBER(9),
    bank_id NUMBER(9),
    CONSTRAINT fk_customer_bank_id FOREIGN KEY (bank_id) REFERENCES bank(bank_id),
    CONSTRAINT fk_customer_acc_id FOREIGN KEY (acc_id) REFERENCES account(acc_id)
);

CREATE TABLE loan(
    loan_id NUMBER(9) PRIMARY KEY,
    interest_rate NUMBER(12),
    apr NUMBER(12),
    ori_fee NUMBER(12),
    loan_term NUMBER(12),
    loan_amount NUMBER(12),
    loan_approved NUMBER(1,0),
    acc_id NUMBER(9),
    
    CONSTRAINT fk_loan_acc_id FOREIGN KEY(acc_id) REFERENCES account(acc_id)
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

DROP SEQUENCE loan_seq;
CREATE SEQUENCE loan_seq
    START WITH 100
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER loan_seq_trigger
BEFORE INSERT ON loan --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.loan_id IS NULL THEN
        SELECT loan_seq.NEXTVAL INTO :new.loan_id FROM dual;
    END IF;
END;
/


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

CREATE OR REPLACE PROCEDURE insertAdmin(
	accType IN VARCHAR2,
	firstName IN VARCHAR2,
	lastName IN VARCHAR2,
	customerUserName IN VARCHAR2,
	customerPassword IN VARCHAR2)
IS
BEGIN 
	INSERT INTO account(acc_type, first_name, last_name, user_name, user_password)
	VALUES (accType, firstName, lastName, customerUserName, customerPassword);
	INSERT INTO admin(acc_id, bank_id)
	VALUES((SELECT acc_id FROM account WHERE first_name=firstName AND last_name=lastName),
    1);
	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertCustomer(
	accType IN VARCHAR2,
	firstName IN VARCHAR2,
	lastName IN VARCHAR2,
	adminUserName IN VARCHAR2,
	adminPassword IN VARCHAR2,
    adminApproved IN VARCHAR2,
    adminBanned IN VARCHAR2)
IS
BEGIN 
	INSERT INTO account(acc_type, first_name, last_name, user_name, user_password)
	VALUES (accType, firstName, lastName, customerUserName, customerPassword);
	INSERT INTO customer(acc_id, bank_id, balance, approved, banned)
	VALUES((SELECT acc_id FROM account WHERE first_name=firstName AND last_name=lastName),
    1, customerBalance, customerApproved, customerBanned);
	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertLoan(
	interestRate IN NUMBER,
	apr IN NUMBER,
	oriFee IN NUMBER,
	loanTerm IN NUMBER,
    loanAmount IN NUMBER,
    approved IN NUMBER,
    accId IN NUMBER)
IS
BEGIN 
	INSERT INTO loan(interest_rate, apr, ori_fee, loan_term, loan_amount, loan_approved, 
    acc_id)
	VALUES (interestRate, apr, oriFee, loanTerm, loanAmount, approved, accId);
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
