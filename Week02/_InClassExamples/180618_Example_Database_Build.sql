--This is a SQL Developer comment!
/*
    This is a SQL block comment
*/

/*
SUB LANGUAGES

DDL - Data definition Language
-CREATE
-ALTER
-DROP
-TRUNCATE
DML - Data Manipulation Language
-SELECT
-INSERT
-UPDATE
-DELETE
TCL - Transaction Contorl Language
-COMMIT
-ROLLBACK
-SAVEPOINT
-SET_TRANSACTIOn
DCL - Data Control Language
-GRANT
-REVOKE
DQL
-SELECT
*/

--DDL
DROP TABLE item CASCADE CONSTRAINTS;
DROP TABLE shop CASCADE CONSTRAINTS;
DROP TABLE npc CASCADE CONSTRAINTS;
DROP TABLE shop_2_item CASCADE CONSTRAINTS;
DROP TABLE job_class CASCADE CONSTRAINTS;
--CASCADE CONSTRAINTS will remove any constraints from other tables in the event
--droppping a table violates them.

CREATE TABLE job_class (
    job_id number(6) PRIMARY KEY,
    job_name varchar2(100) NOT NULL
);

CREATE TABLE npc (
    npc_id number(6) primary key,
    npc_name varchar(100) NOT NULL,
    npc_lvl number(3) NOT NULL,
    currency number(6) NOT NULL,
    job_id number(6),
    CONSTRAINT fk_job_id FOREIGN KEY (job_id) REFERENCES job_class (job_id)
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

--DML OLD
--INSERT INTO shop (SHOP_ID, SHOP_NAME, SHOP_OWNER)
--VALUES (1, 'Bobbert''s Wares', 'Bobbert');
----use 2 apostrophes to write an actual apostrophe in the string
----Any strings in SQL Developer MUST be surrounded by apostrophes, NOT quotes.
--INSERT INTO shop (SHOP_ID, SHOP_NAME, SHOP_OWNER)
--VALUES (1, 'The Birdcage', 'Salara');
--INSERT INTO shop VALUES (2, 'The Quiet Library', 'KnokcKnock');
----If you do not explicitly label column names, you will have to provide values
----for each named column.


--ALTER with adding columns
--ALTER TABLE shop ADD item1 varchar2(100);
--ALTER TABLE shop ADD item2 varchar2(100);
--ALTER TABLE shop ADD item3 varchar2(100);

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

commit;