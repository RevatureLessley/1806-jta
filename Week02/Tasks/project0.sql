DROP TABLE person CASCADE CONSTRAINTS;
DROP TABLE account_type CASCADE CONSTRAINTS;
DROP TABLE approved CASCADE CONSTRAINTS;
DROP TABLE bank_account CASCADE CONSTRAINTS;
DROP TABLE bank_transaction CASCADE CONSTRAINTS;

CREATE TABLE person
(
    person_id NUMBER(6),
    fname VARCHAR2(100),
    lname VARCHAR2(100),
    CONSTRAINT pk_person_id PRIMARY KEY (person_id)
);

CREATE TABLE account_type
(
    type_id NUMBER(6),
    type_name VARCHAR2(100),
    CONSTRAINT pk_type_id PRIMARY KEY (type_id)
);

CREATE TABLE approved
(
    approved_id NUMBER(1),
    approved_name VARCHAR2(100),
    CONSTRAINT pk_approved_id PRIMARY KEY (approved_id)
);

CREATE TABLE bank_account
(
    account_id NUMBER(6),
    account_username VARCHAR2(100),
    account_password VARCHAR2(100),
    currency NUMBER(6),
    type_id NUMBER(6),
    --person_id NUMBER(6),
    approved_id NUMBER(1),
    CONSTRAINT pk_account_id PRIMARY KEY (account_id),
    CONSTRAINT fk_type_id FOREIGN KEY (type_id) REFERENCES account_type (type_id),
    --CONSTRAINT fk_person_id FOREIGN KEY (person_id) REFERENCES person (person_id),
    CONSTRAINT fk_approved_id FOREIGN KEY (approved_id) REFERENCES approved (approved_id)
);

CREATE TABLE bank_transaction
(
    transaction_id NUMBER(6),
    deposit_amount NUMBER(6),
    withdraw_amount NUMBER(6),
    account_id NUMBER(6),
    CONSTRAINT pk_transaction_id PRIMARY KEY (transaction_id),
    CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES bank_account (account_id)
);

INSERT INTO person
VALUES(1, 'Logan', 'Brewer');
SELECT * FROM person;

INSERT INTO account_type
VALUES(0, 'normal_user');
INSERT INTO account_type
VALUES(1, 'admin');
SELECT * FROM account_type;

INSERT INTO approved
VALUES(0, 'false');
INSERT INTO approved
VALUES(1, 'true');
SELECT * FROM approved;
    
INSERT INTO bank_account
VALUES(1, 'logan', 'brewer', 100, 1, 1);
SELECT * FROM bank_account;

--INSERT INTO bank_transaction
--VALUES(1, 10, 0, 1);
--SELECT * FROM bank_transaction;

CREATE OR REPLACE PROCEDURE insert_into_bank_account(
                                          accountId IN NUMBER, 
                                          accountUsername IN VARCHAR2,
                                          accountPassword in VARCHAR2,
                                          currency IN NUMBER,
                                          typeId IN NUMBER,
                                          approvedId IN NUMBER)
IS
BEGIN
    INSERT INTO bank_account (account_id, account_username, 
                              account_password, currency,
                              type_id, approved_id)
    VALUES (accountId, accountUsername,
            accountPassword, currency,
            typeId, approvedId);
    COMMIT;
END;

SELECT * FROM bank_account;
--SELECT * FROM person;
--INSERT INTO person VALUES(2, 'testfname', 'testlname');
CALL insert_into_bank_account(2, 'testname', 'testpass', 999, 0, 1);

CREATE OR REPLACE PROCEDURE update_currency(accountId IN bank_account.account_id%TYPE,
                                            newCurrency IN bank_account.currency%TYPE)
IS
BEGIN
    UPDATE bank_account SET currency = newCurrency WHERE account_id = accountId;
    COMMIT;
END;

CALL update_currency(2, 50);
SELECT * FROM bank_account;

CREATE OR REPLACE PROCEDURE update_approved(accountId IN bank_account.account_id%TYPE,
                                            approvedId IN bank_account.approved_id%TYPE)
IS
BEGIN
    UPDATE bank_account SET approved_id = approvedId WHERE account_id = accountId;
    COMMIT;
END;

CALL update_approved(2, 1);
SELECT * FROM bank_account;
commit;

UPDATE bank_account SET currency = 50 WHERE account_id = 3;

CREATE OR REPLACE PROCEDURE insert_into_bank_transaction(
                                          transactionId IN NUMBER,
                                          depositAmount IN NUMBER,
                                          withdrawAmount IN NUMBER,
                                          accountId IN NUMBER)
IS
BEGIN
    INSERT INTO bank_transaction (transaction_id, deposit_amount,
                                  withdraw_amount, account_id)
    VALUES (transactionId, depositAmount, withdrawAmount, accountId);
    COMMIT;
END;

SELECT COUNT(*) FROM bank_transaction;