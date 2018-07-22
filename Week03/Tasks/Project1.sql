
--CREATE USER projOneAdmin IDENTIFIED BY "admin";
--GRANT DBA TO projOneAdmin;

DROP TABLE employee CASCADE CONSTRAINTS;
DROP TABLE employee_user CASCADE CONSTRAINTS;
DROP TABLE employee_type CASCADE CONSTRAINTS;
DROP TABLE event_type CASCADE CONSTRAINTS;
DROP TABLE grade_scale CASCADE CONSTRAINTS;
DROP TABLE grade_value CASCADE CONSTRAINTS;
DROP TABLE event CASCADE CONSTRAINTS;
DROP TABLE event_document CASCADE CONSTRAINTS;
DROP TABLE notification CASCADE CONSTRAINTS;
DROP TABLE notification_flag CASCADE CONSTRAINTS;
DROP TABLE department CASCADE CONSTRAINTS;

CREATE TABLE employee(
    emp_id NUMBER(10) PRIMARY KEY,
    emp_first_name VARCHAR(32) NOT NULL,
    emp_last_name VARCHAR(32) NOT NULL,
    emp_email VARCHAR(64) NOT NULL,
    emp_type NUMBER(3) NOT NULL,
    emp_supervised_by NUMBER(10),
    emp_balance DECIMAL(12, 4) NOT NULL,
    emp_reimbursement_available DECIMAL(12, 4) NOT NULL,
    emp_department NUMBER(10)
);

CREATE TABLE employee_user(
    emp_id NUMBER(10) PRIMARY KEY,
    emp_username VARCHAR(32) UNIQUE NOT NULL,
    emp_password VARCHAR(32) NOT NULL
);

CREATE TABLE employee_type(
    et_id NUMBER(10) PRIMARY KEY,
    et_name VARCHAR(32) NOT NULL
);

CREATE TABLE event_type(
    et_id NUMBER(3) PRIMARY KEY,
    et_name VARCHAR(32) NOT NULL,
    et_percent NUMBER(3)
);

CREATE TABLE grade_scale(
    gs_id NUMBER(3) PRIMARY KEY,
    gs_name VARCHAR(12) NOT NULL,
    gs_presentation NUMBER(1) NOT NULL
);

CREATE TABLE grade_value(
    gv_id NUMBER(1) PRIMARY KEY,
    gv_scale NUMBER(3),
    gv_name VARCHAR(12)
);

CREATE TABLE event(
    ev_id NUMBER(10) PRIMARY KEY,
    emp_id NUMBER(10) NOT NULL,
    ev_type NUMBER(3) NOT NULL,
    ev_grade_scale NUMBER(3) NOT NULL,
    ev_grade NUMBER(4),
    ev_name VARCHAR(20) NOT NULL,
    ev_date TIMESTAMP NOT NULL,
    ev_location VARCHAR(20),
    ev_description VARCHAR(200),
    ev_justification VARCHAR(200),
    ev_cost DECIMAL(10,2) NOT NULL,
    ev_r_amt DECIMAL(10,2),
    ev_super_approve TIMESTAMP,
    ev_head_approve TIMESTAMP,
    ev_benco_approve TIMESTAMP,
    ev_r_confirm TIMESTAMP,
    ev_r_message VARCHAR(200)
);

CREATE TABLE event_document(
    evdoc_id NUMBER(10) PRIMARY KEY,
    ev_id NUMBER(10) NOT NULL,
    evdoc_name VARCHAR(50) NOT NULL,
    evdoc_file BLOB NOT NULL,
    evdoc_type NUMBER(1) NOT NULL
);

CREATE TABLE notification(
    nt_id NUMBER(10) PRIMARY KEY,
    nt_emp_target NUMBER(10) NOT NULL,
    nt_emp_source NUMBER(10) NOT NULL,
    nt_message VARCHAR(200) NOT NULL,
    nt_event NUMBER(10),
    nt_read NUMBER(1) DEFAULT 0,
    nt_timestamp TIMESTAMP,
    nt_flag NUMBER(10)
);

