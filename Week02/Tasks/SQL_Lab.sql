-- 2.1 SELECT
-- Task – Select all records from the Employee table.
SELECT * FROM Employee;
-- Task – Select all records from the Employee table where last name is King.
SELECT * FROM Employee WHERE LastName = 'King';
-- Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM Employee WHERE FirstName = 'Andrew' and ReportsTo IS NULL;


-- 2.2 ORDER BY
-- Task – Select all albums in Album table and sort result set in descending order by title.
SELECT *  From Album ORDER BY Title DESC;
-- Task – Select first name from Customer and sort result set in ascending order by city
SELECT FirstName FROM Customer ORDER BY City ASC;


-- 2.3 INSERT INTO
-- Task – Insert two new records into Genre table
INSERT INTO Genre (GenreId, Name) VALUES (26, 'Country');
INSERT INTO Genre (GenreId, Name) VALUES (27, 'Urban');
-- Task – Insert two new records into Employee table
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) 
VALUES (9, 'Crisanto', 'Kevin', 'CEO', TO_DATE('1993-8-28 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2018-6-30 00:00:00','yyyy-mm-dd hh24:mi:ss'), '1242 N Minot St', 'Anaheim', 'CA', 'United States', '92801', '+1 (990) 999-8888', '+1 (714) 299-0605', 'kevincrisanto@gmail.com');
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) 
VALUES (10, 'Wang', 'Kevin', 'Janitor', 9, TO_DATE('1993-10-8 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2018-7-1 00:00:00','yyyy-mm-dd hh24:mi:ss'), '825 8 Ave SW', 'Minneapolis', 'MN', 'United States', '55401', '+1 (403) 262-3443', '+1 (403) 262-3322', 'wang.kevin@revature.com');
-- Task – Insert two new records into Customer table
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, State, Country, PostalCode, Phone, Email, SupportRepId) 
VALUES (60, 'Bobbert', 'Johnson', '111 Bourke Street', 'Sidney', 'NSW', 'Australia', '2010', '+61 (02) 1111 2222', 'bobbert.johnson@yahoo.au', 4);
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, State, Country, PostalCode, Phone, Email, SupportRepId) 
VALUES (61, 'Chris', 'Green', '429 Bourke Street', 'Sidney', 'NSW', 'Australia', '2010', '+61 (02) 9338 9999', 'green.chris@yahoo.au', 4);


-- 2.4 UPDATE
-- Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE Customer SET FirstName = 'Robert', LastName = 'Walter' WHERE FirstName = 'Aaron' AND LastName = 'Mitchell';
-- Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE Artist SET Name = 'CCR' WHERE Name = 'Creedence Clearwater Revival';


-- 2.5 LIKE
-- Task – Select all invoices with a billing address like “T%”
SELECT * FROM Invoice WHERE BillingAddress LIKE 'T%';


-- 2.6 BETWEEN
-- Task – Select all invoices that have a total between 15 and 50
SELECT * FROM Invoice WHERE Total BETWEEN 15 AND 50;
-- Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM Employee WHERE HireDate BETWEEN '01-JUN-03' and '01-MAR-04';


-- 2.7 DELETE
-- Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
ALTER TABLE Invoice DROP CONSTRAINT FK_InvoiceCustomerId;
ALTER TABLE Invoice ADD CONSTRAINT FK_InvoiceCustomerId 
  FOREIGN KEY (CustomerId) references Customer (CustomerId) ON DELETE CASCADE;
DELETE FROM Customer WHERE FirstName = 'Robert' AND LastName = 'Walter';
  

-- 7.0 JOINS
-- In this section you will be working with combing various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.

-- 7.1 INNER
-- Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT Customer.FirstName, Customer.LastName, Invoice.InvoiceID
FROM Customer
INNER JOIN Invoice ON Customer.CustomerID = Invoice.CustomerID;

-- 7.2 OUTER
-- Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT Customer.CustomerId, Customer.FirstName, Customer.LastName, Invoice.InvoiceId, Invoice.Total
FROM Customer
FULL OUTER JOIN Invoice ON Customer.CustomerID = Invoice.CustomerID;


-- 7.3 RIGHT
-- Task – Create a right join that joins album and artist specifying artist name and title.
SELECT Artist.Name, Album.Title
FROM Album
RIGHT JOIN Artist ON Album.ArtistID = Artist.ArtistID;

-- 7.4 CROSS
-- Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT * 
FROM Artist
CROSS JOIN Album ORDER BY Artist.Name ASC;

-- 7.5 SELF
-- Task – Perform a self-join on the employee table, joining on the reportsto column.
SELECT A.EmployeeID AS "Emp_ID", A.FirstName AS "EMP_FNAME", A.LastName AS "EMP_LNAME",
B.EmployeeID AS "ReportsToID", B.FirstName AS "ReportsTo_FName", B.LastName AS "ReportsTo_LName"
FROM Employee A, Employee B
WHERE A.EmployeeID <> B.EmployeeID
AND A.ReportsTo = B.EmployeeID;
