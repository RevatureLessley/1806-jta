--RYAN's DATABASE BUILDER SCRIPT
DROP TABLE users CASCADE CONSTRAINTS;
DROP TABLE item CASCADE CONSTRAINTS;
DROP TABLE shop CASCADE CONSTRAINTS;
DROP TABLE npc CASCADE CONSTRAINTS;
DROP TABLE shop_2_item CASCADE CONSTRAINTS;
DROP TABLE job_class CASCADE CONSTRAINTS;

CREATE TABLE job_class (
    job_id number(6) PRIMARY KEY,
    job_name varchar2(100) NOT NULL
);

CREATE TABLE npc (
    npc_id number(6) primary key,
    npc_name varchar(100),
    npc_lvl number(3) DEFAULT 3,
    currency number(6) NOT NULL,
    job_id number(6) NOT NULL CHECK (job_id > 0),
    CONSTRAINT fk_job_id FOREIGN KEY (job_id) REFERENCES job_class (job_id)
    ,CONSTRAINT unique_name UNIQUE (npc_name)
);

CREATE TABLE shop (
    shop_id NUMBER(6),
    shop_name VARCHAR2(100) NOT NULL,
    owner_id number(6) NOT NULL,
    CONSTRAINT pk_shop_id PRIMARY KEY (shop_id), --adds shop_id as a primary key
    CONSTRAINT fk_owner_id FOREIGN KEY (owner_id) 
        REFERENCES npc (npc_id)
);

CREATE TABLE item (
    item_id number(6) primary key,
    item_name varchar(100) NOT NULL,
    item_price number(6) NOT NULL
);

CREATE TABLE shop_2_item(
    shop_id number(6),
    item_id number(6),
    CONSTRAINT fk_shop_id FOREIGN KEY (shop_id) REFERENCES shop (shop_id),
    CONSTRAINT fk_item_id FOREIGN KEY (item_id) REFERENCES item (item_id)
);

CREATE TABLE users(
    npc_id number(6) primary key,
    username varchar2(100) unique,
    password varchar2(100),
    constraint fk_npc_id foreign key (npc_id) REFERENCES npc(npc_id)
);


--DML NEW
INSERT INTO job_class VALUES(1, 'Shopkeep');
INSERT INTO job_class VALUES(2, 'Assassin');
INSERT INTO job_class VALUES(3, 'Professional Bob');

INSERT INTO npc VALUES(1, 'Bobbert', 99, 12, 3);
INSERT INTO npc VALUES(2, 'Salara', 72, 1000000 - 1, 2);
INSERT INTO npc VALUES(3, 'Zachary', 50, 6000, 3);
INSERT INTO npc VALUES(4, 'Ryan', 98, 2, 1);
INSERT INTO npc VALUES(5, 'Little Tommy Pickles', 1, 75000, 2);
INSERT INTO npc VALUES(6, 'Big Bobby Gherkin', 33, 4500, 1);
INSERT INTO npc VALUES(7, 'Medium Larry Vlasic', 64, 692, 1);
INSERT INTO npc VALUES(8, 'Variable Victor Salami', 13, 133000, 1);

INSERT INTO shop VALUES (1, 'Bobbert''s Wares', 1);
INSERT INTO shop VALUES (2, 'The Birdcage', 2);
INSERT INTO shop VALUES (3, 'Quiet Library', 3);

INSERT INTO ITEM VALUES (1, 'Water', 3);
INSERT INTO ITEM VALUES (2, 'Gauntlets Of Infinity', 50);
INSERT INTO ITEM VALUES (3, 'XBOX 360', 10);
INSERT INTO ITEM VALUES (4, 'Premium Water', 500);
INSERT INTO ITEM VALUES (5, 'Revature Training', 19400);

INSERT INTO shop_2_item VALUES (1,1);
INSERT INTO shop_2_item VALUES (1,4);
INSERT INTO shop_2_item VALUES (2,2);
INSERT INTO shop_2_item VALUES (2,3);
INSERT INTO shop_2_item VALUES (3,5);
INSERT INTO shop_2_item VALUES (3,1);

--PL SQL
DROP SEQUENCE npc_seq;
CREATE SEQUENCE npc_seq
    START WITH 100
    INCREMENT BY 1;
    --This sequence will be used to handle the id field for our tables
    --In order to actually utilize it, we will need to build something that
    --reacts to situations where an employee inserted. E.G. Triggers
/ --Use forward slash to separate transactions and other parts of the script.

CREATE OR REPLACE TRIGGER npc_seq_trigger
BEFORE INSERT ON npc --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.npc_id IS NULL THEN
        SELECT npc_seq.NEXTVAL INTO :new.npc_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE hello_world_procedure
IS
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello World!'); --SQL DEVELOPER equivalent to syso
END;
/

CREATE OR REPLACE PROCEDURE insertIntoNpc(npcName IN VARCHAR2, 
                                            npcLvl IN NUMBER,
                                            npcCurrency IN NUMBER,
                                            npcJobId IN NUMBER)
IS
BEGIN
    INSERT INTO npc (npc_name, npc_lvl, currency, job_id)
    VALUES(npcName, npcLvl, npcCurrency, npcJobId);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertIntoUser(npcId IN NUMBER, 
                                            username IN VARCHAR2,
                                            userpass IN VARCHAR2)
IS
BEGIN
    INSERT INTO users 
    VALUES(npcId, username, userpass);
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE get_class(npcId IN NUMBER, className OUT VARCHAR2)
IS
BEGIN
    SELECT b.JOB_NAME INTO className FROM npc a
    INNER JOIN JOB_CLASS b
    ON a.job_id = b.job_id
    WHERE a.npc_id = npcId;
END;
/

CREATE OR REPLACE PROCEDURE get_currency(npcID IN OUT number)
IS
BEGIN
    SELECT currency INTO npcID FROM npc WHERE npc_id = npcID;
END;
/

CREATE OR REPLACE PROCEDURE get_all_npc(cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR --Cursor is like a stream, you OPEN if FOR some QUERY
    SELECT * FROM npc;
END;
/

commit;