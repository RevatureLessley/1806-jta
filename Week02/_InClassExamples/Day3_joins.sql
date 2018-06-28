--Joins
SELECT NPC_Name, npc_lvl, currency,job_name FROM npc
INNER JOIN shop
ON npc.job_id = shop.Job_id;

DROP TABLE ta;
DROP TABLE tb;
DROP TABLE tc;
DROP TABLE td;
CREATE TABLE ta(
    tanum number(3)
);
CREATE TABLE tb(
    tbnum number(3)
);
CREATE TABLE tc(
    nums number(3)
);
CREATE TABLE td(
    nums number(3)
);
INSERT INTO ta values(1);
INSERT INTO ta values(2);
INSERT INTO ta values(3);
INSERT INTO ta values(4);
INSERT INTO tb values(3);
INSERT INTO tb values(4);
INSERT INTO tb values(5);
INSERT INTO tb values(6);
INSERT INTO tc values(1);
INSERT INTO tc values(2);
INSERT INTO tc values(3);
INSERT INTO tc values(3);
INSERT INTO tc values(4);
INSERT INTO td values(3);
INSERT INTO td values(3);
INSERT INTO td values(4);
INSERT INTO td values(5);
INSERT INTO td values(6);



select * from tb;

--INNER JOIN
SELECT * FROM ta
inner join tb
ON ta.tanum = tb.tbnum;

--LEFT JOIN
SELECT * FROM ta
LEFT JOIN tb
ON ta.tanum = tb.tbnum;

--RIGHT JOIN
SELECT * FROM ta
RIGHT JOIN tb
ON ta.tanum = tb.tbnum;

--FULL OUTER JOIN
SELECT * FROM ta
FULL OUTER JOIN tb
on ta.tanum = tb.tbnum;


--CROSS JOIN
SELECT * FROM ta
CROSS JOIN tb;

/*
    In the above examples, we did comparisons of two columns where 
    A = B. This is called an EQUI-JOIN for obvious reasons.
    (It uses the = operator)
    Should you NOT use an = operator, but instead use other comparators,
    this is called a THETA JOIN.
*/

--THETA JOIN
SELECT * FROM ta
INNER JOIN tb
on ta.tanum < tb.tbnum;

--NATURAL
SELECT * FROM tc
NATURAL INNER JOIN td;
/*
    A natural, as the name suggests, will aim to join two tables together
    in a natural way. IE, automatically match the tables together via matching
    column names. After doing this, it will automatically remove duplicate columns
    in the result set. Therefore, the 'ON' clause is not necessary.
    You may preface any keywork join with natural to apply it. E.G.
    NATURAL LEFT JOIN/ NATURAL FULL OUTER JOIN.
*/
