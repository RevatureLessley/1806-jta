
ALTER SESSION SET nls_date_format = 'mm/dd/yyyy hh24:mi:ss';

DROP TABLE Pending_Notifications CASCADE CONSTRAINTS ;
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
    email varchar2(100) UNIQUE,
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
    status number(1) NOT NULL,
    optional_file clob,
    file_name varchar2(100),
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

CREATE TABLE Pending_Notifications(
    notification_id number(6) PRIMARY KEY,
    request_id number(6) NOT NULL UNIQUE,
    employee_id number(6) NOT NULL,
    is_at_supervisor number(1) NOT NULL,
    is_at_dept_head number(1) NOT NULL,
    is_at_ben_co number(1) NOT NULL,
    approval_count number(1) NOT NULL,
    information varchar2(1000),
    CONSTRAINT fk_request_id FOREIGN KEY (request_id) REFERENCES Reimbursement_Requests (request_id),
    CONSTRAINT fk3_employee_id FOREIGN KEY (employee_id) REFERENCES Employees (employee_id)
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

DROP SEQUENCE Notification_Id_Inc;
CREATE SEQUENCE Notification_Id_Inc
    START WITH 3 INCREMENT BY 1    --start with 3 because 2 were made below (with server)
/
CREATE OR REPLACE TRIGGER On_Insert_Notif
BEFORE INSERT ON Pending_Notifications
FOR EACH ROW
BEGIN
    IF :new.notification_id IS NULL THEN
        SELECT Notification_Id_Inc.NEXTVAL INTO :new.notification_id FROM dual;
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

CREATE OR REPLACE TRIGGER On_Delete_Reimb
BEFORE DELETE ON Reimbursement_Requests
FOR EACH ROW
BEGIN
    DELETE FROM Pending_Notifications WHERE request_id = Pending_Notifications.request_id;
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

CREATE OR REPLACE PROCEDURE Insert_Reimbursement_Request(request_id IN number,
                                                         employee_id IN number,
                                                         date_request IN date,
                                                         location IN varchar2,
                                                         description IN varchar2,
                                                         amount IN number,
                                                         format IN varchar2,
                                                         event_type IN number,
                                                         status IN number,
                                                         opt_file IN clob,
                                                         file_name IN varchar2,
                                                         notification_id IN number,
                                                         supervisor_id IN number,
                                                         information IN varchar2)
IS
BEGIN
    INSERT INTO Reimbursement_Requests VALUES (request_id, employee_id, date_request, location, description, amount, format, event_type, status, opt_file, file_name);
    INSERT INTO Pending_Notifications VALUES (notification_id, Reimbursement_Id_Inc.CURRVAL, supervisor_id, 1,0,0,0, information);
END;
/

--The following inserts are for default values
INSERT INTO Employees VALUES(5, 'Eric', 'Sundberg', 'password', 1111111111, 'sundbeed@company.email', 102.23, 0);
INSERT INTO Employees VALUES(4, 'Super', 'Visor', 'koolaid', 2222222222, 'visorsup@company.email', 0, 127.39);
INSERT INTO Employees VALUES(3, 'Department', 'Head', 'fancypants', 3333333333, 'headdep@company.email', 0, 0);
INSERT INTO Employees VALUES(2, 'Benefits', 'Coordinator', 'sql', 4444444444, 'coordben@company.email', 0, 0);
INSERT INTO Employees VALUES(1, 'Benefits', 'Supervisor', 'headhoncho', 5555555555, 'superben@company.email', 0, 0);

INSERT INTO CLEARANCES VALUES(5, 4, 3, 2);
INSERT INTO CLEARANCES VALUES(4, 4, 3, 2);
INSERT INTO CLEARANCES VALUES(3, 3, 3, 2);
INSERT INTO CLEARANCES VALUES(2, 1, 1, 1);
INSERT INTO CLEARANCES VALUES(1, 1, 1, 1);

--INSERT INTO REIMBURSEMENT_REQUESTS VALUES(1, 4, to_date('03/14/2005', 'mm/dd/yyyy'), 'Virginia', 'Boss Training', 127.39, 'Format?', 5, 1, '', '');
--INSERT INTO REIMBURSEMENT_REQUESTS VALUES(2, 5, to_date('06/22/2017', 'mm/dd/yyyy'), 'Texas', 'Employee Training', 89.24, 'Format?', 5, 0, '', '');
--INSERT INTO REIMBURSEMENT_REQUESTS VALUES(3, 5, to_date('08/15/2017', 'mm/dd/yyyy'), 'Texas', 'Employee Certification', 12.99, 'Format?', 4, 0, '', '');

COMMIT;

-- THE FOLLOWING FOR TESTING PURPOSES ONLY
--INSERT INTO PENDING_NOTIFICATIONS VALUES (1, 2, 4, 1, 0, 0) ;
--INSERT INTO PENDING_NOTIFICATIONS VALUES (2, 3, 4, 1, 0, 0) ;


SELECT * FROM EMPLOYEES ;
SELECT * FROM REIMBURSEMENT_REQUESTS ;
SELECT * FROM CLEARANCES ;
SELECT * FROM Complete_Employee ;
SELECT * FROM REIMBURSEMENT_TYPES ;
SELECT * FROM PENDING_NOTIFICATIONS ;

SELECT * FROM REIMBURSEMENT_REQUESTS a
INNER JOIN EMPLOYEES b
ON a.EMPLOYEE_ID = b.EMPLOYEE_ID
WHERE a.EMPLOYEE_ID = 5 ;

SELECT * FROM Complete_Employee a INNER JOIN Reimbursement_Requests b ON a.employee_id = b.employee_id WHERE b.request_id = 3 ;
