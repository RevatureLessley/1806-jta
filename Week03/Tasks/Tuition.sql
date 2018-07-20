DROP TABLE Employee CASCADE CONSTRAINTS;
DROP TABLE EmployeeType CASCADE CONSTRAINTS;
DROP TABLE Department CASCADE CONSTRAINTS;
DROP TABLE RForm CASCADE CONSTRAINTS;
DROP TABLE EventType CASCADE CONSTRAINTS;
DROP TABLE Approval CASCADE CONSTRAINTS;

--Tables
CREATE TABLE EmployeeType (
    emp_type_id number(6) PRIMARY KEY,
    emp_type varchar2(100) NOT NULL
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
    pending_reim number(6,2) NOT NULL,
    awarded_reim number(6,2) NOT NULL,
    emp_type_id number(6) NOT NULL,
    
    CONSTRAINT fk_emp_type_id FOREIGN KEY (emp_type_id) REFERENCES EmployeeType (emp_type_id), 
    CONSTRAINT fk_dep_id FOREIGN KEY (dep_id) REFERENCES Department(dep_id)
);

CREATE TABLE EventType(
    event_type_id number(6) PRIMARY KEY,
    event_type varchar2(100),
    percent_reimb number(3)
);


CREATE TABLE RForm (
    rform_id number(6) PRIMARY KEY,
    emp_id number(6) NOT NULL,
    rform_date date NOT NULL,
    place varchar2(100) NOT NULL,
    info varchar2(1000),
    prop_reim number(6,2), --value from event is used, this is proposed value
    justification varchar2(500),
    filekey varchar2(100),
    --event related attatchment blobs id
    time_missed number(6), --hours
    form_closed number(1), --binary, edited by supervisor or benco emp
    app_lvl number(1),
    grade_format number(1) NOT NULL, --0 for presentation 1 for grade
    cutoff_grade number(6,2) NOT NULL, --defaults to 70 if not specified (can be changed)
    event_type_id number(6) NOT NULL,
    event_cost number(6,2),
    
    CONSTRAINT fk_emp_id FOREIGN KEY (emp_id) REFERENCES Employee (emp_id),
    CONSTRAINT fk_event_type_id FOREIGN KEY (event_type_id) REFERENCES EventType(event_type_id)
);

CREATE TABLE Approval (
    app_id number(6) PRIMARY KEY,
    rform_id number(6),
    is_app number(1),
    app_lvl number(1),
    approver_id number(6),
    requester_id number(6),
    
    CONSTRAINT fk_rform_id FOREIGN KEY (rform_id) REFERENCES RForm(rform_id),
    CONSTRAINT fk_approver FOREIGN KEY (approver_id) REFERENCES Employee(emp_id),
    CONSTRAINT fk_requester FOREIGN KEY (requester_id) REFERENCES Employee(emp_id)
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

--Employee types table
INSERT INTO EmployeeType (emp_type_id,emp_type)
VALUES(0,'Regular');
INSERT INTO EmployeeType (emp_type_id,emp_type)
VALUES(1,'Supervisor');
INSERT INTO EmployeeType (emp_type_id,emp_type)
VALUES(2,'Head');
INSERT INTO EmployeeType (emp_type_id,emp_type)
VALUES(3,'Executive');

--Department table
INSERT INTO Department(dep_id,dep_name,dep_head_id)
VALUES(0,'Executive',0);
INSERT INTO Department(dep_id,dep_name,dep_head_id)
VALUES(1,'Benefits',1);
INSERT INTO Department(dep_id,dep_name,dep_head_id)
VALUES(2,'Production',2);
INSERT INTO Department(dep_id,dep_name,dep_head_id)
VALUES(3,'Finance',3);
INSERT INTO Department(dep_id,dep_name,dep_head_id)
VALUES(4,'Marketing',4);

--Department heads
INSERT INTO Employee (emp_id,username,pass_word,f_name,l_name,
                            dep_id,pending_reim,awarded_reim,emp_type_id)
VALUES(0,'executive','executive','Boss','Man',0,0,0,3);
INSERT INTO Employee (emp_id,username,pass_word,f_name,l_name,dir_sup_id,
                            dep_id,pending_reim,awarded_reim,emp_type_id)
VALUES(1,'benefits','benefits','Bobert','Bobson',0,1,0,0,2);
INSERT INTO Employee (emp_id,username,pass_word,f_name,l_name,dir_sup_id,
                            dep_id,pending_reim,awarded_reim,emp_type_id)
VALUES(2,'production','production','Tommy','DROP TABLE Employee;',0,2,0,0,2);
INSERT INTO Employee (emp_id,username,pass_word,f_name,l_name,dir_sup_id,
                            dep_id,pending_reim,awarded_reim,emp_type_id)
VALUES(3,'finance','finance','Rich','Richman',0,3,0,0,2);
INSERT INTO Employee (emp_id,username,pass_word,f_name,l_name,dir_sup_id,
                            dep_id,pending_reim,awarded_reim,emp_type_id)
VALUES(4,'marketing','marketing','Timmy','Turner',0,4,0,0,2);

--Event types table
INSERT INTO EventType(event_type_id,event_type,percent_reimb)
VALUES(0,'University Course',80);
INSERT INTO EventType(event_type_id,event_type,percent_reimb)
VALUES(1,'Seminar',60);
INSERT INTO EventType(event_type_id,event_type,percent_reimb)
VALUES(2,'Certification Preparation Class',75);
INSERT INTO EventType(event_type_id,event_type,percent_reimb)
VALUES(3,'Certification',100);
INSERT INTO EventType(event_type_id,event_type,percent_reimb)
VALUES(4,'Technical Training',90);
INSERT INTO EventType(event_type_id,event_type,percent_reimb)
VALUES(5,'Other',30);

--insert employee
CREATE OR REPLACE PROCEDURE insertNewEmployee(userN IN varchar2,
                                            userP IN varchar2,
                                            firstN IN varchar2,
                                            lastN IN varchar2,
                                            dirS IN varchar2,
                                            depId IN number,
                                            empT IN number)
IS
BEGIN
    INSERT INTO Employee (username,pass_word,f_name,l_name,dir_sup_id,
                            dep_id,pending_reim,awarded_reim,emp_type_id)
    VALUES(userN,userP,firstN,lastN,dirS,depId,0,0,empT);
    commit;
END;
/

--insert rform
CREATE OR REPLACE PROCEDURE insertNewRForm(empId IN number,
                                        rformD IN date,
                                        pl IN varchar2,
                                        inf IN varchar2,
                                        propR IN number,
                                        just IN varchar2,
                                        timeM IN number,
                                        gradeF IN number,
                                        cutoffG in number,
                                        eventT_Id IN number,
                                        eventC IN number)
IS
BEGIN
    INSERT INTO RForm (emp_id,rform_date,place,info,prop_reim,
                        justification,time_missed,form_closed,app_lvl,
                        grade_format,cutoff_grade,event_type_id,event_cost)
    VALUES(empId,rformD,pl,inf,propR,just,timeM,0,0,gradeF,cutoffG,
                        eventT_Id,eventC);
    commit;
END;
/

CREATE OR REPLACE PROCEDURE insertNewApproval(rformid IN number,
                                            isapp IN number,
                                            applvl IN number,
                                            appr IN number,
                                            req IN number)
IS
BEGIN
    INSERT INTO Approval(rform_id,is_app,app_lvl,approver_id,requester_id)
    VALUES(rformid,isapp,applvl,appr,req);
    commit;
END;
/

commit;