CREATE TABLE notification_flag(
    nf_id NUMBER(10) PRIMARY KEY,
    nf_name VARCHAR(20)
);

CREATE TABLE department(
    dp_id NUMBER(10) PRIMARY KEY,
    dp_name VARCHAR(30),
    dp_head NUMBER(10)
);

/*******************************************************************************
   Create Foreign Keys
********************************************************************************/

ALTER TABLE employee ADD CONSTRAINT fk_employee_supervised_by
    FOREIGN KEY (emp_supervised_by) REFERENCES employee (emp_id);
    
ALTER TABLE employee_user ADD CONSTRAINT fk_employee_user
    FOREIGN KEY (emp_id) REFERENCES employee (emp_id);
    
ALTER TABLE event ADD CONSTRAINT fk_event_employee_id
    FOREIGN KEY (emp_id) REFERENCES employee (emp_id);
    
ALTER TABLE event ADD CONSTRAINT fk_event_type
    FOREIGN KEY (ev_type) REFERENCES event_type (et_id);
    
ALTER TABLE event ADD CONSTRAINT fk_event_grade_scale
    FOREIGN KEY (ev_grade_scale) REFERENCES grade_scale (gs_id);
    
ALTER TABLE event_document ADD CONSTRAINT fk_event_doc_event
    FOREIGN KEY (ev_id) REFERENCES event (ev_id);
    
ALTER TABLE notification ADD CONSTRAINT fk_notif_emp_target
    FOREIGN KEY (nt_emp_target) REFERENCES employee (emp_id);
    
ALTER TABLE notification ADD CONSTRAINT fk_notif_emp_source
    FOREIGN KEY (nt_emp_source) REFERENCES employee (emp_id);
    
ALTER TABLE notification ADD CONSTRAINT fk_notif_event
    FOREIGN KEY (nt_event) REFERENCES event (ev_id);
    
ALTER TABLE employee ADD CONSTRAINT fk_emp_department
    FOREIGN KEY (emp_department) REFERENCES department (dp_id);
    
/*******************************************************************************
   PL/SQL: Triggers/Sequences
********************************************************************************/

DROP SEQUENCE event_seq;
CREATE SEQUENCE event_seq
    START WITH 1
    INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER event_seq_trigger
BEFORE INSERT ON event
FOR EACH ROW --
BEGIN
    IF :NEW.ev_id IS NULL THEN
        SELECT event_seq.NEXTVAL INTO :NEW.ev_id FROM dual;
    END IF;
END;
/

DROP SEQUENCE doc_seq;
CREATE SEQUENCE doc_seq
    START WITH 1
    INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER doc_seq_trigger
BEFORE INSERT ON event_document
FOR EACH ROW --
BEGIN
    IF :NEW.evdoc_id IS NULL THEN
        SELECT doc_seq.NEXTVAL INTO :NEW.evdoc_id FROM dual;
    END IF;
END;
/

DROP SEQUENCE notif_seq;
CREATE SEQUENCE notif_seq
    START WITH 1
    INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER notif_seq_trigger
BEFORE INSERT ON notification
FOR EACH ROW --
BEGIN
    IF :NEW.nt_id IS NULL THEN
        SELECT notif_seq.NEXTVAL INTO :NEW.nt_id FROM dual;
    END IF;
END;
/

/*******************************************************************************
   PL/SQL: Stored Procedures/Functions
********************************************************************************/

CREATE OR REPLACE FUNCTION CALC_REIMB_AMOUT(evId IN NUMBER)
RETURN NUMBER
IS
evtPercent NUMBER;
evCost NUMBER;
evType NUMBER;
empId NUMBER;
empRemain NUMBER;
BEGIN

