BEGIN
    DBMS_OUTPUT.put_line('2.1 SELECT');
    DBMS_OUTPUT.put_line('Select all records from the Employee table');
    DBMS_OUTPUT.put_line('SELECT * FROM EMPLOYEE;');
END;
/

SELECT *
FROM EMPLOYEE;

BEGIN
    DBMS_OUTPUT.put_line('Select all records from the Employee table where last name is King');
    DBMS_OUTPUT.put_line('SELECT * FROM EMPLOYEE WHERE LASTNAME = ''King'';');
END;
/

SELECT *
FROM EMPLOYEE
WHERE LASTNAME = 'King';

BEGIN
    DBMS_OUTPUT.put_line('Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL');
    DBMS_OUTPUT.put_line('SELECT * FROM EMPLOYEE WHERE FIRSTNAME = ''Andrew'' AND REPORTSTO IS NULL;');
END;
/

SELECT *
FROM EMPLOYEE
WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;

BEGIN
    DBMS_OUTPUT.put_line('2.2 ORDER BY');
    DBMS_OUTPUT.put_line('Select all albums in Album table and sort result set in descending order by title');
    DBMS_OUTPUT.put_line('SELECT * FROM ALBUM ORDER BY TITLE DESC;');
END;
/

SELECT *
FROM ALBUM
ORDER BY TITLE DESC;

BEGIN
    DBMS_OUTPUT.put_line('Select first name from Customer and sort result set in ascending order by city');
    DBMS_OUTPUT.put_line('SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY;');
END;
/

SELECT FIRSTNAME
FROM CUSTOMER
ORDER BY CITY;

BEGIN
    DBMS_OUTPUT.put_line('2.3 INSERT INTO');
    DBMS_OUTPUT.put_line('Insert two new records into Genre table');
    DBMS_OUTPUT.put_line('INSERT INTO GENRE  VALUES (26, ''Country'');');
    DBMS_OUTPUT.put_line('INSERT INTO GENRE  VALUES (27, ''Classical'');');
END;
/

SELECT * 
FROM GENRE;

INSERT INTO GENRE 
VALUES (26, 'Country');
INSERT INTO GENRE 
VALUES (27, 'Classical');

SELECT * 
FROM GENRE;

BEGIN
    DBMS_OUTPUT.put_line('Insert two new records into Employee table');
    DBMS_OUTPUT.put_line('INSERT INTO EMPLOYEE VALUES(9, ''Jin'', ''Helen'', ''IT Staff'', 6, ''22-DEC-60'', ''06-MAY-04'', ''23 Coachwood Rd W'', ''Lethbridge'', ''AB'', ''Canada'', ''T1K 6B7'', ''+1 (403) 322-8806'', ''+1 (403) 322-2107'', ''helen@chinookcorp.com'');');
    DBMS_OUTPUT.put_line('INSERT INTO EMPLOYEE VALUES(10, ''Qiao'', ''Harry'', ''IT Staff'', 6, ''06-JAN-55'', ''08-JUL-04'', ''505-533 10 St N'', ''Lethbridge'', ''AB'', ''Canada'', ''T1H 2C9'', ''+1 (403) 591-9733'', ''+1 (403) 591-5494'', ''harry@chinookcorp.com'');');
END;
/

SELECT * 
FROM EMPLOYEE;

INSERT INTO EMPLOYEE 
VALUES(9, 'Jin', 'Helen', 'IT Staff', 6, '22-DEC-60', '06-MAY-04', '23 Coachwood Rd W', 
       'Lethbridge', 'AB', 'Canada', 'T1K 6B7', '+1 (403) 322-8806', 
       '+1 (403) 322-2107', 'helen@chinookcorp.com');
INSERT INTO EMPLOYEE 
VALUES(10, 'Qiao', 'Harry', 'IT Staff', 6, '06-JAN-55', '08-JUL-04', '505-533 10 St N', 
       'Lethbridge', 'AB', 'Canada', 'T1H 2C9', '+1 (403) 591-9733', 
       '+1 (403) 591-5494', 'harry@chinookcorp.com');
       
SELECT * 
FROM EMPLOYEE;

BEGIN
    DBMS_OUTPUT.put_line('Insert two new records into Customer table');
    DBMS_OUTPUT.put_line('INSERT INTO CUSTOMER VALUES(60, ''Qun'', ''Xia'', ''ERS'', ''200 E 18th St'', ''Austin'', ''TX'', ''USA'', ''78701'', ''+1 (877) 275-4377'', ''+1 (877) 275-8802'', ''qxia123@yahoo.com'', 5);');
    DBMS_OUTPUT.put_line('INSERT INTO CUSTOMER VALUES(61, ''Samuel'', ''Zhao'', ''TWC'', ''101 E 15th St'', ''Austin'', ''TX'', ''USA'', ''78778'', ''+1 (512) 463-2222'', ''+1 (512) 463-3926'', ''samuelzhao71@yahoo.com'', 4);');
