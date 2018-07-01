DROP TABLE player_list;
DROP TABLE admin_acc;
DROP TABLE banker;
DROP TABLE loaner;
DROP TABLE player;
DROP TABLE acc;

CREATE TABLE acc(
    acc_id number(6) PRIMARY KEY,
    acc_name varchar2(100) NOT NULL,
    acc_uname varchar(30) NOT NULL,
    acc_pword varchar(30) NOT NULL
);

CREATE TABLE admin_acc(
    admin_id number(6) PRIMARY KEY,
    acc_id number (6),
    CONSTRAINT fk_admin_id FOREIGN KEY (acc_id) REFERENCES acc(acc_id)
);

CREATE TABLE banker(
    banker_id number(6) PRIMARY KEY,
    banker_interest decimal(5,2),
    acc_id number(6),
    CONSTRAINT fk_banker_id FOREIGN KEY (acc_id) REFERENCES acc(acc_id)
);

CREATE TABLE loaner(
    loaner_id number(6) PRIMARY KEY,
    loaner_interest decimal(5,2),
    acc_id number(6),
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

CREATE TABLE player_list(
    list_id number(6) PRIMARY KEY,
    player_id number(6),
    active_id number(1),
    acc_id number(6),
    CONSTRAINT fk_player_list_id FOREIGN KEY (player_id) REFERENCES player(player_id)
);
INSERT INTO acc VALUES(1, 'Admin', 'Admin', 'pass');
INSERT INTO acc VALUES(2, 'Banker', 'Banker', 'pass');
INSERT INTO acc VALUES(3, 'Loaner', 'Loaner', 'pass');
INSERT INTO acc VALUES(4, 'Salara Elris', 'Salara', 'pass');
INSERT INTO admin_acc VALUES(1,1);
INSERT INTO banker VALUES(1,1.10, 2);
INSERT INTO loaner VALUES(1,1.30, 3);
INSERT INTO player VALUES(1,100, 100, 0, 0, 0, 1, 4);


SELECT * FROM player
FULL OUTER JOIN acc
ON player.acc_id  = acc.acc_id
WHERE player.acc_id >3;


commit;