SELECT ev_cost, emp_id, ev_type INTO evCost, empId, evType FROM event WHERE ev_id = evId;
SELECT et_percent INTO evtPercent FROM event_type WHERE et_id = evType;
SELECT emp_reimbursement_available INTO empRemain FROM employee WHERE emp_id = empId;

return LEAST(empRemain*100, evCost*evtPercent);

END;
/

CREATE OR REPLACE FUNCTION HAS_NOTIF(userId IN NUMBER, evId IN NUMBER)
RETURN NUMBER
IS
cnt NUMBER;
BEGIN

SELECT count(*) INTO cnt FROM notification WHERE nt_event = evId AND nt_emp_target = userId AND nt_read = 0;

IF cnt > 0 THEN
    return 1;
ELSE
    return 0;
END IF;

END;
/

CREATE OR REPLACE PROCEDURE ADD_NEW_EVENT(empId IN NUMBER, evType IN NUMBER, evGradeScale IN NUMBER, evName IN VARCHAR, evDate IN TIMESTAMP, evLocation IN VARCHAR, evDescription IN VARCHAR, evJustification IN VARCHAR, evCost IN DECIMAL, evId OUT NUMBER)
IS
    reimb NUMBER;
BEGIN
--    INSERT INTO department VALUES (1, 'Human Resources');
    INSERT INTO event (emp_id, ev_type, ev_grade_scale, ev_name, ev_date, ev_location, ev_description, ev_justification, ev_cost) 
                VALUES(empId, evType, evGradeScale, evName, evDate, evLocation, evDescription, evJustification, evCost);

    SELECT MAX(ev_id) INTO evId FROM event WHERE emp_id = empId;
    
    reimb := CALC_REIMB_AMOUT(evId)/100;
    UPDATE event SET ev_r_amt = reimb WHERE ev_id = evId;
END;
/

CREATE OR REPLACE PROCEDURE ADD_DOCUMENT(evId IN NUMBER, evName IN VARCHAR, evFile IN BLOB, docType IN NUMBER)
IS
BEGIN

    INSERT INTO event_document (ev_id, evdoc_name, evdoc_file, evdoc_type)
        VALUES (evId, evName, evFile, docType);

END;
/

CREATE OR REPLACE PROCEDURE EVENT_UPDATE_APPROVAL_FROM(evId IN NUMBER, otherId IN NUMBER, status OUT NUMBER)
IS
    empId NUMBER(6);
    empRel NUMBER(6);
    temp TIMESTAMP;
    reimb NUMBER;
BEGIN
    SELECT emp_id INTO empId FROM event WHERE ev_id = evId;
    empRel := GET_EMP_RELATION(otherId, empId);
    
    DBMS_OUTPUT.put_line(empRel);
    
    IF empRel = 1 THEN
        --BenCo
        SELECT ev_benco_approve INTO temp FROM event WHERE ev_id = evId;
        IF temp != NULL THEN
            status := 0;
            return;
        END IF;
        
        SELECT ev_r_amt INTO reimb FROM event WHERE ev_id = evId;
        UPDATE event SET ev_benco_approve = SYSDATE WHERE ev_id = evId;
        UPDATE employee SET emp_reimbursement_available = emp_reimbursement_available-reimb WHERE emp_id = empId;
        status := 1;
    END IF;
    
    IF empRel = 2 THEN
        --Department Head
        SELECT ev_head_approve INTO temp FROM event WHERE ev_id = evId;
        IF temp != NULL THEN
            status := 0;
            return;
        END IF;
        
         UPDATE event SET ev_head_approve  = SYSDATE WHERE ev_id = evId;
         status := 1;
    END IF;
    
    IF empRel = 3 THEN
        --Supervisor
        SELECT ev_super_approve INTO temp FROM event WHERE ev_id = evId;
        IF temp != NULL THEN
            status := 0;
            return;
        END IF;
        
         UPDATE event SET ev_super_approve = SYSDATE WHERE ev_id = evId;
         status := 1;
    END IF;
    
    status := 0;
