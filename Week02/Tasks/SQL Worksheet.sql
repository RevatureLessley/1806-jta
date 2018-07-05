--2.1 Selecting
SELECT * FROM employee;
SELECT * FROM employee WHERE lastname = 'King';
SELECT * FROM employee WHERE firstname = 'Andrew' AND reportsto = null;
--2.2 Order By
SELECT * FROM album ORDER BY Title DESC;
SELECT firstname FROM customer ORDER BY city;
--2.3 Insert Into
INSERT INTO genre VALUES(26, 'Taco Music');
INSERT INTO genre VALUES(27, 'Dumpling Music');
INSERT INTO employee VALUES(9, 'Dudington', 'Dude', 'Proffessional Dude', 5, '2008-11-11', '2008-11-11', '1001 N Dude Street', 'Chicken Wing City', 'IN', 'United States of the Dude', 'Dud 186', '+1 (403) 262-3443', '+1 (403) 262-3443','dude@dude.com');
INSERT INTO employee VALUES(9, 'Taco', 'Man', 'Proffessional Taco', 5, '2008-11-11', '2008-11-11', '1001 N Taco Street', 'Taco Bell City', 'IN', 'United States of the Taco', 'Tac 186', '+1 (403) 262-3443', '+1 (403) 262-3443','Taco@Taco.com');
INSERT INTO customer VALUES(60, 'Another Dude', 'Dude', null, '1111 Stuff Blv.', 'Things City', 'STF', 'StuffThings', '11111', '+54 (0)11 4311 4333', null,'stuff@things.org.net.stuff', 4);
INSERT INTO customer VALUES(60, 'Yet Another Dude', 'Dude2', null, '222 Stuff Blv.', 'Stuffs City', 'STF', 'StuffThings', '11111', '+54 (0)11 4311 4333', null,'stuff@things.org.net.stuff', 4);

--2.4
UPDATE customer SET firstname = 'Robert', lastname = 'Walter' WHERE customerid=32;
UPDATE artist SET name = 'CCR' WHERE name = 'Creedence Clearwater Revival';

--2.5
SELECT * FROM invoice WHERE BillingAddress LIKE 'T%';

--2.6
SELECT * FROM invoice WHERE invoiceid BETWEEN 15 AND 50;
SELECT * FROM employee WHERE hiredate BETWEEN '01-JUN-03' AND '01-MAR-04';

--2.7
ALTER TABLE invoice DROP CONSTRAINT FK_InvoiceCustomerId;
ALTER TABLE Invoice ADD CONSTRAINT FK_InvoiceCustomerId
    FOREIGN KEY (CustomerId) REFERENCES Customer (CustomerId) ON DELETE CASCADE;
DELETE FROM customer WHERE firstname='Robert' AND lastname='Walter';

--7.0
--7.1
SELECT customer.firstName, customer.LastName, Invoice.Invoiceid
FROM customer 
INNER JOIN invoice ON customer.customerid  = invoice.customerid;

--7.2
SELECT a.customerid, a.firstname, a.lastname, b.invoiceid, B.Total
FROM customer a 
FULL OUTER JOIN invoice b ON a.customerid = b.customerid;

--7.3
SELECT b.name, A.Title
FROM album a
RIGHT JOIN artist b ON a.artistid = b.artistid;

--7.4
SELECT A.Title, B.Name 
FROM album a 
CROSS JOIN artist b ORDER BY B.Name asc;

--7.5
SELECT * FROM employee a, employee b
WHERE a.reportsto = b. reportsto;