DROP TABLE Account_Dynamic 
CASCADE CONSTRAINTS;
DROP TABLE Account_Static 
CASCADE CONSTRAINTS;
DROP TABLE Person 
CASCADE CONSTRAINTS;
DROP TABLE Status 
CASCADE CONSTRAINTS;
DROP SEQUENCE acc_dyn_seq;
DROP SEQUENCE acc_sta_seq;
DROP SEQUENCE per_seq;
DROP SEQUENCE sta_seq;

CREATE TABLE Account_Dynamic (
    acc_dyn_id NUMBER PRIMARY KEY,
    acc_dyn_balance NUMBER 
    CHECK (acc_dyn_balance >= 0)
);

CREATE TABLE Account_Static (
    acc_sta_id NUMBER PRIMARY KEY,
    acc_per NUMBER NOT NULL,
    acc_sta NUMBER NOT NULL,
    acc_sta_username VARCHAR2(4000) 
    NOT NULL UNIQUE,
    acc_sta_password VARCHAR2(4000) NOT NULL
);

CREATE TABLE Person (
    per_id NUMBER PRIMARY KEY,
    firstname VARCHAR2(4000) NOT NULL,
    lastname VARCHAR2(4000) NOT NULL
);

CREATE TABLE Status (
    sta_id NUMBER PRIMARY KEY,
    code VARCHAR2(10) NOT NULL
    CHECK (code IN ('APPROVED','DENIED','PENDING')) 
);

CREATE OR REPLACE VIEW Account_Join AS
SELECT * 
FROM Account_Dynamic, Account_Static
INNER JOIN Person ON acc_per = per_id
INNER JOIN Status ON acc_sta = sta_id;

ALTER TABLE Account_Dynamic    
ADD CONSTRAINT fk_dyn_sta 
FOREIGN KEY (acc_dyn_id) 
REFERENCES Account_Static(acc_sta_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Account_Static
ADD CONSTRAINT fk_acc_per 
FOREIGN KEY (acc_per) 
REFERENCES Person(per_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Account_Static
ADD CONSTRAINT fk_acc_sta 
FOREIGN KEY (acc_sta) 
REFERENCES Status(sta_id);

ALTER TABLE Account_Static
ADD CONSTRAINT fk_sta_dyn 
FOREIGN KEY (acc_sta_id) 
REFERENCES Account_Dynamic(acc_dyn_id);

CREATE SEQUENCE acc_sta_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER acc_sta_tri
BEFORE INSERT ON Account_Static
FOR EACH ROW
BEGIN
    IF :new.acc_sta_id IS NULL THEN
        SELECT acc_sta_seq.NEXTVAL 
        INTO :new.acc_sta_id FROM dual;
    END IF;
END;
/

CREATE SEQUENCE acc_dyn_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER acc_dyn_tri
BEFORE INSERT ON Account_Dynamic
FOR EACH ROW
BEGIN
    IF :new.acc_dyn_id IS NULL THEN
        SELECT acc_dyn_seq.NEXTVAL 
        INTO :new.acc_dyn_id FROM dual;
    END IF;
END;
/

CREATE SEQUENCE per_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER per_tri
BEFORE INSERT ON Person
FOR EACH ROW
BEGIN
    IF :new.per_id IS NULL THEN
        SELECT per_seq.NEXTVAL 
        INTO :new.per_id FROM dual;
    END IF;
END;
/

CREATE SEQUENCE sta_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER sta_tri
BEFORE INSERT ON Status
FOR EACH ROW
BEGIN
    IF :new.sta_id IS NULL THEN
        SELECT sta_seq.NEXTVAL 
        INTO :new.sta_id FROM dual;
    END IF;
END;
/

INSERT INTO Status (code)
VALUES ('APPROVED');
INSERT INTO Status (code)
VALUES ('DENIED');
INSERT INTO Status (code)
VALUES ('PENDING');

CREATE OR REPLACE FUNCTION getPersonID(fir IN VARCHAR2, las in VARCHAR2)
RETURN NUMBER
IS
    pID NUMBER;
    
    CURSOR cur IS
    SELECT per_id
    FROM Person
    WHERE firstname = fir AND lastname = las;
BEGIN
    OPEN cur;
    FETCH cur INTO pID;
    
    IF cur%NOTFOUND THEN
      pID := 0;
    END IF;
    
    CLOSE cur;
    RETURN pID;
END;
/

CREATE OR REPLACE FUNCTION getStatusCode(sta IN VARCHAR2)
RETURN NUMBER
IS
    co NUMBER;
    
    CURSOR cur IS
    SELECT sta_id
    FROM Status
    WHERE code = sta;
BEGIN
    OPEN cur;
    FETCH cur INTO co;
    
    IF cur%NOTFOUND THEN
      co := 0;
    END IF;
    
    CLOSE cur;
    RETURN co;
END;
/

CREATE OR REPLACE PROCEDURE insertAccountDynamic(balance IN NUMBER)
IS
BEGIN
    INSERT INTO Account_Dynamic(acc_dyn_balance)
    VALUES(balance);
END;
/

CREATE OR REPLACE PROCEDURE insertAccountStatic(username IN VARCHAR2, 
                                                pas IN VARCHAR2,
                                                co IN NUMBER, 
                                                pID IN NUMBER)
IS
BEGIN
    INSERT INTO Account_Static(acc_per, acc_sta, 
                               acc_sta_username, 
                               acc_sta_password)
    VALUES(pID, co, username, pas);
END;
/

CREATE OR REPLACE PROCEDURE insertPerson(fir IN VARCHAR2, las IN VARCHAR2)
IS
BEGIN
    INSERT INTO Person (firstname, lastname)
    VALUES(fir, las);
END;
/

CREATE OR REPLACE PROCEDURE insertAccount(username IN VARCHAR2,
                                          pas IN VARCHAR2, 
                                          fir IN VARCHAR2, 
                                          las IN VARCHAR2, 
                                          balance IN NUMBER, 
                                          sta IN VARCHAR2)
IS
    co NUMBER;
    pID NUMBER;
BEGIN
    insertAccountDynamic(balance);
    pID := getPersonID(fir, las);
    
    IF pID = 0 THEN
        insertPerson(fir, las);
        pID := getPersonID(fir, las);
    END IF;
    
    co := getStatusCode(sta);
    insertAccountStatic(username, pas, co, pID);
    COMMIT;
END;
/

--call insertAccount('walterx', 'walterx', 'Walter', 'Xia', 0, 'APPROVED');

COMMIT;