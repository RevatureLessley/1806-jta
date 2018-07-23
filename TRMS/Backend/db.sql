DROP TABLE GradeAttachment CASCADE CONSTRAINTS;
DROP TABLE FileAttachment CASCADE CONSTRAINTS;
DROP TABLE Supervisor_Approval CASCADE CONSTRAINTS;
DROP TABLE DeptHead_Approval CASCADE CONSTRAINTS;
DROP TABLE BenCo_Approval CASCADE CONSTRAINTS;
DROP TABLE Request CASCADE CONSTRAINTS;
DROP TABLE EventType CASCADE CONSTRAINTS;
DROP TABLE GradeFormat CASCADE CONSTRAINTS;
DROP TABLE Password CASCADE CONSTRAINTS;
DROP TABLE Employee CASCADE CONSTRAINTS;
DROP TABLE Department CASCADE CONSTRAINTS;
DROP TABLE Title CASCADE CONSTRAINTS;

CREATE TABLE Title (
  title_id   NUMBER(3),
  title_name VARCHAR2(256),
  CONSTRAINT pk_title_id PRIMARY KEY (title_id)
);

CREATE TABLE Department (
  dept_id   NUMBER(3),
  dept_name VARCHAR2(40) UNIQUE NOT NULL,
  dept_abbr VARCHAR2(10) UNIQUE NOT NULL,
  dept_head NUMBER(6) UNIQUE NOT NULL,
  CONSTRAINT pk_dept_id PRIMARY KEY (dept_id)
);

CREATE TABLE Employee (
  employee_id NUMBER(6),
  fname       VARCHAR2(20)         NOT NULL,
  lname       VARCHAR2(20)         NOT NULL,
  email       VARCHAR2(256) UNIQUE NOT NULL, -- MAX Length a email can be per spec
  title_id    NUMBER(3)            NOT NULL,
  dept_id     NUMBER(3),
  boss_id     NUMBER(6),
  CONSTRAINT pk_employee_id PRIMARY KEY (employee_id),
  CONSTRAINT fk_boss_id FOREIGN KEY (boss_id) REFERENCES Employee (employee_id),
  CONSTRAINT fk_title_id FOREIGN KEY (title_id) REFERENCES Title (title_id),
  CONSTRAINT fk_dept_id FOREIGN KEY (dept_id) REFERENCES Department (dept_id)
);

CREATE TABLE Password (
  employee_id   NUMBER(6),
  password_hash VARCHAR2(64), --SHA-256
  CONSTRAINT pk_password_employee_id PRIMARY KEY (employee_id),
  CONSTRAINT fk_password_employee_id FOREIGN KEY (employee_id) REFERENCES Employee (employee_id)
);

CREATE TABLE GradeFormat (
  grade_format_id   NUMBER(2),
  grade_format_desc VARCHAR2(256) NOT NULL,
  CONSTRAINT pk_grade_format_id PRIMARY KEY (grade_format_id)
);

CREATE TABLE EventType (
  event_type_id      NUMBER(3),
  event_type_name    VARCHAR2(256) UNIQUE,
  reimbursement_rate NUMBER(3) NOT NULL,
  CONSTRAINT pk_event_type_id PRIMARY KEY (event_type_id)
);

CREATE TABLE Request (
  request_id          NUMBER(10),
  employee_id         NUMBER(6),
  event_name      VARCHAR2(256),
  event_location      VARCHAR2(256),
  event_time          TIMESTAMP     NOT NULL,
  event_cost          NUMBER(6, 2)  NOT NULL,
  event_type_id   NUMBER(3),
  grade_format_id NUMBER(3),
  request_time        TIMESTAMP     NOT NULL,
  justification       VARCHAR2(256) NOT NULL,
  amount              NUMBER(6,2),
  CONSTRAINT pk_request_id PRIMARY KEY (request_id),
  CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES Employee (employee_id)
);

CREATE TABLE Supervisor_Approval (
  request_id          NUMBER(10),
  approver_id         NUMBER(6),
  approved            NUMBER(1),
  message             VARCHAR2(256),
  CONSTRAINT pk_sa_request_id PRIMARY KEY (request_id),
  CONSTRAINT fk_sa_request_id FOREIGN KEY (request_id) REFERENCES Request(request_id),
  CONSTRAINT fk_sa_approver_id FOREIGN KEY (approver_id) REFERENCES Employee(employee_id)
);

CREATE TABLE DeptHead_Approval (
  request_id          NUMBER(10),
  approver_id         NUMBER(6),
  approved            NUMBER(1),
  message             VARCHAR2(256),
  CONSTRAINT pk_dh_request_id PRIMARY KEY (request_id),
  CONSTRAINT fk_dh_request_id FOREIGN KEY (request_id) REFERENCES Request(request_id),
  CONSTRAINT fk_dh_approver_id FOREIGN KEY (approver_id) REFERENCES Employee(employee_id)
);

