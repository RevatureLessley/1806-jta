/*
    Oracle has an extension called PL/SQL. (Procedural language/ SQL)
    -It provides tools that we can use with SQL
    --such as, funcitons, stored procedures, sequences, and triggers.
    --As well as exception handling.
    --In other words, it provides a means to write actual code in SQL
*/

--SEQUENCE
/*
    A sequence is an object that will maintain a counter for you.
*/
DROP SEQUENCE npc_seq;
CREATE SEQUENCE npc_seq
    START WITH 100
    INCREMENT BY 1;
    --This sequence will be used to handle the id field for our tables
    --In order to actually utilize it, we will need to build something that
    --reacts to situations where an employee inserted. E.G. Triggers
/ --Use forward slash to separate transactions and other parts of the script.

--Triggers
/*
    A trigger is an object that we can create that listens for actions to occur
    on a specific table in which the trigger was made for.
    A trigger can be coded to react to most CRUD operations. (excluding select)
*/

CREATE OR REPLACE TRIGGER npc_seq_trigger
BEFORE INSERT ON npc --NOTE: you can use BEFORE or AFTER followed by a CRUD
FOR EACH ROW --PL/SQL for loop
BEGIN --This keyword signifies a block for a transaction
    IF :new.npc_id IS NULL THEN
        SELECT npc_seq.NEXTVAL INTO :new.npc_id FROM dual;
    END IF;
END;
/
/*
    Few things above are new here:
    -:new
        :new can be used to represent newly inserted data. You can think of it
        as a staging platform for data you are ABOUT to insert.
        You can also use :old to access data prior to an insert/update
    -IF/END IF
        -Simple if statement block,
    -SELECT INTO statement
        -A SELECT INTO statement is a  statement that lets you grab data
        and immediately assign it to a location/variable/table
        Above, I call a select query on the from our SEQUENCE, and immediately
        assign it to the variable  :new.npc_id, thus making the data im about
        to insert, use the sequence's next value as the id instead of what is
        previously being inserted.
        In this case, we ONLY do this, if the data to be inserted, has a null
        for the id field.
    -dual
        -A dummy table that serves only to follow proper syntaxes where we
        dont care about table in question, but still need to have syntax
        references SOME table.
*/

INSERT INTO npc (npc_name, npc_lvl, currency, job_id)
VALUES ('Auto Imcremented Adam', 3, 150, 3);
INSERT INTO npc (npc_name, npc_lvl, currency, job_id)
VALUES ('Auto Imcremented Alice', 4, 151, 3);
select * from npc;
/

--STORED PROCEDURES
/*
    [Anything in brakcets is optional]

    A named transaction that cna be invoked when called.

    Syntax:

    CREATE [OR REPLACE] proc_name
    IS
        //This section is where you can DECLARE variables
    BEGIN
        //This section is where you can write the execution or utilize other
        //function or procedures
    [EXCEPTION]
        //perform exception ahndling ehre
    END;

    You can invoke a stored procedure in two ways:
    1.Via a transaction block
    BEGIN
        proc_name();
    END

    2. Outside of a transaction block
    call proc_name();
*/

CREATE OR REPLACE PROCEDURE hello_world_procedure
IS
BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello World!'); --SQL DEVELOPER equivalent to syso
END;
/

BEGIN
    hello_world_procedure();
END;
/
/*
    In order to access the dbms_output console, go to view > Dbms_output
    -This brigns up the dbms_output window where you will click the '+'
    -Selec thte schema you are currently using and it will open the console.
    -Then execute programs
*/

--Procedures can allow us to definte executions( Transaction) that can change
--data. Most acitons can be taken against a table using procedures.

/*
    Thigns of note:
        Parameters in stored procedures are  bit unique.
        You have the following keywords to use with parameters
        -IN
        -OUT
        -IN OUT
    IN parameters, whatever is passed as input during a procedure call
    OUT parameters, whatever is returend from the procedure
    IN OUT, a parameter that is used for input, the mutated into output.

    SYNTAX: varname IN/OUT/IN OUT DATATYPE
*/

CREATE OR REPLACE PROCEDURE insertIntoNpc(npcName IN VARCHAR2,
                                            npcLvl IN NUMBER,
                                            npcCurrency IN NUMBER,
                                            npcJobId IN NUMBER)
IS
BEGIN
    INSERT INTO npc (npc_name, npc_lvl, currency, job_id)
    VALUES(npcName, npcLvl, npcCurrency, npcJobId);
    COMMIT;
END;
/

