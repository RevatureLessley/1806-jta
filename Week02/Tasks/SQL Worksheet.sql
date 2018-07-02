
--2.1 SELECT
SELECT * FROM EMPLOYEE;
SELECT * FROM EMPLOYEE WHERE lastname = 'King';
SELECT * FROM EMPLOYEE WHERE firstname = 'Andrew' AND reportsto IS NULL;

--2.2 ORDER BY
SELECT * FROM album 
ORDER BY TITLE DESC;

SELECT firstname FROM customer
ORDER BY city;

--INSERT INTO
--Insert into genre
INSERT INTO genre VALUES (26, 'Russian Pop');
INSERT INTO genre VALUES (27, 'Ukrainian Folk');

--Insert into employee
INSERT INTO employee VALUES (9, 'Bukhalo', 'Vladimir', 'Associate',1, TO_DATE('1921-01-01 00:00:00','yyyy-mm-dd hh24:mi:ss'), 
TO_DATE('2018-06-01 00:00:00','yyyy-mm-dd hh24:mi:ss'), '25 W Arlington', 'Arlington', 'TX', 'USA', '11111', '+1 (555) 555-5555',
'+1 (555) 555-5556', 'vladimir@revature.com');

INSERT INTO employee VALUES (10, 'Putin', 'Vladimir', 'Emperor',1, TO_DATE('1952-10-07 00:00:00','yyyy-mm-dd hh24:mi:ss'), 
TO_DATE('2000-05-07 00:00:00','yyyy-mm-dd hh24:mi:ss'), 'The Kremlin', 'Moscow', 'Moscow', 'USSR', '11111', '+1 (555) 555-5555',
'+1 (555) 555-5556', 'vladimir@motherRussia.com');

--Insert into customer
INSERT INTO Customer (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId) 
VALUES (60, 'John', 'Doe', 'Morgue Inc.', '27 W Arlington', 'Arlington', 'TX', 'USA', 57463, '+1 (703) 123-4567', '+1 (703) 321-9876', 'john@dead.com', 3);

INSERT INTO Customer (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Email, SupportRepId) 
VALUES (61, 'Jane', 'Doe', 'Morgue Inc.', '27 W Arlington', 'Arlington', 'TX', 'USA', 57463, 'john@dead.com', 3);

--2.4 UPDATE
--Update Aaron Mitchell to Robert Walter
UPDATE customer 
SET firstname = 'Robert', lastname = 'Walter'
WHERE firstname = 'Aaron' and lastname = 'Mitchell';

--Update Creedence Clearwater Revival to CCR
SELECT * FROM artist ORDER BY NAME;
UPDATE artist 
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';

--2.5 LIKE
SELECT * FROM invoice WHERE billingaddress LIKE 'T%';

--2.6 BETWEEN
SELECT * FROM invoice WHERE total BETWEEN 15 AND 50;

SELECT * FROM employee WHERE hiredate BETWEEN 
TO_DATE('2003-06-01 00:00:00','yyyy-mm-dd hh24:mi:ss') AND
TO_DATE('2004-03-01 00:00:00','yyyy-mm-dd hh24:mi:ss');

SAVEPOINT VLAD;
--2.7 DELETE
ALTER TABLE invoice DROP CONSTRAINT FK_InvoiceCustomerId;

--Add delete cascase to forign key
ALTER TABLE Invoice ADD CONSTRAINT FK_InvoiceCustomerId
    FOREIGN KEY (CustomerId) REFERENCES Customer (CustomerId)
    ON DELETE CASCADE;
    
ALTER TABLE invoiceline DROP CONSTRAINT FK_InvoiceLineInvoiceId;

--Add delete cascade to forign key
ALTER TABLE InvoiceLine ADD CONSTRAINT FK_InvoiceLineInvoiceId
    FOREIGN KEY (InvoiceId) REFERENCES Invoice (InvoiceId)
    ON DELETE CASCADE;

DELETE FROM customer 
WHERE firstname = 'Robert' AND lastname = 'Walter';

COMMIT;


--7.1 INNER JOIN
SELECT firstname, lastname, invoiceid FROM customer c
INNER JOIN invoice i
ON c.customerid = i.customerid;

--7.2 OUTER JOIN
SELECT c.customerid, c.firstname, c.lastname, i.invoiceid, i.total FROM customer c
FULL OUTER JOIN invoice i
ON c.customerid = i.customerid;

--7.3 RIGHT
SELECT ar.name, al.title FROM album al
RIGHT JOIN artist ar
ON ar.artistid = al.artistid;

--7.4 CROSS
SELECT * FROM artist
CROSS JOIN album
ORDER BY artist.name;

--7.5 SELF
SELECT e.lastname AS Employee, m.lastname as Manager FROM employee e
LEFT JOIN employee m
ON m.employeeid = e.reportsto;


