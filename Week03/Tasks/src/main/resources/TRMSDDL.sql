DROP TABLE Approval 
CASCADE CONSTRAINTS;
DROP TABLE Approval_Additional_Info 
CASCADE CONSTRAINTS;
DROP TABLE Approval_Attachment 
CASCADE CONSTRAINTS;
DROP TABLE Approval_Type 
CASCADE CONSTRAINTS;
DROP TABLE Department 
CASCADE CONSTRAINTS;
DROP TABLE Employee 
CASCADE CONSTRAINTS;
DROP TABLE Event 
CASCADE CONSTRAINTS;
DROP TABLE Event_Attachment 
CASCADE CONSTRAINTS;
DROP TABLE Event_Type 
CASCADE CONSTRAINTS;
DROP TABLE Grading_Format 
CASCADE CONSTRAINTS;
DROP TABLE Reimbursement 
CASCADE CONSTRAINTS;
DROP SEQUENCE app_seq;
DROP SEQUENCE app_addinf_seq;
DROP SEQUENCE app_att_seq;
DROP SEQUENCE app_typ_seq;
DROP SEQUENCE dep_seq;
DROP SEQUENCE emp_seq;
DROP SEQUENCE eve_seq;
DROP SEQUENCE eve_att_seq;
DROP SEQUENCE eve_typ_seq;
DROP SEQUENCE gra_for_seq;
DROP SEQUENCE rei_seq;

CREATE TABLE Approval (
    app_id NUMBER PRIMARY KEY,
    app_typ_id NUMBER,
--    app_gra_for_id NUMBER,
    app_rei_id NUMBER,
    app_isApproved CHAR(1) DEFAULT 'U'
        CHECK (app_isApproved IN ('N', 'Y', 'U')),
    app_reason VARCHAR2(4000)
);

CREATE TABLE Approval_Additional_Info (
    app_addinf_id NUMBER PRIMARY KEY,
    app_addinf_approval NUMBER,
    app_addinf_name VARCHAR2(4000),
    app_addinf_file BLOB DEFAULT EMPTY_BLOB()
);

CREATE TABLE Approval_Attachment (
    app_att_id NUMBER PRIMARY KEY,
    app_att_approval NUMBER,
    app_att_name VARCHAR2(4000),
    app_att_file BLOB DEFAULT EMPTY_BLOB()
);

CREATE TABLE Approval_Type (
    app_typ_id NUMBER PRIMARY KEY,
    app_typ_value CHAR(20)
        CHECK (app_typ_value IN ('BENEFITS_COORDINATOR', 
                                 'DEPARTMENT_HEAD', 
                                 'DIRECT_SUPERVISOR'))
);

CREATE TABLE Department (
    dep_id NUMBER PRIMARY KEY,
    dep_name VARCHAR2(4000) UNIQUE
);

CREATE TABLE Employee (
    emp_id NUMBER PRIMARY KEY,
    emp_department NUMBER,
    emp_supervisor NUMBER,
    emp_available_reimbursement NUMBER DEFAULT 1000,
    emp_username VARCHAR2(4000) UNIQUE,
    emp_password VARCHAR2(4000),
    emp_firstname VARCHAR2(4000),
    emp_lastname VARCHAR2(4000),
    emp_isBenCo CHAR(1) DEFAULT 'N'
        CHECK (emp_isBenCo IN ('N', 'Y'))
);

CREATE TABLE Event (
    eve_id NUMBER PRIMARY KEY,
    eve_typ_id NUMBER,
    eve_cost NUMBER CHECK (eve_cost > 0),
    eve_datetime TIMESTAMP WITH LOCAL TIME ZONE,
    eve_description VARCHAR2(4000),
    eve_location VARCHAR2(4000),
    eve_work_missed INTERVAL DAY(9) TO SECOND(0)
);

CREATE TABLE Event_Attachment (
    eve_att_id NUMBER PRIMARY KEY,
    eve_att_event NUMBER,
    eve_att_name VARCHAR2(4000),
    eve_att_file BLOB DEFAULT EMPTY_BLOB()
);

CREATE TABLE Event_Type (
    eve_typ_id NUMBER PRIMARY KEY,
    eve_typ_coverage NUMBER
        CHECK (eve_typ_coverage IN (0.3, 0.6, 0.75, 0.8, 1)),
    eve_typ_value VARCHAR(40)
        CHECK (eve_typ_value IN ('CERTIFICATION',
                                 'CERTIFICATION_PREPARATION_CLASS',
                                 'SEMINAR', 
                                 'TECHNICAL_TRAINING',
                                 'UNIVERSITY_COURSE'))
);