END;
/

SELECT *
FROM CUSTOMER;

INSERT INTO CUSTOMER 
VALUES(60, 'Qun', 'Xia', 'ERS', '200 E 18th St', 'Austin', 'TX', 'USA', 
       '78701', '+1 (877) 275-4377', '+1 (877) 275-8802', 'qxia123@yahoo.com',
       5);
INSERT INTO CUSTOMER 
VALUES(61, 'Samuel', 'Zhao', 'TWC', '101 E 15th St', 'Austin', 'TX', 'USA', 
       '78778', '+1 (512) 463-2222', '+1 (512) 463-3926', 
       'samuelzhao71@yahoo.com', 4);
       
SELECT *
FROM CUSTOMER;

BEGIN
    DBMS_OUTPUT.put_line('2.4 UPDATE');
    DBMS_OUTPUT.put_line('Update Aaron Mitchell in Customer table to Robert Walter');
    DBMS_OUTPUT.put_line('UPDATE CUSTOMER SET FIRSTNAME = ''Robert'', LASTNAME = ''Walter'' WHERE FIRSTNAME = ''Aaron'' AND LASTNAME = ''Mitchell'';');
END;
/

SELECT *
FROM CUSTOMER
WHERE CUSTOMERID = 32;

UPDATE CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';

SELECT *
FROM CUSTOMER
WHERE CUSTOMERID = 32;

BEGIN
    DBMS_OUTPUT.put_line('Update name of artist in the Artist table "Creedence Clearwater Revival" to "CCR"');
    DBMS_OUTPUT.put_line('UPDATE ARTIST SET NAME = ''CCR'' WHERE NAME = ''Creedence Clearwater Revival'';');
END;
/

SELECT *
FROM ARTIST
WHERE ARTISTID = 76;

UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';

SELECT *
FROM ARTIST
WHERE ARTISTID = 76;

BEGIN
    DBMS_OUTPUT.put_line('2.5 LIKE');
    DBMS_OUTPUT.put_line('Select all invoices with a billing address like "T%"');
    DBMS_OUTPUT.put_line('SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE ''T%'';');
END;
/

SELECT * 
FROM INVOICE
WHERE BILLINGADDRESS LIKE 'T%';

BEGIN
    DBMS_OUTPUT.put_line('2.6 BETWEEN');
    DBMS_OUTPUT.put_line('Select all invoices that have a total between 15 and 50');
    DBMS_OUTPUT.put_line('SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;');
END;
/

SELECT * 
FROM INVOICE
WHERE TOTAL BETWEEN 15 AND 50;

BEGIN
    DBMS_OUTPUT.put_line('Select all employees hired between 1st of June 2003 and 1st of March 2004');
    DBMS_OUTPUT.put_line('SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN ''01-JUN-03'' AND ''01-MAR-04'';');
END;
/

SELECT * 
FROM EMPLOYEE
WHERE HIREDATE BETWEEN '01-JUN-03' AND '01-MAR-04';

BEGIN
    DBMS_OUTPUT.put_line('2.7 DELETE');
    DBMS_OUTPUT.put_line('Delete a record in Customer table where the name is Robert Walter');
    DBMS_OUTPUT.put_line('ALTER TABLE INVOICE DROP CONSTRAINT FK_INVOICECUSTOMERID;');
    DBMS_OUTPUT.put_line('ALTER TABLE INVOICE ADD CONSTRAINT FK_INVOICECUSTOMERID FOREIGN KEY (CUSTOMERID) REFERENCES INVOICE (INVOICEID) ON DELETE CASCADE;');
    DBMS_OUTPUT.put_line('DELETE FROM CUSTOMER WHERE FIRSTNAME = ''Robert'' AND LASTNAME = ''Walter'';');
END;
/

SELECT * 
FROM CUSTOMER;

ALTER TABLE INVOICE
DROP CONSTRAINT FK_INVOICECUSTOMERID;

ALTER TABLE INVOICE
ADD CONSTRAINT FK_INVOICECUSTOMERID
FOREIGN KEY (CUSTOMERID)
REFERENCES INVOICE (INVOICEID)
ON DELETE CASCADE;

DELETE FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

SELECT * 
FROM CUSTOMER;

BEGIN
    DBMS_OUTPUT.put_line('7.0 JOINS');
    DBMS_OUTPUT.put_line('7.1 INNER');
    DBMS_OUTPUT.put_line('Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId');
    DBMS_OUTPUT.put_line('SELECT INVOICEID, FIRSTNAME, LASTNAME FROM CUSTOMER  INNER JOIN INVOICE  ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;');
END;
/

SELECT INVOICEID, FIRSTNAME, LASTNAME
FROM CUSTOMER 
INNER JOIN INVOICE 
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

