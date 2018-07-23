DROP TABLE employee CASCADE CONSTRAINTS;
DROP TABLE reimbursement CASCADE CONSTRAINTS;
DROP TABLE grading_format CASCADE CONSTRAINTS;
DROP TABLE training_type CASCADE CONSTRAINTS;
DROP TABLE employee_role CASCADE CONSTRAINTS;

CREATE TABLE employee_role(
    role_id NUMBER(6),
    role VARCHAR2(100),
    CONSTRAINT pk_role_id PRIMARY KEY (role_id)
);
/

CREATE TABLE grading_format(
    format_id NUMBER(6),
    format_type VARCHAR2(100),
    CONSTRAINT pk_grade_id PRIMARY KEY (format_id)
);
/

CREATE TABLE training_type(
    train_id NUMBER(6),
    train_type VARCHAR2(100),
    coverage FLOAT,
    CONSTRAINT pk_train_id PRIMARY KEY (train_id)
);
/

CREATE TABLE employee(
    employee_id NUMBER(6),
    email VARCHAR2(100),
    password VARCHAR2(100),
    role_id NUMBER(6),
    first_name VARCHAR2(100),
    last_name VARCHAR2(100),
    phone NUMBER(15),
    available_reim NUMBER(6),
    pending_reim NUMBER(6),
    awarded_reim NUMBER(6),
    supervisor_id NUMBER(6),
    d_head_id NUMBER(6),
    benco_id NUMBER(6),
    CONSTRAINT pk_emp_id PRIMARY KEY (employee_id),
    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES employee_role (role_id)
);
/

CREATE TABLE reimbursement(
    reim_id NUMBER(6),
    employee_id NUMBER(6),
    reim_date DATE,
    start_date DATE,
    train_location VARCHAR2(100),
    description VARCHAR2(100),
    cost NUMBER(6),
    grading_format_id NUMBER(6),
    train_type_id NUMBER(6),
    justification VARCHAR2(100),
    passing_grade NUMBER(6),
    ds_approval NUMBER DEFAULT 0,
    dh_approval NUMBER DEFAULT 0,
    benco_approval NUMBER DEFAULT 0,
    urgent NUMBER,
    projected_coverage NUMBER(6),
    CONSTRAINT pk_reim_id PRIMARY KEY (reim_id),
    CONSTRAINT fk_emp_id FOREIGN KEY (employee_id) REFERENCES employee (employee_id),
    CONSTRAINT fk_grade_form_id FOREIGN KEY (grading_format_id) REFERENCES grading_format (format_id),
    CONSTRAINT fk_train_type FOREIGN KEY (train_type_id) REFERENCES training_type (train_id)
);
/


DROP SEQUENCE reim_seq;
CREATE SEQUENCE reim_seq
    START WITH 100
    INCREMENT BY 1;
/
    
CREATE OR REPLACE TRIGGER reim_seq_trigger
BEFORE INSERT ON reimbursement
FOR EACH ROW
BEGIN
    IF :new.reim_id IS NULL THEN
        SELECT reim_seq.NEXTVAL INTO :new.reim_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER reim_start_trigger
BEFORE INSERT ON reimbursement
FOR EACH ROW
BEGIN
    IF :new.start_date - :new.reim_date < 7 
    THEN
        raise_application_error(-20000, 'Training start date is within a week');
    END IF;
END;
/

CREATE OR REPLACE TRIGGER reim_urgent_trigger
BEFORE INSERT ON reimbursement
FOR EACH ROW
BEGIN
    IF :new.urgent IS NULL THEN
        IF (:new.start_date - :new.reim_date) < 14 THEN
            :new.urgent := 1;
        ELSE :new.urgent := 0;
        END IF;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER reim_coverage_trigger
BEFORE INSERT ON reimbursement
FOR EACH ROW
DECLARE 
    x NUMBER;
    ar NUMBER;
BEGIN
    SELECT coverage INTO X FROM training_type
    WHERE train_id = :new.train_type_id;
    
    SELECT available_reim INTO ar FROM employee
    WHERE employee_id = :new.employee_id;
    
    IF :new.projected_coverage IS NULL THEN
        IF :new.cost*x < ar
        THEN
            :new.projected_coverage := :new.cost*x;
        ELSE
            :new.projected_coverage := ar;
        END IF;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER emp_avail_trigger
AFTER INSERT ON reimbursement
FOR EACH ROW
BEGIN 
    UPDATE employee 
    SET available_reim = available_reim - :new.projected_coverage
    WHERE employee_id = :new.employee_id;
END;
/


CREATE OR REPLACE TRIGGER emp_pend_trigger
AFTER INSERT ON reimbursement
FOR EACH ROW
DECLARE x NUMBER;
BEGIN  
    SELECT pending_reim INTO x FROM employee
    WHERE employee_id = :new.employee_id;
    
    IF x + :new.projected_coverage <= 1000
    THEN
        UPDATE employee
        SET pending_reim = pending_reim + :new.projected_coverage
        WHERE employee_id = :new.employee_id;    
    ELSE
        UPDATE employee
        SET pending_reim = 1000
        WHERE employee_id = :new.employee_id;
    END IF;    
