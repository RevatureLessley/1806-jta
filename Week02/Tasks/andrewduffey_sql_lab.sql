
/*
    2.1.1 - Select all records from the Employee table
*/
SELECT * FROM Employee;

/*
    2.1.2 - Select all records from the Employee table where last name is King.
*/
SELECT * FROM Employee WHERE LastName = 'King';

/*
    2.1.3 - Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
*/
SELECT * FROM Employee WHERE FirstName = 'Andrew' AND ReportsTo IS NULL;

/*
    2.2.1 - Select all albums in Album table and sort result set in descending order by title.
*/
SELECT * FROM Album ORDER BY Title DESC;

/*
    2.2.2 - Select first name from Customer and sort result set in ascending order by city
*/
SELECT FirstName FROM Customer ORDER BY City ASC;

/*
    2.3.1 - Insert two new records into Genre table
*/
INSERT INTO Genre (GenreID, Name) VALUES (26, 'Horror');
INSERT INTO Genre (GenreID, Name) VALUES (27, 'Suspense');

/*
    2.3.2 - Insert two new records into Employee table
*/
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (9, 'Richardson', 'Micheal', 'IT Staff', 6, TO_DATE('1952-11-7 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2006-10-3 00:00:00','yyyy-mm-dd hh24:mi:ss'), ' 4002 Forest Drive', 'Mclean', 'VA', 'United States', '22101', '+1 (707) 883-5394', '+1 (707) 883-5394', 'micheal@chinookcorp.com');
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (10, 'Scott', 'Leonard', 'IT Staff', 6, TO_DATE('1983-10-19 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2003-11-2 00:00:00','yyyy-mm-dd hh24:mi:ss'), ' 376 Patterson Street', 'Lennon', 'MI', 'Michigan', '48449', '+1 (810) 621-9261', '+1 (810) 621-9261', 'leonard@chinookcorp.com');

/*
    2.3.3 - Insert two new records into Customer table
*/
INSERT INTO Customer (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId) VALUES (60, 'Isaiah', 'Fonville', 'Allied City Stores', '501 Little Acres Lane', 'Peoria', 'IL', 'United States', '61602', '+1 (217) 934-7651', '+1 (217) 934-7651', 'isaiah.fonville@gmail.com', 3);
INSERT INTO Customer (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Fax, Email, SupportRepId) VALUES (61, 'Ralph', 'Jones', 'Allied City Stores', '501 Little Acres Lane', 'Peoria', 'IL', 'United States', '61602', '+1 (217) 934-7651', '+1 (217) 934-7651', 'ralph.jones@gmail.com', 3);

/*
    2.4.1 - Update Aaron Mitchell in Customer table to Robert Walter
*/
UPDATE Customer SET FirstName = 'Robert', LastName = 'Walter' WHERE FirstName = 'Aaron' AND LastName = 'Mitchell';

/*
    2.4.2 - Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
*/
UPDATE Artist SET Name = 'CCR' WHERE Name = 'Creedence Clearwater Revival';

/*
    2.5 - Select all invoices with a billing address like “T%”
*/
SELECT * FROM Invoice WHERE BillingAddress LIKE 'T%';

/*
    2.6.1 - Select all invoices that have a total between 15 and 50
*/
SELECT * FROM Invoice WHERE Total BETWEEN 15 AND 50;

/*
    2.6.2 - Select all employees hired between 1st of June 2003 and 1st of March 2004
*/
SELECT * FROM Employee WHERE HireDate BETWEEN TO_DATE('2003-06-01', 'yyyy-mm-dd') AND TO_DATE('2004-03-01', 'yyyy-mm-dd');

/*
    2.7 - Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
*/
DELETE FROM Customer WHERE FirstName = 'Robert' AND LastName = 'Walter';

/*
    7.1 - Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
*/
SELECT Customer.FirstName, Customer.LastName, Invoice.InvoiceId FROM Customer INNER JOIN Invoice ON Customer.CustomerID = Invoice.CustomerId;

/*
    7.2 - Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
*/
SELECT Customer.CustomerId, Customer.FirstName, Customer.LastName, Invoice.InvoiceId, Invoice.Total FROM Customer FULL OUTER JOIN Invoice ON Customer.CustomerID = Invoice.CustomerId;

/*
    7.3 - Create a right join that joins album and artist specifying artist name and title.
*/
SELECT Artist.Name, Album.Title FROM Album RIGHT JOIN Artist ON Album.ArtistId = Artist.ArtistId;

/*
    7.4 - Create a cross join that joins album and artist and sorts by artist name in ascending order.
*/
SELECT * FROM Album CROSS JOIN Artist ORDER BY Artist.Name ASC;

/*
    7.5 - Perform a self-join on the employee table, joining on the reportsto column.
*/
SELEcT * FROM Employee a, Employee b WHERE a.ReportsTo = b.ReportsTo;