CREATE TABLE Grading_Format (
    gra_for_id NUMBER PRIMARY KEY,
    gra_for_confirmed CHAR(1) DEFAULT 'U'
        CHECK (gra_for_confirmed IN ('N', 'Y', 'U')),
    gra_for_filename VARCHAR2(4000),
    gra_for_file BLOB DEFAULT EMPTY_BLOB(),
    gra_for_passing_cutoff NUMBER DEFAULT NULL
);

CREATE TABLE Reimbursement (
  rei_id NUMBER PRIMARY KEY,
  rei_emp_id NUMBER,
  rei_awarded NUMBER DEFAULT 0,
  rei_isCancelled CHAR(1) DEFAULT 'N'
    CHECK (rei_isCancelled IN ('N', 'Y')),
  rei_isPending CHAR(1) DEFAULT 'Y'
    CHECK (rei_isPending IN ('N', 'Y')),
  rei_justification VARCHAR2(4000),
  rei_reason_exeeded_max VARCHAR2(4000)
);

ALTER TABLE Approval   
ADD CONSTRAINT fk_app_rei 
FOREIGN KEY (app_rei_id) 
REFERENCES Reimbursement(rei_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Approval   
ADD CONSTRAINT fk_app_typ 
FOREIGN KEY (app_typ_id) 
REFERENCES Approval_Type(app_typ_id)
DEFERRABLE INITIALLY DEFERRED;

--ALTER TABLE Approval   
--ADD CONSTRAINT fk_app_gra_for 
--FOREIGN KEY (app_gra_for_id) 
--REFERENCES Grading_Format(gra_for_id)
--DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Approval_Additional_Info   
ADD CONSTRAINT fk_app_addinf 
FOREIGN KEY (app_addinf_approval) 
REFERENCES Approval(app_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Approval_Attachment    
ADD CONSTRAINT fk_app_att 
FOREIGN KEY (app_att_approval) 
REFERENCES Approval(app_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Employee    
ADD CONSTRAINT fk_emp_dep 
FOREIGN KEY (emp_department) 
REFERENCES Department(dep_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Employee    
ADD CONSTRAINT fk_emp_emp 
FOREIGN KEY (emp_supervisor) 
REFERENCES Employee(emp_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Event    
ADD CONSTRAINT fk_eve_rei 
FOREIGN KEY (eve_id) 
REFERENCES Reimbursement(rei_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Event    
ADD CONSTRAINT fk_eve_typ 
FOREIGN KEY (eve_typ_id) 
REFERENCES Event_Type(eve_typ_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Event_Attachment    
ADD CONSTRAINT fk_eve_att 
FOREIGN KEY (eve_att_event) 
REFERENCES Event(eve_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Grading_Format    
ADD CONSTRAINT fk_gra_for_rei 
FOREIGN KEY (gra_for_id) 
REFERENCES Reimbursement(rei_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Reimbursement    
ADD CONSTRAINT fk_rei_emp 
FOREIGN KEY (rei_emp_id) 
REFERENCES Employee(emp_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Reimbursement    
ADD CONSTRAINT fk_rei_eve 
FOREIGN KEY (rei_id) 
REFERENCES Event(eve_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Reimbursement    
ADD CONSTRAINT fk_rei_gra_for 
FOREIGN KEY (rei_id) 
REFERENCES Grading_Format(gra_for_id)
DEFERRABLE INITIALLY DEFERRED;

CREATE SEQUENCE app_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER app_tri
BEFORE INSERT ON Approval
FOR EACH ROW
BEGIN
    IF :new.app_id IS NULL THEN
        SELECT app_seq.NEXTVAL 
        INTO :new.app_id FROM dual;
    END IF;
END;
/

CREATE SEQUENCE app_addinf_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER app_addinf_tri
BEFORE INSERT ON Approval_Additional_Info
FOR EACH ROW
BEGIN
    IF :new.app_addinf_id IS NULL THEN
        SELECT app_addinf_seq.NEXTVAL 
        INTO :new.app_addinf_id FROM dual;
    END IF;
END;
/

CREATE SEQUENCE app_att_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER app_att_tri
BEFORE INSERT ON Approval_Attachment
FOR EACH ROW
BEGIN
    IF :new.app_att_id IS NULL THEN
        SELECT app_att_seq.NEXTVAL 
        INTO :new.app_att_id FROM dual;
    END IF;
END;
/


CREATE SEQUENCE app_typ_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER app_typ_tri
BEFORE INSERT ON Approval_Type
FOR EACH ROW
BEGIN
    IF :new.app_typ_id IS NULL THEN
        SELECT app_typ_seq.NEXTVAL 
        INTO :new.app_typ_id FROM dual;
    END IF;
END;
/

CREATE SEQUENCE dep_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER dep_tri
BEFORE INSERT ON Department
FOR EACH ROW
BEGIN
    IF :new.dep_id IS NULL THEN
        SELECT dep_seq.NEXTVAL 
        INTO :new.dep_id FROM dual;
    END IF;
END;
/

CREATE SEQUENCE emp_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER emp_tri
BEFORE INSERT ON Employee
FOR EACH ROW
BEGIN
    IF :new.emp_id IS NULL THEN
        SELECT emp_seq.NEXTVAL 
        INTO :new.emp_id FROM dual;
    END IF;
END;
/

CREATE SEQUENCE eve_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER eve_tri
BEFORE INSERT ON Event
FOR EACH ROW
BEGIN
    IF :new.eve_id IS NULL THEN
        SELECT eve_seq.NEXTVAL 
        INTO :new.eve_id FROM dual;
    END IF;
END;
/

CREATE SEQUENCE eve_att_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER eve_att_tri
BEFORE INSERT ON Event_Attachment
FOR EACH ROW
BEGIN
    IF :new.eve_att_id IS NULL THEN
        SELECT eve_att_seq.NEXTVAL 
        INTO :new.eve_att_id FROM dual;
    END IF;
END;
/

CREATE SEQUENCE eve_typ_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER eve_typ_tri
BEFORE INSERT ON Event_Type
FOR EACH ROW
BEGIN
    IF :new.eve_typ_id IS NULL THEN
        SELECT eve_typ_seq.NEXTVAL 
        INTO :new.eve_typ_id FROM dual;
    END IF;
END;
/

CREATE SEQUENCE gra_for_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER gra_for_tri
BEFORE INSERT ON Grading_Format
FOR EACH ROW
BEGIN
    IF :new.gra_for_id IS NULL THEN
        SELECT gra_for_seq.NEXTVAL 
        INTO :new.gra_for_id FROM dual;
    END IF;
END;
/

CREATE SEQUENCE rei_seq
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER rei_tri
BEFORE INSERT ON Reimbursement
FOR EACH ROW
BEGIN
    IF :new.rei_id IS NULL THEN
        SELECT rei_seq.NEXTVAL 
        INTO :new.rei_id FROM dual;
    END IF;
END;
/

INSERT INTO Approval_Type(app_typ_value)
VALUES ('BENEFITS_COORDINATOR');
INSERT INTO Approval_Type(app_typ_value)
VALUES ('DEPARTMENT_HEAD');
INSERT INTO Approval_Type(app_typ_value)
VALUES ('DIRECT_SUPERVISOR');

INSERT INTO Event_Type(eve_typ_value, eve_typ_coverage)
VALUES('CERTIFICATION', 1);
INSERT INTO Event_Type(eve_typ_value, eve_typ_coverage)
VALUES('CERTIFICATION_PREPARATION_CLASS', 0.75);
INSERT INTO Event_Type(eve_typ_value, eve_typ_coverage)
VALUES('SEMINAR', 0.6);
INSERT INTO Event_Type(eve_typ_value, eve_typ_coverage)
VALUES('TECHNICAL_TRAINING', 0.3);
INSERT INTO Event_Type(eve_typ_value, eve_typ_coverage)
VALUES('UNIVERSITY_COURSE', 0.8);

CREATE OR REPLACE FUNCTION getEmployee(username IN VARCHAR2)
RETURN NUMBER
IS
    empID NUMBER;
    
    CURSOR cur IS
    SELECT emp_id
    FROM Employee
    WHERE emp_username = username;
BEGIN
    OPEN cur;
    FETCH cur INTO empID;
    
    IF cur%NOTFOUND THEN
      empID := 0;
    END IF;
    
    CLOSE cur;
    
    RETURN empID;
END;
/

CREATE OR REPLACE FUNCTION getEventType(typ IN VARCHAR2)
RETURN NUMBER
IS
    evetypID NUMBER;
    NONEXISTENT_EVENT_TYPE EXCEPTION;
    
    CURSOR cur IS
    SELECT eve_typ_id
    FROM Event_Type
    WHERE eve_typ_value = typ;
BEGIN
    OPEN cur;
    FETCH cur INTO evetypID;
    
    IF cur%NOTFOUND THEN
      RAISE NONEXISTENT_EVENT_TYPE;
    END IF;
    
    CLOSE cur;
    
    RETURN evetypID;
END;
/

CREATE OR REPLACE FUNCTION checkSupervisor(supervisor IN VARCHAR2)
RETURN NUMBER
IS
    supID NUMBER;
    NONEXISTENT_EMPLOYEE EXCEPTION;
BEGIN
    IF supervisor IS NULL THEN
        supID := NULL;
    ELSE
        supID := getEmployee(supervisor);
    
        IF supID = 0 THEN
            RAISE NONEXISTENT_EMPLOYEE;
        END IF;
    END IF;
    
    RETURN supID;
END;
/

CREATE OR REPLACE FUNCTION getDepartment(nam IN VARCHAR2)
RETURN NUMBER
IS
    depID NUMBER;
    
    CURSOR cur IS
    SELECT dep_id
    FROM Department
    WHERE dep_name = UPPER(nam);
BEGIN
    OPEN cur;
    FETCH cur INTO depID;
    
    IF cur%NOTFOUND THEN
      depID := 0;
    END IF;
    
    CLOSE cur;
    
    RETURN depID;
END;
/

CREATE OR REPLACE PROCEDURE insertDepartment(department IN VARCHAR2)
IS
BEGIN
    INSERT INTO Department(dep_name)
    VALUES(UPPER(department));
END;
/

CREATE OR REPLACE PROCEDURE insertEmployee(username IN VARCHAR2, 
                                           pas IN VARCHAR2, 
                                           firstname IN VARCHAR2, 
                                           lastname IN VARCHAR2, 
                                           department IN VARCHAR2, 
                                           supervisor IN VARCHAR2, 
                                           isBenCo IN CHAR)
IS
    depID NUMBER;
    supID NUMBER;
BEGIN
    supID := checkSupervisor(supervisor);
    depID := getDepartment(department);
        
    IF depID = 0 THEN
        insertDepartment(department);
        depID := getDepartment(department);
    END IF;

    INSERT INTO Employee(emp_username, emp_password, emp_firstname,
                         emp_lastname, emp_department, emp_supervisor,
                         emp_isBenco)
    VALUES(username, pas, firstname, lastname, depID, supID, isBenCo);
    COMMIT;
END;
/

CALL insertEmployee('swilery', 'swilery', 'Walter', 'Xia', 'Computer Science',
                    NULL, 'N');
CALL insertEmployee('walterx', 'walterx', 'Walter', 'Xia', 'Computer Science',
                    'swilery', 'N');
--CALL insertEmployee('ryanl', 'ryanl', 'Ryan', 'Lessley', 'Computer Science',
--                    'bobbertb', 'Y');

CREATE OR REPLACE PROCEDURE insertApprovalAdditionalInfo(info IN NUMBER, 
                                                         filename IN VARCHAR2,
                                                         fil IN BLOB)
IS
--    loc BFILE;
--    fil BLOB;
BEGIN
    INSERT INTO Approval_Additional_Info(app_addinf_approval, app_addinf_name,
                                         app_addinf_file)
    VALUES (info, filename, fil);
--    RETURN app_addinf_file INTO fil;
--    loc := BFILENAME('FROMFS', filename);
--    DBMS_LOB.FILEOPEN(loc, DBMS_LOB.FILE_READONLY);
--    DBMS_LOB.LOADFROMFILE(fil, loc, DBMS_LOB.GETLENGTH(loc));
--    DBMS_LOB.FILECLOSE(loc);
END;
/

CREATE OR REPLACE PROCEDURE insertApprovalAttachment(approval IN NUMBER,
                                                     filename IN VARCHAR2,
                                                     fil IN BLOB)
IS
--    loc BFILE;
--    fil BLOB;
BEGIN
    INSERT INTO Approval_Attachment (app_att_approval, app_att_name, app_att_file)
    VALUES (approval, filename, fil);
--    RETURN app_att_file INTO fil;
--    loc := BFILENAME('FROMFS', filename);
--    DBMS_LOB.FILEOPEN(loc, DBMS_LOB.FILE_READONLY);
--    DBMS_LOB.LOADFROMFILE(fil, loc, DBMS_LOB.GETLENGTH(loc));
--    DBMS_LOB.FILECLOSE(loc);
END;
/

CREATE OR REPLACE PROCEDURE insertEventAttachment(event IN NUMBER, 
                                                  filename IN VARCHAR2,
                                                  fil IN BLOB)
IS
--    loc BFILE;
--    fil BLOB;
BEGIN
    INSERT INTO Event_Attachment (eve_att_event, eve_att_name, eve_att_file)
    VALUES (event, filename, fil);
--    RETURN eve_att_file INTO fil;
--    loc := BFILENAME('FROMFS', filename);
--    DBMS_LOB.FILEOPEN(loc, DBMS_LOB.FILE_READONLY);
--    DBMS_LOB.LOADFROMFILE(fil, loc, DBMS_LOB.GETLENGTH(loc));
--    DBMS_LOB.FILECLOSE(loc);
END;
/

CREATE OR REPLACE PROCEDURE updateGradingFormatProof(graforID IN NUMBER, 
                                                     filename IN VARCHAR2,
                                                     fil IN BLOB)
IS
--    loc BFILE;
--    fil BLOB;
BEGIN
    UPDATE Grading_Format
    SET gra_for_filename = filename, gra_for_file = fil
    WHERE gra_for_id = graforID;
--    RETURN gra_for_file INTO fil;
--    loc := BFILENAME('FROMFS', filename);
--    DBMS_LOB.FILEOPEN(loc, DBMS_LOB.FILE_READONLY);
--    DBMS_LOB.LOADFROMFILE(fil, loc, DBMS_LOB.GETLENGTH(loc));
--    DBMS_LOB.FILECLOSE(loc);
END;
/

CREATE OR REPLACE PROCEDURE insertEvent(typ IN VARCHAR2, 
                                        cos IN NUMBER, 
                                        dat IN VARCHAR2,
                                        loc IN VARCHAR2,
                                        work_missed IN INTERVAL DAY TO SECOND,
                                        des IN VARCHAR2)
IS
    evetypID NUMBER;
BEGIN
    evetypID := getEventType(typ);
    INSERT INTO Event(eve_typ_id, eve_cost, eve_datetime, eve_location, 
                    eve_work_missed, eve_description)
    VALUES(evetypID, cos, dat, loc, work_missed, des);
END;
/

CREATE OR REPLACE PROCEDURE insertGradingFormat(passingCutoff IN NUMBER)
IS
BEGIN
    INSERT INTO Grading_Format(gra_for_passing_cutoff)
    VALUES(passingCutoff);
--    SELECT gra_for_seq.CURRVAL 
--    INTO graforID 
--    FROM dual;
END;
/

CREATE OR REPLACE PROCEDURE insertApproval(reiID IN NUMBER)
IS
    approvals NUMBER;
    counter NUMBER;
--    graforID NUMBER;
BEGIN
    SELECT COUNT(*) 
    INTO approvals 
    FROM Approval_Type;
--    insertGradingFormat(passingCutoff, graforID);
    
    FOR counter IN 1..approvals LOOP
        INSERT INTO Approval(app_typ_id, app_rei_id)
        VALUES(counter, reiID);
    END LOOP;
END;
/

CREATE OR REPLACE PROCEDURE insertReimbursement(employee IN VARCHAR2, 
                                                typ IN VARCHAR2, 
                                                cos IN NUMBER, 
                                                dat IN VARCHAR2, 
                                                loc IN VARCHAR2,
                                                work_missed IN 
                                                    INTERVAL DAY TO SECOND,
                                                passingCutoff IN NUMBER,
                                                des IN VARCHAR2,
                                                justification IN VARCHAR2)
IS
    empID NUMBER;
    reiID NUMBER;
    NONEXISTENT_EMPLOYEE EXCEPTION;
BEGIN
    empID := getEmployee(employee);
    
    IF empID = 0 THEN
        RAISE NONEXISTENT_EMPLOYEE;
    END IF;
    
    INSERT INTO Reimbursement(rei_emp_id, rei_justification)
    VALUES(empID, justification);
    
    insertEvent(typ, cos, dat, loc, work_missed, des);
    insertGradingFormat(passingCutoff);
    SELECT rei_seq.CURRVAL
    INTO reiID
    FROM dual;
    insertApproval(reiID);
    COMMIT;
END;
/

CALL insertReimbursement('walterx', 'TECHNICAL_TRAINING', 20000,
                         TIMESTAMP '2018-06-18 8:30:00', 'Arlington, TX',
                         INTERVAL '70' DAY, 0.7, 'Revature training', 
                         'i dunno y');
--CALL insertReimbursement('swilery', 'TECHNICAL_TRAINING', 20000,
--                         TIMESTAMP '2018-06-18 8:30:00', 'Arlington, TX',
--                         NULL, 'Revature training', 'i dunno y');
--CALL insertEventAttachment(1, 
--    '10369913_267485166779577_5573839803579672783_n.jpg');
--CALL insertApprovalAttachment(1, 'Interview Prep Handbook.doc');
--CALL insertApprovalAdditionalInfo(1, 'Journey to the West Vocabulary.odt');
--CALL updateGradingFormatProof(1, 'What does this poem in Journey to the West mean.pdf');

COMMIT;