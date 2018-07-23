-- formUUID
create or replace procedure newApplicationForm(formUUID in varchar2,
                                               employeeUUID in varchar2,
                                               formComments in varchar2)
is
    v_current_date date;
begin
    select systimestamp into v_current_date from dual;
    insert into project_1_reimbursement_form (form_uuid, employee_uuid, form_creation_date, form_comments,
    status, direct_supervisor_uuid, department_head_uuid, benco_uuid, benco_decision, benco_decision_date,
    benco_comments, supervisor_decision, supervisor_decision_date, supervisor_comments, department_head_decision,
    department_head_decision_date, department_head_comments, form_closed_date, present_to_management_required,
    completed_with_satisfaction)
    values (formUUID, employeeUUID, v_current_date, formComments, 'Pending', null, null, null, null, null,
    null, null, null, null, null, null, null, null, null, null);
    commit;
end;
/

create or replace procedure selectForm(formUUID in varchar2,
                                       resultCursor out sys_refcursor)
is
begin
    open resultCursor for select * from project_1_reimbursement_form where form_uuid = formUUID;
end;
/

create or replace procedure selectFormWithUserUUID(userUUID in varchar2,
                                                   resultCursor out sys_refcursor)
is
begin
    open resultCursor for select * from project_1_reimbursement_form where employee_uuid = userUUID;
end;
/

create or replace procedure selectAllForms(resultCursor out sys_refcursor)
is
begin
    open resultCursor for select * from project_1_reimbursement_form;
end;
/

-- employeeUUID
create or replace procedure getEmployeeUUID(formUUID in varchar2,
                                            employeeUUID out varchar2)
is
begin
    select employee_uuid into employeeUUID from project_1_reimbursement_form where form_uuid = formUUID;
end;
/

create or replace procedure updateEmployeeUUID(formUUID in varchar2,
                                               employeeUUID in varchar2)
is
begin
    update project_1_reimbursement_form set employee_uuid = employeeUUID where formUUID = form_uuid;
end;
/

-- directSupervisorUUID
create or replace procedure getDirectSupervisorUUID(formUUID in varchar2,
                                                    directSupervisorUUID out varchar2)
is
begin
    select direct_supervisor_uuid into directSuperVisorUUID from project_1_reimbursement_form where form_uuid = formUUID;
end;
/

create or replace procedure updateSupervisorUUID(formUUID in varchar2,
                                                 directSupervisorUUID in varchar2)
is
begin
    update project_1_reimbursement_form set direct_supervisor_uuid = directSupervisorUUID where form_uuid = formUUID;
end;
/

-- departmentHeadUUID
create or replace procedure getDepartmentHeadUUID(formUUID in varchar2,
                                                  deptHeadUUID out varchar2)
is
begin
    select department_head_uuid into deptHeadUUID from project_1_reimbursement_form where form_uuid = formUUID;
end;
/

create or replace procedure updateDeptHeadUUID(formUUID in varchar2,
                                               deptHeadUUID out varchar2)
is
begin
    update project_1_reimbursement_form set department_head_uuid = deptHeadUUID where form_uuid = formUUID;
end;
/

-- benefitsCoordinatorUUID
create or replace procedure getBenefitsCoordinatorUUID(formUUID in varchar2,
                                                       bencoUUID out varchar2)
is
begin
    select benco_uuid into bencoUUID from project_1_reimbursement_form where form_uuid = bencoUUID;
end;
/

create or replace procedure updateBenCoUUID(formUUID in varchar2,
                                            bencoUUID out varchar2)
is
begin
    update project_1_reimbursement_form set benco_uuid = bencoUUID where form_uuid = formUUID;
end;
/

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
create or replace procedure getFormStatus(formUUID in varchar2,
                                          formStatus out varchar2)
is
begin
    select status into formStatus from project_1_reimbursement_form where form_uuid = form_uuid;
end;
/

create or replace procedure updateFormStatus(formUUID in varchar2,
                                             formStatus in varchar2)
is
begin
    update project_1_reimbursement_form set status = formStatus where form_uuid = formUUID;
end;
/

-- supervisorComments
create or replace procedure getSupervisorComments(formUUID in varchar2,
                                                  supervisorComments out varchar2)
is
begin
    select supervisor_comments into supervisorComments from project_1_reimbursement_form where form_uuid = formUUID;
end;
/

create or replace procedure updateSupervisorComments(formUUID in varchar2,
                                                     supervisorComments in varchar2)
is
begin
    update project_1_reimbursement_form set supervisor_comments = supervisorComments where form_uuid = formUUID;
end;
/
-- benco_comments
create or replace procedure getBenCoComments(formUUID in varchar2,
                                             benCoComments out varchar2)
is
begin
    select benco_comments into benCoComments from project_1_reimbursement_form where form_uuid = formUUID;
end;
/

create or replace procedure updateBenCoComments(formUUID in varchar2,
                                                benCoComments in varchar2)
is
begin
    update project_1_reimbursement_form set benco_comments = benCoComments where form_uuid = formUUID;
