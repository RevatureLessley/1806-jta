package trms.beans;

public interface Form {
	String formUUID = "";
	String employeeUUID = "";
	String firstName = "";
	String lastName = "";
	String formComments = "";
	
	
	public String getFormUUID();
	public void setFormUUID(String uuid);
	public String getFormComments();
	public void setFormComments(String comment);
	public void appendFormComments(String comment);
	public String getEmployeeUUID();
	public void setEmployeeUUID(String uuid);
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	void setLastName(String lastName);	
	
}
