DROP TABLE reimbursement CASCADE CONSTRAINTS;
DROP TABLE employee CASCADE CONSTRAINTS;
DROP TABLE event CASCADE CONSTRAINTS;
DROP TABLE documents CASCADE CONSTRAINTS;
DROP TABLE job_type CASCADE CONSTRAINTS;
DROP TABLE grading_format CASCADE CONSTRAINTS;
DROP TABLE approval CASCADE CONSTRAINTS;

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
    event_date DATE,
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

INSERT INTO approval
VALUES (0, 'Declined');
INSERT INTO approval
VALUES (1, 'Need Supervisor Approval');
INSERT INTO approval
VALUES (2, 'Need Department Head');
INSERT INTO approval
VALUES (3, 'Need Coordinator');
INSERT INTO approval
VALUES (4, 'Approved');

INSERT INTO grading_format
VALUES (1, 'Letter Grade', 0);
INSERT INTO grading_format
VALUES (2, 'Pass or Fail', 0);
INSERT INTO grading_format
VALUES (3, 'Attend', 1);

INSERT INTO job_type
VALUES (1, 'Benefit Coordinator');
INSERT INTO job_type
VALUES (2, 'Direct Supervisor and Department Head');
INSERT INTO job_type
VALUES (3, 'Department Head');
INSERT INTO job_type
VALUES (4, 'Direct Supervisor');
INSERT INTO job_type
VALUES (5, 'Normal Employee');

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

--SELECT * FROM approval;
--SELECT * FROM grading_format;
--SELECT * FROM job_type;
--SELECT * FROM documents;
--SELECT * FROM event;
--SELECT * FROM employee;
--SELECT * FROM reimbursement;

INSERT INTO employee
VALUES (1, 'loganbrewer', 'password', 'Logan', 'Brewer', 'logan@logan.com', 1000, 5);
INSERT INTO employee
VALUES (2, 'supervisor', 'password', 'Supervisor', 'Guy', 'surpervisor@supervisor.com', 1000, 4);
INSERT INTO employee
VALUES (3, 'dephead', 'password', 'Department', 'Head', 'dephead@dephead.com', 1000, 3);
INSERT INTO employee
VALUES (4, 'both', 'password', 'Both', 'Things', 'both@both.com', 1000, 2);
INSERT INTO employee
VALUES (5, 'benco', 'password', 'Ben', 'Co', 'benco@benco.com', 1000, 1);
INSERT INTO reimbursement
VALUES (1, '05-JAN-2018', '15:00', 'Arlington, TX', 'learn stuff', 100, 'gotta learn', 0, 0, 1, 5, 2, null, 0);

COMMIT;

CREATE OR REPLACE PROCEDURE update_amount_left(empId IN employee.emp_id%TYPE,
                                               newAmountLeft IN employee.amount_left%TYPE)
IS
BEGIN
    UPDATE employee SET amount_left = newAmountLeft WHERE emp_id = empId;
    COMMIT;
END;


--CALL update_amount_left(1, 900);
--SELECT * FROM employee WHERE emp_accountname = 'loganbrewer';
--SELECT * FROM employee;
--SELECT job_type_id FROM employee WHERE emp_accountname = 'loganbrewer';