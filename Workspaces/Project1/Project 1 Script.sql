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
    reportsto NUMBER(6),
    first_name VARCHAR2(100),
    last_name VARCHAR2(100),
    phone NUMBER(15),
    available_reim NUMBER(6),
    pending_reim NUMBER(6),
    awarded_reim NUMBER(6),
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

CREATE OR REPLACE TRIGGER emp_pend_trigger
AFTER INSERT ON reimbursement
FOR EACH ROW
BEGIN    
    UPDATE employee
    SET pending_reim = 25;
        
END;
    
    
    
        


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

CALL INSERTINTOREIM(1,
                    TO_DATE('11/22/2018' , 'MM/DD/YYYY'),
                    TO_DATE('11/22/2018' , 'MM/DD/YYYY'),
                    'HERE',
                    'DESC',
                    100,
                    1,
                    1,
                    'justification',
                    90);
                                            
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
    


INSERT INTO employee VALUES(1,'test@test.com','test',1,1,'fname','lname',1111111111,1000,0,0);
INSERT INTO employee VALUES(2,'dp@test.com','test',2,2,'dp','dpl',1111111111,1000,0,0);
SELECT * FROM employee WHERE email = 'test@test.com';

SELECT * FROM employee;
SELECT * FROM reimbursement;
SELECT * FROM employee_role;
SELECT * FROM grading_format;
SELECT * FROM training_type;

SELECT sum(r.cost * coverage) FROM reimbursement r
INNER JOIN training_type t 
ON r.train_type_id = t.train_id
WHERE r.EMPLOYEE_ID=1;

COMMIT;