END;
/

CREATE OR REPLACE FUNCTION GET_EMP_RELATION(superId IN NUMBER, otherId IN NUMBER)
RETURN NUMBER
IS
    empRel NUMBER;
    empType NUMBER;
    temp1 NUMBER;
    temp2 NUMBER;
BEGIN
    SELECT emp_type INTO empType FROM employee WHERE emp_id = superId;
    
    DBMS_OUTPUT.put_line(empType);
    
    IF empType = 1 THEN
        return 1;
    END IF;
    
    IF empType = 2 THEN 
        SELECT emp_department INTO temp1 FROM employee WHERE emp_id = otherId;
        
        SELECT dp_head INTO temp2 FROM department WHERE dp_id = temp1;
        
        IF superId = temp2 THEN
            return 2;
        END IF;
    END IF;
    
    IF empType = 3 THEN 

        SELECT emp_supervised_by INTO temp1 FROM employee WHERE emp_id = otherId;
        
        IF temp1 = superId THEN
            return 3;
        END IF;
    END IF;
    
    return 0;    
END;
/

CREATE OR REPLACE PROCEDURE GET_SUBORDINATE_EVENTS(empId IN NUMBER, resultSet OUT sys_refcursor)
IS
    empType NUMBER;
    department NUMBER;
BEGIN

    SELECT emp_type INTO empType FROM employee WHERE emp_id = empId;
    
    IF empType = 1 THEN
        -- benco
        OPEN resultSet FOR 
            SELECT * FROM event;
    END IF;
    
    IF empType = 2 THEN
        -- department
        SELECT dp_id INTO department FROM department WHERE dp_head = empId;
        
        OPEN resultSet FOR 
            SELECT * FROM event WHERE emp_id IN(
                SELECT emp_id FROM employee
                WHERE emp_department = department);
    END IF;
    
    IF empType = 3 THEN
        -- supervisor
        OPEN resultSet FOR 
            SELECT * FROM event WHERE emp_id IN(
                SELECT emp_id FROM employee
                WHERE emp_supervised_by = empId);
    END IF;
    
END;
/

CREATE OR REPLACE PROCEDURE EVENT_ADD_NOTIFICATION(eventId IN NUMBER, fromEmp IN NUMBER, message IN VARCHAR)
IS
toEmp NUMBER;
BEGIN

    SELECT emp_id INTO toEmp FROM event WHERE ev_id = eventId;
    
    INSERT INTO notification (nt_emp_target, nt_emp_source, nt_message, nt_event, nt_timestamp) 
    VALUES (toEmp, fromEmp, message, eventId, SYSDATE);
    
END;
/

CREATE OR REPLACE PROCEDURE EMPLOYEE_ADD_NOTIFICATION(toEmp IN NUMBER, fromEmp IN NUMBER, message IN VARCHAR)
IS
BEGIN

    INSERT INTO notification (nt_emp_target, nt_emp_source, nt_message, nt_timestamp) 
    VALUES (toEmp, fromEmp, message, SYSDATE);
    
END;
/

CREATE OR REPLACE PROCEDURE ADD_APPROVED_EVENT(empId IN NUMBER, evType IN NUMBER, evGradeScale IN NUMBER, evName IN VARCHAR, evDate IN TIMESTAMP, evLocation IN VARCHAR, evDescription IN VARCHAR, evJustification IN VARCHAR, evCost IN DECIMAL)
IS
evId NUMBER;
temp NUMBER;
BEGIN
--    INSERT INTO department VALUES (1, 'Human Resources');

    ADD_NEW_EVENT(empId, evType, evGradeScale, evName, evDate, evLocation, evDescription, evJustification, evCost, evId);
        
    EVENT_UPDATE_APPROVAL_FROM(evId, 2, temp);
END;
/

CREATE OR REPLACE PROCEDURE EVENT_CONFIRM(eventId IN NUMBER, managerId IN NUMBER)
IS
    reimb NUMBER;
    empId NUMBER;
    evName VARCHAR(20);
