drop table employee;
drop table reimbursement_request;
drop table event;
drop table event_type;





CREATE TABLE employee(
emp_id number(7) unique,
fname varchar2(20) not null,
lname varchar2(20) not null,
address varchar2(100) not null,
remaining_reimbursement number(7,2)
);

CREATE TABLE reimbursement_request(
reimbursement_id number(10) primary key,
emp_id number(7,2) not null,
amnt_req number(7,2) not null,
ds_approve varchar2(3),
dh_approve varchar2(3),
bc_approve varchar2(3),
exceed_amount_exception varchar2(3),
constraint fk_emp_id FOREIGN KEY (emp_id) REFERENCES employee(emp_id),
constraint fk_event_id FOREIGN KEY (event_type) REFERENCES event (event_type_id)
);

CREATE TABLE event(
event_type_id number(2) primary key,
event_date date,
event_time time,
event_location varchar2(100),
event_description varchar2(100),
event_cost number(7,2),
event_grade number(3),
event_presentation varchar2(100),
constraint fk_event_id foreign key (event_type_id) references event_type(event_type_id)
);

CREATE TABLE event_type(
event_type_id number(2),
event_type_name varchar2(20),
event_type_reimb number(4,2),
event_grade_format varchar2(100)
);
