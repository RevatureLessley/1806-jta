
--Statements By: Cyril Mathew

-- 2.0 SQL Queries
----------------------------------------------------
-- 2.1 SELECT

SELECT * FROM Employee;

SELECT * FROM Employee
WHERE lastname LIKE 'King';

SELECT * FROM Employee
WHERE firstname LIKE 'Andrew' AND reportsto IS NULL;

------------------------------------------------------
-- 2.2 Order By

SELECT * FROM Album
ORDER BY title DESC;

SELECT firstname FROM customer
ORDER BY city ASC;

-------------------------------------------------------
-- 2.3 INSERT INTO

--SELECT * FROM genre;

INSERT INTO genre VALUES (26, 'CustomGenre1');
INSERT INTO genre VALUES (27, 'CustomGenre2');

--SELECT * FROM Employee;

INSERT INTO Employee VALUES (9, 'Cadet', 'Mark', 'Custodian', 1, TO_DATE('1928-12-8 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2004-2-3 00:00:00','yyyy-mm-dd hh24:mi:ss'), '22 Cannon Lake', 'Salmon Arm', 'BC', 'Canada', 'V0E 2T0', '+1 250-804-4921', '+1 250-804-4765', 'cadet@chinookcorp.com');
INSERT INTO Employee VALUES (10, 'Deere', 'John', 'Custodian',1, TO_DATE('1969-2-18 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2002-10-14 00:00:00','yyyy-mm-dd hh24:mi:ss'), '22 49th Avenue', 'Iqaluit', 'NU', 'Canada', 'X0A 0H0', '+1 867-975-5260', '+1 867-975-5298', 'deere@chinookcorp.com');

--SELECT * FROM customer;

INSERT INTO Customer VALUES (60, 'Stanley', 'McCall', 'Map Planners', '2196 Hinkle Lake Road', 'Roxbury', 'MA', 'USA', '02119', '+1 617-442-4938', '+1 617-442-4343', 'mccal@maplanners.com', 3);
INSERT INTO Customer VALUES (61, 'Allen', 'Miller', 'Buena Vista Realty Service', 'Mjövattnet 63', 'NORDINGRÅ','', 'Sweden', '870 30', '+46 0613-8636537', '+46 0613-221319', 'miller@buena.sw', 5);

------------------------------------------------
-- 2.4 Update

SELECT * FROM customer WHERE firstname = 'Robert';

UPDATE customer
SET firstname = 'Robert', lastname = 'Walter'
WHERE firstname = 'Aaron' AND lastname = 'Mitchell';

--SELECT * FROM artist WHERE name LIKE 'CCR';

UPDATE artist 
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';


----------------------------------------------------
-- 2.5 Like

SELECT * FROM invoice WHERE billingaddress LIKE 'T%';

---------------------------------------------------
-- 2.6 Between

SELECT * FROM invoice WHERE total BETWEEN 15 AND 50;
SELECT * FROM employee WHERE hiredate BETWEEN TO_DATE('2003-1-6 00:00:00','yyyy-mm-dd hh24:mi:ss') AND TO_DATE('2004-3-1 00:00:00','yyyy-mm-dd hh24:mi:ss');

---------------------------------------------------
-- 2.7 Delete

--SELECT * FROM customer;

DELETE FROM invoiceline WHERE invoiceid IN 
    (SELECT invoiceid FROM invoice WHERE customerid IN 
        (SELECT customerid FROM customer WHERE firstname = 'Robert' AND lastname = 'Walter'));

DELETE FROM invoice WHERE customerid IN (SELECT customerid FROM customer 
WHERE firstname = 'Robert' AND lastname = 'Walter');

DELETE FROM customer WHERE firstname = 'Robert' AND lastname = 'Walter';

--------------------------------------------------------------
--------------------------------------------------------------
-- 7.0 JOINS

-- 7.1 Inner

SELECT i.invoiceid, c.firstname, c.lastname FROM customer c 
INNER JOIN invoice i
ON c.customerid = i.customerid;

-- 7.2 Outer

SELECT c.customerid, c.firstname, c.lastname, i.invoiceid, i.total FROM customer c
FULL OUTER JOIN invoice i
ON c.customerid = i.customerid;

-- 7.3 Right

SELECT artist.name, album.title FROM artist
RIGHT JOIN album
ON artist.artistid = album.artistid;

-- 7.4 Cross

SELECT * FROM album
CROSS JOIN artist
ORDER BY artist.name ASC;

-- 7.5 Self

SELECT * FROM employee e1
JOIN employee e2
ON e1.reportsto = e2.reportsto;