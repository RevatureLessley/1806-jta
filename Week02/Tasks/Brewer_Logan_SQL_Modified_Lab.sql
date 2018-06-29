--2.1
SELECT * FROM employee;

SELECT * FROM employee
WHERE lastname = 'King';

SELECT * FROM employee
WHERE  firstname = 'Andrew' AND reportsto IS NULL;

--2.2
SELECT * FROM album
ORDER BY title DESC;

SELECT firstname FROM customer
ORDER BY city ASC;

--2.3 
INSERT INTO genre VALUES (26, 'New Music Stuff');
INSERT INTO genre VALUES (27, 'Cymbals');

INSERT INTO employee VALUES 
(9, 'Logan', 'Brewer', 'JTA', null, '07-DEC-94', 
'18-JUN-18', '16266 N Oachs Dr', 'Surprise', 'AZ', 
'United States', '85374', '+1 (623) 986-6348', 'null', 'logan@logan.com');
INSERT INTO employee VALUES 
(10, 'Random', 'Albert', 'JTA', null, '20-APR-16', 
'18-JUN-18', '12345 E Corner Dr', 'Flagstaff', 'AZ', 
'United States', '86011', '+1 (923) 123-4567', 'null', 'random@random.com');

INSERT INTO customer VALUES 
(60, 'First', 'Customer', 'Revature', 
'12345 W Other Dr', 'Blahblah City', 'TX', 
'United States', '45454', '+1 (700) 321-4567',
'null', 'first@first.com', 5);
INSERT INTO customer VALUES 
(61, 'Second', 'Customer', 'Revature', 
'12345 W Other Dr', 'Blahblah City', 'TX', 
'United States', '45454', '+1 (700) 321-4568',
'null', 'second@second.com', 5);

--2.4
UPDATE customer
SET firstname = 'Robert', lastname = 'Walter'
WHERE firstname = 'Aaron' AND lastname = 'Mitchell';

UPDATE artist
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';

--2.5
SELECT * FROM invoice
WHERE billingaddress LIKE 'T%';

--2.6
SELECT * FROM invoice
WHERE total BETWEEN 15 AND 50;

SELECT * FROM employee
WHERE hiredate BETWEEN '01-JUN-03' AND '01-MAR-04';

--2.7
DELETE FROM invoiceline
WHERE invoiceid = 50
OR invoiceid = 61
OR invoiceid = 342
OR invoiceid = 116
OR invoiceid = 245
OR invoiceid = 268
OR invoiceid = 290;
DELETE FROM invoice
WHERE customerid = 32;
DELETE FROM customer
WHERE firstname = 'Robert' AND lastname = 'Walter';

--7.1
SELECT c.firstname, c.lastname, i.invoiceid FROM customer c
INNER JOIN invoice i
ON c.customerid = i.customerid;

--7.2
SELECT c.customerid, c.firstname, c.lastname, i.invoiceid, i.total FROM customer c
FULL OUTER JOIN invoice i
ON c.customerid = i.customerid;

--7.3
SELECT artist.name, album.title FROM album
RIGHT OUTER JOIN artist
ON album.artistid = artist.artistid;

--7.4
SELECT * FROM album
CROSS JOIN artist
ORDER BY artist.name ASC;

--7.5
SELECT * FROM employee a, employee b
WHERE a.reportsto = b.reportsto;