BEGIN

    SELECT emp_id, ev_r_amt, ev_name INTO empId, reimb, evName FROM event WHERE ev_id = eventId;

    UPDATE event SET ev_r_confirm = SYSDATE WHERE ev_id = eventId;
    UPDATE employee SET emp_balance = emp_balance+reimb WHERE emp_id = empId;

    EMPLOYEE_ADD_NOTIFICATION(empId, managerId,'Event '||evName||' has been confirmed.');
END;
/

/*******************************************************************************
   Populate Tables: Core
********************************************************************************/

INSERT INTO department (dp_id, dp_name) VALUES (1, 'Management');
INSERT INTO department (dp_id, dp_name) VALUES (2, 'Human Resources');
INSERT INTO department (dp_id, dp_name) VALUES (57, 'Arlington Branch');

INSERT INTO employee_type VALUES (1, 'Benefits Coordinator');
INSERT INTO employee_type VALUES (2, 'Department Head');
INSERT INTO employee_type VALUES (3, 'Supervisor');
INSERT INTO employee_type VALUES (4, 'Associate');

INSERT INTO event_type VALUES (1, 'University Course', 80);
INSERT INTO event_type VALUES (2, 'Seminar', 60);
INSERT INTO event_type VALUES (3, 'Certification', 100);
INSERT INTO event_type VALUES (4, 'Cert. Preparation Class', 75);
INSERT INTO event_type VALUES (5, 'Technical Training', 90);
INSERT INTO event_type VALUES (6, 'Other', 30);

INSERT INTO grade_scale VALUES (1, 'Standard', 0);
INSERT INTO grade_value VALUES (1, 1, 'A');
INSERT INTO grade_value VALUES (2, 1, 'B');
INSERT INTO grade_value VALUES (3, 1, 'C');
INSERT INTO grade_value VALUES (4, 1, 'D');
INSERT INTO grade_value VALUES (5, 1, 'F');
INSERT INTO grade_scale VALUES (2, 'Pass/Fail', 0);
INSERT INTO grade_value VALUES (6, 2, 'Pass');
INSERT INTO grade_value VALUES (7, 2, 'Fail');
INSERT INTO grade_scale VALUES (3, 'Presentation', 1);
INSERT INTO grade_value VALUES (8, 3, 'Presentation');

INSERT INTO notification_flag VALUES (1, 'Escalated');
INSERT INTO notification_flag VALUES (2, 'Requires Grade');

/*******************************************************************************
   Populate Tables: Users 
********************************************************************************/

INSERT INTO employee (emp_id, emp_first_name, emp_last_name, emp_email, emp_type, emp_supervised_by,
    emp_balance, emp_reimbursement_available, emp_department) VALUES (1, 'Tom', 'Bobberson', 'bobbert@email.com', 2, NULL, 0, 1000, 1);
    
INSERT INTO employee (emp_id, emp_first_name, emp_last_name, emp_email, emp_type, emp_supervised_by,
    emp_balance, emp_reimbursement_available, emp_department) VALUES (2, 'Gai', 'Sensei', 'gai333@email.com', 1, 1, 0, 1000, 2);
    
INSERT INTO employee (emp_id, emp_first_name, emp_last_name, emp_email, emp_type, emp_supervised_by,
    emp_balance, emp_reimbursement_available, emp_department) VALUES (3, 'Ryan', 'Bobson', 'ryan@email.com', 3, 1, 0, 1000, 57);
    
INSERT INTO employee (emp_id, emp_first_name, emp_last_name, emp_email, emp_type, emp_supervised_by,
    emp_balance, emp_reimbursement_available, emp_department) VALUES (4, 'Austin', 'Molina', 'austin.molina@email.com', 4, 3, 0, 1000, 57);
    
