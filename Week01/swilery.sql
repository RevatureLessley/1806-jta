DROP TABLE shop CASCADE CONSTRAINTS;
DROP TABLE npc CASCADE CONSTRAINTS;
DROP TABLE item CASCADE CONSTRAINTS;
DROP TABLE shop_2_item CASCADE CONSTRAINTS;
DROP TABLE job_clazz CASCADE CONSTRAINTS;
DROP TABLE ta CASCADE CONSTRAINTS;
DROP TABLE tb CASCADE CONSTRAINTS;
DROP TABLE tc CASCADE CONSTRAINTS;
DROP TABLE td CASCADE CONSTRAINTS;

CREATE TABLE shop (
    shop_id NUMBER(6),
    shop_name VARCHAR2(100),
    shop_owner NUMBER(6),
    CONSTRAINT pk_shopid PRIMARY KEY (shop_id),
    CONSTRAINT fk_ownerid FOREIGN KEY (owner_id) REFERENCES npc(npc_id)
);

create TABLE job_clazz (
    job_id NUMBER(6) PRIMARY KEY,
    job_name VARCHAR2(100) NOT NULL
);

CREATE TABLE npc (
    npc_id NUMBER(6) PRIMARY KEY,
    npc_name VARCHAR2(100),
    npc_lvl NUMBER(6),
    currency NUMBER(6),
    job_id NUMBER(6),
    CONSTRAINT fk_jobid FOREIGN KEY (job_id) REFERENCES job_class (job_id)
);

CREATE TABLE item (
    item_id NUMBER(6) PRIMARY KEY,
    item_name VARCHAR2(100),
    item_owner NUMBER(6)
);

CREATE TABLE shop_2_item (
    shop_id NUMBER(6),
    item_id NUMBER(6),
    CONSTRAINT fk_shop_id FOREIGN KEY (shop_id) REFERENCES shop (shop_id),
    CONSTRAINT fk_item_id FOREIGN KEY (item_id) REFERENCES item (item_id)
);

CREATE TABLE ta (
    tanum NUMBER(3)
);

CREATE TABLE tb (
    tanum NUMBER(3)
);

CREATE TABLE tc (
    nums NUMBER(3)
);

CREATE TABLE td (
    nums NUMBER(3)
);

INSERT INTO ta values (1); 
INSERT INTO ta values (2); 
INSERT INTO ta values (3); 
INSERT INTO ta values (4); 

INSERT INTO tb values (3); 
INSERT INTO tb values (4); 
INSERT INTO tb values (5); 
INSERT INTO tb values (6); 


INSERT INTO tc values (1); 
INSERT INTO tc values (2); 
INSERT INTO tc values (3); 
INSERT INTO tc values (4); 

INSERT INTO td values (3); 
INSERT INTO td values (4); 
INSERT INTO td values (5); 
INSERT INTO td values (6); 

SELECT * FROM ta INNER JOIN tb ON ta.tanum = tb.tbnum;
SELECT * FROM ta LEFT JOIN tb ON ta.tanum = tb.tbnum;
SELECT * FROM ta RIGHT JOIN tb ON ta.tanum = tb.tbnum;
SELECT * FROM ta FULL OUTER JOIN tb ON ta.tanum = tb.tbnum;
SELECT * FROM ta CROSS JOIN tb;
SELECT * FROM tc NATRUAL INNER JOIN td;

INSERT INTO npc VALUES(1, 'Bobbert', 99, 12, 3);
INSERT INTO npc VALUES(1, 'Salara', 72, 1000000 - 1, 2);
INSERT INTO npc VALUES(1, 'Zachary', 50, 6000, 3);
INSERT INTO npc VALUES(1, 'Ryan', 98, 2, 1);
INSERT INTO npc VALUES(1, 'Little Tommy Fickles',1, 75000, 2);
INSERT INTO npc VALUES(1, 'Big Bobby Gherkin',33, 4500, 1);
INSERT INTO npc VALUES(1, 'Memium Larry Vlastic',54, 692, 1);
INSERT INTO npc VALUES(1, 'Variable Victor Salami',13, 133000, 1);

INSERT INTO shop(shop_id, shop_name, shop_owner)
VALUES (1, 'Bobbert''s Wares', 1);
INSERT INTO shop(shop_id, shop_name, shop_owner)
VALUES (2, 'The Birdcage', 2);
INSERT INTO shop VALUES(3, 'The Quiet Library', 3);

INSERT INTO item VALUES (1, 'Water', 3);
INSERT INTO item VALUES (1, 'Guantlets of Infinity', 50);
INSERT INTO item VALUES (1, 'XBOX 360', 10);
INSERT INTO item VALUES (1, 'Premium Water', 500);
INSERT INTO item VALUES (1, 'Revature Training', 19400);

INSERT INTO shop_2_item VALUES (1, 1);
INSERT INTO shop_2_item VALUES (1, 4);
INSERT INTO shop_2_item VALUES (2, 2);
INSERT INTO shop_2_item VALUES (2, 3);
INSERT INTO shop_2_item VALUES (3, 5);
INSERT INTO shop_2_item VALUES (3, 1);

INSERT INTO job_clazz VALUES (1, 'Shopkeep');
INSERT INTO job_clazz VALUES (2, 'Assassin');
INSERT INTO job_clazz VALUES (3, 'Professional Bob');

SELECT npc_name, npc_lvl, currency, job_name FROM npc INNER JOIN job_class 
ON npc.job_id = job_clazz.job_id;

--SELECT * FROM shop;
--SELECT * FROM npc;
--SELECT * FROM npc WHERE currency > 30;
--SELECT npc_name, currency FROM npc WHERE currency > 30;
--SELECT npc_name AS "Name", currency AS "Money On Hand", job_id AS "JoBiD" 
--FROM npc WHERE currency > 30;
--SELECT SUM(currency), job_id FROM npc WHERE currency > 30 GROUP BY job_id 
--HAVING sum(currency) > 10000;
--
--INSERT INTO npc VALUES (33, 'Bill', -21, 1);
--INSERT INTO npc VALUES (33, 'Bill23', -89, 1);
--INSERT INTO npc VALUES (33, 'Bill45', -154, 1);
--
--DELETE FROM npc WHERE npc_id = 33;
--DELETE FROM npc WHERE npc_id = 33;
--DELETE FROM npc WHERE npc_id = 33 AND npc_id = 34 OR npc_id = 35;
--DELETE FROM npc WHERE currency < 0;
--DELETE FROM npc WHERE npc_id IN (33,34,35);
--DELETE FROM npc WHERE npc_id BETWEEN 33 AND 35;
--
--SELECT * FROM npc WHERE npc_name LIKE '%a%';
--
--UPDATE npc SET npc_name = UPPER(npc_name), SET currency = currency - 1000 
--WHERE npc_name LIKE '%a%',
--
--SELECT * FROM npc WHERE LOWER(npc_name) LIKE '%a%';
--
----ALTER TABLE shops ADD item1 VARCHAR2(100);
----ALTER TABLE shops ADD item2 VARCHAR2(100);
----ALTER TABLE shops ADD item3 VARCHAR2(100);
--
--COMMIT;
--
--INSERT INTO npc SELECT npc_id + (SELECT COUNT(*) 
--FROM npc), npc_name, npc_lvl, currency, job_id FROM npc;
--
--SAVEPOINT ryan;
--
--ROLLBACK TO ryan;
--
--ROLLBACK;
--
--COMMIT;
--
--TRUNCATE TABLE shop_2_item;