DROP TABLE Employee CASCADE CONSTRAINTS;
DROP TABLE EmployeeType CASCADE CONSTRAINTS;
DROP TABLE Department CASCADE CONSTRAINTS;
DROP TABLE RFORM CASCADE CONSTRAINTS;
DROP TABLE Event CASCADE CONSTRAINTS;
DROP TABLE EventType CASCADE CONSTRAINTS;

CREATE TABLE EmployeeType (
    emp_type_id number(6) PRIMARY KEY,
    emp_type varchar(100) NOT NULL
);

CREATE TABLE Department (
    dep_id number(6) PRIMARY KEY,
    dep_name varchar2(100)
);

CREATE TABLE Employee (
    emp_id number(6) PRIMARY KEY,
    username varchar2(100) NOT NULL,
    pass_word varchar2(100) NOT NULL,
    f_name varchar2(100) NOT NULL,
    l_name varchar2(100) NOT NULL,
    dir_sup_id number(6),
    dep_id number(6) NOT NULL,
    bal number(8,2) NOT NULL,
    emp_type_id number(6) NOT NULL,
    
    CONSTRAINT fk_emp_type_id FOREIGN KEY (emp_type_id) REFERENCES EmployeeType (emp_type_id), 
    CONSTRAINT fk_dep_id FOREIGN KEY (dep_id) REFERENCES Department(dep_id)
);

CREATE TABLE EventType(
    event_type_id number(6) PRIMARY KEY,
    event_type varchar2(100),
    percent_reimb number(6)
);

CREATE TABLE Event (
    event_id number(6) PRIMARY KEY,
    event_name varchar2(100) NOT NULL,
    event_type_id number(6) NOT NULL,
    grade_format number(1) NOT NULL, --0 for presentation 1 for grade
    cutoff_grade number(6) NOT NULL, --defaults from event type
    event_cost number(6),
    
    CONSTRAINT fk_event_type_id FOREIGN KEY (event_type_id) REFERENCES EventType(event_type_id)
    
);

CREATE TABLE RForm (
    form_id number(6) PRIMARY KEY,
    emp_id number(6) NOT NULL,
    form_date date,
    deadline_date date,
    place varchar2(100) NOT NULL,
    info varchar2(1000),
    event_id number(6),
    reimbursement_amount number(6), --default from event, can be changed
    
    justification varchar2(500),
    --event related attatchment blobs id
    time_missed number(6), --hours
    form_closed number(1), --binary, edited by supervisor or benco emp
    
    CONSTRAINT fk_emp_id FOREIGN KEY (emp_id) REFERENCES Employee (emp_id),
    CONSTRAINT fk_event_id FOREIGN KEY (event_id) REFERENCES Event(event_id)
);
