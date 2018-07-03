
--2.1 SELECT
SELECT *
FROM Employee;

SELECT *
FROM Employee
WHERE lastname = 'King';

SELECT *
FROM Employee
WHERE firstname = 'Andrew' AND REPORTSTO = null;

--2.2 ORDER BY
SELECT *
FROM Album
ORDER BY title DESC;

SELECT FIRSTNAME
FROM Customer
ORDER BY CITY;

--2.3
SELECT * 
FROM Customer;

INSERT INTO Genre VALUES (26, 'Down Tempo');
INSERT INTO Genre VALUES (27, 'Vaporwave');

INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (8, 'Dean', 'Eddie', 'Ka-tet', 1, TO_DATE('1999-9-9 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2004-3-4 00:00:00','yyyy-mm-dd hh24:mi:ss'), '923 7 ST NW', 'Brooklyn', 'AB', 'New York', 'T1H 1Y8', '+1 (555) 467-1999', '+1 (555) 467-1998', 'eddie.dean@tower.com');
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (8, 'Dean', 'Susannah', 'Ka-tet', 1, TO_DATE('1999-9-9 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2004-3-4 00:00:00','yyyy-mm-dd hh24:mi:ss'), '923 7 ST NW', 'Brooklyn', 'AB', 'New York', 'T1H 1Y8', '+1 (555) 467-1999', '+1 (555) 467-1998', 'susannah.dean@tower.com');

INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId) VALUES (9, 'Hermoine', 'Granger', 'Godric''s Hollow 14', 'London', 'UK', '1720', '+453 3331 9991', 'hgranger@hogwarts.mag', 4);
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId) VALUES (9, 'Ron', 'Weasley', 'Godric''s Hollow 14', 'London', 'UK', '1720', '+453 3331 9991', 'ronaldweas@hogwarts.mag', 4);


--2.4

UPDATE customer SET firstname='Robert', lastname='Walter' WHERE firstname='Aaron' AND lastname='Mitchell';

UPDATE artist SET name='CCR' WHERE name='Creedence Clearwater Revival';

--2.5

SELECT * FROM Invoice WHERE BillingAddress LIKE 'T%';

--2.6

SELECT * FROM Invoice WHERE Total BETWEEN 15 AND 50 ORDER BY Total;
SELECT * FROM Employee WHERE HireDate BETWEEN  DATE '2003-06-01' AND DATE '2004-03-01';

--2.7

ALTER TABLE Invoice
    DROP CONSTRAINT FK_InvoiceCustomerId;

ALTER TABLE Invoice ADD CONSTRAINT FK_InvoiceCustomerId
    FOREIGN KEY (CustomerId) REFERENCES Customer (CustomerId)
    ON DELETE CASCADE;
    
ALTER TABLE InvoiceLine
    DROP CONSTRAINT FK_InvoiceLineInvoiceId;
    
ALTER TABLE InvoiceLine ADD CONSTRAINT FK_InvoiceLineInvoiceId
    FOREIGN KEY (InvoiceId) REFERENCES Invoice (InvoiceId)
    ON DELETE CASCADE;

DELETE FROM Customer WHERE firstname='Robert' AND lastname='Walter';

--7.1

SELECT Customer.FIRSTNAME, Customer.LASTNAME, Invoice.INVOICEID FROM Customer
INNER JOIN Invoice on Customer.CUSTOMERID = Invoice.CUSTOMERID;

--7.2

SELECT Customer.FIRSTNAME, Customer.LASTNAME, Invoice.INVOICEID, Invoice.TOTAL FROM Customer
FULL OUTER JOIN Invoice ON Customer.CUSTOMERID = Invoice.CUSTOMERID;

--7.3
SELECT Artist.NAME, Album.TITLE FROM Album
RIGHT JOIN Artist ON Album.artistid = Artist.ARTISTID;

--7.4
SELECT Artist.NAME, Album.TITLE FROM Album
CROSS JOIN Artist ORDER BY Artist.NAME;

--7.5
SELECT * FROM Employee a, Employee b
WHERE a.REPORTSTO=b.REPORTSTO;
