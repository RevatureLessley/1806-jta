-- Eric Sundberg
-- Using Chinook_Oracle database tables

-- 2.1 SELECT
SELECT * FROM Employee;
SELECT * FROM Employee WHERE lastname = 'King';
SELECT * FROM Employee WHERE firstname = 'Andrew' AND reportsto IS NULL; 

-- 2.2 ORDER BY
SELECT * FROM Album ORDER BY title DESC;
SELECT (firstname) FROM Customer ORDER BY city ASC;

-- 2.3 INSERT INTO
INSERT INTO Genre Values (26, 'Atmospheric');
INSERT INTO Genre Values (27, 'Miscellaneous');

-- 2.4 UPDATE
UPDATE Customer SET firstname = 'Robert', lastname = 'Walter' WHERE firstname = 'Aaron' AND lastname = 'Mitchell';
UPDATE Artist SET name = 'CCR' WHERE name = 'Creedence Clearwater Revival';

-- 2.5 LIKE
SELECT * FROM Invoice WHERE billingaddress LIKE 'T%';

-- 2.6 BETWEEN
SELECT * FROM Invoice WHERE total BETWEEN 15 AND 50;
SELECT * FROM Employee WHERE hiredate BETWEEN '01-JUN-03' AND '01-MAR-04';

-- 2.7 DELETE
ALTER TABLE InvoiceLine DROP CONSTRAINT FK_InvoiceLineInvoiceId;
ALTER TABLE Invoice DROP CONSTRAINT FK_InvoiceCustomerId;
ALTER TABLE Invoice ADD CONSTRAINT FK_InvoiceCustomerId
    FOREIGN KEY (CustomerId) REFERENCES Customer (CustomerId) ON DELETE CASCADE;
ALTER TABLE InvoiceLine ADD CONSTRAINT FK_InvoiceLineInvoiceId
    FOREIGN KEY (InvoiceId) REFERENCES Invoice (InvoiceId) ON DELETE CASCADE;
    
DELETE FROM Customer WHERE firstname = 'Robert' AND lastname = 'Walter';

-- 7.0 --
-- 7.1 INNER
SELECT c.firstname, c.lastname, i.invoiceId FROM Customer c
INNER JOIN Invoice i
ON c.CustomerId = i.CustomerId;

-- 7.2 OUTER
SELECT c.customerid, c.firstname, c.lastname, i.invoiceId, i.total FROM Customer c
FULL OUTER JOIN Invoice i
ON c.CustomerId = i.CustomerId;

-- 7.3 RIGHT
SELECT ar.name, al.title FROM Album al
RIGHT JOIN Artist ar
ON ar.artistId = al.artistId;

-- 7.4 CROSS
SELECT * FROM Album al
CROSS JOIN Artist ar ORDER BY ar.name ASC;

-- 7.5 SELF
SELECT * FROM Employee a 
JOIN Employee b
ON (a.reportsto = b.reportsto);
