Drop Table ReimbursementForm CASCADE CONSTRAINTS;
Drop Table Employee CASCADE CONSTRAINTS;
Drop Table Attachment CASCADE CONSTRAINTS;
-------------------------------------------------------------------------------

CREATE TABLE Employee(
    Employee_Id NUMBER(10)PRIMARY KEY,
	Employee_Fname VARCHAR2(100)NOT NULL, 
	Employee_Lname VARCHAR2(100)NOT NULL,
    Employee_UserName VARCHAR2(100)NOT NULL,
    Employee_Password VARCHAR2(100)NOT NULL,
    Employee_Phone VARCHAR2(100)NOT NULL,
    Employee_Email VARCHAR2(100)NOT NUll,
    Employee_Dept VARCHAR2(100)NOT NUll,
    Employee_Role VARCHAR2(100),
    Employee_Role2 VARCHAR2(100),
    Employee_Form_ID NUMBER(10),
    Available_Reimburstment NUMBER(10)
);
--select * from Employee;
-------------------------------------------------------

CREATE TABLE ReimbursementForm (
    Rf_Id NUMBER(6)PRIMARY KEY,
    Employee_Id NUMBER(10),
    Form_Status VARCHAR2(100) NOT NULL,
    Event_Type VARCHAR2(100) NOT NULL,
    Event_Location VARCHAR2(100) NOT NULL,
    Event_Description VARCHAR2(100),
    Event_Cost NUMBER(10),
    Forms_Date TIMESTAMP,
    Start_Date TIMESTAMP,
    Start_Time TIMESTAMP,
    Grade_Format VARCHAR2(20) NOT NULL,
    Grade_Cut_Off VARCHAR2(20) NOT NULL,
    Work_Time_Missed VARCHAR2(100) NOT NULL,
    CONSTRAINT FK_ReimbursementForm FOREIGN KEY (Employee_Id)
	  REFERENCES Employee (Employee_Id)
);
--select  * from ReimbursementForm;
-------------------------------------------------------------------
CREATE TABLE Attachment(
    Rf_Id NUMBER(6),
    Attachment_File BLOB,
    Attachment_File2 CLOB,
    CONSTRAINT FK_Attachment FOREIGN KEY (Rf_Id)
	  REFERENCES ReimbursementForm (Rf_Id)
);
----------------------------------------------------------------------------------
/
DROP SEQUENCE Employee_seq;
CREATE SEQUENCE Employee_seq
    START WITH 100001
    INCREMENT BY 1; 
 / 
CREATE OR REPLACE TRIGGER Employee_seq_Triger
BEFORE INSERT ON Employee 
FOR EACH ROW
BEGIN 
    IF :new.Employee_Id IS NULL THEN
        SELECT Employee_seq.NEXTVAL INTO :new.Employee_Id FROM dual;
    END IF;
END;
/

---------------------------------------------------------------------------------

DROP SEQUENCE Reimbursement_Form_seq;
CREATE SEQUENCE Reimbursement_Form_seq
    START WITH 100
    INCREMENT BY 1; 
 / 
CREATE OR REPLACE TRIGGER Reimbursement_Form_seq_Triger
BEFORE INSERT ON ReimbursementForm 
FOR EACH ROW
BEGIN 
    IF :new.RF_ID IS NULL THEN
        SELECT Reimbursement_Form_seq.NEXTVAL INTO :new.RF_ID FROM dual;
    END IF;
END;
/
---------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE InsertEmployee (
                                            EmployeeFname IN VARCHAR2, 
                                            EmployeeLname IN VARCHAR2,
                                            EmployeeUserName IN VARCHAR2,
                                            EmployeePassword IN VARCHAR2,
                                            EmployeePhone IN VARCHAR2,
                                            EmployeeEmail IN VARCHAR2,
                                            EmployeeDept IN VARCHAR2,
                                            EmployeeRole IN VARCHAR2,
                                            EmployeeRole2 IN VARCHAR2,
                                           
                                            AvailableReimburstment IN NUMBER
                                            )
IS
BEGIN
    INSERT INTO Employee (Employee_Fname, Employee_Lname, Employee_UserName, Employee_Password, Employee_Phone, Employee_Email, Employee_Dept, Employee_Role, Employee_Role2, Available_Reimburstment)
    VALUES(EmployeeFname, EmployeeLname, EmployeeUserName, EmployeePassword, EmployeePhone, EmployeeEmail, EmployeeDept, EmployeeRole, EmployeeRole2, AvailableReimburstment);
    COMMIT;
END;
/
CALL InsertEmployee ('Zack', 'Diaz', 'ZackDiaz', 'ZackDiaz', '999-999-9999', 'Test@test.com', 'Hr', 'Direct Supper', 'Department Head',  1000);
CALL InsertEmployee ('Tony', 'Diaz', 'TonyDiaz', 'TonyDiaz', '999-999-9999', 'Test@test.com', 'Hr', 'Benefit Coordinator', null,  1000);

CALL InsertEmployee ('Mike', 'Diaz', 'MilkDiaz', 'MilkDiaz', '999-999-9999', 'Test@test.com', 'Math', 'Direct Supper', null,  1000);
CALL InsertEmployee ('Jhon', 'Do', 'JhonDo', 'JhonDo', '999-999-9999', 'Test@test.com', 'Math', 'Department Head', null, null, 1000);
CALL InsertEmployee ('Watter', 'Dog', 'WaterDog', 'WaterDog', '999-999-9999', 'Test@test.com', 'Math', 'Benefit Coordinator', null,  1000);

CALL InsertEmployee ('Tree', 'Frog', 'TreeFrog', 'TreeFrog', '999-999-9999', 'Test@test.com', 'History', 'Direct Supper', null,  1000);
CALL InsertEmployee ('Tree', 'Dog', 'TreeDog', 'TreeDog', '999-999-9999', 'Test@test.com', 'History', 'Department Head', null,  1000);
CALL InsertEmployee ('Hot', 'Dog', 'HotDog', 'HotDog', '999-999-9999', 'Test@test.com', 'History', 'Benefit Coordinator', null,  1000);


SELECT * FROM Employee;
/
-----------------------------------------------------------------------------------------------


SELECT * FROM EMPLOYEE;

commit;