END;
/   

CREATE OR REPLACE TRIGGER emp_award_trigger
AFTER UPDATE OF benco_approval ON reimbursement 
FOR EACH ROW
BEGIN    
    IF :new.benco_approval = 1
    THEN
        UPDATE employee
        SET awarded_reim = awarded_reim + :old.projected_coverage,
            pending_reim = pending_reim - :old.projected_coverage       
        WHERE employee_id = :new.employee_id;
    END IF;
END;
/  

CREATE OR REPLACE PROCEDURE insertIntoReim(   
                                            empId IN NUMBER,
                                            reimDate IN DATE,
                                            startDate IN DATE,
                                            trainLoc IN VARCHAR2,
                                            descrip IN VARCHAR2,
                                            cost IN NUMBER,
                                            gradeFormat IN NUMBER,
                                            trainType IN NUMBER,
                                            justification IN VARCHAR2,
                                            grade IN NUMBER
                                            )
IS 
BEGIN
    INSERT INTO reimbursement(employee_id, 
                                reim_date, 
                                start_date, 
                                train_location, 
                                description, 
                                cost,
                                grading_format_id,
                                train_type_id,
                                justification,
                                passing_grade
                                )
        VALUES(empId, reimDate, startDate, trainLoc, descrip, cost, gradeFormat, trainType, justification, grade);
        COMMIT;
END;
/


                                            
INSERT INTO employee_role VALUES(1,'employee');
INSERT INTO employee_role VALUES(2,'supervisor');
INSERT INTO employee_role VALUES(3,'dept head');
INSERT INTO employee_role VALUES(4,'benco');

INSERT INTO grading_format VALUES(1, 'grade');
INSERT INTO grading_format VALUES(2, 'presentation');

INSERT INTO training_type VALUES(1, 'university course', 0.8);
INSERT INTO training_type VALUES(2, 'seminar', 0.6);
INSERT INTO training_type VALUES(3, 'certification prep', 0.75);
INSERT INTO training_type VALUES(4, 'certification', 1);
INSERT INTO training_type VALUES(5, 'technical training', 0.9);
INSERT INTO training_type VALUES(6, 'other', 0.3);
    


INSERT INTO employee VALUES(1,'test@test.com','test',1,'fname','lname',0000000000,1000,0,0,100,200,300);
INSERT INTO employee VALUES(2,'e2@test.com','test',1,'e2','e2l',0000000000,1000,0,0,101,201,300);
INSERT INTO employee VALUES(3,'e3@test.com','test',1,'e3','e3l',0000000000,1000,0,0,102,200,300);
INSERT INTO employee VALUES(4,'e4@test.com','test',1,'e4','e4l',0000000000,1000,0,0,100,201,300);
INSERT INTO employee VALUES(5,'e5@test.com','test',1,'e5','e5l',0000000000,1000,0,0,101,200,300);
INSERT INTO employee VALUES(100,'s1@test.com','test',2,'s1','s1l',1111111111,1000,0,0,100,200,300);
INSERT INTO employee VALUES(101,'s2@test.com','test',2,'s2','s2l',1111111111,1000,0,0,101,201,300);
INSERT INTO employee VALUES(102,'s3@test.com','test',2,'s3','s3l',1111111111,1000,0,0,102,201,300);
INSERT INTO employee VALUES(200,'dh@test.com','test',3,'dh1','dhl',2222222222,1000,0,0,200,200,300);
INSERT INTO employee VALUES(201,'dh2@test.com','test',3,'dh2','dhl',222222222,1000,0,0,201,201,300);
INSERT INTO employee VALUES(300,'benco@test.com','test',4,'benco','bencol',3333333333,1000,0,0,300,300,300);

COMMIT;



SELECT * FROM employee WHERE d_head_id = 200 AND supervisor_id != 200;
SELECT * FROM employee WHERE benco_id = 300 AND d_head_id != 300;

CALL INSERTINTOREIM(1,
                    TO_DATE('07/21/2018' , 'MM/DD/YYYY'),
                    TO_DATE('07/22/2018' , 'MM/DD/YYYY'),
                    'Test',
                    'Test',
                    1000,
                    1,
                    1,
                    'justification',
                    90);

SELECT * FROM employee;
SELECT * FROM reimbursement;
SELECT * FROM employee_role;
SELECT * FROM grading_format;
SELECT * FROM training_type;

SELECT sum(r.cost * coverage) FROM reimbursement r
INNER JOIN training_type t 
ON r.train_type_id = t.train_id
WHERE r.EMPLOYEE_ID=1;

SELECT r.*, e.first_name,e.last_name FROM reimbursement r
INNER JOIN employee e
ON r.employee_id = e.employee_id
WHERE e.benco_id = 300 AND benco_approval = 0;

update REIMBURSEMENT
set benco_approval = 1
where reim_id = 100;

select (start_date - reim_date) from REIMBURSEMENT where reim_id = 115;
            
SELECT * FROM reimbursement WHERE employee_id = 1;

Select coverage from TRAINING_TYPE where TRAIN_id = 3;

TRUNCATE TABLE employee;
TRUNCATE TABLE reimbursement;

COMMIT;