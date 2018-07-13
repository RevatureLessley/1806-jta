
ALTER SESSION SET nls_date_format = 'mm/dd/yyyy hh24:mi:ss';

DROP TABLE Clearances CASCADE CONSTRAINTS ;
DROP TABLE Reimbursement_Requests CASCADE CONSTRAINTS ;
DROP TABLE Employees CASCADE CONSTRAINTS ;
DROP TABLE Reimbursement_Types CASCADE CONSTRAINTS ;

CREATE TABLE Reimbursement_Types (
    reimbursement_id number(1) PRIMARY KEY,
    reimbursement_type varchar2(35) NOT NULL,
    reimbursement_percent number(3) NOT NULL
);

CREATE TABLE Employees (
    employee_id number(6) PRIMARY KEY,
    first_name varchar2(100) NOT NULL,
    last_name varchar2(100) NOT NULL,
    password varchar2(100) NOT NULL,
    phone number(10),
    email varchar2(100),
    pending_reimbursement number(6,2) NOT NULL,
    awarded_reimbursement number(6,2) NOT NULL
);

CREATE TABLE Reimbursement_Requests (
    request_id number(6) PRIMARY KEY,
    employee_id number(6) NOT NULL,
    date_requested date NOT NULL,
    --time_requested varchar2(5) NOT NULL,
    location varchar2(100) NOT NULL,
    description varchar2(100) NOT NULL,
    reimbursement number(8,2) NOT NULL,
    grading_format varchar2(100) NOT NULL,
    event_type number(1) NOT NULL,
    is_approved number(1) NOT NULL,
    CONSTRAINT fk_employee_id FOREIGN KEY (employee_id) REFERENCES Employees (employee_id),
    CONSTRAINT fk_event_type FOREIGN KEY (event_type) REFERENCES Reimbursement_Types (reimbursement_id)
);

CREATE TABLE Clearances(
    employee_id number(6) PRIMARY KEY,
    dir_supervisor_id number(6) NOT NULL,
    dept_head_id number(6) NOT NULL,
    ben_co_id number(6) NOT NULL,
    CONSTRAINT fk2_employee_id FOREIGN KEY (employee_id) REFERENCES Employees (employee_id)
);
--Joins Employees and Clearances
DROP VIEW Complete_Employee ;
CREATE VIEW Complete_Employee AS
SELECT a.employee_id, first_name, last_name, phone, password, email, pending_reimbursement, awarded_reimbursement, dir_supervisor_id, dept_head_id, ben_co_id
FROM Employees a INNER JOIN Clearances b ON a.employee_id = b.employee_id ;

-- END TABLE CREATIONS

--Initialize Reimbursement values
INSERT INTO Reimbursement_Types VALUES (1,'University Courses', 80);
INSERT INTO Reimbursement_Types VALUES (2,'Seminars', 60);
INSERT INTO Reimbursement_Types VALUES (3,'Certification Preparation Classes', 75);
INSERT INTO Reimbursement_Types VALUES (4,'Certification', 100);
INSERT INTO Reimbursement_Types VALUES (5,'Technical Training', 90);
INSERT INTO Reimbursement_Types VALUES (6,'Other', 30);

--PL/SQL
DROP SEQUENCE Reimbursement_Id_Inc;
CREATE SEQUENCE Reimbursement_Id_Inc
    START WITH 4 INCREMENT BY 1    --start with 4 because 3 were made below (with server)
/

CREATE OR REPLACE TRIGGER On_Insert_Reimb
BEFORE INSERT ON Reimbursement_Requests
FOR EACH ROW
BEGIN
    IF :new.request_id IS NULL THEN
        SELECT Reimbursement_Id_Inc.NEXTVAL INTO :new.request_id FROM dual;
    END IF;
END;
/

DROP SEQUENCE Employee_Id_Inc;
CREATE SEQUENCE Employee_Id_Inc
    START WITH 6 INCREMENT BY 1    --start with 6 because 5 were made below (with server)
