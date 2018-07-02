CREATE TABLE UserRole (
    RoleID NUMBER(6) PRIMARY KEY,
    RoleName VARCHAR2(50)
);

CREATE TABLE UserState (
    StateID NUMBER(6) PRIMARY KEY,
    State VARCHAR2(20)
);

CREATE TABLE Users (
    UserID NUMBER(6) PRIMARY KEY,
    FName VARCHAR2(20) NOT NULL,
    LName VARCHAR2(20) NOT NULL,
    Email VARCHAR2(100) NOT NULL,
    Role NUMBER(2) NOT NULL,
    State NUMBER(2) NOT NULL
);

CREATE TABLE UserBalance (
    BalanceID NUMBER(6) PRIMARY KEY,
    Balance NUMBER(38),
    CardNumber NUMBER(20),
    UserID NUMBER(6),
    CONSTRAINT FK_User FOREIGN KEY (User_ID) REFERENCES Users (UserID)
);

INSERT INTO USERROLE (RoleID, RoleName) VALUES (1, "Administrator");
INSERT INTO USERROLE (RoleID, RoleName) VALUES (1, "Regular user");

INSERT INTO UserState (StateID, State) VALUES (1, "Approved");
INSERT INTO UserState (StateID, State) VALUES (2, "Waiting for approval");
INSERT INTO UserState (StateID, State) VALUES (1, "Banned");

INSERT INTO Users (UserID, FName, LName, Password, Email, Role, State) 
    VALUES (1, "Kevin", "Crisanto", "pass", "kev@email.com", 1, 1);

CREATE SEQUENCE user_seq
    START WITH 2
    INCREMENT BY 1;

CREATE SEQUENCE balance_seq
    START WITH 2
    INCREMENT BY 1;
    
create or replace TRIGGER balance_seq_trigger
BEFORE INSERT ON UserBalance
FOR EACH ROW 
BEGIN 
    IF :new.BalanceID IS NULL THEN
        SELECT balance_seq.NEXTVAL INTO :new.BalanceID FROM dual;
    END IF;
END;

create or replace TRIGGER user_seq_trigger
BEFORE INSERT ON Users
FOR EACH ROW 
BEGIN 
    IF :new.UserID IS NULL THEN
        SELECT user_seq.NEXTVAL INTO :new.UserID FROM dual;
    END IF;
END;

create or replace Function FindBalance
   ( userid_in IN NUMBER )
   RETURN number
IS
   cnumber number;

   cursor c1 is
   SELECT Balance
     FROM UserBalance
     WHERE userid_in = User_ID;

BEGIN

   open c1;
   fetch c1 into cnumber;

   if c1%notfound then
      cnumber := 0;
   end if;

   close c1;

RETURN cnumber;

EXCEPTION
WHEN OTHERS THEN
   raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;

create or replace PROCEDURE insertIntoBalance(Balance IN NUMBER, 
                                            Card IN NUMBER,
                                            UsID IN NUMBER
                                            )
IS
BEGIN
    INSERT INTO UserBalance (Balance, CardNumber, User_ID)
    VALUES(Balance, Card, UsID);
    COMMIT;
END;

create or replace PROCEDURE insertIntoUsers(F_Name IN VARCHAR2, 
                                            L_Name IN VARCHAR2,
                                            Pass IN VARCHAR2,
                                            UEmail IN VARCHAR2,
                                            URole IN NUMBER,
                                            UState IN NUMBER)
IS
BEGIN
    INSERT INTO Users (FName, LName, Password, Email, Role, State)
    VALUES(F_Name, L_Name, Pass, UEmail, URole, UState);
    COMMIT;
END;
