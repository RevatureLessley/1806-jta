DROP TABLE Account_Dynamic 
CASCADE CONSTRAINTS;
DROP TABLE Account_Static 
CASCADE CONSTRAINTS;
DROP TABLE Person 
CASCADE CONSTRAINTS;
DROP TABLE Status 
CASCADE CONSTRAINTS;

CREATE TABLE Account_Static (
    acc_sta_id NUMBER PRIMARY KEY,
    acc_sta_username VARCHAR2(4000),
    acc_sta_password VARCHAR2(4000)
);

CREATE TABLE Account_Dynamic (
    acc_dyn_id NUMBER PRIMARY KEY,
    acc_dyn_balance NUMBER
);

CREATE TABLE Person (
    per_id NUMBER PRIMARY KEY,
    firstname VARCHAR2(4000),
    lastname VARCHAR2(4000)
);

CREATE TABLE Status (
    sta_id NUMBER PRIMARY KEY,
    code VARCHAR2(10) 
    CHECK (code IN ('APPROVED','DENIED','PENDING'))
);

ALTER TABLE Account_Static
ADD CONSTRAINT fk_acc_per 
FOREIGN KEY (acc_sta_id) 
REFERENCES Person(per_id);

ALTER TABLE Account_Static
ADD CONSTRAINT fk_acc_sta 
FOREIGN KEY (acc_sta_id) 
REFERENCES Status(sta_id);

ALTER TABLE Account_Static
ADD CONSTRAINT fk_sta_dyn 
FOREIGN KEY (acc_sta_id) 
REFERENCES Account_Dynamic(acc_dyn_id);

ALTER TABLE Account_Dynamic    
ADD CONSTRAINT fk_dyn_sta 
FOREIGN KEY (acc_dyn_id) 
REFERENCES Account_Static(acc_sta_id);