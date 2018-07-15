Drop Table EmployeeType CASCADE CONSTRAINTS;
Drop Table Event CASCADE CONSTRAINTS;
Drop Table ReimbursementForm CASCADE CONSTRAINTS;
Drop Table Employee CASCADE CONSTRAINTS;
Drop Table Aprovel CASCADE CONSTRAINTS;
Drop Table EventType CASCADE CONSTRAINTS;
Drop Table Attachment CASCADE CONSTRAINTS;
-------------------------------------------------------------------------------

CREATE TABLE EmployeeType(
    Employee_Type_id NUMBER(6)PRIMARY KEY,
    Employee_Title VARCHAR2(20)NOT NULL
);
CREATE TABLE Aprovel(
    Aprovel_ID NUMBER(6)PRIMARY Key,
    Aproved_account VARCHAR2(20),
    Aprovel_One VARCHAR2(20),
    Aprovel_Two VARCHAR2(20),
    Aprovel_Three VARCHAR2(20),
    Total_Reimburstment NUMBER(6),
    Pending_Reimburstments NUMBER(6),
    Awarded_Reimburstments NUMBER(6),
    Available_Reimburstment NUMBER(6)
);
CREATE TABLE EventType(
    Event_Type_ID NUMBER(6)PRIMARY KEY,
    Event_Type VARCHAR2(100)NOT NULL,
    ReimburstmentPrecentage NUMBER(6)
);
CREATE TABLE Event(
    Event_Id NUMBER(6)PRIMARY KEY,
    Event_Type_ID NUMBER(6)NOT NULL,
    Event_Name VARCHAR2(20)NOT NULL,
    Event_Cost NUMBER(10),
    Event_Description VARCHAR2(100),
    Aprovel_ID NUMBER(6),
    CONSTRAINT FK_Aprovel FOREIGN KEY (Aprovel_ID)
	  REFERENCES Aprovel (Aprovel_ID),
    CONSTRAINT FK_EventType FOREIGN KEY (Event_Type_ID)
	  REFERENCES EventType (Event_Type_ID)
);
CREATE TABLE Attachment(
    Event_attach_Id NUMBER(6)PRIMARY KEY,
    Attachment_File BLOB
);
INSERT INTO Attachment VALUES(1,null);
CREATE TABLE ReimbursementForm (
    RF_ID NUMBER(6)PRIMARY KEY,
    Start_Date DATE,
    Forms_Date DATE,
    Grade_Cut_Off VARCHAR2(20) NOT NULL,
    Event_id NUMBER(6),
    Event_attach_Id NUMBER(6),
    CONSTRAINT FK_Event FOREIGN KEY (Event_id)
	  REFERENCES Event (Event_id),
    CONSTRAINT FK_Attach FOREIGN KEY (Event_attach_Id)
	  REFERENCES Attachment (Event_attach_Id)
);
CREATE TABLE Employee(
    Employee_Id NUMBER(6)PRIMARY KEY, 
    RF_ID NUMBER(6),
    Employee_Type_id NUMBER(6),
	Employee_Fname VARCHAR2(100)NOT NULL, 
	Employee_Lname VARCHAR2(100)NOT NULL,
    Employee_Password VARCHAR2(100)NOT NULL,
    Employee_Phone VARCHAR2(100)NOT NULL,
    Employee_Email VARCHAR2(100)NOT NUll,
    CONSTRAINT FK_Employee_Type FOREIGN KEY (Employee_Type_id)
	  REFERENCES EmployeeType (Employee_Type_id ),
    CONSTRAINT FK_Reimbursement_Form FOREIGN KEY (RF_ID)
	  REFERENCES ReimbursementForm (RF_ID )
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
------------------------------------------------------------------------------------
DROP SEQUENCE Event_seq;
CREATE SEQUENCE Event_seq
    START WITH 1
    INCREMENT BY 1; 
 / 
CREATE OR REPLACE TRIGGER Event_seq_Triger
BEFORE INSERT ON Event 
FOR EACH ROW
BEGIN 
    IF :new.Event_ID IS NULL THEN
        SELECT Event_seq.NEXTVAL INTO :new.Event_ID FROM dual;
    END IF;
END;
/
------------------------------------------------------------------------------------
DROP SEQUENCE Aprovel_seq;
CREATE SEQUENCE Aprovel_seq
    START WITH 500
    INCREMENT BY 1; 
 / 
CREATE OR REPLACE TRIGGER Aprovel_seq_Triger
BEFORE INSERT ON Aprovel 
FOR EACH ROW
BEGIN 
    IF :new.Aprovel_ID IS NULL THEN
        SELECT Aprovel_seq.NEXTVAL INTO :new.Aprovel_ID FROM dual;
    END IF;
END;
/
------------------------------------------------------------------------------------

INSERT INTO EMPLOYEETYPE VALUES(1,'Direct Supervisor');
INSERT INTO EMPLOYEETYPE VALUES(2,'Department Head');
INSERT INTO EMPLOYEETYPE VALUES(3,'Benefits Coordinator');
INSERT INTO EMPLOYEETYPE VALUES(4,'Employee');

SELECT * FROM EMPLOYEETYPE;

------------------------------------------------------------------------------------

INSERT INTO EVENTTYPE VALUES(1,'University Courses',80);
INSERT INTO EVENTTYPE VALUES(2,'Seminars',60);
INSERT INTO EVENTTYPE VALUES(3,'Certification Preparation Classes',80);
INSERT INTO EVENTTYPE VALUES(4,'Certification',100);
INSERT INTO EVENTTYPE VALUES(5,'Technical Training',90);
INSERT INTO EVENTTYPE VALUES(6,'Other',30);

SELECT * FROM EVENTTYPE;

---------------------------------------------------------------------------------------
/
CREATE OR REPLACE PROCEDURE insertIntoAprovel(AprovedAccount IN VARCHAR2,
                                              AprovelOne IN VARCHAR2,
                                              AprovelTwo IN VARCHAR2,
                                              AprovelThree IN VARCHAR2,
                                              TotalReimburstment IN NUMBER,
                                              PendingReimburstments IN NUMBER,
                                              AwardedReimburstments IN NUMBER,
                                              AvailableReimburstment IN NUMBER
                                              )
IS
BEGIN
    INSERT INTO Aprovel (Aproved_Account,Aprovel_One, Aprovel_Two, Aprovel_Three,Total_Reimburstment,Pending_Reimburstments,Awarded_Reimburstments,Available_Reimburstment)
    VALUES(AprovedAccount,AprovelOne, AprovelTwo, AprovelThree,TotalReimburstment,PendingReimburstments,AwardedReimburstments,AvailableReimburstment);
    COMMIT;
END;
/
CALL insertIntoAprovel('Yes','No','No','No',1000,0,0,1000);
Select * from Aprovel;
---------------------------------------------------------------------------------------
/
CREATE OR REPLACE PROCEDURE insertEvent(AprovelID IN NUMBER,
                                        EventTypeID IN NUMBER,
                                        EventName IN VARCHAR2,
                                        EventCost IN NUMBER,
                                        EventDescription VARCHAR2
                                        )
IS
BEGIN
    INSERT INTO EVENT (Aprovel_ID,Event_Type_ID, Event_Name, Event_Cost, Event_Description)
    VALUES(AprovelID,EventTypeID, EventName, EventCost,EventDescription);
    COMMIT;
END;
/
CALL insertEvent(500,2,'IEEE',50,null);
Select * from EVENT;
-------------------------------------------------------------------------
/
CREATE OR REPLACE PROCEDURE insertReimbursementForm(StartDate IN DATE,
                                                    FormsDate IN DATE,
                                                    GradeCutOff IN VARCHAR2,
                                                    EventID IN NUMBER,
                                                    EventAttachId IN NUMBER
                                                    )
IS
BEGIN
    INSERT INTO ReimbursementForm (Start_Date, Forms_Date, Grade_Cut_Off,Event_ID,Event_Attach_Id)
    VALUES(StartDate, FormsDate, GradeCutOff,EventID,EventAttachId);
    COMMIT;
END;
/
CALL insertReimbursementForm(TO_DATE('2018-10-01','YYYY-MM-DD'),TO_DATE('2018-07-07','YYYY-MM-DD'),'B',1,1);
Select * from ReimbursementForm;

---------------------------------------------------------------------------------------------------------------------------
/
CREATE OR REPLACE PROCEDURE insertEmployee (ReimbursementFormID IN NUMBER,
                                            EmployeeTypeid IN NUMBER,
                                            EmployeeFname IN VARCHAR2,
                                            EmployeeLname IN VARCHAR2,
                                            EmployeePassword IN VARCHAR2,
                                            EmployeePhone IN VARCHAR2,
                                            EmployeeEmail IN VARCHAR2
                                            )
IS
BEGIN
    INSERT INTO Employee (RF_ID,Employee_Type_id, Employee_Fname, Employee_Lname, Employee_Password, Employee_Phone, Employee_Email)
    VALUES(ReimbursementFormID,EmployeeTypeid, EmployeeFname, EmployeeLname, EmployeePassword, EmployeePhone, EmployeeEmail);
    COMMIT;
END;
/
CALL insertEmployee (100,1,'TEST','TEST','123','972-555-5533','diaz_zachary@test.com');
CALL insertEmployee (100,2,'TEST2','TEST2','456','972-555-5533','diaz_zachary@test.com');
CALL insertEmployee (100,3,'TEST3','TEST3','123','972-555-5533','diaz_zachary@test.com');
CALL insertEmployee (100,4,'TEST4','TEST4','123','972-555-5533','diaz_zachary@test.com');
SELECT * FROM Employee;
/

---------------------------------------------------------------------------------------------


SELECT EMPLOYEE.EMPLOYEE_ID,EMPLOYEE.EMPLOYEE_FNAME,EMPLOYEE.EMPLOYEE_LNAME,EMPLOYEE.EMPLOYEE_PHONE,EMPLOYEE.EMPLOYEE_EMAIL,
REIMBURSEMENTFORM.START_DATE,REIMBURSEMENTFORM.FORMS_DATE,REIMBURSEMENTFORM.GRADE_CUT_OFF,EVENT.EVENT_NAME,EVENT.EVENT_COST,
APROVEL.APROVEL_ONE,APROVEL.APROVEL_TWO,APROVEL.APROVEL_THREE
FROM EMPLOYEE
FULL OUTER JOIN REIMBURSEMENTFORM 
ON EMPLOYEE.RF_ID = REIMBURSEMENTFORM.RF_ID
FULL OUTER JOIN EVENT
ON REIMBURSEMENTFORM.EVENT_ID = EVENT.EVENT_ID
FULL OUTER JOIN APROVEL
ON EVENT.APROVEL_ID = APROVEL.APROVEL_ID;






commit;