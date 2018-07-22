package com.revature.beans;

public class Employee {
	private Integer empId;
	private String fName;
	private String lName;
	private String userName;
	private String empPassword;
	private String empPhone;
	private String empEmail;
	private String empDept;
	private String role;
	private String role2;
	private Integer formId;
	private Integer id;
	private Integer availableReimburstment;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public String getEmpPhone() {
		return empPhone;
	}
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getEmpDept() {
		return empDept;
	}
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRole2() {
		return role2;
	}
	public void setRole2(String role2) {
		this.role2 = role2;
	}
	public Integer getFormId() {
		return formId;
	}
	public void setFormId(Integer formId) {
		this.formId = formId;
	}
	public Integer getAvailableReimburstment() {
		return availableReimburstment;
	}
	public void setAvailableReimburstment(Integer availableReimburstment) {
		this.availableReimburstment = availableReimburstment;
	}
	
	public Employee(Integer empId, String fName, String lName, String userName, String empPassword, String empPhone,
			String empEmail, String empDept, String role, String role2, Integer formId,
			Integer availableReimburstment) {
		super();
		this.empId = empId;
		this.fName = fName;
		this.lName = lName;
		this.userName = userName;
		this.empPassword = empPassword;
		this.empPhone = empPhone;
		this.empEmail = empEmail;
		this.empDept = empDept;
		this.role = role;
		this.role2 = role2;
		this.formId = formId;
		this.availableReimburstment = availableReimburstment;
	}
	
	public Employee(String fName, String lName, String userName, String empPassword, String empPhone, String empEmail,
			String empDept, String role, String role2, Integer formId, Integer availableReimburstment) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.userName = userName;
		this.empPassword = empPassword;
		this.empPhone = empPhone;
		this.empEmail = empEmail;
		this.empDept = empDept;
		this.role = role;
		this.role2 = role2;
		this.formId = formId;
		this.availableReimburstment = availableReimburstment;
	}
	
	public Employee(String fName, String lName, String userName, String empPassword, String empPhone, String empEmail,
			String empDept) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.userName = userName;
		this.empPassword = empPassword;
		this.empPhone = empPhone;
		this.empEmail = empEmail;
		this.empDept = empDept;
	}
	public Employee() {
		super();
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", fName=" + fName + ", lName=" + lName + ", userName=" + userName
				+ ", empPassword=" + empPassword + ", empPhone=" + empPhone + ", empEmail=" + empEmail + ", empDept="
				+ empDept + ", role=" + role + ", role2=" + role2 + ", formId=" + formId + ", availableReimburstment="
				+ availableReimburstment + "]";
	}
	
}
