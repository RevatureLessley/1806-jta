DROP TABLE GradeAttachment CASCADE CONSTRAINTS;
DROP TABLE FileAttachment CASCADE CONSTRAINTS;
DROP TABLE Approval CASCADE CONSTRAINTS;
DROP TABLE Request CASCADE CONSTRAINTS;
DROP TABLE Event CASCADE CONSTRAINTS;
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
  dept_name VARCHAR2(20) UNIQUE NOT NULL,
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
  passward_hash VARCHAR2(64), --SHA-256
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

CREATE TABLE Event (
  event_id        NUMBER(6),
  event_name      VARCHAR2(256),
  grade_format_id NUMBER(3),
  event_type_id   NUMBER(3),
  CONSTRAINT pk_event_id PRIMARY KEY (event_id),
  CONSTRAINT fk_grade_format_id FOREIGN KEY (grade_format_id) REFERENCES GradeFormat (grade_format_id),
  CONSTRAINT fk_event_type_id FOREIGN KEY (event_type_id) REFERENCES EventType (event_type_id)
);

CREATE TABLE Request (
  request_id          NUMBER(10),
  event_id            NUMBER(6),
  employee_id         NUMBER(6),
  event_location      VARCHAR2(256),
  event_time          TIMESTAMP     NOT NULL,
  event_cost          NUMBER(6, 2)  NOT NULL,
  request_time        TIMESTAMP     NOT NULL,
  justification       VARCHAR2(256) NOT NULL,
  amount              NUMBER(6,2),
  CONSTRAINT pk_request_id PRIMARY KEY (request_id),
  CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES Employee (employee_id),
  CONSTRAINT fk_event_id FOREIGN KEY (event_id) REFERENCES Event (event_id)
);

CREATE TABLE Approval (
  approval_id         NUMBER(10),
  request_id          NUMBER(10),
  approver_id         NUMBER(6),
  approved            NUMBER(1),
  message             VARCHAR2(256),
  CONSTRAINT pk_approval_id PRIMARY KEY (approval_id),
  CONSTRAINT fk_approval_request_id FOREIGN KEY (request_id) REFERENCES Request(request_id),
  CONSTRAINT fk_approval_approver_id FOREIGN KEY (approver_id) REFERENCES Employee(employee_id)
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
