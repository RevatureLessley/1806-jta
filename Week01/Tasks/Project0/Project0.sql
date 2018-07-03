DROP TABLE account CASCADE CONSTRAINTS;
DROP TABLE users CASCADE CONSTRAINTS;
DROP TABLE unapp_user CASCADE CONSTRAINTS;
DROP TABLE user_2_account CASCADE CONSTRAINTS;

CREATE TABLE account(
                    account_id NUMBER(6),
                    balance NUMBER(6) NOT NULL,
                    CONSTRAINT pk_account_id PRIMARY KEY (account_id)
);
/
CREATE TABLE users(
                    user_id NUMBER(6),
                    username VARCHAR2(100) NOT NULL,
                    password VARCHAR2(100) NOT NULL,
                    account_id NUMBER(6),
                    is_approved VARCHAR2(100),
                    is_admin VARCHAR(100),
                    CONSTRAINT pk_user_id PRIMARY KEY (user_id),
                    CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES account (account_id)
);
/


CREATE TABLE unapp_user(
                        unapp_user_id NUMBER(6),
                        unapp_username VARCHAR2(100) NOT NULL, 
                        CONSTRAINT pk_unappuser_id PRIMARY KEY (unapp_user_id)
);
/

CREATE TABLE user_2_account(
                            user_id NUMBER(6),
                            account_id NUMBER(6),
                            CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id),
                            CONSTRAINT fk_accound_id FOREIGN KEY (account_id) REFERENCES account (account_id)
);
/

--Sequence for new users
DROP SEQUENCE user_seq;
CREATE SEQUENCE user_seq
    START WITH 100
    INCREMENT BY 1;
/
 --Sequence for unapproved users
DROP SEQUENCE unapp_user_seq;
CREATE SEQUENCE unapp_user_seq
    START WITH 100
    INCREMENT BY 1;
/

--Seqence for new accounts
DROP SEQUENCE account_seq;
CREATE SEQUENCE account_seq
    START WITH 100
    INCREMENT BY 1;
/

--Trigger for auto incrementing new users
CREATE OR REPLACE TRIGGER user_seq_trigger
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    IF :new.user_id IS NULL THEN
        SELECT user_seq.NEXTVAL INTO :new.user_id FROM dual;
    END IF;
END;
/

--Trigger for giving a new user the last created account, which is unassigned
CREATE OR REPLACE TRIGGER user_account_trigger
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    IF :new.account_id IS NULL THEN
        SELECT MAX(account_id) INTO :new.account_id FROM account;
    END IF;
END;
/

--Trigger for auto-incrementing unapproved user ids
CREATE OR REPLACE TRIGGER unapp_user_seq_trigger
BEFORE INSERT ON unapp_user
FOR EACH ROW
BEGIN
    IF :new.unapp_user_id IS NULL THEN
        SELECT unapp_user_seq.NEXTVAL INTO :new.unapp_user_id FROM dual;
    END IF;
END;
/

--Trigger for auto-incrementing account ids
CREATE OR REPLACE TRIGGER account_seq_trigger
BEFORE INSERT ON account
FOR EACH ROW
BEGIN
    IF :new.account_id IS NULL THEN
        SELECT account_seq.NEXTVAL INTO :new.account_id FROM dual;
    END IF;
END;
/

--Stored procedure for inserting new users
CREATE OR REPLACE PROCEDURE insertIntoUsers(username IN VARCHAR2,
                                            pass IN VARCHAR2,                                             
                                            isApproved IN VARCHAR2,
                                            isAdmin IN VARCHAR2)
IS
BEGIN
    INSERT INTO users (username, password, is_Approved, is_Admin)
    VALUES (username, 
            pass, 
            isApproved, 
            isAdmin);
    COMMIT;
END;
/

--Stored procedure for inserting new accounts
CREATE OR REPLACE PROCEDURE insertIntoAccount(balance IN NUMBER)
IS
BEGIN 
    INSERT INTO account (balance)
    VALUES (balance);
    COMMIT;
END;
/

--Stored procedure for inserting unapproved users
CREATE OR REPLACE PROCEDURE insertIntoUnAppUser(username IN VARCHAR2)
IS
BEGIN
    INSERT INTO unapp_user (unapp_username)
    VALUES (username);
    COMMIT;
END;
/

CREATE OR REPLACE FUNCTION get_max_account 
RETURN NUMBER
IS
    max_acc number(6);
BEGIN
    SELECT max(balance) INTO max_acc FROM account;
    RETURN max_acc;
END;
/

