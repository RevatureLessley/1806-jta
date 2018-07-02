--1.0	Setting up Oracle Chinook
----In this section you will begin the process of working with the Oracle Chinook database
----Task – Open the Chinook_Oracle.sql file and execute the scripts within.

--2.0 SQL Queries
----In this section you will be performing various queries against the Oracle Chinook database.

--2.1 SELECT
----Task – Select all records from the Employee table.
SELECT *
FROM employee;
----Task – Select all records from the Employee table where last name is King.
SELECT *
FROM employee
WHERE lastname='King';
----Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT *
FROM employee
WHERE firstname='Andrew' AND reportsto IS NULL;

--2.2 ORDER BY
----Task – Select all albums in Album table and sort result set in descending order by title.
SELECT *
FROM album
ORDER BY title DESC;
----Task – Select first name from Customer and sort result set in ascending order by city
SELECT firstname
FROM customer
ORDER BY city ASC;

--2.3 INSERT INTO
----Task – Insert two new records into Genre table

DROP SEQUENCE genre_seq;
CREATE SEQUENCE genre_seq
    START WITH 26
    INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER genre_seq_trigger
BEFORE INSERT ON genre --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.genreid IS NULL THEN
        SELECT genre_seq.NEXTVAL INTO :new.genreid FROM dual;
    END IF;
END;
/
commit;
INSERT INTO genre(name)
VALUES('rap');
INSERT INTO genre(name)
VALUES('reggaeton');
SELECT * FROM genre;
rollback;
----Task – Insert two new records into Employee table
DROP SEQUENCE employee_seq;
CREATE SEQUENCE employee_seq
    START WITH 9
    INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER employee_seq_trigger
BEFORE INSERT ON employee --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.employeeid IS NULL THEN
        SELECT employee_seq.NEXTVAL INTO :new.employeeid FROM dual;
    END IF;
END;
/
commit;
INSERT INTO employee(firstname, lastname)
VALUES('Leo', 'Garcon');
INSERT INTO employee(firstname, lastname)
VALUES('Ray', 'Rodriguez');
SELECT * FROM employee;
rollback;
----Task – Insert two new records into Customer table
DROP SEQUENCE customer_seq;
CREATE SEQUENCE customer_seq
    START WITH 60
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER customer_seq_trigger
BEFORE INSERT ON customer --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.customerid IS NULL THEN
        SELECT customer_seq.NEXTVAL INTO :new.customerid FROM dual;
    END IF;
END;
/
commit;
INSERT INTO customer(firstname, lastname, email)
VALUES('Marshall', 'Mathers', 'marshal@email.com');
INSERT INTO customer(firstname, lastname, email)
VALUES('Slim', 'Shady', 'slim@email.com');
SELECT * FROM customer;
rollback;
--2.4 UPDATE
----Task – Update Aaron Mitchell in Customer table to Robert Walter
commit;
UPDATE customer
SET firstname='Robert', lastname='Walter'
WHERE firstname='Aaron' AND lastname='Mitchell';

SELECT firstname, lastname
FROM customer
WHERE firstname='Robert' AND lastname='Walter';
rollback;
----Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
commit;
UPDATE artist
SET name='CCR'
WHERE name='Creedence Clearwater Revival';

SELECT name
FROM artist
WHERE name='CCR';

--2.5 LIKE
----Task – Select all invoices with a billing address like “T%”
SELECT * FROM invoice
WHERE billingaddress LIKE 'T%';

--2.6 BETWEEN
----Task – Select all invoices that have a total between 15 and 50
SELECT * FROM invoice
WHERE total BETWEEN 15 AND 50;
----Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM employee
WHERE hiredate BETWEEN '01-JUN-03' AND '01-MAR-04';

--2.7 DELETE
----Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
INSERT INTO customer (firstname, lastname, email)
VALUES('Robert', 'Walter', 'robert@email.com');

DELETE FROM customer
WHERE firstname='Robert' AND lastname='Walter';

rollback;

--7.0 JOINS
----In this section you will be working with combing various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
--7.1 INNER
----Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT customer.firstname, customer.lastname, invoice.invoiceid
FROM invoice
INNER JOIN customer ON invoice.customerid = customer.customerid;
--7.2 OUTER
----Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT customer.customerid, customer.firstname, customer.lastname, invoice.invoiceid, invoice.total
FROM invoice
FULL OUTER JOIN customer ON invoice.customerid = customer.customerid;
--7.3 RIGHT
----Task – Create a right join that joins album and artist specifying artist name and title.
SELECT artist.name, album.title
FROM album
RIGHT JOIN artist ON album.artistid = artist.artistid;
--7.4 CROSS
----Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT * FROM album
CROSS JOIN artist
ORDER BY artist.name;
--7.5 SELF
----Task – Perform a self-join on the employee table, joining on the reportsto column.
SELECT *
FROM employee e1, employee e2
WHERE e1.reportsto=e2.reportsto;