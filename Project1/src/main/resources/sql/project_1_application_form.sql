-- formUUID

-- employeeUUID

-- directSupervisorUUID

-- departmentHeadUUID

-- benefitsCoordinatorUUID

-- firstName
create or replace procedure getEmployeeFirstNameFromForm (formUUID in varchar2,
                                                          employeeFirstName out varchar2)
is
begin
    select first_name into employeeFirstName from project_1_user inner join project_1_reimbursement_form
    on project_1_user.uuid = project_1_reimbursement_form.employee_uuid
    where project_1_reimbursement_form.form_uuid = formUUID;
end;
/

-- lastName
create or replace procedure getEmployeeLastNameFromForm (formUUID in varchar2,
                                                         employeeLastName out varchar2)
is
begin
    select last_name into employeeLastName from project_1_user inner join project_1_reimbursement_form
    on project_1_user.uuid = project_1_reimbursement_form.employee_uuid
    where project_1_reimbursement_form.form_uuid = formUUID;
end;
/

-- generalStatus

-- supervisorComments

-- formComments

-- isCompletedWithSatisfaction

-- isFormClosed
create or replace procedure isFormClosed(formUUID in varchar2,
                                         is_form_closed out varchar2)
is
    v_form_yes varchar(3) := 'YES';
    v_form_no varchar(3) := 'NO';
    v_form_closed_date_exists date;
begin
    select form_closed_date into v_form_closed_date_exists
                            from project_1_reimbursement_form
                            where form_uuid = formUUID;
    if v_form_closed_date_exists = null then
        is_form_closed := v_form_no;
    else
        is_form_closed := v_form_yes;
    end if;
end;
/

-- formClosedDate
create or replace procedure getFormClosedDate(formUUID in varchar2,
                                              formClosedDate out date)
is
begin
    select form_closed_date into formClosedDate from project_1_reimbursement_form where form_uuid = formUUID;
end;
/

-- isPresentationToManagementRequired
create or replace procedure isPresentationRequired(formUUID in varchar2,
                                                   presentation_required out varchar2)
is
begin
    select present_to_management_required into presentation_required from project_1_reimbursement_form;
end;
/

-- isSupervisorDecisionMade
create or replace procedure hasSupervisorMadeADecision(formUUID in varchar2,
                                                       response out varchar2)
is
    v_decision varchar2(15);
begin  
    select supervisor_decision into v_decision from project_1_reimbursement_form where form_uuid = formUUID;
    if v_decision = null then
        response := 'NO';
    else
        response := 'YES';
    end if;
end;
/

-- isBenCoDecisionMade
create or replace procedure hasBenCoMadeADecision(formUUID in varchar2,
                                                  response out varchar2)
is
    v_decision varchar2(15);
begin
    select benco_decision into v_decision from project_1_reimbursement_form where form_uuid = formUUID;
    if v_decision = null then
        response := 'NO';
    else
        response := 'YES';
    end if;
end;
/

-- isDepartmentHeadDecisionMade
create or replace procedure hasDeptHeadMadeADecision(formUUID in varchar2,
                                                     response out varchar2)
is
    v_decision varchar2(15);
begin
    select department_head_decision into v_decision from project_1_reimbursement_form where formUUID = formUUID;
    if v_decision = null then
        response := 'NO';
    else
        response := 'YES';
    end if;
end;
/

-- formCreationDate


-- dateFormWasClosed


-- departmentHeadDecisionDate


-- benefitsCoordinatorDecisionDate


-- directSupervisorDecisionDate

