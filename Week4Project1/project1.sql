DROP TABLE reimbursement CASCADE CONSTRAINTS;
DROP TABLE employee CASCADE CONSTRAINTS;
DROP TABLE event CASCADE CONSTRAINTS;
DROP TABLE documents CASCADE CONSTRAINTS;
DROP TABLE job_type CASCADE CONSTRAINTS;
DROP TABLE grading_format CASCADE CONSTRAINTS;
DROP TABLE approval CASCADE CONSTRAINTS;

DROP SEQUENCE emp_seq;
DROP SEQUENCE reim_seq;
DROP SEQUENCE doc_seq;

DROP TRIGGER emp_seq_trigger;
DROP TRIGGER reim_seq_trigger;
DROP TRIGGER doc_seq_trigger;

DROP PROCEDURE update_amount_left;
DROP PROCEDURE update_approval;
DROP PROCEDURE insert_into_reimbursement;

CREATE TABLE approval
(
    approval_id NUMBER(6),
    approval_name VARCHAR2(100),
    CONSTRAINT pk_approval_id PRIMARY KEY (approval_id)
);

CREATE TABLE grading_format
(
    grading_format_id NUMBER(6),
    grading_format_name VARCHAR2(100),
    presentation_required NUMBER(1),
    CONSTRAINT pk_grading_format_id PRIMARY KEY (grading_format_id)
);

CREATE TABLE job_type
(
    job_type_id NUMBER(6),
    job_type_name VARCHAR2(100),
    CONSTRAINT pk_job_type_id PRIMARY KEY (job_type_id)
);

CREATE TABLE documents
(
    doc_id NUMBER(6),
    doc_ext VARCHAR2(100),
    document_info BLOB,
    CONSTRAINT pk_doc_id PRIMARY KEY (doc_id)
);

CREATE TABLE event
(
    event_id NUMBER(6),
    event_name VARCHAR2(100),
    reimbursement_percent NUMBER(6),
    grading_format_id NUMBER(6),
    CONSTRAINT pk_event_id PRIMARY KEY (event_id),
    CONSTRAINT fk_grading_format_id FOREIGN KEY (grading_format_id) REFERENCES grading_format (grading_format_id)
);

CREATE TABLE employee
(
    emp_id NUMBER(6),
    emp_accountname VARCHAR2(100) UNIQUE,
    emp_password VARCHAR2(100),
    fname VARCHAR2(100),
    lname VARCHAR2(100),
    email VARCHAR2(100) UNIQUE,
    amount_left NUMBER(6),
    job_type_id NUMBER(6),
    CONSTRAINT pk_emp_id PRIMARY KEY (emp_id),
    CONSTRAINT fk_job_type_id FOREIGN KEY (job_type_id) REFERENCES job_type (job_type_id)
);

CREATE TABLE reimbursement
(
    r_id NUMBER(6),
    event_date VARCHAR2(100),
    event_time VARCHAR2(100),
    event_location VARCHAR2(100),
    event_desc VARCHAR2(100),
    event_cost NUMBER(6),
    justification VARCHAR2(100),
    grade_cutoff NUMBER(6),
    grade NUMBER(6),
    emp_id NUMBER(6),
    event_id NUMBER(6),
    grading_format_id NUMBER(6),
    doc_id NUMBER(6),
    approval_id NUMBER(6),
    CONSTRAINT pk_r_id PRIMARY KEY (r_id),
    CONSTRAINT fk_emp_id FOREIGN KEY (emp_id) REFERENCES employee (emp_id),
    CONSTRAINT fk_event_id FOREIGN KEY (event_id) REFERENCES event (event_id),
    CONSTRAINT fk_grading_format_id_2 FOREIGN KEY (grading_format_id) REFERENCES grading_format (grading_format_id),
    CONSTRAINT fk_doc_id FOREIGN KEY (doc_id) REFERENCES documents (doc_id),
    CONSTRAINT fk_approval_id FOREIGN KEY (approval_id) REFERENCES approval (approval_id)
);
/
CREATE SEQUENCE emp_seq
    START WITH 1
    INCREMENT BY 1;
/   
CREATE SEQUENCE reim_seq
    START WITH 1
    INCREMENT BY 1;
/    
CREATE SEQUENCE doc_seq
    START WITH 1
    INCREMENT BY 1;