INSERT INTO employee (emp_id, emp_first_name, emp_last_name, emp_email, emp_type, emp_supervised_by,
    emp_balance, emp_reimbursement_available, emp_department) VALUES (5, 'Karen', 'Fineman', 'k.fineman@email.com', 2, 1, 0, 1000, 2);
    
INSERT INTO employee (emp_id, emp_first_name, emp_last_name, emp_email, emp_type, emp_supervised_by,
    emp_balance, emp_reimbursement_available, emp_department) VALUES (6, 'Shaniqua', 'Sundberg', 's.sundberg@email.com', 3, 5, 0, 1000, 2);
    
INSERT INTO employee (emp_id, emp_first_name, emp_last_name, emp_email, emp_type, emp_supervised_by,
    emp_balance, emp_reimbursement_available, emp_department) VALUES (7, 'John', 'Bukhalo', 'jk.bukhalo@email.com', 4, 3, 0, 1000, 57);
  
INSERT INTO employee (emp_id, emp_first_name, emp_last_name, emp_email, emp_type, emp_supervised_by,
    emp_balance, emp_reimbursement_available, emp_department) VALUES (8, 'Harrid', 'Brewer', 'homebrew@email.com', 4, 3, 0, 1000, 57); 
    
INSERT INTO employee (emp_id, emp_first_name, emp_last_name, emp_email, emp_type, emp_supervised_by,
    emp_balance, emp_reimbursement_available, emp_department) VALUES (9, 'Larry', 'Headson', 'captain@email.com', 2, 2, 0, 1000, 57);
   
INSERT INTO employee_user VALUES (1, 'admin', 'admin');
INSERT INTO employee_user VALUES (2, 'benco', 'admin');
INSERT INTO employee_user VALUES (3, 'super', 'admin');
INSERT INTO employee_user VALUES (4, 'ausmo', 'user');
INSERT INTO employee_user VALUES (5, 'fineman', 'admin');
INSERT INTO employee_user VALUES (6, 'ssundberg', 'admin');
INSERT INTO employee_user VALUES (7, 'jbu678', 'user');
INSERT INTO employee_user VALUES (8, 'homebrew', 'user');
INSERT INTO employee_user VALUES (9, 'head', 'admin');

UPDATE department SET dp_head = 1 WHERE dp_id = 1;
UPDATE department SET dp_head = 5 WHERE dp_id = 2;
UPDATE department SET dp_head = 9 WHERE dp_id = 57;

/*******************************************************************************
   Populate Tables: Test Data
********************************************************************************/

DECLARE
    temp NUMBER;
BEGIN
    ADD_APPROVED_EVENT(4, 4, 1, 'Enthuware', TO_TIMESTAMP('2018-07-16', 'YYYY-MM-DD HH24:MI:SS.FF'), 'Arlington', 'Online study tool', 'Extra training to earn OTA certification', 20);
    ADD_APPROVED_EVENT(4, 2, 3, 'Seminar', TO_TIMESTAMP('2018-07-16', 'YYYY-MM-DD HH24:MI:SS.FF'), 'Arlington', 'Online study tool', 'Extra training to earn OTA certification', 20);
    
    ADD_NEW_EVENT(8, 4, 1, 'Urgent/Escalated', TO_TIMESTAMP('2018-07-16', 'YYYY-MM-DD HH24:MI:SS.FF'), 'Arlington', 'Online study tool', 'Extra training to earn OTA certification', 20, temp);
END;
/

--UPDATE notification SET nt_read = 0;

--SELECT * FROM notification;
SELECT * FROM employee;
--SELECT * FROM employee_user;
SELECT HAS_NOTIF(4, ev_id), event.* FROM event;
--SELECT * FROM event_document;
----
--call EVENT_UPDATE_APPROVAL_FROM(1, 3);
--call EVENT_UPDATE_APPROVAL_FROM(1, 9);
--call EVENT_UPDATE_APPROVAL_FROM(1, 1);
--call EVENT_UPDATE_APPROVAL_FROM(1, 2);

commit;