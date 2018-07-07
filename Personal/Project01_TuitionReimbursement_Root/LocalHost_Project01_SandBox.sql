SELECT * FROM branch_manager;
DELETE FROM branch_manager WHERE branch_manager_id > 100;
commit;

CREATE TABLE branch_manager (
    branch_manager_id NUMBER(9) PRIMARY KEY,
    fname VARCHAR2(60) NOT NULL,
    lname VARCHAR(60) NOT NULL,
    phone NUMBER(20),
    email VARCHAR(60) NOT NULL,
    address VARCHAR(60),
    branch_manager_location VARCHAR(32)
);

CREATE TABLE ben_co (
    ben_co_id NUMBER(9) PRIMARY KEY,
    branch_manager_id NUMBER(9),
    fname VARCHAR2(60) NOT NULL,
    lname VARCHAR(60) NOT NULL,
    phone NUMBER(10),
    email VARCHAR(60) NOT NULL,
    address VARCHAR(60),
    ben_co_location VARCHAR(32),
    
    CONSTRAINT fk_branch_manager_id FOREIGN KEY (branch_manager_id) REFERENCES branch_manager (branch_manager_id)
);

CREATE TABLE head (
    head_id NUMBER(9) PRIMARY KEY,
    ben_co_id NUMBER(9),
    fname VARCHAR2(60) NOT NULL,
    lname VARCHAR(60) NOT NULL,
    phone NUMBER(10),
    email VARCHAR(60) NOT NULL,
    address VARCHAR(60),
    head_location VARCHAR(32),
    
    CONSTRAINT fk_ben_co_id FOREIGN KEY (ben_co_id) REFERENCES ben_co (ben_co_id)
);

CREATE TABLE supervisor (
    supv_id NUMBER(9) PRIMARY KEY,
    head_id NUMBER(9),
    fname VARCHAR2(60) NOT NULL,
    lname VARCHAR(60) NOT NULL,
    phone VARCHAR2(15),
    email VARCHAR(60) NOT NULL,
    address VARCHAR(60),
    supv_location VARCHAR(32),
    
    CONSTRAINT fk_head_id FOREIGN KEY (head_id) REFERENCES head (head_id)
); 

CREATE TABLE employee (
    emp_id NUMBER(9) PRIMARY KEY,
    supv_id NUMBER(9),
    fname VARCHAR2(60) NOT NULL,
    lname VARCHAR(60) NOT NULL,
    phone VARCHAR2(20),
    email VARCHAR(60) NOT NULL,
    address VARCHAR(60),
    emp_location VARCHAR(32),
    
    CONSTRAINT fk_supervisor_id FOREIGN KEY (supv_id) REFERENCES supervisor (supv_id)
);

CREATE TABLE reimbursement (
    reimbursement_id NUMBER(9) PRIMARY KEY,
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
    
    CONSTRAINT fd_emp_id FOREIGN KEY (emp_id) REFERENCES employee(emp_id)
);

CREATE TABLE grade_reference (
    format VARCHAR2(30),
    requirement VARCHAR2(30),
    grade_requirement VARCHAR2(30)
);
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
    IF :new.ben_co_id IS NULL THEN
        SELECT ben_co_seq.NEXTVAL INTO :new.ben_co_id FROM dual;
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
    IF :new.branch_manager_id IS NULL THEN
        SELECT branch_manager_seq.NEXTVAL INTO :new.branch_manager_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE PROCEDURE insertEmployee(
	supVId IN NUMBER,
	fName IN VARCHAR2,
	lName IN VARCHAR2,
	phone IN NUMBER,
	email IN VARCHAR2,
	address IN VARCHAR2,
	empLocation IN VARCHAR2)
IS
BEGIN 
	INSERT INTO employee (supv_id, fname, lname, phone, email, address, emp_location)
	VALUES (supVId, fName, lName, phone, email, address, empLocation);

	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertSupervisor(
	supVId IN NUMBER,
	headId IN NUMBER,
	fName IN VARCHAR2,
	lName IN VARCHAR2,
	phone IN VARCHAR2,
	email IN VARCHAR2,
	address IN VARCHAR2,
	supVLocation IN VARCHAR2)
IS
BEGIN 
	INSERT INTO supervisor (supv_id, head_id, fname, lname, phone, email, address, supv_location)
	VALUES (supVId, headId, fName, lName, phone, email, address, supVLocation);

	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertHead(
	headId IN NUMBER,
	benCoId IN NUMBER,
	fName IN VARCHAR2,
	lName IN VARCHAR2,
	phone IN VARCHAR2,
	email IN VARCHAR2,
	address IN VARCHAR2,
	headLocation IN VARCHAR2)
IS
BEGIN 
	INSERT INTO head (head_id, ben_co_id, fname, lname, phone, email, address, head_location)
	VALUES (headId, benCoId, fName, lName, phone, email, address, headLocation);

	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertBenCo(
	benCoId IN NUMBER,
	branchManagerId IN NUMBER,
	fName IN VARCHAR2,
	lName IN VARCHAR2,
	phone IN NUMBER,
	email IN VARCHAR2,
	address IN VARCHAR2,
	benCoLocation IN VARCHAR2)
IS
BEGIN 
	INSERT INTO ben_co (branch_manager_id, fname, lname, phone, email, address, ben_co_location)
	VALUES (branchManagerId, fName, lName, phone, email, address, benCoLocation);

	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertBranchManager(
	fName IN VARCHAR2,
	lName IN VARCHAR2,
	phone IN VARCHAR2,
	email IN VARCHAR2,
	address IN VARCHAR2,
	branchManagerLocation IN VARCHAR2)
IS
BEGIN 
	INSERT INTO branch_manager (fname, lname, phone, email, address, branch_manager_location)
	VALUES (fName, lName, phone, email, address, branchManagerLocation);

	COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertReimbursement(
	reimbursementId IN NUMBER,
	empId IN NUMBER,
	reimbursementDate IN DATE,
	reimbursementLocation VARCHAR2,
	description IN VARCHAR2,
	cost IN NUMBER,
	gradingFormat IN VARCHAR2,
	eventType IN VARCHAR2,
	coverage IN NUMBER, 
	justification IN VARCHAR2,
	attachment IN BLOB,
	emailApproval IN BLOB)
IS
BEGIN 
	INSERT INTO reimbursement (reimbursement_id, emp_id, reimbursement_date, reimbursement_location,
		description, cost, grading_format, event_type, coverage, justification, attachment, email_approval)
	VALUES (reimbursementId, empId, reimbursementDate, reimbursementLocation, description, cost,
		gradingFormat, eventType, coverage, justification, attachment, emailApproval);

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

commit;