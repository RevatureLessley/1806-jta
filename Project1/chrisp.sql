---------------------------------------------------------------------------------------------
-----------  DROP/CREATE Tables and Constraints ---------------------------------------------
---------------------------------------------------------------------------------------------
DROP TABLE employee;
DROP TABLE reimbursement;
DROP TABLE event cascade constraints;
DROP TABLE event_type;

CREATE TABLE employee (
    emp_id NUMBER(7) UNIQUE,
    emp_password varchar2(30),
    fname VARCHAR2(20) NOT NULL,
    lname VARCHAR2(20) NOT NULL,
    address VARCHAR2(100) NOT NULL,
    remaining_reimbursement NUMBER(7,2),
    emp_role varchar2(20)
);

CREATE TABLE event_type (
    event_type_id NUMBER(2) PRIMARY KEY,
    event_type_name VARCHAR2(20),
    event_type_reimb NUMBER(4,2),
    event_grade_format VARCHAR2(100)
);

CREATE TABLE event (
    event_id NUMBER(6) PRIMARY KEY,
    event_type_id NUMBER(2),
    event_date DATE,
    event_time interval day(0) to second,
    event_location VARCHAR2(100),
    event_description VARCHAR2(100),
    event_cost NUMBER(7,2),
    event_grade NUMBER(3),
    event_presentation VARCHAR2(100),
    CONSTRAINT fk_event_id FOREIGN KEY ( event_type_id )
        REFERENCES event_type ( event_type_id )
);

CREATE TABLE reimbursement (
    reimbursement_id NUMBER(10) PRIMARY KEY,
    emp_id NUMBER(7,2) NOT NULL,
    event_id NUMBER(6),
    amnt_req NUMBER(7,2) NOT NULL,
    ds_approve VARCHAR2(3),
    ds_approve_date DATE,
    dh_approve VARCHAR2(3),
    dh_approve_date DATE,
    bc_approve VARCHAR2(3),
    bc_approve_date DATE,
    exceed_amount_exception VARCHAR2(3),
    CONSTRAINT fk_emp_id FOREIGN KEY ( emp_id )
        REFERENCES employee ( emp_id ),
    CONSTRAINT fk_req_event_id FOREIGN KEY ( event_id )
        REFERENCES event ( event_id )
);

CREATE TABLE reimbursement_ds (
    reimbursement_id NUMBER(10) PRIMARY KEY,
    emp_id NUMBER(7,2) NOT NULL,
    event_id NUMBER(6),
    amnt_req NUMBER(7,2) NOT NULL,
    ds_approve VARCHAR2(3),
    ds_approve_date DATE,
    dh_approve VARCHAR2(3),
    dh_approve_date DATE,
    bc_approve VARCHAR2(3),
    bc_approve_date DATE,
    exceed_amount_exception VARCHAR2(3),
    CONSTRAINT fk_emp_id_ds FOREIGN KEY ( emp_id )
        REFERENCES employee ( emp_id ),
    CONSTRAINT fk_req_event_id_ds FOREIGN KEY ( event_id )
        REFERENCES event ( event_id )
);

CREATE TABLE reimbursement_dh (
    reimbursement_id NUMBER(10) PRIMARY KEY,
    emp_id NUMBER(7,2) NOT NULL,
    event_id NUMBER(6),
    amnt_req NUMBER(7,2) NOT NULL,
    ds_approve VARCHAR2(3),
    ds_approve_date DATE,
    dh_approve VARCHAR2(3),
    dh_approve_date DATE,
    bc_approve VARCHAR2(3),
    bc_approve_date DATE,
    exceed_amount_exception VARCHAR2(3),
    CONSTRAINT fk_emp_id_dh FOREIGN KEY ( emp_id )
        REFERENCES employee ( emp_id ),
    CONSTRAINT fk_req_event_id_dh FOREIGN KEY ( event_id )
        REFERENCES event ( event_id )
);

CREATE TABLE reimbursement_bc (
    reimbursement_id NUMBER(10) PRIMARY KEY,
    emp_id NUMBER(7,2) NOT NULL,
    event_id NUMBER(6),
    amnt_req NUMBER(7,2) NOT NULL,
    ds_approve VARCHAR2(3),
    ds_approve_date DATE,
    dh_approve VARCHAR2(3),
    dh_approve_date DATE,
    bc_approve VARCHAR2(3),
    bc_approve_date DATE,
    exceed_amount_exception VARCHAR2(3),
    CONSTRAINT fk_emp_id_bc FOREIGN KEY ( emp_id )
        REFERENCES employee ( emp_id ),
    CONSTRAINT fk_req_event_id_bc FOREIGN KEY ( event_id )
        REFERENCES event ( event_id )
);


---------------------------------------------------------------------------------------------
-----------  Create Sequences  ---------------------------------------------------------------
---------------------------------------------------------------------------------------------

DROP SEQUENCE reimbursement_seq;

CREATE SEQUENCE reimbursement_seq START WITH 1000 INCREMENT BY 1;

DROP SEQUENCE event_seq;

CREATE SEQUENCE event_seq START WITH 100100 INCREMENT BY 1;

---------------------------------------------------------------------------------------------
-----------  Create Triggers  ---------------------------------------------------------------
---------------------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER reimbursement_seq_trigger BEFORE
    INSERT ON reimbursement
    FOR EACH ROW
