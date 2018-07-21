DROP TABLE employee CASCADE CONSTRAINTS;
DROP TABLE grade_reference CASCADE CONSTRAINTS;
DROP TABLE head CASCADE CONSTRAINTS;
DROP TABLE reimbursement CASCADE CONSTRAINTS;
DROP TABLE document CASCADE CONSTRAINTS;
DROP TABLE approval_email CASCADE CONSTRAINTS;
    
SELECT * FROM employee;
SELECT * FROM contact_info;
SELECT * FROM login_info;
SELECT * FROM reimbursement;

truncate table employee;

commit;

truncate table reimbursement;

SELECT * 
FROM branch_manager bm
JOIN contact_info c ON bm.cont_id = c.id
JOIN login_info l ON bm.log_id = l.id
WHERE bm.bm_id = '100';

CREATE TABLE eventCoverage (
    type VARCHAR2(32) NOT NULL UNIQUE,
    coverage NUMBER(10) NOT NULL
);

CREATE TABLE employee(
    id NUMBER(9) PRIMARY KEY,
    role VARCHAR2(20) NOT NULL,
    supervisor_id NUMBER(9),
    fname VARCHAR2(60) NOT NULL,
    lname VARCHAR2(60) NOT NULL,
    phone NUMBER(20),
    email VARCHAR(60) NOT NULL,
    address VARCHAR(60) NOT NULL,
    location VARCHAR(60),
    username VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL
);

CREATE TABLE document (
    id NUMBER(9) PRIMARY KEY,
    emp_id NUMBER(9),
    name VARCHAR2(60),
    type VARCHAR2(30),
    date_craeted DATE,
    author VARCHAR2(30),
    img BLOB,
    
    CONSTRAINT fk_doc_em_id FOREIGN KEY (emp_id) REFERENCES employee(id)
);

CREATE TABLE approval_email (
    id NUMBER(9) PRIMARY KEY,
    emp_id NUMBER(9),
    approver_id NUMBER(9),
    
    CONSTRAINT fk_apem_emp_id FOREIGN KEY (emp_id) REFERENCES employee(id)
);

CREATE TABLE reimbursement (
    reimbursement_id NUMBER(9) PRIMARY KEY,
    doc_id NUMBER(9),
    aprov_em_id NUMBER(9),
    emp_id NUMBER(9) NOT NULL,
    reimbursement_date VARCHAR2(32) NOT NULL,
    reimbursement_location VARCHAR2(32) NOT NULL,
    description VARCHAR2(100) NOT NULL,
    cost NUMBER(12) NOT NULL,
    grading_format VARCHAR(32) NOT NULL,
    event_type VARCHAR(32) NOT NULL,
    coverage NUMBER(9) NOT NULL,
    justification VARCHAR(100) NOT NULL,
    attachment BLOB,
    email_approval BLOB,
    
    CONSTRAINT fk_ri_emp_id FOREIGN KEY (emp_id) REFERENCES employee(id)
);

CREATE TABLE grade_reference (
    format VARCHAR2(30),
    requirement VARCHAR2(30),
    grade_requirement VARCHAR2(30)
);
/

DROP SEQUENCE id_seq;
CREATE SEQUENCE id_seq
    START WITH 100
    INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER emp_seq_trigger
BEFORE INSERT ON employee --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.id IS NULL THEN
        SELECT id_seq.NEXTVAL INTO :new.id FROM dual;
    END IF;
END;
/

DROP SEQUENCE reim_seq;
CREATE SEQUENCE reim_seq
    START WITH 100
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER reim_seq_trigger
BEFORE INSERT ON reimbursement --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.reimbursement_id IS NULL THEN
        SELECT reim_seq.NEXTVAL INTO :new.reimbursement_id FROM dual;
    END IF;
END;
/

DROP SEQUENCE doc_seq;
CREATE SEQUENCE doc_seq
    START WITH 100
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER doc_seq_trigger
BEFORE INSERT ON document --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.id IS NULL THEN
        SELECT doc_seq.NEXTVAL INTO :new.id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE insertEmployee(
    p_role IN VARCHAR2,
	p_supVId IN NUMBER,
	p_fname IN VARCHAR2,
	p_lname IN VARCHAR2,
	p_phone IN NUMBER,
	p_email IN VARCHAR2,
	p_address IN VARCHAR2,
	p_location IN VARCHAR2,
    p_username IN VARCHAR2,
    P_password IN VARCHAR2)
IS
BEGIN 
	INSERT INTO employee (role, supervisor_id, fname, lname, phone, email, address,
	location, username, password)
	VALUES (p_role, p_supVId, p_fname, p_lname, p_phone, p_email, p_address,
	p_location, p_username, p_password);

	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertReimbursement(
	p_empId IN NUMBER,
    p_approverId IN NUMBER, 
	p_reimbursementDate IN VARCHAR2,
	p_reimbursementLocation VARCHAR2,
	p_description IN VARCHAR2,
	p_cost IN NUMBER,
	p_gradingFormat IN VARCHAR2,
	p_eventType IN VARCHAR2,
	p_coverage IN NUMBER, 
	p_justification IN VARCHAR2)
IS
BEGIN 
	INSERT INTO reimbursement (emp_id, approver_id, reimbursement_date, reimbursement_location,
		description, cost, grading_format, event_type, coverage, justification)
	VALUES (p_empId, p_approverId, p_reimbursementDate, p_reimbursementLocation, p_description, p_cost,
		p_gradingFormat, p_eventType, p_coverage, p_justification);

	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE updateReimbursement(
	p_empId IN NUMBER,
    p_approverId IN NUMBER, 
	p_reimbursementDate IN VARCHAR2,
	p_reimbursementLocation VARCHAR2,
	p_description IN VARCHAR2,
	p_cost IN NUMBER,
	p_gradingFormat IN VARCHAR2,
	p_eventType IN VARCHAR2,
	p_coverage IN NUMBER, 
	p_justification IN VARCHAR2,
    p_reimbursementId IN NUMBER)
IS
BEGIN 
	UPDATE reimbursement
    SET 
		emp_id = p_empId,
		reimbursement_date = p_reimbursementDate,
		reimbursement_location = p_reimbursementLocation,
		description = p_description,
		cost = p_cost,
		grading_format = p_gradingFormat,
		event_type = p_eventType,
		coverage = p_coverage,
		justification = p_justification
    WHERE reimbursement_id = p_reimbursementId;    
		
	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertGradeReference(
	format IN VARCHAR2,
	requirement IN VARCHAR2,
	gradeRequirement IN VARCHAR2)
IS
BEGIN 
	INSERT INTO grade_reference (format, requirement, grade_requirement)
	VALUES (format, requirement, gradeRequirement);

	COMMIT;
END;
/

COMMIT;