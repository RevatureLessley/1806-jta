--========2.1 SELECT========
Select * FROM Employee;

SELECT * FROM EMPLOYEE
WHERE LASTNAME = 'King';

SELECT * FROM EMPLOYEE
WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;

--========2.2 ORDER BY========
SELECT * FROM ALBUM a
ORDER BY  a.title DESC;

SELECT FIRSTNAME FROM Customer
ORDER BY Customer.city;

--========2.3 INSERT INTO========
INSERT INTO GENRE VALUES(26,'Indie');
INSERT INTO GENRE VALUES(27,'Chiptune');

INSERT INTO EMPLOYEE VALUES(9,'Bobsmith','Bobbert','Bobber',8,'01-JAN-11','01-FEB-12',
                            '111 Bob Boulevard','Bobsville','AB','CANADA','T1B 1B1',
                            '+1 (111)111-1111','+1 (222)222-2222','bobbert@chincookcorp.com');
INSERT INTO EMPLOYEE VALUES(10,'Cobsmith','Cobbert','Cobber',8,'02-JAN-22','02-FEB-22',
                            '111 Cob Boulevard','Cobsville','AB','CANADA','T1C 1C1',
                            '+1 (333)333-3333','+1 (444)444-4444','cobbert@chincookcorp.com');


INSERT INTO Customer VALUES(60,'Dobbert','Dobsmith',null,'111 Dob Boulevard','Los Angelos','CA',
                            'USA','90001','+1 (555)555-5555',null,'dobbert@chincookcorp.com',2);
INSERT INTO Customer VALUES(61,'Fobbert','Fobsmith',null,'111 Fob Boulevard','Los Angelos','CA',
                            'USA','90001','+1 (666)666-6666',null,'fobbert@chincookcorp.com',4);

--========2.4 UPDATE========
UPDATE Customer
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE CUSTOMERID = 32;

UPDATE Artist a
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';

--========2.5 LIKE========
SELECT * FROM Invoice
WHERE BILLINGADDRESS LIKE 'T%';

--========2.6 BETWEEN========
SELECT * FROM Invoice
WHERE TOTAL BETWEEN 15 AND 50;

SELECT * FROM Employee
WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04';

--========2.7 DELETE========
ALTER TABLE Invoice
    DROP CONSTRAINT FK_INVOICECUSTOMERID;
    
ALTER TABLE Invoice
    ADD CONSTRAINT FK_INVOICECUSTOMERID
    FOREIGN KEY (CUSTOMERID)
    REFERENCES Customer(CUSTOMERID)
    ON DELETE CASCADE;
    
ALTER TABLE InvoiceLine
    DROP CONSTRAINT FK_INVOICELINEINVOICEID;
    
ALTER TABLE InvoiceLine
    ADD CONSTRAINT FK_INVOICELINEINVOICEID
    FOREIGN KEY (INVOICEID)
    REFERENCES Invoice(INVOICEID)
    ON DELETE CASCADE;
    
DELETE
FROM Customer
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';
commit;

--========7.0 JOINS========

--========7.1 INNER========
SELECT c.FIRSTNAME,c.LASTNAME,i.INVOICEID
FROM Customer c
INNER JOIN Invoice i
ON c.CUSTOMERID = i.CUSTOMERID;

--========7.2 OUTER========
SELECT c.CUSTOMERID,c.FIRSTNAME,c.LASTNAME,i.INVOICEID,i.TOTAL
FROM Customer c
FULL OUTER JOIN Invoice i
ON c.CUSTOMERID = i.CUSTOMERID;

--========7.3 RIGHT========
SELECT ar.name,al.title
FROM Album al
RIGHT JOIN Artist ar
ON al.ARTISTID = ar.ARTISTID;

--========7.4 CROSS========
SELECT * FROM Artist
CROSS JOIN Album
ORDER BY Artist.NAME;

--========7.5 SELF========
SELECT a.EMPLOYEEID AS "EMP_ID",a.FIRSTNAME AS "EMP_FNAME",a.LASTNAME AS "EMP_LNAME",
s.EMPLOYEEID AS "SUP_ID",s.FIRSTNAME AS "SUP_FNAME",s.LASTNAME AS "SUP_LNAME"
FROM Employee a,Employee s
WHERE a.REPORTSTO = s.EMPLOYEEID;
commit;