BEGIN
    IF
        :new.reimbursement_id IS NULL
    THEN
        SELECT reimbursement_seq.NEXTVAL
        INTO :new.reimbursement_id
        FROM dual;

    END IF;
END;
/

CREATE OR REPLACE TRIGGER event_seq_trigger BEFORE
    INSERT ON event
    FOR EACH ROW
BEGIN
    IF
        :new.event_id IS NULL
    THEN
        SELECT event_seq.NEXTVAL
        INTO :new.event_id
        FROM dual;

    END IF;
END;

---------------------------------------------------------------------------------------------
-----------  Create Procedures  -------------------------------------------------------------
---------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE insertintoreimbursement (
    reqid IN NUMBER,
    empid IN NUMBER,
    eventid IN NUMBER,
    amntreq IN NUMBER,
    dsapprove IN VARCHAR2,
    dsapprovedate IN DATE,
    dhapprove IN VARCHAR2,
    dhapprovedate IN DATE,
    bcapprove IN VARCHAR2,
    bcapprovedate IN DATE,
    exceedamountexception IN VARCHAR2
)
    IS
BEGIN
    INSERT INTO reimbursement VALUES (
        reqid,
        empid,
        eventid,
        amntreq,
        dsapprove,
        dsapprovedate,
        dhapprove,
        dhapprovedate,
        bcapprove,
        bcapprovedate,
        exceedamountexception
    );

    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertintoreimbursementds (
    reqid IN NUMBER,
    empid IN NUMBER,
    eventid IN NUMBER,
    amntreq IN NUMBER,
    dsapprove IN VARCHAR2,
    dsapprovedate IN DATE,
    dhapprove IN VARCHAR2,
    dhapprovedate IN DATE,
    bcapprove IN VARCHAR2,
    bcapprovedate IN DATE,
    exceedamountexception IN VARCHAR2
)
    IS
BEGIN
    INSERT INTO reimbursement_ds VALUES (
        reqid,
        empid,
        eventid,
        amntreq,
        dsapprove,
        dsapprovedate,
        dhapprove,
        dhapprovedate,
        bcapprove,
        bcapprovedate,
        exceedamountexception
    );

    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertintoreimbursementdh (
    reqid IN NUMBER,
    empid IN NUMBER,
    eventid IN NUMBER,
    amntreq IN NUMBER,
    dsapprove IN VARCHAR2,
    dsapprovedate IN DATE,
    dhapprove IN VARCHAR2,
    dhapprovedate IN DATE,
    bcapprove IN VARCHAR2,
    bcapprovedate IN DATE,
    exceedamountexception IN VARCHAR2
)
    IS
BEGIN
    INSERT INTO reimbursement_dh VALUES (
        reqid,
        empid,
        eventid,
        amntreq,
        dsapprove,
        dsapprovedate,
        dhapprove,
        dhapprovedate,
        bcapprove,
        bcapprovedate,
        exceedamountexception
    );

    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE insertintoreimbursementbc (
    reqid IN NUMBER,
    empid IN NUMBER,
    eventid IN NUMBER,
    amntreq IN NUMBER,
    dsapprove IN VARCHAR2,
    dsapprovedate IN DATE,
    dhapprove IN VARCHAR2,
    dhapprovedate IN DATE,
    bcapprove IN VARCHAR2,
    bcapprovedate IN DATE,
    exceedamountexception IN VARCHAR2
)
    IS
BEGIN
    INSERT INTO reimbursement_bc VALUES (
        reqid,
        empid,
        eventid,
        amntreq,
        dsapprove,
        dsapprovedate,
        dhapprove,
        dhapprovedate,
        bcapprove,
        bcapprovedate,
        exceedamountexception
    );

    COMMIT;
END;
/

---------------------------------------------------------------------------------------------
-----------  Populate Tables  ---------------------------------------------------------------
---------------------------------------------------------------------------------------------

INSERT INTO event_type VALUES (
    1,
    'University Courses',
    .80,
    'Grade'
);

INSERT INTO event_type VALUES (
    2,
    'Seminar',
    .60,
    'Presentation'
);

INSERT INTO event_type VALUES (
    3,
    'Certification Prep',
    .75,
    'Presentation'
);

INSERT INTO event_type VALUES (
    4,
    'Certification',
    1,
    'Grade'
);

INSERT INTO event_type VALUES (
    5,
    'Technical Training',
    .90,
    'Presentation'
);

INSERT INTO event_type VALUES (
    6,
    'Other',
    .30,
    'Presentation'
);

INSERT INTO employee VALUES (
    1000,
    'password',
    'chris',
    'parsons',
    '1405 trip St',
    950,
    'user'
);

INSERT INTO employee VALUES (
    0519,
    'password',
    'Meghan',
    'Trihard',
    '4356 segwauy Dr',
    1000,
    'bc'
);

INSERT INTO employee VALUES (
    1024,
    'password',
    'John',
    'Slickerson',
    '93028 Baker St',
    543.23,
    'dh'
);

INSERT INTO employee VALUES (
    10192,
    'password',
    'Topher',
    'Harlott',
    '1405 Harrower St',
    950,
    'ds'
);

INSERT INTO employee VALUES (
    10001,
    'password',
    'Blip',
    'Blop',
    '0000 Bloop St',
    25,
    'user'
);

commit;