/    
CREATE OR REPLACE TRIGGER emp_seq_trigger
BEFORE INSERT ON employee
FOR EACH ROW
BEGIN
    IF :new.emp_id IS NULL THEN
        SELECT emp_seq.NEXTVAL INTO :new.emp_id FROM dual;
    END IF;
END;
/    
CREATE OR REPLACE TRIGGER reim_seq_trigger
BEFORE INSERT ON reimbursement
FOR EACH ROW
BEGIN
    IF :new.r_id IS NULL THEN
        SELECT reim_seq.NEXTVAL INTO :new.r_id FROM dual;
    END IF;
END;
/
CREATE OR REPLACE TRIGGER doc_seq_trigger
BEFORE INSERT ON documents
FOR EACH ROW
BEGIN
    IF :new.doc_id IS NULL THEN
        SELECT doc_seq.NEXTVAL INTO :new.doc_id FROM dual;
    END IF;
END;
/

INSERT INTO approval
VALUES (0, 'Declined');
INSERT INTO approval
VALUES (1, 'Need Supervisor Approval');
INSERT INTO approval
VALUES (2, 'Need Department Head');
INSERT INTO approval
VALUES (3, 'Need Coordinator');
INSERT INTO approval
VALUES (4, 'Need Coordinator');
INSERT INTO approval
VALUES (5, 'Approved');

INSERT INTO grading_format
VALUES (1, 'Letter Grade', 0);
INSERT INTO grading_format
VALUES (2, 'Pass or Fail', 0);
INSERT INTO grading_format
VALUES (3, 'Attend', 1);

INSERT INTO job_type
VALUES (1, 'Normal Employee');
INSERT INTO job_type
VALUES (2, 'Direct Supervisor');
INSERT INTO job_type
VALUES (3, 'Department Head');
INSERT INTO job_type
VALUES (4, 'Direct Supervisor and Department Head');
INSERT INTO job_type
VALUES (5, 'Benefit Coordinator');

INSERT INTO event
VALUES (1, 'University Course', 80, 1);
INSERT INTO event
VALUES (2, 'Seminar', 60, 3);
INSERT INTO event
VALUES (3, 'Certification Preparation Course', 75, 2);
INSERT INTO event
VALUES (4, 'Certification', 100, 2);
INSERT INTO event
VALUES (5, 'Technical Training', 90, 3);
INSERT INTO event
VALUES (6, 'Other', 30, 3);

INSERT INTO documents
VALUES (null, null, null);

INSERT INTO employee
VALUES (null, 'loganbrewer', 'password', 'Logan', 'Brewer', 'logan@logan.com', 1000, 1);
INSERT INTO employee
VALUES (null, 'supervisor', 'password', 'Supervisor', 'Guy', 'surpervisor@supervisor.com', 1000, 2);
INSERT INTO employee
VALUES (null, 'dephead', 'password', 'Department', 'Head', 'dephead@dephead.com', 1000, 3);
INSERT INTO employee
VALUES (null, 'both', 'password', 'Both', 'Things', 'both@both.com', 1000, 4);
INSERT INTO employee
VALUES (null, 'benco', 'password', 'Ben', 'Co', 'benco@benco.com', 1000, 5);

INSERT INTO reimbursement
VALUES (null, '01-JAN-2018', '00:00', 'Arlington, TX', 'employee declined', 100, 'emp declined', 0, 0, 1, 5, 1, null, 0);
INSERT INTO reimbursement
VALUES (null, '02-FEB-2018', '01:00', 'Arlington, TX', 'employee need a supervisor', 100, 'emp need Supervisor approval', 0, 0, 1, 4, 2, null, 1);
INSERT INTO reimbursement
VALUES (null, '03-MAR-2018', '02:00', 'Arlington, TX', 'employee need a department head', 100, 'emp need DepHead approval', 0, 0, 1, 3, 3, null, 2);
INSERT INTO reimbursement
VALUES (null, '04-APR-2018', '03:00', 'Arlington, TX', 'employee need a BenCo', 100, 'emp need BenCo approval', 0, 0, 1, 2, 1, null, 3);
INSERT INTO reimbursement
VALUES (null, '05-MAY-2018', '04:00', 'Arlington, TX', 'employee approved', 100, 'emp approved', 0, 0, 1, 1, 2, null, 5);

