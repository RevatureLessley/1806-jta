DROP TABLE Employee CASCADE CONSTRAINTS;
DROP TABLE EmployeeType CASCADE CONSTRAINTS;
DROP TABLE Department CASCADE CONSTRAINTS;
DROP TABLE RForm CASCADE CONSTRAINTS;
DROP TABLE EventType CASCADE CONSTRAINTS;

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
    available_reim number (6,2) NOT NULL,
    
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
    prop_reim number(6), --value from event is used, this is proposed value
    justification varchar2(500),
    filekey varchar2(100),
    --event related attatchment blobs id
    time_missed number(6), --hours
    form_closed number(1), --binary, edited by supervisor or benco emp
    app_lvl number(1),
    grade_format number(1) NOT NULL, --0 for presentation 1 for grade
    cutoff_grade number(6) NOT NULL, --defaults to 70 if not specified (can be changed)
    event_type_id number(6) NOT NULL,
    event_cost number(6,2),
    event_name varchar2(100),
    dir_sup_id number(6),
    dep_id number(3),
    more_info number(1),
    
    CONSTRAINT fk_emp_id FOREIGN KEY (emp_id) REFERENCES Employee (emp_id),
    CONSTRAINT fk_event_type_id FOREIGN KEY (event_type_id) REFERENCES EventType(event_type_id)
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
                            dep_id,pending_reim,awarded_reim,emp_type_id,available_reim)
VALUES(0,'executive','executive','Boss','Man',0,0,0,3,1000);
INSERT INTO Employee (emp_id,username,pass_word,f_name,l_name,dir_sup_id,
                            dep_id,pending_reim,awarded_reim,emp_type_id,available_reim)
VALUES(1,'benefits','benefits','Bobbert','Bobson',0,1,0,0,2,1000);
INSERT INTO Employee (emp_id,username,pass_word,f_name,l_name,dir_sup_id,
                            dep_id,pending_reim,awarded_reim,emp_type_id,available_reim)
VALUES(2,'production','production','Tommy','DROP TABLE Employee;',0,2,0,0,2,1000);
INSERT INTO Employee (emp_id,username,pass_word,f_name,l_name,dir_sup_id,
                            dep_id,pending_reim,awarded_reim,emp_type_id,available_reim)
VALUES(3,'finance','finance','Rich','Richman',0,3,0,0,2,1000);
INSERT INTO Employee (emp_id,username,pass_word,f_name,l_name,dir_sup_id,
                            dep_id,pending_reim,awarded_reim,emp_type_id,available_reim)
VALUES(4,'marketing','marketing','Timmy','Turner',0,4,0,0,2,1000);

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
                            dep_id,pending_reim,awarded_reim,emp_type_id,available_reim)
    VALUES(userN,userP,firstN,lastN,dirS,depId,0,0,empT,1000);
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
                                        eventC IN number,
                                        supId IN number,
                                        eventname IN varchar2,
                                        depId IN number)
IS
rformid number;
emptype number;
dirsup number;
dirsuptype number;

cursor c1 is SELECT emp_type_id FROM Employee WHERE emp_id = empId;
cursor c2 is SELECT emp_type_id FROM Employee WHERE emp_id = dirsup;
BEGIN
    INSERT INTO RForm (emp_id,rform_date,place,info,prop_reim,
                        justification,time_missed,form_closed,app_lvl,
                        grade_format,cutoff_grade,event_type_id,event_cost,
                        dir_sup_id,event_name,more_info)
    VALUES(empId,rformD,pl,inf,propR,just,timeM,0,0,gradeF,cutoffG,
                        eventT_Id,eventC,supId,eventname,0);

    SELECT rformid_seq.CURRVAL INTO rformid from dual;
    --open c1;
    --fetch c1 into emptype;
    --close c1;
    SELECT emp_type_id INTO emptype FROM Employee WHERE emp_id = empId;
    SELECT dir_sup_id INTO dirsup FROM Employee WHERE emp_id = empId;
    --open c2;
    --fetch c2 into dirsuptype;
    --close c2;
    --dbms_output.put_line(emptype);
    SELECT emp_type_id INTO dirsuptype FROM Employee WHERE emp_id = dirsup;
    IF emptype >= 2 THEN
        UPDATE RForm SET app_lvl = 2 WHERE rform_id = rformid;
    ELSIF dirsuptype >= 2 THEN
        UPDATE RForm SET app_lvl = 1 WHERE rform_id = rformid;
    END IF;
    commit;
END;
/

commit;

