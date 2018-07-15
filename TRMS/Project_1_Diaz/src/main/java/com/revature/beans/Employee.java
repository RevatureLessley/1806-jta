package com.revature.beans;

public class Employee {
	private String empId;
	private String rfId;
	private String empTypeId;
	private String fName;
	private String lName;
	private String empPassword;
	private String empPhone;
	private String empEmail;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getRfId() {
		return rfId;
	}
	public void setRfId(String rfId) {
		this.rfId = rfId;
	}
	public String getEmpTypeId() {
		return empTypeId;
	}
	public void setEmpTypeId(String empTypeId) {
		this.empTypeId = empTypeId;
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
	public Employee(String empId, String rfId, String empTypeId, String fName, String lName, String empPassword,
			String empPhone, String empEmail) {
		super();
		this.empId = empId;
		this.rfId = rfId;
		this.empTypeId = empTypeId;
		this.fName = fName;
		this.lName = lName;
		this.empPassword = empPassword;
		this.empPhone = empPhone;
		this.empEmail = empEmail;
	}
	
	
	public Employee(String rfId, String empTypeId, String fName, String lName, String empPassword, String empPhone,
			String empEmail) {
		super();
		this.rfId = rfId;
		this.empTypeId = empTypeId;
		this.fName = fName;
		this.lName = lName;
		this.empPassword = empPassword;
		this.empPhone = empPhone;
		this.empEmail = empEmail;
	}
	public Employee() {
		super();
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", rfId=" + rfId + ", empTypeId=" + empTypeId + ", fName=" + fName
				+ ", lName=" + lName + ", empPassword=" + empPassword + ", empPhone=" + empPhone + ", empEmail="
				+ empEmail + "]";
	}
	
	
	
	
}