end;
/
-- department_head_comments
create or replace procedure getDeptHeadComments(formUUID in varchar2,
                                                deptHeadComments out varchar2)
is
begin
    select department_head_comments into deptHeadComments from project_1_reimbursement_form where form_uuid = formUUID;
end;
/

create or replace procedure updateDeptHeadComments(formUUID in varchar2,
                                                   deptHeadComments in varchar2)
is
begin
    update project_1_reimbursement_form set department_head_comments = deptHeadComments where form_uuid = formUUID;
end;
/

-- formComments
create or replace procedure getFormComments(formUUID in varchar2,
                                            formComments out varchar2)
is
begin
    select form_comments into formComments from project_1_reimbursement_form where form_uuid = formUUID;
end;
/

create or replace procedure updateFormComments(formUUID in varchar2,
                                               formComments in varchar2)
is
begin
    update project_1_reimbursement_form set form_comments = formComments where form_uuid = formUUID;
end;
/

-- isCompletedWithSatisfaction
create or replace procedure getCompletedWithSatisfaction(formUUID in varchar2,
                                                         isCompleted out varchar2)
is
begin
    select completed_with_satisfaction into isCompleted from project_1_reimbursement_form where form_uuid = formUUID;
end;
/

create or replace procedure updateCompletedFormField(formUUID in varchar2,
                                                     isCompleted in varchar2)
is
begin
    update project_1_reimbursement_form set completed_with_satisfaction = isCompleted where form_uuid = formUUID;
end;
/

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

create or replace procedure closeForm(formUUID in varchar2,
                                      is_completed in varchar2)
is
    v_current_time date;
begin
    select systimestamp into v_current_time from dual;
    update project_1_reimbursement_form set form_closed_date = v_current_time,
                                            completed_with_satisfaction = is_completed 
                                            where form_uuid = formUUID;
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

create or replace procedure updatePresentationRequirements(formUUID in varchar2,
                                                           presentation_required in varchar2)
is
begin
    update project_1_reimbursement_form set present_to_management_required = presentation_required where form_uuid = formUUID;
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
create or replace procedure selectFormCreationDate(formUUID in varchar2,
                                                   formCreationDate out date)
is
begin
    select form_creation_date into formCreationDate from project_1_reimbursement_form where form_uuid = formUUID;
end;
/

-- departmentHeadDecisionDate
create or replace procedure selectDeptHeadDecisionDate(formUUID in varchar2,
                                                       decisionDate out Date)
is
begin
    select department_head_decision_date into decisionDate from project_1_reimbursement_form where form_uuid = formUUID;
end;
/

-- benefitsCoordinatorDecisionDate
create or replace procedure selectBenCoDecisionDate(formUUID in varchar2,
                                                    decisionDate out Date)
is
begin
    select benco_decision_date into decisionDate from project_1_reimbursement_form where form_uuid = formUUID;
end;
/

-- supervisorDecisionDate
create or replace procedure selectSupervisorDecisionDate(formUUID in varchar2,
                                                         decisionDate out Date)
is
begin
    select benco_decision_date into decisionDate from project_1_reimbursement_form where form_uuid = formUUID;
end;
/

-- departmentHeadDecisionDate
create or replace procedure updateDepartmentHeadDecision(formUUID in varchar2,
                                                         deptDecision in varchar2)
is
    v_current_time date;
begin
    select systimestamp into v_current_time from dual;
    update project_1_reimbursement_form set department_head_decision = deptDecision,
                                            department_head_decision_date = v_current_time
                                        where form_uuid = formUUID;
    commit;
end;
/

-- benefitsCoordinatorDecisionDate
create or replace procedure updateBenCoDecision(formUUID in varchar2,
                                                benCoDecision in varchar2)
is
    v_current_time date;
begin
    select systimestamp into v_current_time from dual;
    update project_1_reimbursement_form set benco_decision = benCoDecision,
                                            benco_decision_date = v_current_time
                                        where form_uuid = formUUID;
    commit;
end;
/

-- directSupervisorDecisionDate
create or replace procedure updateSupervisorDecision(formUUID in varchar2,
                                                     supervisorDecision in varchar2)
is
    v_current_time date;
begin
    select systimestamp into v_current_time from dual;
    update project_1_reimbursement_form set supervisor_decision = supervisorDecision,
                                            supervisor_decision_date = v_current_time
                                        where form_uuid = formUUID;
    commit;
end;
/

-- project_1_attachments
create or replace procedure addAttachment(formUUID in varchar2,
                                          employeeUUID in varchar2,
                                          attachment in blob)
is
begin
    insert into project_1_attachment (employee_uuid, form_uuid, attachment_data) values (employeeUUID, formUUID, attachment);
    commit;
end;
/

create or replace procedure addAttachment(formUUID in varchar2,
                                          employeeUUID in varchar2)
is
begin
    delete from project_1_attachment where form_uuid = formUUID and employee_uuid = employeeUUID;
    commit;
end;
/                                      