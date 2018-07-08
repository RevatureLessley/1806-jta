DROP TABLE ben_co CASCADE CONSTRAINTS;
DROP TABLE branch_manager CASCADE CONSTRAINTS;
DROP TABLE employee CASCADE CONSTRAINTS;
DROP TABLE grade_reference CASCADE CONSTRAINTS;
DROP TABLE head CASCADE CONSTRAINTS;
DROP TABLE reimbursement CASCADE CONSTRAINTS;
DROP TABLE supervisor CASCADE CONSTRAINTS;
DROP TABLE document CASCADE CONSTRAINTS;
DROP TABLE approval_email CASCADE CONSTRAINTS;
DROP TABLE contact_info CASCADE CONSTRAINTS;

SELECT id FROM contact_info WHERE contact_info.fname='Jerod' 
    AND contact_info.lname='Belford';
    
SELECT * FROM branch_manager;
SELECT * FROM ben_co;
SELECT * FROM head;
SELECT * FROM supervisor;
SELECT * FROM employee;
SELECT * FROM contact_info;

CREATE TABLE contact_info(
    id NUMBER(9) PRIMARY KEY,
    fname VARCHAR2(60) NOT NULL,
    lname VARCHAR2(60) NOT NULL,
    phone NUMBER(20),
    email VARCHAR(60) NOT NULL,
    address VARCHAR(60),
    location VARCHAR(60)
);

CREATE TABLE branch_manager (
    bm_id NUMBER(9) PRIMARY KEY,
    cont_id NUMBER(9),
    
    CONSTRAINT fk_bm_cont_i_id FOREIGN KEY (cont_id) 
    REFERENCES contact_info (id)
);

CREATE TABLE ben_co (
    benco_id NUMBER(9) PRIMARY KEY,
    bm_id NUMBER(9),
    cont_id NUMBER(9),
    
    CONSTRAINT fk_branch_manager_id FOREIGN KEY (bm_id) 
    REFERENCES branch_manager (bm_id),
    CONSTRAINT fk_bc_cont_i_id FOREIGN KEY (cont_id) 
    REFERENCES contact_info (id)  
);

CREATE TABLE head (
    head_id NUMBER(9) PRIMARY KEY,
    benco_id NUMBER(9),
    cont_id NUMBER(9),
    
    CONSTRAINT fk_ben_co_id FOREIGN KEY (benco_id) REFERENCES ben_co (benco_id),
    CONSTRAINT fk_hd_cont_i_id FOREIGN KEY (cont_id) 
    REFERENCES contact_info (id)
);

CREATE TABLE supervisor (
    supv_id NUMBER(9) PRIMARY KEY,
    head_id NUMBER(9),
    cont_id NUMBER(9),
    
    CONSTRAINT fk_head_id FOREIGN KEY (head_id) REFERENCES head (head_id),
    CONSTRAINT fk_sp_cont_i_id FOREIGN KEY (cont_id) 
    REFERENCES contact_info (id)
); 

CREATE TABLE employee (
    emp_id NUMBER(9) PRIMARY KEY,
    supv_id NUMBER(9),
    cont_id NUMBER(9),
    
    CONSTRAINT fk_supervisor_id FOREIGN KEY (supv_id) REFERENCES supervisor (supv_id),
    CONSTRAINT fk_em_cont_i_id FOREIGN KEY (cont_id) 
    REFERENCES contact_info (id)
);

CREATE TABLE document (
    id NUMBER(9) PRIMARY KEY,
    name VARCHAR2(60),
    type VARCHAR2(30),
    date_craeted DATE,
    author VARCHAR2(30),
    img BLOB
);

CREATE TABLE approval_email (
    id NUMBER(9) PRIMARY KEY,
    approver_id NUMBER(9)
);

CREATE TABLE reimbursement (
    reimbursement_id NUMBER(9) PRIMARY KEY,
    doc_id NUMBER(9),
    aprov_em_id NUMBER(9),
    emp_id NUMBER(9) NOT NULL,
    reimbursement_date DATE NOT NULL,
    reimbursement_location VARCHAR2(32) NOT NULL,
    description VARCHAR2(100) NOT NULL,
    cost NUMBER(12) NOT NULL,
    grading_format VARCHAR(32) NOT NULL,
    event_type VARCHAR(32) NOT NULL,
    coverage NUMBER(9) NOT NULL,
    justification VARCHAR(100) NOT NULL,
    attachment BLOB,
    email_approval BLOB,
    
    CONSTRAINT fk_ri_emp_id FOREIGN KEY (emp_id) REFERENCES employee(emp_id),
    CONSTRAINT fk_ri_doc_id FOREIGN KEY (doc_id) REFERENCES document(id), 
    CONSTRAINT fk_ri_aprov_em_id FOREIGN KEY (aprov_em_id) REFERENCES approval_email(id)
);

CREATE TABLE grade_reference (
    format VARCHAR2(30),
    requirement VARCHAR2(30),
    grade_requirement VARCHAR2(30)
);
/

DROP SEQUENCE cont_seq;
CREATE SEQUENCE cont_seq
    START WITH 100
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER cont_seq_trigger
BEFORE INSERT ON contact_info --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.id IS NULL THEN
        SELECT cont_seq.NEXTVAL INTO :new.id FROM dual;
    END IF;
END;
/