INSERT INTO reimbursement
VALUES (null, '01-JAN-2018', '00:00', 'Arlington, TX', 'supervisor declined', 100, 'sup declined', 0, 0, 2, 5, 1, null, 0);
INSERT INTO reimbursement
VALUES (null, '03-MAR-2018', '02:00', 'Arlington, TX', 'supervisor need a department head', 100, 'sup need DepHead approval', 0, 0, 2, 3, 3, null, 2);
INSERT INTO reimbursement
VALUES (null, '04-APR-2018', '03:00', 'Arlington, TX', 'supervisor need a BenCo', 100, 'sup need BenCo approval', 0, 0, 2, 2, 1, null, 3);
INSERT INTO reimbursement
VALUES (null, '05-MAY-2018', '04:00', 'Arlington, TX', 'supervisor approved', 100, 'sup get approved', 0, 0, 2, 1, 2, null, 5);

INSERT INTO reimbursement
VALUES (null, '01-JAN-2018', '00:00', 'Arlington, TX', 'dephead declined', 100, 'dephead declined', 0, 0, 3, 5, 1, null, 0);
INSERT INTO reimbursement
VALUES (null, '04-APR-2018', '03:00', 'Arlington, TX', 'dephead need a BenCo', 100, 'dephead need BenCo approval', 0, 0, 3, 2, 1, null, 3);
INSERT INTO reimbursement
VALUES (null, '05-MAY-2018', '04:00', 'Arlington, TX', 'dephead approved', 100, 'dephead get approved', 0, 0, 3, 1, 2, null, 5);

INSERT INTO reimbursement
VALUES (null, '01-JAN-2018', '00:00', 'Arlington, TX', 'both declined', 100, 'both declined', 0, 0, 4, 5, 1, null, 0);
INSERT INTO reimbursement
VALUES (null, '04-APR-2018', '03:00', 'Arlington, TX', 'both need a BenCo', 100, 'both need BenCo approval', 0, 0, 4, 2, 1, null, 4);
INSERT INTO reimbursement
VALUES (null, '05-MAY-2018', '04:00', 'Arlington, TX', 'both approved', 100, 'both approved', 0, 0, 4, 1, 2, null, 5);

INSERT INTO reimbursement
VALUES (null, '06-JUN-2018', '05:00', 'Arlington, TX', 'benco approved', 100, 'benco approved', 0, 0, 5, 1, 2, null, 5);

CREATE OR REPLACE PROCEDURE update_amount_left(empId IN employee.emp_id%TYPE,
                                               newAmountLeft IN employee.amount_left%TYPE)
IS
BEGIN
    UPDATE employee SET amount_left = newAmountLeft WHERE emp_id = empId;
    COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE update_approval(empId IN reimbursement.emp_id%TYPE,
                                            approvalId IN reimbursement.approval_id%TYPE)
IS
BEGIN
    UPDATE reimbursement SET approval_id = approvalId WHERE emp_id = empId;
    COMMIT;
END;
/
CREATE OR REPLACE PROCEDURE insert_into_reimbursement(rId IN reimbursement.r_id%TYPE,
                                                      eventDate IN reimbursement.event_date%TYPE,
                                                      eventTime IN reimbursement.event_time%TYPE,
                                                      eventLocation IN reimbursement.event_location%TYPE,
                                                      eventDesc IN reimbursement.event_desc%TYPE,
                                                      eventCost IN reimbursement.event_cost%TYPE,
                                                      justificationVar IN reimbursement.justification%TYPE,
                                                      gradeCutoff IN reimbursement.grade_cutoff%TYPE,
                                                      gradeVar IN reimbursement.grade%TYPE,
                                                      empId IN reimbursement.emp_id%TYPE,
                                                      eventId IN reimbursement.event_id%TYPE,
                                                      gradingFormatId IN reimbursement.grading_format_id%TYPE,
                                                      docId IN reimbursement.doc_id%TYPE,
                                                      approvalId IN reimbursement.approval_id%TYPE)
IS
BEGIN
    INSERT INTO reimbursement (r_id, event_date,
                               event_time, event_location,
                               event_desc, event_cost,
                               justification, grade_cutoff,
                               grade, emp_id,
                               event_id, grading_format_id,
                               doc_id, approval_id)
    VALUES (rId, eventDate, eventTime, eventLocation,
            eventDesc, eventCost, justificationVar, 
            gradeCutoff, gradeVar, empId,
            eventId, gradingFormatId, 
            docId, approvalId);
    COMMIT;
END;
/   

COMMIT;