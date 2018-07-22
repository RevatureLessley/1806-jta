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
	private String benCoComments;
	private String supervisorComments;
	private String departmentHeadComments;
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
		if (generalStatus == null) {
			this.generalStatus = "Not filled.";
			return;
		}
		this.generalStatus = generalStatus;
	}

	public String getSupervisorComments() {
		return supervisorComments;
	}

	public void setSupervisorComments(String supervisorComments) {
		if (supervisorComments == null) {
			this.supervisorComments = "Not filled.";
			return;
		}
		this.supervisorComments = supervisorComments;
	}

	public String getFormComments() {
		return formComments;
	}

	public void setFormComments(String formComments) {
		if (formComments == null) {
			this.formComments = "Not filled.";
			return;
		}
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
		if (dateFormWasClosed == null) {
			return;
		}
		this.dateFormWasClosed = dateFormWasClosed;
	}

	public Date getDepartmentHeadDecisionDate() {
		return departmentHeadDecisionDate;
	}

	public void setDepartmentHeadDecisionDate(Date departmentHeadDecisionDate) {
		if (departmentHeadDecisionDate == null) {
			return;
		}
		this.departmentHeadDecisionDate = departmentHeadDecisionDate;
	}

	public Date getBenefitsCoordinatorDecisionDate() {
		return benefitsCoordinatorDecisionDate;
	}

	public void setBenefitsCoordinatorDecisionDate(Date benefitsCoordinatorDecisionDate) {
		if (benefitsCoordinatorDecisionDate == null) {
			return;
		}
		this.benefitsCoordinatorDecisionDate = benefitsCoordinatorDecisionDate;
	}

	public Date getDirectSupervisorDecisionDate() {
		return directSupervisorDecisionDate;
	}

	public void setDirectSupervisorDecisionDate(Date directSupervisorDecisionDate) {
		if (directSupervisorDecisionDate == null) {
			return;
		}
		this.directSupervisorDecisionDate = directSupervisorDecisionDate;
	}
	
	@Override
	public String getFormUUID() {
		return this.formUUID;
	}

	@Override
	public void setFormUUID(String uuid) {
		if (uuid == null) {
			this.formUUID = "Not filled.";
			return;
		}
		this.formUUID = uuid;

	}

	@Override
	public String getEmployeeUUID() {
		return this.getEmployeeUUID();
	}

	@Override
	public void setEmployeeUUID(String uuid) {
		if (uuid == null) {
			this.employeeUUID = "Not filled.";
			return;
		}
		this.employeeUUID = uuid;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		if (firstName == null) {
			this.firstName = "Not filled.";
			return;
		}
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return this.lastName;
	}
	
	@Override
	public void setLastName(String lastName) {
		if (lastName == null) {
			this.lastName = "Not filled.";
			return;
		}
		this.lastName = lastName;
	}

	public String getDirectSupervisorUUID() {
		return directSupervisorUUID;
	}

	public void setDirectSupervisorUUID(String directSupervisorUUID) {
		if (directSupervisorUUID == null) {
			this.directSupervisorUUID = "Not filled.";
			return;
		}
		this.directSupervisorUUID = directSupervisorUUID;
	}

	public String getDepartmentHeadUUID() {
		return departmentHeadUUID;
	}

	public void setDepartmentHeadUUID(String departmentHeadUUID) {
		if (departmentHeadUUID == null) {
			this.departmentHeadUUID = "Not filled.";
			return;
		}
		this.departmentHeadUUID = departmentHeadUUID;
	}

	public String getBenefitsCoordinatorUUID() {
		return benefitsCoordinatorUUID;
	}

	public void setBenefitsCoordinatorUUID(String benefitsCoordinatorUUID) {
		if (benefitsCoordinatorUUID == null) {
			this.benefitsCoordinatorUUID = "Not filled.";
			return;
		}
		this.benefitsCoordinatorUUID = benefitsCoordinatorUUID;
	}

	@Override
	public void appendFormComments(String comment) {
		if (comment == null) {
			return;
		}
		this.formComments += "\n" + comment;
	}

	public String getBenCoComments() {
		return benCoComments;
	}

	public void setBenCoComments(String benCoComments) {
		if (benCoComments == null) {
			this.benCoComments = "Not filled.";
			return;
		}
		this.benCoComments = benCoComments;
	}

	public String getDepartmentHeadComments() {
		return departmentHeadComments;
	}

	public void setDepartmentHeadComments(String departmentHeadComments) {
		if (departmentHeadComments == null) {
			this.departmentHeadComments = "Not filled.";
			return;
		}
		this.departmentHeadComments = departmentHeadComments;
	}

	@Override
	public String toString() {
		return "ApplicationForm [formUUID=" + formUUID + ", employeeUUID=" + employeeUUID + ", directSupervisorUUID="
				+ directSupervisorUUID + ", departmentHeadUUID=" + departmentHeadUUID + ", benefitsCoordinatorUUID="
				+ benefitsCoordinatorUUID + ", firstName=" + firstName + ", lastName=" + lastName + ", generalStatus="
				+ generalStatus + ", benCoComments=" + benCoComments + ", supervisorComments=" + supervisorComments
				+ ", departmentHeadComments=" + departmentHeadComments + ", formComments=" + formComments
				+ ", isCompletedWithSatisfaction=" + isCompletedWithSatisfaction + ", isFormClosed=" + isFormClosed
				+ ", isPresentationToManagementRequired=" + isPresentationToManagementRequired
				+ ", isSupervisorDecisionMade=" + isSupervisorDecisionMade + ", isBenCoDecisionMade="
				+ isBenCoDecisionMade + ", isDepartmentHeadDecisionMade=" + isDepartmentHeadDecisionMade
				+ ", formCreationDate=" + formCreationDate + ", dateFormWasClosed=" + dateFormWasClosed
				+ ", departmentHeadDecisionDate=" + departmentHeadDecisionDate + ", benefitsCoordinatorDecisionDate="
				+ benefitsCoordinatorDecisionDate + ", directSupervisorDecisionDate=" + directSupervisorDecisionDate
				+ "]";
	}

}
