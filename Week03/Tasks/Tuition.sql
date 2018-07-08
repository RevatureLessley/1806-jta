DROP TABLE Employee CASCADE CONSTRAINTS;
DROP TABLE EmployeeType CASCADE CONSTRAINTS;
DROP TABLE Department CASCADE CONSTRAINTS;
DROP TABLE RForm CASCADE CONSTRAINTS;
DROP TABLE Event CASCADE CONSTRAINTS;
DROP TABLE EventType CASCADE CONSTRAINTS;
DROP TABLE Approval CASCADE CONSTRAINTS;

--Tables
CREATE TABLE EmployeeType (
    emp_type_id number(6) PRIMARY KEY,
    emp_type varchar(100) NOT NULL
);

CREATE TABLE Department (
    dep_id number(6) PRIMARY KEY,
    dep_name varchar2(100),
    dep_head_id number(6)
);

CREATE TABLE Employee (
    emp_id number(6) PRIMARY KEY,
    username varchar2(100) NOT NULL,
    pass_word varchar2(100) NOT NULL,
    f_name varchar2(100) NOT NULL,
    l_name varchar2(100) NOT NULL,
    dir_sup_id number(6),
    dep_id number(6) NOT NULL,
    bal number(6,2) NOT NULL,
    pending_reim number(6,2) NOT NULL,
    awarded_reim number(6,2) NOT NULL,
    emp_type_id number(6) NOT NULL,
    auth_lvl number(1) NOT NULL,
    
    CONSTRAINT fk_emp_type_id FOREIGN KEY (emp_type_id) REFERENCES EmployeeType (emp_type_id), 
    CONSTRAINT fk_dep_id FOREIGN KEY (dep_id) REFERENCES Department(dep_id)
);

CREATE TABLE EventType(
    event_type_id number(6) PRIMARY KEY,
    event_type varchar2(100),
    percent_reimb number(6,2)
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
    rform_id number(6) PRIMARY KEY,
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
    app_lvl number(1),
    
    CONSTRAINT fk_emp_id FOREIGN KEY (emp_id) REFERENCES Employee (emp_id),
    CONSTRAINT fk_event_id FOREIGN KEY (event_id) REFERENCES Event(event_id)
);

CREATE TABLE Approval (
    app_id number(6) PRIMARY KEY,
    rform_id number(6),
    is_app number(1),
    app_lvl number(1),
    
    CONSTRAINT fk_rform_id FOREIGN KEY (rform_id) REFERENCES RForm(rform_id)
);

--Sequencers and their triggers

--Create new employee id
DROP SEQUENCE empid_seq;
CREATE SEQUENCE empid_seq
    START WITH 100
    INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER empid_seq_trigger
BEFORE INSERT ON Employee
FOR EACH ROW 
BEGIN 
    IF :new.emp_id IS NULL THEN
        SELECT empid_seq.NEXTVAL INTO :new.emp_id FROM dual;
    END IF;
END;
/

--Create new rform id
DROP SEQUENCE rformid_seq;
CREATE SEQUENCE rformid_seq
    START WITH 100
    INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER rformid_seq_trigger
BEFORE INSERT ON RForm
FOR EACH ROW 
BEGIN 
    IF :new.rform_id IS NULL THEN
        SELECT rformid_seq.NEXTVAL INTO :new.rform_id FROM dual;
    END IF;
END;
/

--Create new event id
DROP SEQUENCE eventid_seq;
CREATE SEQUENCE eventid_seq
    START WITH 100
    INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER eventid_seq_trigger
BEFORE INSERT ON Event
FOR EACH ROW 
BEGIN 
    IF :new.event_id IS NULL THEN
        SELECT eventid_seq.NEXTVAL INTO :new.event_id FROM dual;
    END IF;
END;
/

--Create new approval id
DROP SEQUENCE appid_seq;
CREATE SEQUENCE appid_seq
    START WITH 100
    INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER appid_seq_trigger
BEFORE INSERT ON Approval
FOR EACH ROW 
BEGIN 
    IF :new.app_id IS NULL THEN
        SELECT appid_seq.NEXTVAL INTO :new.app_id FROM dual;
    END IF;
END;
/

--Event types table
INSERT INTO EventType(event_type_id,event_type,percent_reimb)
VALUES(0,'University Course',0.8);
INSERT INTO EventType(event_type_id,event_type,percent_reimb)
VALUES(1,'Seminar',0.6);
INSERT INTO EventType(event_type_id,event_type,percent_reimb)
VALUES(2,'Certification Preparation Class',0.75);
INSERT INTO EventType(event_type_id,event_type,percent_reimb)
VALUES(3,'Certification',1.0);
INSERT INTO EventType(event_type_id,event_type,percent_reimb)
VALUES(4,'Technical Training',0.9);
INSERT INTO EventType(event_type_id,event_type,percent_reimb)
VALUES(5,'Other',0.3);

CREATE OR REPLACE PROCEDURE insertNewEmployee(userN IN varchar2,
                                            userP IN varchar2,
                                            firstN IN varchar2,
                                            lastN IN varchar2,
                                            dirS IN varchar2,
                                            depId IN number,
                                            pending_reim IN number,
                                            awarded_reim IN number,
                                            empT in number)
IS
BEGIN
    INSERT INTO Employee (username,pass_word,f_name,l_name,dir_sup_id,
                            dep_id,bal,pending_reim,awarded_reim,emp_type_id,auth_lvl)
    VALUES(userN,userP,firstN,lastN,dirS,depId,0,empT,0);
    commit;
END;
/

CREATE OR REPLACE PROCEDURE insertNewEvent(eventN
                                        )
commit;

