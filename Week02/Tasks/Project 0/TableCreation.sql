-- Script to create (or reset) the Database Tables

--TODO: If you want to be fancy, insert in this script a table that is filled with
--      the BoardPiece data, and then read it into the Game when it is ran.

DROP TABLE Player_Properties CASCADE CONSTRAINTS ;
DROP TABLE Players CASCADE CONSTRAINTS ;
--DROP TABLE Board_Pieces CASCADE CONSTRAINTS ;
DROP TABLE Visit_Prices CASCADE CONSTRAINTS ;
DROP TABLE Colors CASCADE CONSTRAINTS ;
DROP TABLE Board_Squares CASCADE CONSTRAINTS ;
DROP TABLE Boards CASCADE CONSTRAINTS ;
DROP TABLE Game_States CASCADE CONSTRAINTS ;
DROP TABLE Accounts CASCADE CONSTRAINTS ;

CREATE TABLE Accounts (
    account_id number(6) PRIMARY KEY,
    username varchar2(100) NOT NULL,
    password varchar2(100) NOT NULL
);

CREATE TABLE Game_States (
    game_state_id number(6) PRIMARY KEY,
--    player_list_id number(6) NOT NULL,
--    board_id number(6) NOT NULL,
    player_turn number(6) NOT NULL,
    jackpot_value number(6) NOT NULL
);

CREATE TABLE Boards (
    board_id number(6) PRIMARY KEY,
    game_state_id number(6) NOT NULL,
    CONSTRAINT fk_game_state_id FOREIGN KEY (game_state_id) REFERENCES Game_States (game_state_id)
);

CREATE TABLE Board_Squares (
    square_id number(2) PRIMARY KEY,
    board_id number(6) NOT NULL,
    square_name varchar2(22) NOT NULL,   -- NOTE: If custom BoardSquares are implemented, this needs to be bigger
    buy_price number(6) NOT NULL,
    owner_id number(6),
    expansion_count number(1) NOT NULL,  -- (0,5)
    mortgage_value number(6),
    CONSTRAINT fk_board_id FOREIGN KEY (board_id) REFERENCES Boards (board_id)
);

CREATE TABLE Colors (
    color_id number(6) PRIMARY KEY,
    board_square_id number(2) NOT NULl,
    red number(3) ,
    green number (3) ,
    blue number (3) ,
    CONSTRAINT fk_board_square_id FOREIGN KEY (board_square_id) REFERENCES Board_Squares (square_id)
);

CREATE TABLE Visit_Prices (
    visit_price_id number(6) PRIMARY KEY,
    board_square_id number(2) NOT NULl,
    cell_0 number(3),
    cell_1 number(3),
    cell_2 number(3),
    cell_3 number(4),
    cell_4 number(4),   -- These two can be null because Railroads are size 4.
    cell_5 number(4),
    CONSTRAINT fk_square_id FOREIGN KEY (board_square_id) REFERENCES Board_Squares (square_id)
);

/*
CREATE TABLE Board_Pieces (
    board_piece_id number(1) PRIMARY KEY,
    board_piece_name varchar2(12)
);
*/
CREATE TABLE Players (
    player_id number(6) PRIMARY KEY,
    player_name varchar2(100) NOT NULL,
    --board_piece_id number(1) NOT NULL,
    board_piece varchar2(12) NOT NULL,
    money number(6) NOT NULL,
    location_id number(2) NOT NULL,
    is_in_jail number(1) NOT NULL,    --0 = false, 1 = true
    --owned properties?
    has_jail_card number(1) NOT NULL,
    game_state_id number(6) NOT NULL,   -- NOTE: need a join table if players can be in multiple games
    CONSTRAINT fk_gamestate_id FOREIGN KEY (game_state_id) REFERENCES Game_States (game_state_id)
    --CONSTRAINT fk_board_piece_id FOREIGN KEY (board_piece_id) REFERENCES Board_Pieces (board_piece_id)
);

CREATE TABLE Player_Properties (
    player_id number(6),
    property_id number(6),
    is_mortgaged number(1),
    CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES Players (player_id),
    CONSTRAINT fk_property_id FOREIGN KEY (property_id) REFERENCES Board_Squares (square_id)
);
--End Table Creation


