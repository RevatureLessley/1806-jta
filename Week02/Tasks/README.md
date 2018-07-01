-- SELECT
-- select all records from the Employee table
```
select * from employee;
```

-- select all records from the Employee table where last name is King.
```
select * from employee where lastname = 'King';
```

-- select all records from the employee table where first name is Andrew and REPORTSO is NULL
```
select * from employee where firstname = 'Andrew' and REPORTSTO is null;
```

-- ORDER BY
-- select all albums in Album table and sort result set in descending order by title
```
select * from album order by title desc;
```
-- select all first names from customer and sort result set in ascending order by city
```
select firstname from customer order by city asc;
```

```
select count(*) from genre;
```

-- INSERT INTO
-- insert two new records into genre table

```
select count(*) from genre;
```

```
select count(*) from customer;
```

```
select count(*) from employee;
```

[StackOverflow covering IF EXISTS for multiple objects](https://stackoverflow.com/questions/1799128/oracle-if-table-exists)
```
begin
    execute immediate 'drop sequence ' || genre_sequence;
exception
    when others then
        if sqlcode != -2289 then
            raise;
        end if;
end;
```

-- drop sequence genre_sequence;
```
create sequence genre_sequence
start with 26
increment by 1;
```

```
create or replace trigger genre_seq_trigger
before insert on genre
for each row
begin
    if :new.genreid is null then
        select genre_sequence.NEXTVAL into :new.genreid from dual;
    end if;
end;
```

```
select * from genre where name = 'House Music';
```

```
select * from genre where name = 'Country';
```

```
insert into genre values (null, 'House Music');
```

```
insert into genre values (null, 'Country');
```

-- insert two records into employee table
```
create sequence employee_sequence
start with 60
increment by 1;
```

```
create or replace trigger employee_seq_trigger
before insert on employee
for each row
begin 
    if :new.employeeid is null then
    select employee_sequence.NEXTVAL into :new.employeeid from dual;
    end if;
end;
```

```
select * from employee;
```

```
insert into employee
values (null, 'Edwards', 'Nathan', 'Software Engineer', 2, DATE '1993-05-05', DATE '2018-06-27', 'That Address', 'This city', 'VT', 'United States', '55555', null, null, null);
update employee
set phone = '+1 (403) 342 - 3423', fax = '+1 (403) 262-3322', email = 'nathan@chinookcorp.com'
where employeeid = 60;
```

```
insert into employee
values (null, 'Smith', 'Alexa', 'Aeronautical Engineer', 1, DATE '1985-03-29', DATE '2018-05-18', 'This Address', 'That city', 'CA', 'United States', '11111', '+1 (403) 485-2967', '+1 (403) 467-8772', 'alexandra@chinookcorp.com');
```

-- insert two new records in customer table
```
create sequence customer_sequence
start with 8
increment by 1;
```

```
create or replace trigger customer_seq_trigger
before insert on customer
for each row
begin
    if :new.customerid is null then
    select customer_sequence.NEXTVAL into :new.customerid from dual;
    end if;
end;
```

-- UPDATE
-- update Aaron Mitchell in customer table to Robert Walter
```
update customer
set firstname = 'Robert', lastname = 'Walter'
where firstname = 'Aaron' and lastname = 'Mitchell';
```

-- update name of artist in the Artist table "creedence clearwater revival" to "CCR"
update artist
```
set name = 'CCR'
where name = 'creedence clearwater revival';
```

```
select * from artist where name like '%clear%' ;
```

-- LIKE
-- select all invoices with a billing address like "T%"
```
select * from invoice where billingaddress like'T%';
```

-- BETWEEN
-- select all invoices that have a total between 15 and 50
```
select * from invoice where total between 15 and 30;
```

```
select * from invoice where total >= 15 and total <= 30;
```

-- select all employees hired between the 1st of June 2003 and 1st of March 2004
```
select * from employee where hiredate between '01-JUN-03' and '01-MAR-04';
```

-- DELETE
-- delete a record in customer table where the names is Robert Walter 
```
select * from customer where firstname = 'Robert' and lastname = 'Walter';
```

```
select * from invoice where customerid = (select customerid from customer where firstname = 'Robert' and lastname = 'Walter');
```

```
select * from invoiceline join (select invoiceid from invoice where customerid = (select customerid from customer where firstname = 'Robert' and lastname = 'Walter')) sub_invoiceids on invoiceline.invoiceid = sub_invoiceids.invoiceid;
```

```
delete from (select * from invoiceline join (select invoiceid from invoice where customerid = (select customerid from customer where firstname = 'Robert' and lastname = 'Walter')) sub_invoiceids on invoiceline.invoiceid = sub_invoiceids.invoiceid);
```

```
delete from (select * from invoice where customerid = (select customerid from customer where firstname = 'Robert' and lastname = 'Walter'));
delete from customer where firstname = 'Robert' and lastname = 'Walter';
```

-- INNER JOIN
-- create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId
```
select firstname || ' ' || lastname as name, invoiceId from customer
inner join invoice
on customer.customerId = invoice.customerId;
```

-- OUTER JOIN
-- create an outer join that joins the customer and invoice table and specifies the CustomerId, firstname, lastname, invoiceId, and total.
```
select c.customerId, firstname, lastname, invoiceId, total
from customer c
full outer join invoice i
on c.customerId = i.customerId;
```

-- RIGHT JOIN
-- create a right join that joins album and artist specifying artist name and title.
```
select name, title
from artist
right join album
on album.artistId = artist.artistId;
```

-- CROSS JOIN
-- create a cross join that joins album and artist and sorts by artst name in ascending order.
select *
```
from artist
cross join album
order by name asc;
```

-- SELF JOIN
-- perform a self-join on the employee table, joining on the reportsto column.
```
select *
from employee tableA
full outer join employee tableB
on tableA.reportsto = tableB.reportsto;
```
