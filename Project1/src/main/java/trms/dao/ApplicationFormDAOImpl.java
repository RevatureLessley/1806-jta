package trms.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleTypes;
import trms.beans.ApplicationForm;
import trms.utilities.Connection;

public class ApplicationFormDAOImpl extends Connection implements ApplicationFormDAO {

	@Override
	public List<ApplicationForm> getAllForms() {
		ArrayList<ApplicationForm> forms = new ArrayList<ApplicationForm>();
		
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectAllForms(?)}");
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.execute();
			ResultSet rs = (ResultSet) callableStatement.getObject(1);
			
			while (rs.next()) {
				ApplicationForm form = new ApplicationForm();
				System.out.println(rs.getString("form_uuid"));
				form.setFirstName(this.getEmployeeFirstNameFromForm(rs.getString("form_uuid")));
				form.setLastName(this.getEmployeeLastNameFromForm(rs.getString("form_uuid")));			
				form.setFormUUID(rs.getString("form_uuid"));
				form.setEmployeeUUID(rs.getString("employee_uuid"));
				//form.setDirectSupervisorUUID(rs.getString("supervisor_uuid"));
				form.setDepartmentHeadUUID(rs.getString("department_head_uuid"));
				//form.setBenefitsCoordinatorUUID(rs.getString("benco_uuid"));
				form.setGeneralStatus(rs.getString("status"));
				form.setSupervisorComments(rs.getString("supervisor_comments"));
				form.setFormComments(rs.getString("form_comments"));
				form.setCompletedWithSatisfaction(this.getCompletedWithSatisfaction(rs.getString("form_uuid")));
				form.setFormClosed(this.isFormClosed(rs.getString("form_uuid")));
				form.setPresentationToManagementRequired(this.isPresentationRequired(rs.getString("form_uuid")));
				form.setSupervisorDecisionMade(this.hasSupervisorMadeADecision(rs.getString("form_uuid")));
				form.setBenCoDecisionMade(this.hasBenefitsCoordinatorMadeADecision(rs.getString("form_uuid")));
				form.setDepartmentHeadDecisionMade(this.hasDepartmentHeadMadeADecision(rs.getString("form_uuid")));
				form.setFormCreationDate(this.getFormCreationDate(rs.getString("form_uuid")));
				form.setDateFormWasClosed(this.getFormClosedDate(rs.getString("form_uuid")));
				form.setDepartmentHeadDecisionDate(this.getDepartmentHeadDecisionDate(rs.getString("form_uuid")));
				form.setBenefitsCoordinatorDecisionDate(this.getBenefitsCoordinatorDecisionDate(rs.getString("form_uuid")));
				form.setDirectSupervisorDecisionDate(this.getSupervisorDecisionDate(rs.getString("form_uuid")));
				
				forms.add(form);
			}
			return forms;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}

	@Override
	public ApplicationForm getApplicationForm(String formUUID) {
		java.sql.Connection connection = this.getConnection();
		ApplicationForm form = new ApplicationForm();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call selectForm(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			callableStatement.execute();
			ResultSet rs = (ResultSet) callableStatement.getObject(2);
			
			while (rs.next()) {
				
				form.setFirstName(this.getEmployeeFirstNameFromForm(rs.getString("form_uuid")));
				form.setLastName(this.getEmployeeLastNameFromForm(rs.getString("form_uuid")));			
				form.setFormUUID(rs.getString("form_uuid"));
				form.setEmployeeUUID(rs.getString("employee_uuid"));
				form.setDirectSupervisorUUID(rs.getString("supervisor_uuid"));
				form.setDepartmentHeadUUID(rs.getString("department_head_uuid"));
				form.setBenefitsCoordinatorUUID(rs.getString("benco_uuid"));
				form.setGeneralStatus(rs.getString("status"));
				form.setSupervisorComments(rs.getString("supervisor_comments"));
				form.setFormComments(rs.getString("form_comments"));
				form.setCompletedWithSatisfaction(this.getCompletedWithSatisfaction(rs.getString("form_uuid")));
				form.setFormClosed(this.isFormClosed(rs.getString("form_uuid")));
				form.setPresentationToManagementRequired(this.isPresentationRequired(rs.getString("form_uuid")));
				form.setSupervisorDecisionMade(this.hasSupervisorMadeADecision(rs.getString("form_uuid")));
				form.setBenCoDecisionMade(this.hasBenefitsCoordinatorMadeADecision(rs.getString("form_uuid")));
				form.setDepartmentHeadDecisionMade(this.hasDepartmentHeadMadeADecision(rs.getString("form_uuid")));
				form.setFormCreationDate(this.getFormCreationDate(rs.getString("form_uuid")));
				form.setDateFormWasClosed(this.getFormClosedDate(rs.getString("form_uuid")));
				form.setDepartmentHeadDecisionDate(this.getDepartmentHeadDecisionDate(rs.getString("form_uuid")));
				form.setBenefitsCoordinatorDecisionDate(this.getBenefitsCoordinatorDecisionDate(rs.getString("form_uuid")));
				form.setDirectSupervisorDecisionDate(this.getSupervisorDecisionDate(rs.getString("form_uuid")));
			}
			if (form.getFormUUID() != null) {
				return form;
			} else {
				return null;
			}
		
		} catch (Exception sqle) {
			return null;
		}
	}

	@Override
	public String getEmployeeUUID(String formUUID) {
		java.sql.Connection connection = this.getConnection();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call getEmployeeUUID(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
			ResultSet rs = callableStatement.executeQuery();
			
			return (String) callableStatement.getString(2);
		} catch (SQLException sqle) {
			return null;
		}
	}

	@Override
	public boolean updateEmployeeUUID(String formUUID, String employeeUUID) {
		java.sql.Connection connection = this.getConnection();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call updateEmployeeUUID(?, ?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.setString(2, employeeUUID);
			callableStatement.execute();
			
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public String getDirectSupervisorUUID(String formUUID) {
		java.sql.Connection connection = this.getConnection();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call getDirectSupervisorUUID(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.execute();
			
			return (String) callableStatement.getString(2);
		} catch (SQLException sqle) {
			return null;
		}
	}

	@Override
	public boolean updateSupervisorUUID(String formUUID, String supervisorUUID) {
		java.sql.Connection connection = this.getConnection();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call updateEmployeeUUID(?, ?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.setString(2, supervisorUUID);
			callableStatement.execute();
			
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public String getDepartmentHeadUUID(String formUUID) {
		java.sql.Connection connection = this.getConnection();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call getDeptartmentHeadUUID(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.execute();
			
			return (String) callableStatement.getString(2);
		} catch (SQLException sqle) {
			return null;
		}
	}
 
	@Override
	public boolean updateDepartmentHeadUUID(String formUUID, String departmentHeadUUID) {
		java.sql.Connection connection = this.getConnection();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call updateDeptHeadUUID(?, ?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.setString(2, departmentHeadUUID);
			callableStatement.execute();
			
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public String getBenefitsCoordinatorUUID(String formUUID) {
		java.sql.Connection connection = this.getConnection();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call getBenefitsCoordinatorUUID(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
			callableStatement.execute();
			
			return (String) callableStatement.getString(2);
		} catch (SQLException sqle) {
			return null;
		}
	}

	@Override
	public boolean UpdateBenefitsCoordinatorUUID(String formUUID, String benefitsCoordinatorUUID) {
		java.sql.Connection connection = this.getConnection();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call updateBenCoUUID(?, ?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.setString(2, benefitsCoordinatorUUID);
			callableStatement.execute();
			
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public String getEmployeeFirstNameFromForm(String formUUID) {
		java.sql.Connection connection = this.getConnection();
		String firstName = null;
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call getEmployeeFirstNameFromForm(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
			callableStatement.execute();
			firstName = (String) callableStatement.getObject(2);
			
			return firstName;
			
		} catch (SQLException sqle) {
			return null;
		}
	}

	@Override
	public String getEmployeeLastNameFromForm(String formUUID) {
		java.sql.Connection connection = this.getConnection();
		String lastName = null;
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call getEmployeeLastNameFromForm(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
			callableStatement.execute();
			lastName = (String) callableStatement.getObject(2);
			
			return lastName;
		} catch (SQLException sqle) {
			return null;
		}
	}

	@Override
	public String getFormStatus(String formUUID) {
		java.sql.Connection connection = this.getConnection();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call getFormStatus(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
			callableStatement.execute();
			
			return callableStatement.getString(2);
			
		} catch (SQLException sqle) {
			return null;
		}
	}

	@Override
	public boolean updateFormStatus(String formUUID, String formStatus) {
		java.sql.Connection connection = this.getConnection();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call updateFormStatus(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.setString(2, formStatus);
			callableStatement.execute();
			
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public String getSupervisorComments(String formUUID) {
		java.sql.Connection connection = this.getConnection();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call getSupervisorComment(?,?}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
			callableStatement.execute();
			return callableStatement.getString(2);
		} catch (SQLException sqle) {
			return null;
		}
	}

	@Override
	public boolean updateSupervisorComments(String formUUID, String supervisorComments) {
		java.sql.Connection connection = this.getConnection();
		 try {
			 CallableStatement callableStatement = connection.prepareCall("{call updateSupervisor(?,?)}");
			 callableStatement.setString(1, formUUID);
			 callableStatement.setString(2, supervisorComments);
			 callableStatement.execute();
			 return true;
		 } catch (SQLException sqle) {
			 return false;
		 }
	}

	@Override
	public String getFormComments(String formUUID) {
		java.sql.Connection connection = this.getConnection();
		try {
		CallableStatement callableStatement = connection.prepareCall("{call getFormComments(?,?)}");
		callableStatement.setString(1, formUUID);
		callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
		callableStatement.execute();
		
		return callableStatement.getString(2);
		
		} catch (SQLException sqle) {
			return null;
		}
	}

	@Override
	public boolean updateFormComments(String formUUID, String formComments) {
		java.sql.Connection connection = this.getConnection();
		 try {
			 CallableStatement callableStatement = connection.prepareCall("{call updateFormComments(?,?)}");
			 callableStatement.setString(1, formUUID);
			 callableStatement.setString(2, formComments);
			 callableStatement.execute();
			 return true;
		 } catch (SQLException sqle) {
			 return false;
		 }
	}

	@Override
	public boolean getCompletedWithSatisfaction(String formUUID) {
		java.sql.Connection connection = this.getConnection();
		try {
		CallableStatement callableStatement = connection.prepareCall("{call getCompletedWithSatisfaction(?,?)}");
		callableStatement.setString(1, formUUID);
		callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
		callableStatement.execute();
		
		String result = callableStatement.getString(2);
		
		if (result == null) {
			return false;
		}
		
		if (result.equals("YES")){
			return true;
		} else if (result.equals("NO")) {
			return false;
		} else {
			return false;
		}
		
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public boolean updateCompletedWithSatisfaction(String formUUID, boolean isCompleted) {
		java.sql.Connection connection = this.getConnection();
		
		String value = null;
		
		if (isCompleted == true) {
			value = "YES";
		} else {
			value = "NO";
		}
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call updateCompletedFormField(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.setString(2, value);
			callableStatement.execute();
			
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public boolean isFormClosed(String formUUID) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call isFormClosed(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
			callableStatement.execute();
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public boolean closeForm(String formUUID, boolean isCompleted) {
		try {
			
			String value = null;
			if (isCompleted == true) {
				value = "YES";
			} else {
				value = "NO";
			}
			
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call closeForm(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.setString(2, value);
			callableStatement.execute();
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public Date getFormClosedDate(String formUUID) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call getFormClosedDate(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.DATE);
			callableStatement.execute();
			
			return callableStatement.getDate(2);
			
		} catch (SQLException sqle) {
			return null;
		}
	}

	@Override
	public boolean isPresentationRequired(String formUUID) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call isPresentationRequired(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
			callableStatement.execute();
			
			if (callableStatement.getString(2).equals("YES")) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public boolean updatePresentationRequirements(String formUUID, boolean isPresentationRequired) {
		try {
			String value = null;
			
			if (isPresentationRequired = true) {
				value = "YES";
			} else {
				value = "NO";
			}
			
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call updatePresentationRequirements(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.setString(2, value);
			callableStatement.execute();
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public boolean hasSupervisorMadeADecision(String formUUID) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call hasSupervisorMadeADecision(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
			callableStatement.execute();
			
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public boolean hasBenefitsCoordinatorMadeADecision(String formUUID) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call hasBenCoMadeADecision(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
			callableStatement.execute();
			String result = callableStatement.getString(2);
			if (result.equals("YES")) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public boolean hasDepartmentHeadMadeADecision(String formUUID) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call hasDeptHeadMadeADecision(?,?))}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
			callableStatement.execute();
			String result = callableStatement.getString(2);
			if (result.equals("YES")) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public Date getFormCreationDate(String formUUID) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectFormCreationDate(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.DATE);
			callableStatement.execute();
			return callableStatement.getDate(2);
			
		} catch(SQLException sqle) {
			return null;
		}
	}

	@Override
	public boolean updateDepartmentHeadDecision(String formUUID, String decision) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call updateDepartmentHeadDecision(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.setString(2, decision);
			callableStatement.execute();
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public boolean updateBenefitsCoordinatorDecision(String formUUID, String decision) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call updateBenCoDecision(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.setString(2, decision);
			callableStatement.execute();
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public boolean updateSupervisorDecision(String formUUID, String decision) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call updateSupervisorDecision(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.setString(2, decision);
			callableStatement.execute();
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public Date getSupervisorDecisionDate(String formUUID) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectSupervisorDecisionDate(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.DATE);
			callableStatement.execute();
			return callableStatement.getDate(2);
			
		} catch(SQLException sqle) {
			return null;
		}
	}

	@Override
	public Date getBenefitsCoordinatorDecisionDate(String formUUID) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectBenCoDecisionDate(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.DATE);
			callableStatement.execute();
			return callableStatement.getDate(2);
			
		} catch(SQLException sqle) {
			return null;
		}
	}

	@Override
	public Date getDepartmentHeadDecisionDate(String formUUID) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectDeptHeadDecisionDate(?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.registerOutParameter(2, OracleTypes.DATE);
			callableStatement.execute();
			return callableStatement.getDate(2);
			
		} catch(SQLException sqle) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		ApplicationFormDAO applicationForm = new ApplicationFormDAOImpl();
		ApplicationForm appForm = new ApplicationForm();
		String formUUID = "";
		List<ApplicationForm> forms = applicationForm.getAllForms();
		for (ApplicationForm form : forms) {
			formUUID = form.getFormUUID();
			appForm = form;
		}
		System.out.println("getAllForms() returned a list the length of " + forms.size());
		System.out.println("Form UUID: " + formUUID);
		System.out.println(appForm.toString());
	}

	@Override
	public boolean submitNewApplicationForm(String formUUID, String employeeUUID, String formComments) {
		
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call newApplicationForm(?,?,?)}");
			callableStatement.setString(1, formUUID);
			callableStatement.setString(2, employeeUUID);
			callableStatement.setString(3, formComments);
			callableStatement.execute();
			
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public List<ApplicationForm> getUserApplicationForms(String userUUID) {
		List<ApplicationForm> forms = new ArrayList<ApplicationForm>();
		try {
			
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectFormWithUserUUID(?,?)}");
			callableStatement.setString(1, userUUID);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			callableStatement.execute();
			
			ResultSet rs = (ResultSet) callableStatement.getObject(2);
			while (rs.next()) {
				ApplicationForm form = new ApplicationForm();
				form.setFirstName(this.getEmployeeFirstNameFromForm(rs.getString("form_uuid")));
				form.setLastName(this.getEmployeeLastNameFromForm(rs.getString("form_uuid")));			
				form.setFormUUID(rs.getString("form_uuid"));
				form.setEmployeeUUID(rs.getString("employee_uuid"));
				form.setDirectSupervisorUUID(rs.getString("supervisor_uuid"));
				form.setDepartmentHeadUUID(rs.getString("department_head_uuid"));
				form.setBenefitsCoordinatorUUID(rs.getString("benco_uuid"));
				form.setGeneralStatus(rs.getString("status"));
				form.setSupervisorComments(rs.getString("supervisor_comments"));
				form.setFormComments(rs.getString("form_comments"));
				form.setCompletedWithSatisfaction(this.getCompletedWithSatisfaction(rs.getString("form_uuid")));
				form.setFormClosed(this.isFormClosed(rs.getString("form_uuid")));
				form.setPresentationToManagementRequired(this.isPresentationRequired(rs.getString("form_uuid")));
				form.setSupervisorDecisionMade(this.hasSupervisorMadeADecision(rs.getString("form_uuid")));
				form.setBenCoDecisionMade(this.hasBenefitsCoordinatorMadeADecision(rs.getString("form_uuid")));
				form.setDepartmentHeadDecisionMade(this.hasDepartmentHeadMadeADecision(rs.getString("form_uuid")));
				form.setFormCreationDate(this.getFormCreationDate(rs.getString("form_uuid")));
				form.setDateFormWasClosed(this.getFormClosedDate(rs.getString("form_uuid")));
				form.setDepartmentHeadDecisionDate(this.getDepartmentHeadDecisionDate(rs.getString("form_uuid")));
				form.setBenefitsCoordinatorDecisionDate(this.getBenefitsCoordinatorDecisionDate(rs.getString("form_uuid")));
				form.setDirectSupervisorDecisionDate(this.getSupervisorDecisionDate(rs.getString("form_uuid")));
				
				forms.add(form);
			}
			return forms;
		} catch (SQLException sqle) {
			return forms;
		}
	}
}