/*
INSERT INTO Board_Pieces VALUES (0, 'Tophat');
INSERT INTO Board_Pieces VALUES (1, 'Thimble');
INSERT INTO Board_Pieces VALUES (2, 'Iron');
INSERT INTO Board_Pieces VALUES (3, 'Boot');
INSERT INTO Board_Pieces VALUES (4, 'Battleship');
INSERT INTO Board_Pieces VALUES (5, 'Racecar');
INSERT INTO Board_Pieces VALUES (6, 'Dog');
INSERT INTO Board_Pieces VALUES (7, 'Wheelbarrow');
*/

--PL/SQL
DROP SEQUENCE increment_by_one_account;
CREATE SEQUENCE increment_by_one_account MINVALUE 0 START WITH 1 INCREMENT BY 1;
DROP SEQUENCE increment_by_one_game_state;
CREATE SEQUENCE increment_by_one_game_state MINVALUE 0 START WITH 0 INCREMENT BY 1;
DROP SEQUENCE increment_by_one_board;
CREATE SEQUENCE increment_by_one_board MINVALUE 0 START WITH 0 INCREMENT BY 1;
DROP SEQUENCE increment_by_one_board_square;
CREATE SEQUENCE increment_by_one_board_square MINVALUE 0 START WITH 0 INCREMENT BY 1;
DROP SEQUENCE increment_by_one_visit_prices;
CREATE SEQUENCE increment_by_one_visit_prices MINVALUE 0 START WITH 0 INCREMENT BY 1;
DROP SEQUENCE increment_by_one_color;
CREATE SEQUENCE increment_by_one_color MINVALUE 0 START WITH 0 INCREMENT BY 1;
DROP SEQUENCE increment_by_one_player;
CREATE SEQUENCE increment_by_one_player MINVALUE 0 START WITH 0 INCREMENT BY 1;
--/ for some reason, Intellij doesn't need this symbol on Sequences. And if it exists, will repeat the command?

--This procedure is never called, but fulfills Project 0's requirements.
CREATE OR REPLACE PROCEDURE CUBE_VALUE(var IN OUT number)
IS
BEGIN
    var := var * var * var;
END;
/

--Likewise, this function doesn't get called ever.
CREATE OR REPLACE FUNCTION RANDOMIZE(input IN number) RETURN number
IS
    val number(6);
    rand number(6);
BEGIN
    rand := input + 17;
    val := rand * 3 + 4 * input;
END;
/

--This Trigger will give an account_id when none is specified, using the increment_pk sequence
CREATE OR REPLACE TRIGGER on_insert_account
BEFORE INSERT ON Accounts
FOR EACH ROW
BEGIN
    IF :new.account_id IS NULL THEN
        SELECT increment_by_one_account.NEXTVAL INTO :new.account_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER on_insert_board
BEFORE INSERT ON Boards
FOR EACH ROW
BEGIN
    IF :new.board_id IS NULL THEN
        SELECT increment_by_one_board.NEXTVAL INTO :new.board_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER on_insert_board_square
BEFORE INSERT ON Board_Squares
FOR EACH ROW
BEGIN
    IF :new.square_id IS NULL THEN
        SELECT increment_by_one_board_square.NEXTVAL INTO :new.square_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER on_insert_game_state
BEFORE INSERT ON Game_States
FOR EACH ROW
BEGIN
    IF :new.game_state_id IS NULL THEN
        SELECT increment_by_one_game_state.NEXTVAL INTO :new.game_state_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER on_insert_color
BEFORE INSERT ON Colors
FOR EACH ROW
BEGIN
    IF :new.color_id IS NULL THEN
        SELECT increment_by_one_color.NEXTVAL INTO :new.color_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER on_insert_visit_price
BEFORE INSERT ON Visit_Prices
FOR EACH ROW
BEGIN
    IF :new.visit_price_id IS NULL THEN
        SELECT increment_by_one_visit_prices.NEXTVAL INTO :new.visit_price_id FROM dual;
    END IF;
END;
/

CREATE OR REPLACE TRIGGER on_insert_player
BEFORE INSERT ON Players
FOR EACH ROW
BEGIN
    IF :new.player_id IS NULL THEN
        SELECT increment_by_one_player.NEXTVAL INTO :new.player_id FROM dual;
    END IF;
END;
/

select current_utilization, limit_value
    from v$resource_limit
    where resource_name='sessions';

--commit;