BEGIN
    DBMS_OUTPUT.put_line('7.2 OUTER');
    DBMS_OUTPUT.put_line('Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total');
    DBMS_OUTPUT.put_line('SELECT INVOICE.CUSTOMERID AS "CUSTOMERID", INVOICEID, FIRSTNAME, LASTNAME, TOTAL FROM CUSTOMER FULL OUTER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;');
END;
/

SELECT INVOICE.CUSTOMERID AS "CUSTOMERID", 
       INVOICEID, FIRSTNAME, LASTNAME, TOTAL
FROM CUSTOMER 
FULL OUTER JOIN INVOICE 
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

BEGIN
    DBMS_OUTPUT.put_line('7.3 RIGHT');
    DBMS_OUTPUT.put_line('Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total');
    DBMS_OUTPUT.put_line('SELECT NAME AS "ARTIST ANME", TITLE FROM ALBUM  RIGHT JOIN ARTIST ON ALBUM.ARTISTID = ARTIST.ARTISTID;');
END;
/

SELECT NAME AS "ARTIST ANME", TITLE
FROM ALBUM 
RIGHT JOIN ARTIST 
ON ALBUM.ARTISTID = ARTIST.ARTISTID;

BEGIN
    DBMS_OUTPUT.put_line('7.4 CROSS');
    DBMS_OUTPUT.put_line('Create a cross join that joins album and artist and sorts by artist name in ascending order');
    DBMS_OUTPUT.put_line('SELECT ALBUMID, TITLE, ALBUM.ARTISTID, ARTIST.ARTISTID, NAME AS "ARTIST NAME" FROM ALBUM CROSS JOIN ARTIST ORDER BY NAME;');
END;
/

SELECT ALBUMID, TITLE, ALBUM.ARTISTID, ARTIST.ARTISTID, NAME AS "ARTIST NAME"
FROM ALBUM 
CROSS JOIN ARTIST 
ORDER BY NAME;

BEGIN
    DBMS_OUTPUT.put_line('7.5 SELF');
    DBMS_OUTPUT.put_line('Perform a self-join on the employee table, joining on the reportsto column');
    DBMS_OUTPUT.put_line('SELECT L.EMPLOYEEID, L.FIRSTNAME, L.LASTNAME, L.TITLE, L.BIRTHDATE, L.HIREDATE, L.ADDRESS, L.CITY, L.STATE, L.COUNTRY, L.POSTALCODE, L.PHONE, L.FAX, L.EMAIL, L.REPORTSTO, R.EMPLOYEEID AS "SUPERVISORID", R.FIRSTNAME AS "SUPERVISOR FIRSTNAME", R.LASTNAME AS "SUPERVISOR LASTNAME", R.TITLE AS "SUPERVISOR TITLE", R.BIRTHDATE AS "SUPERVISOR BIRTHDATE", R.HIREDATE AS "SUPERVISOR HIREDATE", R.ADDRESS AS "SUPERVISOR ADDRESS", R.CITY AS "SUPERVISOR CITY", R.STATE AS "SUPERVISOR STATE", R.COUNTRY AS "SUPERVISOR COUNTRY", R.POSTALCODE AS "SUPERVISOR POSTALCODE", R.PHONE AS "SUPERVISOR PHONE", R.FAX AS "SUPERVISOR FAX", R.EMAIL AS "SUPERVISORID EMAIL" FROM EMPLOYEE L INNER JOIN EMPLOYEE R ON L.REPORTSTO = R.EMPLOYEEID;');
END;
/

SELECT L.EMPLOYEEID, L.FIRSTNAME, L.LASTNAME, L.TITLE, L.BIRTHDATE, L.HIREDATE,
       L.ADDRESS, L.CITY, L.STATE, L.COUNTRY, L.POSTALCODE, L.PHONE, L.FAX,
       L.EMAIL, L.REPORTSTO, R.EMPLOYEEID AS "SUPERVISORID", 
       R.FIRSTNAME AS "SUPERVISOR FIRSTNAME",  
       R.LASTNAME AS "SUPERVISOR LASTNAME", R.TITLE AS "SUPERVISOR TITLE",
       R.BIRTHDATE AS "SUPERVISOR BIRTHDATE", 
       R.HIREDATE AS "SUPERVISOR HIREDATE", R.ADDRESS AS "SUPERVISOR ADDRESS",
       R.CITY AS "SUPERVISOR CITY", R.STATE AS "SUPERVISOR STATE",
       R.COUNTRY AS "SUPERVISOR COUNTRY", 
       R.POSTALCODE AS "SUPERVISOR POSTALCODE", R.PHONE AS "SUPERVISOR PHONE",
       R.FAX AS "SUPERVISOR FAX", R.EMAIL AS "SUPERVISORID EMAIL"
FROM EMPLOYEE L
INNER JOIN EMPLOYEE R
ON L.REPORTSTO = R.EMPLOYEEID;