DROP SEQUENCE emp_seq;
CREATE SEQUENCE emp_seq
    START WITH 100
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER emp_seq_trigger
BEFORE INSERT ON employee --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.emp_id IS NULL THEN
        SELECT emp_seq.NEXTVAL INTO :new.emp_id FROM dual;
    END IF;
END;
/

DROP SEQUENCE supv_seq;
CREATE SEQUENCE supv_seq
    START WITH 100
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER supv_seq_trigger
BEFORE INSERT ON supervisor --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.supv_id IS NULL THEN
        SELECT supv_seq.NEXTVAL INTO :new.supv_id FROM dual;
    END IF;
END;
/

DROP SEQUENCE head_seq;
CREATE SEQUENCE head_seq
    START WITH 100
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER head_seq_trigger
BEFORE INSERT ON head --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.head_id IS NULL THEN
        SELECT head_seq.NEXTVAL INTO :new.head_id FROM dual;
    END IF;
END;
/

DROP SEQUENCE ben_co_seq;
CREATE SEQUENCE ben_co_seq
    START WITH 100
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER ben_co_seq_trigger
BEFORE INSERT ON ben_co --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.benco_id IS NULL THEN
        SELECT ben_co_seq.NEXTVAL INTO :new.benco_id FROM dual;
    END IF;
END;
/

DROP SEQUENCE branch_manager_seq;
CREATE SEQUENCE branch_manager_seq
    START WITH 100
    INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER branch_managerseq_trigger
BEFORE INSERT ON branch_manager --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.bm_id IS NULL THEN
        SELECT branch_manager_seq.NEXTVAL INTO :new.bm_id FROM dual;
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

CREATE OR REPLACE PROCEDURE insertEmployee(
	p_supVId IN NUMBER,
	p_fName IN VARCHAR2,
	p_lName IN VARCHAR2,
	p_phone IN NUMBER,
	p_email IN VARCHAR2,
	p_address IN VARCHAR2,
	p_location IN VARCHAR2)
IS
BEGIN 
    INSERT INTO contact_info(fname, lname, phone, email, address, location)
    VALUES (p_fName, p_lName, p_phone, p_email, p_address, p_location);
	INSERT INTO employee (supv_id, cont_id)
	VALUES (p_supVId, (SELECT id FROM contact_info WHERE fname=p_fname 
    AND lname=p_lname));

	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertSupervisor(
	p_headId IN NUMBER,
	p_fName IN VARCHAR2,
	p_lName IN VARCHAR2,
	p_phone IN VARCHAR2,
	p_email IN VARCHAR2,
	p_address IN VARCHAR2,
	p_location IN VARCHAR2)
IS
BEGIN 
	INSERT INTO contact_info(fname, lname, phone, email, address, location)
    VALUES (p_fName, p_lName, p_phone, p_email, p_address, p_location);
	INSERT INTO supervisor (head_id, cont_id)
	VALUES (p_headId, (SELECT id FROM contact_info WHERE fname=p_fname 
    AND lname=p_lname));

	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertHead(
	p_benCoId IN NUMBER,
	p_fName IN VARCHAR2,
	p_lName IN VARCHAR2,
	p_phone IN NUMBER,
	p_email IN VARCHAR2,
	p_address IN VARCHAR2,
	p_location IN VARCHAR2)
IS
BEGIN 
	INSERT INTO contact_info(fname, lname, phone, email, address, location)
    VALUES (p_fName, p_lName, p_phone, p_email, p_address, p_location);
	INSERT INTO head (benco_id, cont_id)
	VALUES (p_benCoId, (SELECT id FROM contact_info WHERE fname=p_fname 
    AND lname=p_lname));

	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertBenCo(
	p_branchManagerId IN NUMBER,
	p_fName IN VARCHAR2,
	p_lName IN VARCHAR2,
	p_phone IN NUMBER,
	p_email IN VARCHAR2,
	p_address IN VARCHAR2,
	p_location IN VARCHAR2)
IS
BEGIN 
	INSERT INTO contact_info(fname, lname, phone, email, address, location)
    VALUES (p_fName, p_lName, p_phone, p_email, p_address, p_location);
	INSERT INTO ben_co (bm_id, cont_id)
	VALUES (p_branchManagerId, (SELECT id FROM contact_info WHERE fname=p_fName 
    AND lname=p_lName));

	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertBranchManager(
	p_fName IN VARCHAR2,
	p_lName IN VARCHAR2,
	p_phone IN VARCHAR2,
	p_email IN VARCHAR2,
	p_address IN VARCHAR2,
	p_location IN VARCHAR2)
IS
BEGIN 
	INSERT INTO contact_info(fname, lname, phone, email, address, location)
    VALUES (p_fName, p_lName, p_phone, p_email, p_address, p_location);
	INSERT INTO branch_manager (cont_id)
	VALUES ((SELECT id FROM contact_info WHERE fname=p_fname 
    AND lname=p_lname));

	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertReimbursement(
	empId IN NUMBER,
	reimbursementDate IN DATE,
	reimbursementLocation VARCHAR2,
	description IN VARCHAR2,
	cost IN NUMBER,
	gradingFormat IN VARCHAR2,
	eventType IN VARCHAR2,
	coverage IN NUMBER, 
	justification IN VARCHAR2)
IS
BEGIN 
	INSERT INTO reimbursement (emp_id, reimbursement_date, reimbursement_location,
		description, cost, grading_format, event_type, coverage, justification)
	VALUES (empId, reimbursementDate, reimbursementLocation, description, cost,
		gradingFormat, eventType, coverage, justification);

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