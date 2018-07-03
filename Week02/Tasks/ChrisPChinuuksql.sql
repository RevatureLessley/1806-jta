select * from employee;

select * from employee where lastname = 'King';

select * from employee where firstname = 'Andrew' and REPORTSTO = null;

select * from album order by title desc;

select firstname from customer order by city asc;

insert into genre(genreid, name)
values(26, 'the HEAVIEST of metal');

insert into genre(genreid, name)
values(27, 'Only God kknows what this is');

INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) 
VALUES (9, 'Adams', 'Billy', 'Manager of sales', TO_DATE('2018-6-18 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2018-6-28 00:00:00','yyyy-mm-dd hh24:mi:ss'), '11120 Jacksonville', 'That Place', 'AB', 'USA', 'T5K 2N1', '+1 (832) 555-5555', '+1 (780) 428-3457', 'thenight@chinookcorp.com');
INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) 
VALUES (10, 'bobbert', 'bobbson', 'janitor',1, TO_DATE('2018-6-18 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2018-6-28 00:00:00','yyyy-mm-dd hh24:mi:ss'), '123 main st', 'home', 'Tx', 'USA', 'T2P 2T3', '+1 (403) 262-3443', '+1 (281) 555-5555', 'fangmaster@chinookcorp.com');

INSERT INTO Customer (CustomerId, FirstName, LastName, Company, Address, City, State, Country, PostalCode, Phone, Email, SupportRepId) 
VALUES (25, 'Lucas', 'Onnet', 'Revature', '13298 One ln', 'MainTown', 'NY', 'USA', '77562', '+1 (122) 3923-5555', 'yourbestcustomer@customersrus.com', 3);
INSERT INTO Customer (CustomerId, FirstName, LastName, Address, City, Country, PostalCode, Phone, Email, SupportRepId) 
VALUES (26, 'Chris', 'Parsons', 'Martinique', 'Baytown', 'USA', '77555', '+1 345 675-4231', 'leonheart@finalfantasy.com', 5);

update customer set firstname = 'Robert', lastname = 'Walter' where (FirstName = 'Aaron' and LastName = 'Mitchell');
update artist set name = 'CCR' where name = 'Creedence Clearwater Revival';
--As a note, you can totally just pull up the data in the table and change it there. I didn't, but I totally could

select * from invoice where billingaddress  like 'T%';

select * from invoice where total between 15 and 50;
select * from employee where hiredate between TO_DATE('2003-6-1 00:00:00','yyyy-mm-dd hh24:mi:ss') and TO_DATE ('2004-3-1 00:00:00','yyyy-mm-dd hh24:mi:ss')


ALTER TABLE invoice DROP CONSTRAINT fk_invoicecustomerid;
ALTER TABLE invoiceline DROP CONSTRAINT fk_invoicelineinvoiceid;

ALTER TABLE Invoice ADD CONSTRAINT FK_InvoiceCustomerId
    FOREIGN KEY (CustomerId) REFERENCES Customer (CustomerId)
    on delete cascade;

ALTER TABLE InvoiceLine ADD CONSTRAINT FK_InvoiceLineInvoiceId
    FOREIGN KEY (InvoiceId) REFERENCES Invoice (InvoiceId) 
    on delete cascade;

delete from customer where firstname = 'Robert' and lastname = 'Walter';

SELECT customer.customerid, invoice.invoiceid
FROM customer 
INNER JOIN invoice
ON customer.customerid = invoice.customerid;

SELECT customer.customerid, customer.firstname, customer.lastname, invoice.invoiceid, invoice.total
FROM customer 
full OUTER JOIN invoice
ON customer.customerid = invoice.customerid;

SELECT artist.name, album.title
FROM artist
right OUTER JOIN album
ON artist.artistid = album.artistid

SELECT *
FROM artist
Cross JOIN album
order by artist.name asc

Select *
from employee a, employee b
where a.reportsto = b.reportsto;