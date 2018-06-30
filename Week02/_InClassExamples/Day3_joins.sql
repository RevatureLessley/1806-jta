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
CREATE VIEW Aggregate_Data AS
select a.shop_name as "SHOP NAME",
    e.item_name as "AVAILABLE STOCK", 
    b.npc_name as "SHOP OWNER",
    c.job_name as "OWNER CLASS"
FROM shop a
RIGHT JOIN npc b
on a.owner_id = b.npc_id
LEFT join job_class c
on b.job_id = c.job_id
LEFT join shop_2_item d
on a.shop_id = d.shop_id
LEFT join item e
on d.item_id = e.item_id
ORDER BY a.SHOP_NAME;
--ORDER BY designates how the results are ordered out the gate.
--You can add DESC to order it from highest to lowest if you want.
--It defaults to ASC for lowest to highest.

SELECT * FROM AGGREGATE_DATA;
/*
    VIEW
    -A view is a virtual table that is created any time you query a database.
    -You can shortcut these virtual tables by createing VIEW objects before the specific
    select query you used to get it.
*/

--Nested selects/sub queries
SELECT * FROM npc WHERE npc_id IN (
    SELECT owner_id FROM shop WHERE shop_id IN (
        SELECT shop_id FROM shop_2_item WHERE item_id IN (
            SELECT item_id FROM item WHERE item_name = 'Premium Water'
        )
    )
);

--Use distinct to remove duplicates
SELECT DISTINCT * FROM ta;
--EXISTS
/*
    EXISTS works like IN, except as opposed to seeing if a specific value is in
    a collection like IN does, EXIST only aims to succeed if a at least ONE record id
    returned to it.
*/

select * from npc WHERE EXISTS(
    SELECT * FROM shop WHERE EXISTS(
        SELECT * FROM shop_2_item WHERE EXISTS(
            SELECT * FROM item WHERE item_name = 'Premium Water'
            AND shop_2_item.item_id = item.item_id
        ) AND shop.shop_id = shop_2_item.shop_id
    ) AND npc.npc_id = shop.shop_id
);

/*
    --IN vs EXISTS
    -Both of these commands can be used to perform conditional checks
    -High level overview; EXISTS is GARBAGE
    -It is highly inefficient if the outer query is even remotely large;
    This is because with EXISTS, for each record in teh outer query, we run 
    the innter query once. Should there be, yet another inner query, we run the
    innermost for each record of the middle query, and again for each record in the
    outermost query in an exponential fashion.
    
    Nested statements with IN, works to combine queires together as it digs in
    deeper. So to combine a query with low record count, to an inner query with high
    record count will yield slower results than EXISTS.
    But vice versa is significantly faster.
*/

/*
    --INDEX
    In oracle, you acn create INDEX objects. The purpose of the index object is to
    speed up data retrieval when querying large databases. As opposed to going through
    a table, one record at a time looking for some specific record, you can go directly
    to a specific index, bypassing the search.
    
    There are TWO types indexes, clustered and non clustered.
    --CLUSTERED INDEX
    A clustered index is made implicitly for you when you assign a primary key. A
    clustered index will index a table using the values of a column to do the job.
    
    --NON CLUSTERED INDEX
    SQL will create an index object behind the scenes, and use it to uniquely identify
    every single attribute in a column with its own indexing strategy. (Probably uses
    reference ids). As a developer, we don't see these indexes, but SQL has made its own
    unique ids for every single element, this includes duplicates as well.
    
    When choosing to create indices, you have to weigh the reason.
    An index, makes insertion/deletion take longer in bigger databases since the indexing
    has to be done for each new recod.
    But, of course, looking up data is faster.
    So in databases where inserts are numeorus, but not queries, then dont bother with
    indices. But if inserts are rare, but you are always grabbing data, indices are a good
    choice.
*/

DROP TABLE garbage;
CREATE TABLE garbage(
    num_id number(10),
    num_id2 number(10)
)

--INDEX EXAMPLE
--INSERT INTO garbage VALUES (1,1);
--
--INSERT INTO garbage (SELECT num_id + (SELECT MAX(num_id) FROM garbage), 
--        num_id2 + (SELECT MAX(num_id2) FROM garbage) FROM garbage);
--        
--SELECT * FROM garbage where num_id2 = 6623512;
--
--SELECT count(*) from garbage;
--
--DROP TABLE garbage2;
--CREATE TABLE garbage2(
--    num_id number(10),
--    num_id2 number(10)
--)
--
--CREATE INDEX garbage_index ON garbage2 (num_id2);
--
--INSERT INTO garbage2 VALUES (1,1);
--
--DELETE FROM garbage;
--select count(*) from garbage;
--
--TRUNCATE TABLE garbage;
--
--INSERT INTO garbage2 (SELECT num_id + (SELECT MAX(num_id) FROM garbage2), 
--        num_id2 + (SELECT MAX(num_id2) FROM garbage2) FROM garbage2);
--
--select count(*) FROM garbage2;
--
--SELECT * FROM garbage2 WHERE num_id2 = 3332341;
--
--TRUNCATE TABLE garbage2;