/
CREATE OR REPLACE TRIGGER On_Insert_Emp
BEFORE INSERT ON Employees
FOR EACH ROW
BEGIN
    IF :new.employee_id IS NULL THEN
        SELECT Employee_Id_Inc.NEXTVAL INTO :new.employee_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE Insert_Employee(employee_id IN number,
                                            f_name IN varchar2,
                                            l_name IN varchar2,
                                            password IN varchar2,
                                            phone IN varchar2,
                                            email IN varchar2,
                                            pending_reimbursements IN number,
                                            awarded_reimbursements IN number,
                                            supervisor_id IN number,
                                            dept_head_id IN number,
                                            ben_co_id IN number)
IS
BEGIN
    INSERT INTO Employees VALUES (employee_id, f_name, l_name, password, phone, email, pending_reimbursements, awarded_reimbursements);
    INSERT INTO Clearances VALUES (employee_id, supervisor_id, dept_head_id, ben_co_id);
END;
/

CREATE OR REPLACE PROCEDURE Delete_Employee(in_id IN number)
IS
BEGIN
    DELETE FROM Reimbursement_Requests WHERE employee_id = in_id;
    DELETE FROM Clearances WHERE employee_id = in_id;
    DELETE FROM Employees WHERE employee_id = in_id;
END;
/

--The following inserts are for default values
INSERT INTO Employees VALUES(5, 'Regular', 'Employee', 'donkey', 1111111111, 'emploreg@company.email', 102.23, 0);
INSERT INTO Employees VALUES(4, 'Super', 'Visor', 'koolaid', 2222222222, 'visorsup@company.email', 0, 127.39);
INSERT INTO Employees VALUES(3, 'Department', 'Head', 'fancypants', 3333333333, 'headdep@company.email', 0, 0);
INSERT INTO Employees VALUES(2, 'Benefits', 'Coordinator', 'sql', 4444444444, 'coordben@company.email', 0, 0);
INSERT INTO Employees VALUES(1, 'Benefits', 'Supervisor', 'headhoncho', 5555555555, 'superben@company.email', 0, 0);

INSERT INTO CLEARANCES VALUES(5, 4, 3, 2);
INSERT INTO CLEARANCES VALUES(4, 4, 3, 2);
INSERT INTO CLEARANCES VALUES(3, 3, 3, 2);
INSERT INTO CLEARANCES VALUES(2, 1, 1, 1);
INSERT INTO CLEARANCES VALUES(1, 1, 1, 1);

INSERT INTO REIMBURSEMENT_REQUESTS VALUES(1, 4, to_date('03/14/2005', 'mm/dd/yyyy'), 'Virginia', 'Boss Training', 127.39, 'Format?', 5, 1);
INSERT INTO REIMBURSEMENT_REQUESTS VALUES(2, 5, to_date('06/22/2017', 'mm/dd/yyyy'), 'Texas', 'Employee Training', 89.24, 'Format?', 5, 0);
INSERT INTO REIMBURSEMENT_REQUESTS VALUES(3, 5, to_date('08/15/2017', 'mm/dd/yyyy'), 'Texas', 'Employee Certification', 12.99, 'Format?', 4, 0);

COMMIT;

-- THE FOLLOWING FOR TESTING PURPOSES ONLY
--INSERT INTO Employees VALUES(1774, 'Dummy', 'Doofus', 'do', 1234567890, 'email@email.email', 0, 0);
--INSERT INTO Employees VALUES (2021, 'Abra', 'Kadabra', 'Alakazam', 5555555555, 'abra@kadabra.alakazam', 100, 200);
--INSERT INTO Reimbursement_Requests VALUES (1,1774,to_date('03/14/2017 13:30:34', 'mm/dd/yyyy hh24:mi:ss'),'Texas','Revature Training',10.11,'Format?', 5, 0);


SELECT  * FROM EMPLOYEES ;
SELECT * FROM REIMBURSEMENT_REQUESTS ;
SELECT * FROM CLEARANCES ;
SELECT * FROM Complete_Employee ;

SELECT * FROM REIMBURSEMENT_REQUESTS a
INNER JOIN EMPLOYEES b
ON a.EMPLOYEE_ID = b.EMPLOYEE_ID
WHERE a.EMPLOYEE_ID = 1774 ;

SELECT * FROM Complete_Employee a INNER JOIN Reimbursement_Requests b ON a.employee_id = b.employee_id WHERE b.request_id = 3 ;
