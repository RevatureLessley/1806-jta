--DROP TABLE ApprovalStatus CASCADE CONSTRAINTS;
--DROP TABLE AccountRole CASCADE CONSTRAINTS;
--DROP TABLE AccountBalance CASCADE CONSTRAINTS;
--DROP TABLE Accounts CASCADE CONSTRAINTS;


CREATE TABLE accounts(
    username VARCHAR(20) NOT NULL,
    firstName VARCHAR(20),
    lastName VARCHAR(20),
    pass VARCHAR(50) NOT NULL,
    address VARCHAR(50),
    roleID NUMBER(1) NOT NULL,
    approvalID NUMBER(1) NOT NULL,
    CONSTRAINT PK_Accounts PRIMARY KEY (username));
    
CREATE TABLE AccountRole(
    roleID NUMBER(1) NOT NULL,
    roleName VARCHAR(20),
    CONSTRAINT PK_AccountRole PRIMARY KEY (roleID));
    
CREATE TABLE ApprovalStatus(
    approvalID NUMBER(1) NOT NULL,
    approvalName VARCHAR(20),
    CONSTRAINT PK_ApprovalStatus PRIMARY KEY (approvalID));
    
CREATE TABLE AccountBalance(
    balanceID NUMBER(5),
    username VARCHAR(20) NOT NULL,
    balance NUMBER(6),
    CONSTRAINT PK_AccountBalance PRIMARY KEY (balanceID));
    


ALTER TABLE Accounts
    ADD CONSTRAINT FK_RoleID FOREIGN KEY (roleID) REFERENCES AccountRole (roleID);
    
ALTER TABLE Accounts
    ADD CONSTRAINT FK_ApprovalStatusID FOREIGN KEY (approvalID) REFERENCES ApprovalStatus (approvalID);

ALTER TABLE AccountBalance
    ADD CONSTRAINT FK_Username FOREIGN KEY (username) REFERENCES Accounts (username);

INSERT INTO AccountRole VALUES (0, 'user');
INSERT INTO AccountRole VALUES (1, 'admin');
INSERT INTO ApprovalStatus VALUES (0, 'Not Approved');
INSERT INTO ApprovalStatus VALUES (1, 'Approved');
    
CREATE SEQUENCE bal_seq START WITH 1;

CREATE OR REPLACE TRIGGER bal_seq_trigger 
BEFORE INSERT ON AccountBalance 
FOR EACH ROW
BEGIN
 IF :new.balanceID IS NULL THEN
        SELECT bal_seq.NEXTVAL INTO :new.balanceID FROM dual;
    END IF;
END;
