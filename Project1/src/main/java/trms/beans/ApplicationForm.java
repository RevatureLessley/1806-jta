package trms.beans;

import java.util.Date;

public class ApplicationForm implements Form {
	private String formUUID;
	private String employeeUUID;
	private String directSupervisorUUID;
	private String departmentHeadUUID;
	private String benefitsCoordinatorUUID;
	private String firstName;
	private String lastName;
	private String generalStatus;
	private String supervisorComments;
	private String formComments;
	private boolean isCompletedWithSatisfaction;
	private boolean isFormClosed;
	private boolean isPresentationToManagementRequired;
	private boolean isSupervisorDecisionMade;
	private boolean isBenCoDecisionMade;
	private boolean isDepartmentHeadDecisionMade;
	private Date formCreationDate;
	private Date dateFormWasClosed;
	private Date departmentHeadDecisionDate;
	private Date benefitsCoordinatorDecisionDate;
	private Date directSupervisorDecisionDate;

	public ApplicationForm() {
		
	}
	
	public String getGeneralStatus() {
		return generalStatus;
	}

	public void setGeneralStatus(String generalStatus) {
		this.generalStatus = generalStatus;
	}

	public String getSupervisorComments() {
		return supervisorComments;
	}

	public void setSupervisorComments(String supervisorComments) {
		this.supervisorComments = supervisorComments;
	}

	public String getFormComments() {
		return formComments;
	}

	public void setFormComments(String formComments) {
		this.formComments = formComments;
	}

	public boolean isCompletedWithSatisfaction() {
		return isCompletedWithSatisfaction;
	}

	public void setCompletedWithSatisfaction(boolean isCompletedWithSatisfaction) {
		this.isCompletedWithSatisfaction = isCompletedWithSatisfaction;
	}

	public boolean isFormClosed() {
		return isFormClosed;
	}

	public void setFormClosed(boolean isFormClosed) {
		this.isFormClosed = isFormClosed;
	}

	public boolean isPresentationToManagementRequired() {
		return isPresentationToManagementRequired;
	}

	public void setPresentationToManagementRequired(boolean isPresentationToManagementRequired) {
		this.isPresentationToManagementRequired = isPresentationToManagementRequired;
	}

	public boolean isSupervisorDecisionMade() {
		return isSupervisorDecisionMade;
	}

	public void setSupervisorDecisionMade(boolean isSupervisorDecisionMade) {
		this.isSupervisorDecisionMade = isSupervisorDecisionMade;
	}

	public boolean isBenCoDecisionMade() {
		return isBenCoDecisionMade;
	}

	public void setBenCoDecisionMade(boolean isBenCoDecisionMade) {
		this.isBenCoDecisionMade = isBenCoDecisionMade;
	}

	public boolean isDepartmentHeadDecisionMade() {
		return isDepartmentHeadDecisionMade;
	}

	public void setDepartmentHeadDecisionMade(boolean isDepartmentHeadDecisionMade) {
		this.isDepartmentHeadDecisionMade = isDepartmentHeadDecisionMade;
	}

	public Date getFormCreationDate() {
		return formCreationDate;
	}

	public void setFormCreationDate(Date formCreationDate) {
		this.formCreationDate = formCreationDate;
	}

	public Date getDateFormWasClosed() {
		return dateFormWasClosed;
	}

	public void setDateFormWasClosed(Date dateFormWasClosed) {
		this.dateFormWasClosed = dateFormWasClosed;
	}

	public Date getDepartmentHeadDecisionDate() {
		return departmentHeadDecisionDate;
	}

	public void setDepartmentHeadDecisionDate(Date departmentHeadDecisionDate) {
		this.departmentHeadDecisionDate = departmentHeadDecisionDate;
	}

	public Date getBenefitsCoordinatorDecisionDate() {
		return benefitsCoordinatorDecisionDate;
	}

	public void setBenefitsCoordinatorDecisionDate(Date benefitsCoordinatorDecisionDate) {
		this.benefitsCoordinatorDecisionDate = benefitsCoordinatorDecisionDate;
	}

	public Date getDirectSupervisorDecisionDate() {
		return directSupervisorDecisionDate;
	}

	public void setDirectSupervisorDecisionDate(Date directSupervisorDecisionDate) {
		this.directSupervisorDecisionDate = directSupervisorDecisionDate;
	}
	
	@Override
	public String getFormUUID() {
		return this.formUUID;
	}

	@Override
	public void setFormUUID(String uuid) {
		this.formUUID = uuid;

	}

	@Override
	public String getEmployeeUUID() {
		return this.getEmployeeUUID();
	}

	@Override
	public void setEmployeeUUID(String uuid) {
		this.employeeUUID = uuid;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return this.lastName;
	}
	
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDirectSupervisorUUID() {
		return directSupervisorUUID;
	}

	public void setDirectSupervisorUUID(String directSupervisorUUID) {
		this.directSupervisorUUID = directSupervisorUUID;
	}

	public String getDepartmentHeadUUID() {
		return departmentHeadUUID;
	}

	public void setDepartmentHeadUUID(String departmentHeadUUID) {
		this.departmentHeadUUID = departmentHeadUUID;
	}

	public String getBenefitsCoordinatorUUID() {
		return benefitsCoordinatorUUID;
	}

	public void setBenefitsCoordinatorUUID(String benefitsCoordinatorUUID) {
		this.benefitsCoordinatorUUID = benefitsCoordinatorUUID;
	}

	@Override
	public void appendFormComments(String comment) {
		this.formComments += "\n" + comment;
	}
}
