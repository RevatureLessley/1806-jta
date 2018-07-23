package trms.dao;

import java.util.Date;
import java.util.List;

import trms.beans.ApplicationForm;

public interface ApplicationFormDAO {
	public List<ApplicationForm> getAllForms();
	public ApplicationForm getApplicationForm(String formUUID);
	public List<ApplicationForm> getUserApplicationForms(String userUUID);
	public String getEmployeeUUID(String formUUID);
	public boolean updateEmployeeUUID(String formUUID, String employeeUUID);
	public String getDirectSupervisorUUID(String formUUID);
	public boolean updateSupervisorUUID(String formUUID, String supervisorUUID);
	public String getDepartmentHeadUUID(String formUUID);
	public boolean updateDepartmentHeadUUID(String formUUID, String departmentHeadUUID);
	public String getBenefitsCoordinatorUUID(String formUUID);
	public boolean UpdateBenefitsCoordinatorUUID(String formUUID, String benefitsCoordinatorUUID);
	public String getEmployeeFirstNameFromForm(String formUUID);
	public String getEmployeeLastNameFromForm(String formUUID);
	public String getFormStatus(String formUUID);
	public boolean updateFormStatus(String formUUID, String formStatus);
	public String getSupervisorComments(String formUUID);
	public boolean updateSupervisorComments(String formUUID, String supervisorComments);
	public String getFormComments(String formUUID);
	public boolean updateFormComments(String formUUID, String formComments);
	public boolean getCompletedWithSatisfaction(String formUUID);
	public boolean updateCompletedWithSatisfaction(String formUUID, boolean isCompleted);
	public boolean isFormClosed(String formUUID);
	public boolean closeForm(String formUUID, boolean isCompleted);
	public Date getFormClosedDate(String formUUID);
	public boolean isPresentationRequired(String formUUID);
	public boolean updatePresentationRequirements(String formUUID, boolean isPresentationRequired);
	public boolean hasSupervisorMadeADecision(String formUUID);
	public boolean hasBenefitsCoordinatorMadeADecision(String formUUID);
	public boolean hasDepartmentHeadMadeADecision(String formUUID);
	public Date getSupervisorDecisionDate(String formUUID);
	public Date getBenefitsCoordinatorDecisionDate(String formUUID);
	public Date getDepartmentHeadDecisionDate(String formUUID);
	public Date getFormCreationDate(String formUUID);
	public boolean updateDepartmentHeadDecision(String formUUID, String decision);
	public boolean updateBenefitsCoordinatorDecision(String formUUID, String decision);
	public boolean updateSupervisorDecision(String formUUID, String decision);
	public boolean submitNewApplicationForm(String formUUID, String employeeUUID, String formComments);
	public boolean updateBenefitsCoordinatorComments(String formUUID, String comments);
	public boolean updateDepartmentHeadComments(String formUUID, String comments);
}
