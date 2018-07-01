--Setup for Bank database

DROP TABLE login CASCADE CONSTRAINTS;
DROP TABLE person CASCADE CONSTRAINTS;
DROP TABLE balance CASCADE CONSTRAINTS;
DROP TABLE auth_lvl CASCADE CONSTRAINTS;

CREATE TABLE login (
    u_id number(6) PRIMARY KEY,
    username varchar2(100) NOT NULL,
    pass_word varchar2(100) NOT NULL
);

CREATE TABLE person (
    p_id number(6) PRIMARY KEY,
    FName varchar2(100) NOT NULL,
    LName varchar2(100) NOT NULL,
    CONSTRAINT fk_p_id FOREIGN KEY (p_id) REFERENCES login(u_id)
);

CREATE TABLE balance (
    b_id number(6) PRIMARY KEY,
    bal number(8,2) NOT NULL,
    CONSTRAINT fk_b_id FOREIGN KEY (b_id) REFERENCES login(u_id)
);

CREATE TABLE auth_lvl (
    a_id number(6) PRIMARY KEY,
    lvl number(2) NOT NULL,
    lvl_name varchar2(100) NOT NULL,
    CONSTRAINT fk_a_id FOREIGN KEY (a_id) REFERENCES login(u_id)
);

--Used for creating new users
DROP SEQUENCE userid_seq;
CREATE SEQUENCE userid_seq
    START WITH 100
    INCREMENT BY 1;
/

--Create a user id if one doesn't exist already
CREATE OR REPLACE TRIGGER userid_seq_trigger
BEFORE INSERT ON  user_id
FOR EACH ROW 
BEGIN 
    IF :new.u_id IS NULL THEN
        SELECT userid_seq.NEXTVAL INTO :new.u_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE insertNewUser(userName IN VARCHAR2,
                                            userPass IN VARCHAR2,
                                            firstN IN VARCHAR2,
                                            lastN IN VARCHAR2)
IS
BEGIN
    INSERT INTO login (username,pass_word)
    VALUES(userName,userPass);
    INSERT INTO person (p_id,FName,LName)
    VALUES((SELECT u_id FROM login WHERE username = 'userName'),firstN,lastN);
    commit;
END;
/

commit;