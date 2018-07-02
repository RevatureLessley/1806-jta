DROP TABLE Transactions CASCADE CONSTRAINTS;
DROP TABLE Accounts CASCADE CONSTRAINTS;
DROP TABLE UserInfo CASCADE CONSTRAINTS;
DROP TABLE Users CASCADE CONSTRAINTS;

CREATE TABLE Users (
    user_id NUMBER(10) PRIMARY KEY,
    username VARCHAR2(20) NOT NULL
);

CREATE TABLE UserInfo (
    user_id NUMBER(10) PRIMARY KEY,
    password VARCHAR2(64), -- SHA-256 64 Characters
    admin CHAR(1),
    pending CHAR(1),
    locked CHAR(1),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

CREATE TABLE Accounts (
    account_id NUMBER(10) PRIMARY KEY,
    user_id NUMBER(10),
    CONSTRAINT fk_account_user_id FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

CREATE TABLE Transactions (
    transaction_id Number(10) PRIMARY KEY,
    account_id NUMBER(10),
    amount NUMBER(10),
    CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES Accounts (account_id)
);

-- SEQUENCE
DROP SEQUENCE user_seq;
CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1;
DROP SEQUENCE account_seq;
CREATE SEQUENCE account_seq START WITH 1 INCREMENT BY 1;
DROP SEQUENCE transaction_seq;
CREATE SEQUENCE transaction_seq START WITH 1 INCREMENT BY 1;

-- TRIGGER
CREATE OR REPLACE TRIGGER user_seq_trigger
    BEFORE INSERT ON Users
    FOR EACH ROW
    BEGIN
        IF :new.user_id IS NULL THEN
            SELECT user_seq.NEXTVAL INTO :new.user_id FROM dual;
        END IF;
    END;
/

CREATE OR REPLACE TRIGGER account_seq_trigger
    BEFORE INSERT ON Accounts
    FOR EACH ROW
    BEGIN
        IF :new.account_id IS NULL THEN
            SELECT account_seq.NEXTVAL INTO :new.account_id FROM dual;
        END IF;
    END;
/

CREATE OR REPLACE TRIGGER transaction_seq_trigger
    BEFORE INSERT ON Transactions
    FOR EACH ROW
    BEGIN
        IF :new.transaction_id IS NULL THEN
            SELECT transaction_seq.NEXTVAL INTO :new.transaction_id FROM dual;
        END IF;
    END;
/

-- PROCEDURE
CREATE OR REPLACE PROCEDURE create_account(userId in NUMBER)
    IS
    BEGIN
        INSERT INTO Accounts(user_id) VALUES (userId);
        COMMIT;
    END;
/

-- FUNCTION
CREATE OR REPLACE FUNCTION get_balance(accountId in NUMBER)
    RETURN NUMBER
    IS
        balance NUMBER(10);
    BEGIN
        SELECT SUM(amount) INTO balance FROM Transactions WHERE account_id = accountId;
        RETURN balance;
    END;
/

COMMIT;