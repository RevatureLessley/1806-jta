package trms.dao;

import java.util.Date;
import java.util.List;

import trms.beans.ApplicationForm;

public class ApplicationFormDAOImpl implements ApplicationFormDAO {

	@Override
	public List<ApplicationForm> getAllForms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationForm getApplicationForm(String formUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmployeeUUID(String formUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateEmployeeUUID(String formUUID, String employeeUUID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDirectSupervisoyUUID(String formUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSupervisorUUID(String formUUID, String supervisorUUID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDepartmentHeadUUID(String formUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateDepartmentHeadUUID(String formUUID, String departmentHeadUUID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getBenefitsCoordinatorUUID(String formUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean UpdateBenefitsCoordinatorUUID(String formUUID, String benefitsCoordinatorUUID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getEmployeeFirstNameFromForm(String formUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmployeeLastNameFromForm(String formUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFormStatus(String formUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateFormStatus(String formUUID, String formStatus) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSupervisorComments(String formUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSupervisorComments(String formUUID, String supervisorComments) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getFormComments(String formUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateFormComments(String formUUID, String formComments) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCompletedWithSatisfaction(String formUUID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCompletedWithSatisfaction(String formUUID, boolean isCompleted) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFormClosed(String formUUID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closeForm(String formUUID, boolean isCompleted) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Date getFormClosedDate(String formUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPresentationRequired(String formUUID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePresentationRequirements(String formUUID, boolean isPresentationRequired) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasSupervisorMadeADecision(String formUUID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBenefitsCoordinatorMadeADecision(String formUUID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasDepartmentHeadMadeADecision(String formUUID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Date getFormCreationDate(String formUUID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateDepartmentHeadDecision(String formUUID, String decision) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBenefitsCoordinatorDecision(String formUUID, String decision) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSupervisorDecision(String formUUID, String decision) {
		// TODO Auto-generated method stub
		return false;
	}

}
