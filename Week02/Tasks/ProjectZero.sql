

DROP TABLE account_table CASCADE CONSTRAINTS;
DROP TABLE account_type CASCADE CONSTRAINTS;
DROP TABLE user_table CASCADE CONSTRAINTS;
DROP TABLE user_type CASCADE CONSTRAINTS;
DROP TABLE loan_request CASCADE CONSTRAINTS;
DROP TABLE interest_table CASCADE CONSTRAINTS;

CREATE TABLE account_table(
    acct_id NUMBER(10) PRIMARY KEY,
    acct_type NUMBER(3) NOT NULL,
    acct_balance DECIMAL(12, 4) NOT NULL,
    acct_validated NUMBER(1) DEFAULT 0 NOT NULL,
    user_id NUMBER(10) NOT NULL    
);

CREATE TABLE account_type(
    atype_id NUMBER(3) PRIMARY KEY,
    atype_name VARCHAR2(20) NOT NULL,
    atype_interest_rate DECIMAL(6,4)
);

CREATE TABLE user_table(
    u_id NUMBER(10) PRIMARY KEY,
    u_type NUMBER(3) NOT NULL,
    u_name VARCHAR(20) NOT NULL,
    u_password VARCHAR(100) NOT NULL
);

CREATE TABLE user_type(
    utype_id NUMBER(3) PRIMARY KEY,
    utype_name VARCHAR(20) NOT NULL
);

CREATE TABLE loan_request(
    loan_id NUMBER(10) PRIMARY KEY,
    loan_amount DECIMAL(10),
    loan_acct NUMBER(10),
    loan_target NUMBER(10)    
);

CREATE TABLE interest_table(
    it_id NUMBER(10) PRIMARY KEY,
    it_time TIMESTAMP
);
---------------------------------------------------------------------------------

ALTER TABLE account_table ADD CONSTRAINT fk_acct_type 
    FOREIGN KEY (acct_type) REFERENCES account_type (atype_id);
    
ALTER TABLE account_table ADD CONSTRAINT fk_user_id 
    FOREIGN KEY (user_id) REFERENCES user_table (u_id);
    
ALTER TABLE user_table ADD CONSTRAINT fk_u_type 
    FOREIGN KEY (u_type) REFERENCES user_table (u_id);

ALTER TABLE loan_request ADD CONSTRAINT fk_loan_target 
    FOREIGN KEY (loan_target) REFERENCES account_table (acct_id);


---------------------------------------------------------------------------------
DROP SEQUENCE timestamp_seq;
CREATE SEQUENCE timestamp_seq
    START WITH 1
    INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER timestamp_seq_trigger
BEFORE INSERT ON interest_table
FOR EACH ROW --
BEGIN
    IF :NEW.it_id IS NULL THEN
        SELECT timestamp_seq.NEXTVAL INTO :NEW.it_id FROM dual;
    END IF;
END;
/
DROP SEQUENCE loanreq_seq;
CREATE SEQUENCE loanreq_seq
    START WITH 1
    INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER loanreq_trigger
BEFORE INSERT ON loan_request
FOR EACH ROW --
BEGIN
    IF :NEW.loan_id IS NULL THEN
        SELECT loanreq_seq.NEXTVAL INTO :NEW.loan_id FROM dual;
    END IF;
END;
/
---------------------------------------------------------------------------------


CREATE OR REPLACE FUNCTION get_rate(acctType IN NUMBER) --if no parameters, then dont type parenthesis
RETURN NUMBER
IS
    acctRate DECIMAL(6,4);
BEGIN
    SELECT atype_interest_rate INTO acctRate 
    FROM account_type 
    WHERE atype_id = acctType;

    RETURN acctRate*100;
END;

/
---------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE applyInterest(periods IN NUMBER)
IS
BEGIN

    UPDATE account_table
    SET ACCT_BALANCE = ACCT_BALANCE * POWER(1+get_rate(ACCT_TYPE)/100/365, periods);
    
    COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE requestLoan(userID IN NUMBER, amt IN NUMBER, tar IN NUMBER)
IS
    reqID NUMBER;
    acct NUMBER;
    userType NUMBER;
BEGIN
    SELECT u_type INTO userType FROM user_table WHERE u_id = userID;
    SELECT Max(acct_id)+1 INTO acct FROM account_table;
    INSERT INTO loan_request (loan_amount, loan_acct, loan_target) VALUES (amt, acct, tar);
    INSERT INTO account_table VALUES (acct, 3, -amt, 0, userID);
    
    IF userType = 1 THEN
        validateLoan(acct);
    END IF;
    
    COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE validateLoan(accountId IN NUMBER)
IS
    loanID NUMBER;
    amt NUMBER;
    tar NUMBER;
BEGIN

    SELECT loan_id, loan_amount, loan_target INTO loanID, amt, tar 
    FROM loan_request WHERE loan_acct = accountId;
    
    UPDATE account_table SET acct_balance = acct_balance + amt WHERE acct_id = tar;
    UPDATE account_table SET acct_validated = 1 WHERE acct_id = accountId;
    
    DELETE FROM loan_request WHERE loan_id = loanID;

    COMMIT;
END;
/
---------------------------------------------------------------------------------
INSERT INTO user_type VALUES (1, 'Admin');
INSERT INTO user_type VALUES (2, 'User');

INSERT INTO account_type VALUES (1, 'Checking', 0.025);
INSERT INTO account_type VALUES (2, 'Savings', 0.05);
INSERT INTO account_type VALUES (3, 'Loan', 0.1);

INSERT INTO user_table VALUES (1, 1, 'admin', 'pass123');
INSERT INTO user_table VALUES (2, 2, 'ausmo', '111111');
INSERT INTO user_table VALUES (3, 2, 'dyjo', 'password');

INSERT INTO account_table VALUES (100650, 1, 200, 1, 2);
INSERT INTO account_table VALUES (100651, 2, 400, 1, 2);


--SELECT * FROM user_table;
--SELECT * FROM account_table;
--SELECT * FROM account_type;
--SELECT acct_id, atype_name FROM account_table LEFT JOIN account_type ON acct_type = atype_id WHERE user_id = 3;
--
SELECT * FROM interest_table;
--
--SELECT * FROM interest_table WHERE it_id IN (SELECT MAX(it_id) FROM interest_table);


COMMIT;