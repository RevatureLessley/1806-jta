DROP TABLE admin_acc;
DROP TABLE banker;
DROP TABLE loaner;
DROP TABLE player;
DROP TABLE acc;
DROP SEQUENCE acc_inc;
DROP SEQUENCE player_inc;


CREATE TABLE acc(
    acc_id number(6) PRIMARY KEY,
    acc_name varchar2(100) NOT NULL,
    acc_uname varchar2(30) NOT NULL,
    acc_pword varchar2(30) NOT NULL
);

CREATE TABLE admin_acc(
    admin_id number(6) PRIMARY KEY,
    acc_id number (6) NOT NULL,
    CONSTRAINT fk_admin_id FOREIGN KEY (acc_id) REFERENCES acc(acc_id)
);

CREATE TABLE banker(
    banker_id number(6) PRIMARY KEY,
    banker_interest decimal(5,2) NOT NULL,
    acc_id number(6) NOT NULL,
    CONSTRAINT fk_banker_id FOREIGN KEY (acc_id) REFERENCES acc(acc_id)
);

CREATE TABLE loaner(
    loaner_id number(6) PRIMARY KEY,
    loaner_balance number(10) NOT NULL,
    loaner_interest decimal(5,2) NOT NULL,
    acc_id number(6) NOT NULL,
    CONSTRAINT fk_loaner_id FOREIGN KEY (acc_id) REFERENCES acc(acc_id)
);

CREATE TABLE player(
    player_id number (6) PRIMARY KEY,
    player_acc_balance number(10) NOT NULL,
    player_bank_balance number (10) NOT NULL,
    player_loan_balance number(10) NOT NULL,
    player_has_loan number(1) NOT NULL,
    player_loan_waiting number(1) NOT NULL,
    player_account_approved number(1) NOT NULL,
    acc_id number(6),
    CONSTRAINT fk_player_id FOREIGN KEY (acc_id) REFERENCES acc(acc_id)
);

CREATE SEQUENCE acc_inc
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;
/
CREATE SEQUENCE player_inc
MINVALUE 1
START WITH 2
INCREMENT BY 1
CACHE 10;
/
CREATE OR REPLACE TRIGGER acc_seq_trigger
BEFORE INSERT ON acc --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.acc_id IS NULL THEN
        SELECT acc_inc.NEXTVAL INTO :new.acc_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER player_seq_trigger
BEFORE INSERT ON player --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.player_id IS NULL THEN
        SELECT player_inc.NEXTVAL INTO :new.player_id FROM dual;
    END IF;
END;
/
CREATE OR REPLACE PROCEDURE create_acc(accName varchar2, accUname varchar2, accPword varchar2)
IS
BEGIN
    INSERT INTO acc VALUES(null, accName, accUname, accPword);
END;
/
CREATE OR REPLACE PROCEDURE create_player(playerAbal IN number, playerBbal IN number, playerLbal IN number, playerHasL IN number,
            playerLoanW IN number, playerActive IN number, accID IN number)
IS
BEGIN
    INSERT INTO player VALUES (Null, playerAbal, playerBbal,playerLbal,playerHasL,playerLoanW,playerActive, accID);
END;
/
INSERT INTO acc VALUES(null, 'The Boss', 'Admin', 'pass');
INSERT INTO acc VALUES(null, 'The Money', 'Banker', 'pass');
INSERT INTO acc VALUES(null, 'The Shark', 'Loaner', 'pass');
INSERT INTO acc VALUES(null, 'Salara Elris', 'Salara', 'pass');
INSERT INTO admin_acc VALUES(1,1);
INSERT INTO banker VALUES(1,1.10, 2);
INSERT INTO loaner VALUES(1,10000, 1.30, 3);
INSERT INTO player VALUES(1,100, 100, 0, 0, 0, 1, 4);
--select * from player
--full outer join acc
--on player.acc_id = acc.acc_id
--where player.acc_id >3;
commit;