CREATE TABLE BenCo_Approval (
  request_id          NUMBER(10),
  approver_id         NUMBER(6),
  approved            NUMBER(1),
  message             VARCHAR2(256),
  CONSTRAINT pk_bc_request_id PRIMARY KEY (request_id),
  CONSTRAINT fk_bc_request_id FOREIGN KEY (request_id) REFERENCES Request(request_id),
  CONSTRAINT fk_bc_approver_id FOREIGN KEY (approver_id) REFERENCES Employee(employee_id)
);

CREATE TABLE FileAttachment (
  attachment_id NUMBER(10),
  request_id    NUMBER(10),
  sha1_hash     VARCHAR2(40)  NOT NULL,
  location      VARCHAR2(256) NOT NULL,
  CONSTRAINT pk_attachment_id PRIMARY KEY (attachment_id),
  CONSTRAINT fk_attachment_request_id FOREIGN KEY (request_id) REFERENCES Request (request_id)
);

CREATE TABLE GradeAttachment (
  request_id NUMBER(10),
  sha1_hash  VARCHAR2(40)  NOT NULL,
  location   VARCHAR2(256) NOT NULL,
  CONSTRAINT pk_grade_request_id PRIMARY KEY (request_id),
  CONSTRAINT fk_grade_request_id FOREIGN KEY (request_id) REFERENCES Request (request_id)
);

DROP VIEW EmployeeView;
CREATE VIEW EmployeeView AS
  SELECT e1.employee_id AS "ID", e1.fname AS "FIRST_NAME", e1.lname AS "LAST_NAME", e1.email AS "EMAIL",
    t.title_name AS "TITLE", d.dept_name AS "DEPT", e2.email AS "BOSS_EMAIL" FROM Employee e1
    INNER JOIN Title t ON e1.title_id = t.title_id
    LEFT JOIN Department d ON d.dept_id = e1.dept_id
    LEFT JOIN Employee e2 ON e1.boss_id = e2.employee_id;

-- SEQUENCE
DROP SEQUENCE request_seq;
CREATE SEQUENCE request_seq START WITH 1 INCREMENT BY 1;

DROP SEQUENCE attachment_seq;
CREATE SEQUENCE attachment_seq START WITH 1 INCREMENT BY 1;

-- TRIGGER
CREATE OR REPLACE TRIGGER request_seq_trigger
  BEFORE INSERT ON Request
  FOR EACH ROW
  BEGIN
    IF :new.request_id IS NULL THEN
      SELECT request_seq.NEXTVAL INTO :new.request_id FROM dual;
    END IF;
  END;
/

CREATE OR REPLACE TRIGGER attachment_seq_trigger
  BEFORE INSERT ON FileAttachment
  FOR EACH ROW
  BEGIN
    IF :new.attachment_id IS NULL THEN
      SELECT attachment_seq.NEXTVAL INTO :new.attachment_id FROM dual;
    END IF;
  END;
/


INSERT INTO GradeFormat VALUES (1, 'Grade');
INSERT INTO GradeFormat VALUES (2, 'Presentation');

INSERT INTO EventType VALUES (1, 'University Courses', 80);
INSERT INTO EventType VALUES (2, 'Seminars', 60);
INSERT INTO EventType VALUES (3, 'Certification Prep Classes', 75);
INSERT INTO EventType VALUES (4, 'Certification', 100);
INSERT INTO EventType VALUES (5, 'Technical Training', 90);
INSERT INTO EventType VALUES (6, 'Other', 30);

INSERT INTO Title VALUES (1, 'Associate');
INSERT INTO Title VALUES (2, 'Supervisor');
INSERT INTO Title VALUES (3, 'Department Head');
INSERT INTO Title VALUES (4, 'Benefits Coordinator');

INSERT INTO Department VALUES (1, 'Human Resources', 'HR', 1);
INSERT INTO Department VALUES (2, 'Information Technology', 'IT', 2);

INSERT INTO Employee VALUES (1, 'Benefit', 'Coordinator', 'benco@email.com', 4, 1, NULL);
INSERT INTO Employee VALUES (2, 'IT', 'Department Head', 'depthead@email.com', 3, 2, NULL);
INSERT INTO Employee VALUES (3, 'IT', 'Supervisor', 'supervisor@revature.com', 2, 2, 2);
INSERT INTO Employee VALUES (4, 'IT', 'Associate1', 'test11@email.com', 1, 2, 3);
INSERT INTO Employee VALUES (5, 'IT', 'Associate2', 'test2@email.com', 1, 2, 3);
INSERT INTO Employee VALUES (6, 'IT', 'Associate3', 'test3@email.com', 1, 2, 3);

INSERT INTO Password VALUES (1, '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
INSERT INTO Password VALUES (2, '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
INSERT INTO Password VALUES (3, '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
INSERT INTO Password VALUES (4, '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
INSERT INTO Password VALUES (5, '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
INSERT INTO Password VALUES (6, '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');

COMMIT;

