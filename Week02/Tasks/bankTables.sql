--Setup for Bank database

DROP TABLE login CASCADE CONSTRAINTS;
DROP TABLE person CASCADE CONSTRAINTS;
DROP TABLE balance CASCADE CONSTRAINTS;
DROP TABLE auth CASCADE CONSTRAINTS;

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

CREATE TABLE auth (
    a_id number(6) PRIMARY KEY,
    is_auth number(2) NOT NULL,
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
BEFORE INSERT ON login
FOR EACH ROW 
BEGIN 
    IF :new.u_id IS NULL THEN
        SELECT userid_seq.NEXTVAL INTO :new.u_id FROM dual;
    END IF;
END;
/

--Stored procedure
CREATE OR REPLACE PROCEDURE insertNewUser(userName IN VARCHAR2,
                                            userPass IN VARCHAR2,
                                            firstN IN VARCHAR2,
                                            lastN IN VARCHAR2)
IS
    userid NUMBER;
BEGIN
    INSERT INTO login (username,pass_word)
    VALUES(userName,userPass);
    userid := get_user_id(userName);
    INSERT INTO person (p_id,FName,LName)
    VALUES(userid,firstN,lastN);
    INSERT INTO balance (b_id,bal)
    VALUES(userid,0);
    INSERT INTO auth (a_id,is_auth)
    VALUES(userid,0);
    commit;
END;
/

--User defined function to get user id based on username
CREATE OR REPLACE FUNCTION get_user_id (user_name IN VARCHAR2)
RETURN NUMBER
IS
    uname NUMBER;
    
    cursor c1 is SELECT u_id FROM login WHERE username = user_name;
BEGIN
    open c1;
    fetch c1 into uname;

    RETURN uname;
END;
/


commit;