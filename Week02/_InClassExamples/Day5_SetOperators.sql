DROP TABLE TA;
DROP TABLE TB;
CREATE TABLE TA (
    chars varchar2(100),
    nums number(6)
);
CREATE TABLE TB (
    chars varchar2(100),
    nums number(6)
);

INSERT INTO TA VALUES ('A',1);
INSERT INTO TA VALUES ('B',2);
INSERT INTO TA VALUES ('C',3);
INSERT INTO TA VALUES ('D',4);
INSERT INTO TA VALUES ('E',5);
INSERT INTO TA VALUES ('E',5);
INSERT INTO TA VALUES ('F',5);


INSERT INTO TB VALUES ('C',3);
INSERT INTO TB VALUES ('F',6);
INSERT INTO TB VALUES ('G',7);
INSERT INTO TB VALUES ('H',8);

--Combines all records together, omits duplicates
select * from TA
UNION
SELECT * FROM tb;

SELECT * FROM ta
INTERSECT 
SELECT * FROM tb;

SELECT * FROM ta
MINUS
SELECT * FROM tb;

SELECT * FROM ta
UNION ALL
SELECT * FROM tb;


select a.job_id from npc a
inner join npc b
on a.npc_id = b.npc_id
UNION
select a.job_id from npc a
inner join npc b
on a.npc_id = b.npc_id;