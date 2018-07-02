--TASK 2.1
SELECT * FROM Employee;
SELECT * FROM Employee WHERE LastName = 'King';
SELECT * FROM Employee WHERE FirstName = 'Andrew' AND ReportsTo IS NULL;
--TASK 2.2
SELECT * FROM Album ORDER BY Title DESC;
SELECT FirstName FROM Customer ORDER BY City ASC;
--TASK 2.3
INSERT INTO Genre (GenreId, Name) VALUES (26, 'Folk');
INSERT INTO Genre (GenreId, Name) VALUES (27, 'Soul');
--SELECT * FROM Genre;
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (9, 'Holden', 'Anthony', 'IT Staff', 2, TO_DATE('1981-8-19 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2015-06-29 00:00:00','yyyy-mm-dd hh24:mi:ss'), '285 11th Ave NW ', 'Calgary', 'AB', 'Canada', 'T2P 1M6', '+1 (403) 571-8167', '+1 (403) 571-8167', 'anthonyHolden@chinookcorp.com');
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (10, 'Cook', 'Linda', 'IT Staff', 2, TO_DATE('1995-5-7 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2018-06-29 00:00:00','yyyy-mm-dd hh24:mi:ss'), '1149 Riedel St West', 'Ft Mcmurray', 'AB', 'Canada', 'T9H 3J9', '+1 (780) 791-1835', '+1 (780) 791-1835', 'cook.linda@chinookcorp.com');
--SELECT * FROM Employee;
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, State, Country, PostalCode, Phone, Email, SupportRepId) VALUES (60, 'James', 'Simmons', '4364 Swick Hill St', 'New Orleans', 'LA', 'USA', '70087', '+1 985 226-8484', 'jsimmons@gmail.com', 5);
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, State, Country, PostalCode, Phone, Email, SupportRepId) VALUES (61, 'Beverly', 'Johnson', '3805 Christie Way', 'Lexington', 'MA', 'USA', '02173', '+1 978 836-4732', 'bevjohnson120@yahoo.com', 3);
--SELECT * FROM Customer;
--TASK 2.4
UPDATE Customer SET FirstName = 'Robert', LastName = 'Walter' WHERE FirstName = 'Aaron' AND LastName = 'Mitchell';
SELECT * FROM Customer WHERE FirstName = 'Robert' AND LastName = 'Walter';
UPDATE Artist SET Name = 'CCR' WHERE Name = 'Creedence Clearwater Revival';
SELECT * FROM Artist WHERE Name = 'CCR';
--TASK 2.5
SELECT * FROM Invoice WHERE BillingAddress LIKE 'T%';
--TASK 2.6
SELECT * FROM Invoice WHERE Total BETWEEN 15 AND 50;
SELECT * FROM Employee WHERE HireDate BETWEEN TO_DATE('2003-6-1 00:00:00','yyyy-mm-dd hh24:mi:ss') AND TO_DATE('2004-3-1 00:00:00','yyyy-mm-dd hh24:mi:ss');
--TASK 2.7
DELETE FROM InvoiceLine WHERE InvoiceID IN (SELECT InvoiceID FROM Invoice WHERE CustomerID IN (SELECT CustomerID FROM Customer WHERE Firstname = 'Robert' AND Lastname = 'Walter'));
--SELECT * FROM InvoiceLine;
DELETE FROM Invoice WHERE CustomerID IN (SELECT CustomerID FROM Customer WHERE Firstname = 'Robert' AND Lastname = 'Walter');
--SELECT * FROM Invoice;
DELETE FROM Customer WHERE Firstname = 'Robert' AND Lastname = 'Walter';
--SELECT * FROM Customer;
--TASK 7.1
SELECT C.Firstname || ' ' || C.Lastname AS Name, I.InvoiceID FROM Customer C INNER JOIN Invoice I ON C.CustomerID = I.CustomerID; 
--TASK 7.2
SELECT C.CustomerID, C.FirstName, C.LastName, I.InvoiceID, I.Total FROM Customer C FULL OUTER JOIN Invoice I ON C.CustomerID = I.CustomerID; 
--TASK 7.3
SELECT A.Title, B.Name FROM Album A RIGHT JOIN Artist B ON A.ArtistID = B.ArtistID; 
--TASK 7.4
SELECT * FROM Album A CROSS JOIN Artist B ORDER BY B.Name ASC; 
--TASK 7.5
SELECT X.FirstName || ' ' || X.LastName AS EMPLOYEE, Y.FirstName || ' ' || Y.LastName AS MANAGER FROM Employee X LEFT JOIN Employee Y ON X.ReportsTo=Y.EmployeeID; 
