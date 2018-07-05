DROP TABLE Approval 
CASCADE CONSTRAINTS;
DROP TABLE Approval_Additional_Info 
CASCADE CONSTRAINTS;
DROP TABLE Approval_Attachment 
CASCADE CONSTRAINTS;
DROP TABLE Approval_Type 
CASCADE CONSTRAINTS;
DROP TABLE Department 
CASCADE CONSTRAINTS;
DROP TABLE Employee 
CASCADE CONSTRAINTS;
DROP TABLE Event 
CASCADE CONSTRAINTS;
DROP TABLE Event_Attachment 
CASCADE CONSTRAINTS;
DROP TABLE Event_Type 
CASCADE CONSTRAINTS;
DROP TABLE Grading_Format 
CASCADE CONSTRAINTS;
DROP TABLE Reimbursement 
CASCADE CONSTRAINTS;

CREATE TABLE Approval (
    app_id NUMBER PRIMARY KEY,
    app_typ_id NUMBER,
    app_gra_for_id NUMBER,
    app_rei_id NUMBER,
    app_isApproved CHAR(1) DEFAULT 'U'
        CHECK (app_isApproved IN ('N', 'Y', 'U')),
    app_reason VARCHAR2(4000)
);

CREATE TABLE Approval_Type (
    app_typ_id NUMBER PRIMARY KEY,
    app_typ CHAR(20)
        CHECK (app_typ IN ('BENEFITS_COORDINATOR', 
                           'DEPARTMENT_HEAD', 
                           'DIRECT_SUPERVISOR'))
);

CREATE TABLE Approval_Additional_Info (
    app_addinf_id NUMBER PRIMARY KEY,
    app_addinf_file LONG RAW
);

CREATE TABLE Approval_Attachment (
    app_att_id NUMBER PRIMARY KEY,
    att_att_file LONG RAW
);

CREATE TABLE Department (
    dep_id NUMBER PRIMARY KEY,
    dep_name VARCHAR2(4000)
);

CREATE TABLE Employee (
    emp_id NUMBER PRIMARY KEY,
    emp_department NUMBER,
    emp_supervisor NUMBER,
    emp_available_reimbursement NUMBER,
    emp_username VARCHAR2(4000),
    emp_password VARCHAR2(4000),
    emp_firstname VARCHAR2(4000),
    emp_lastname VARCHAR2(4000),
    emp_isBenCo CHAR(1) 
        CHECK (emp_isBenCo IN ('N', 'Y'))
);

CREATE TABLE Event (
    eve_id NUMBER PRIMARY KEY,
    eve_typ_id NUMBER,
    eve_att_id NUMBER,
    eve_rei_id NUMBER,
    eve_cost NUMBER CHECK (eve_cost > 0),
    eve_description VARCHAR2(4000),
    eve_location VARCHAR2(4000),
    eve_work_missed INTERVAL DAY(9) TO SECOND(0)
);

CREATE TABLE Event_Attachment (
    eve_att_id NUMBER PRIMARY KEY,
    eve_att_file LONG RAW
);

CREATE TABLE Event_Type (
    eve_typ_id NUMBER PRIMARY KEY,
    eve_typ_coverage NUMBER
        CHECK (eve_typ_coverage IN (0.3, 0.6, 0.75, 0.8, 1)),
    eve_typ_value VARCHAR(40)
        CHECK (eve_typ_value IN ('CERTIFICATION',
                                 'CERTIFICATION_PREPARATION_CLASS',
                                 'SEMINAR', 
                                 'TECHINAL_TRAINING',
                                 'UNIVERSITY_COURSE'))
);

CREATE TABLE Grading_Format (
    gra_for_id NUMBER PRIMARY KEY,
    gra_for_confirmed CHAR(1)
        CHECK (gra_for_confirmed IN ('N', 'Y', 'U')),
    gra_for_proof LONG RAW,
    gra_for_passing_cutoff NUMBER DEFAULT NULL
);

CREATE TABLE Reimbursement (
  rei_id NUMBER PRIMARY KEY,
  rei_emp_id NUMBER,
  rei_eve_id NUMBER,
  rei_awarded NUMBER,
  rei_datetime TIMESTAMP WITH LOCAL TIME ZONE,
  rei_isCancelled CHAR(1) DEFAULT 'N'
    CHECK (rei_isCancelled IN ('N', 'Y')),
  rei_isPending CHAR(1) DEFAULT 'Y'
    CHECK (rei_isPending IN ('N', 'Y')),
  rei_justification VARCHAR2(4000),
  rei_reason_exeeded_max VARCHAR2(4000)
);

ALTER TABLE Approval   
ADD CONSTRAINT fk_app_rei 
FOREIGN KEY (app_rei_id) 
REFERENCES Reimbursement(rei_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Approval   
ADD CONSTRAINT fk_app_typ 
FOREIGN KEY (app_typ_id) 
REFERENCES Approval_Type(app_typ_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Approval   
ADD CONSTRAINT fk_app_gra_for 
FOREIGN KEY (app_gra_for_id) 
REFERENCES Grading_Format(gra_for_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Approval_Additional_Info   
ADD CONSTRAINT fk_app_addinf 
FOREIGN KEY (app_addinf_id) 
REFERENCES Approval(app_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Approval_Attachment    
ADD CONSTRAINT fk_app_att 
FOREIGN KEY (app_att_id) 
REFERENCES Approval(app_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Employee    
ADD CONSTRAINT fk_emp_dep 
FOREIGN KEY (emp_department) 
REFERENCES Department(dep_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Employee    
ADD CONSTRAINT fk_emp_emp 
FOREIGN KEY (emp_supervisor) 
REFERENCES Employee(emp_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Event    
ADD CONSTRAINT fk_eve_rei 
FOREIGN KEY (eve_rei_id) 
REFERENCES Reimbursement(rei_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Event    
ADD CONSTRAINT fk_eve_typ 
FOREIGN KEY (eve_typ_id) 
REFERENCES Event_Type(eve_typ_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Event_Attachment    
ADD CONSTRAINT fk_eve_att 
FOREIGN KEY (eve_att_id) 
REFERENCES Event(eve_id)
DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE Reimbursement    
ADD CONSTRAINT fk_rei_emp 
FOREIGN KEY (rei_emp_id) 
REFERENCES Employee(emp_id)
DEFERRABLE INITIALLY DEFERRED;