CALL insertIntoNpc('Dude', 33, 9999, 2);
/

CREATE OR REPLACE PROCEDURE get_class(npcId IN NUMBER, className OUT VARCHAR2)
IS
BEGIN
    SELECT b.JOB_NAME INTO className FROM npc a
    INNER JOIN JOB_CLASS b
    ON a.job_id = b.job_id
    WHERE a.npc_id = npcId;
END;
/

DECLARE --Used to declare variables in a transaction blcok
    input number(6);
    results varchar2(100);
BEGIN
    input := 4; --Variable assignment is done with :=
    get_class(input, results);
    DBMS_OUTPUT.PUT_LINE(results);
END;
/

CREATE OR REPLACE PROCEDURE get_currency(npcID IN OUT number)
IS
BEGIN
    SELECT currency INTO npcID FROM npc WHERE npc_id = npcID;
END;
/
DECLARE
    input number(6);
BEGIN
    input:=2;
    DBMS_OUTPUT.PUT_LINE(input);
    get_currency(input);
    DBMS_OUTPUT.PUT_LINE(input);
END;
/
/*
    CURSORS!
    -A cursor is essentially like a pointer to a table or view.
    -We can use them to iterate through entire queries from teh database.
    -When we want to pass entire or queires, we need to use cursors.

    NOTE: PL/SQL offers a CURSOR and SYS_REFCURSOR
    The SYS_REFCURSOR is a stronger cursor (therefor more costly) that is allowed
    to be passed outside of the scope of the procedure it is in. A normal CURSOR
    must be created and die within the cope of hte procedure that it was created
    in.
*/

CREATE OR REPLACE PROCEDURE get_all_npc(cursorParam OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN cursorParam FOR --Cursor is like a stream, you OPEN if FOR some QUERY
    SELECT * FROM npc;
END;
/

DECLARE
    my_cursor SYS_REFCURSOR;
    npcId npc.npc_id%TYPE;
    npcName npc.npc_name%TYPE; --Make the datatype match THAT of the npc column
    npcLvl number;
    npcCurrency npc.currency%TYPE;
    npcJob number;

BEGIN
    get_all_npc(my_cursor);

    LOOP
        FETCH my_cursor INTO npcId, npcName, npcLvl, npcCurrency,npcJob;
        EXIT WHEN my_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(npcID || ' ' || npcName || ' ' || npcLvl || ' '
                                || npcCurrency || ' ' || npcJob);
    END LOOP;
END;
/*
    In the above example, we created an EXPLICIT CURSOR. This was the case because we
    created it specifically. There also exists IMPLICIT CURSORS which are simply the
    cursors created any time you execute a query in general. (IE, any SELECT STATEMENT)
*/

--USER DEFINED FUNCTIONS
/*
    The key differences between a function and a stored procedure
    -A stored procedure does NOT have to return anything
    -A stored procedure CAN have as many IN/OUT parameters it wants.
    -A stored procedure can alter the database using most DML statements.
    -A stored procedure can NOT be called mid query.
    -A stored procedure can call other stored procedures within it.
    -A stored procedure can call functions within it.

    -A function MUST return ONE and only ONE resource.
    -A function CAN use OUT parameters, but this is highly frowned upon.
    -A function can NOT perform DML/DDL
    -A function can be called mid query.
    -A function can call other functions.
    -A function can NOT call stored procedures.
*/

CREATE OR REPLACE FUNCTION get_max_currency --if no parameters, then dont type parenthesis
RETURN NUMBER
IS
    max_cur number(6);
BEGIN
    SELECT max(currency) INTO max_cur FROM npc;
    RETURN max_cur;
END;
/

DECLARE
    max_cur number;
BEGIN
    max_cur := get_max_currency();
    DBMS_OUTPUT.PUT_LINE(max_cur);
END;
/

--EXCEPTION HANDLING
CREATE OR REPLACE PROCEDURE exceptionExample
IS
    CURSOR badCurse is
        SELECT npc_id FROM npc;
    npcId number(6);
BEGIN
    OPEN badCurse;
    Loop
        FETCH badCurse into npcId;
        EXIT WHEN badCurse%NOTFOUND;
    End loop;
    CLOSE badCurse;

    npcId := (1/0);

EXCEPTION
    WHEN invalid_cursor THEN
        dbms_output.put_line('CURSOR ERROR');
    WHEN zero_divide THEN
        dbms_output.put_line('ARITHMETIC ERROR');
END;

BEGIN
    exceptionExample();
END;

