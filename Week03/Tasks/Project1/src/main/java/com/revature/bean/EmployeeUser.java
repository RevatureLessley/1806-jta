package com.revature.bean;

public class EmployeeUser {

	private Integer empId;
	private String empUsername;
	private String empPassword;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpUsername() {
		return empUsername;
	}

	public void setEmpUsername(String empUsername) {
		this.empUsername = empUsername;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public EmployeeUser(Integer empId, String empUsername, String empPassword) {
		super();
		this.empId = empId;
		this.empUsername = empUsername;
		this.empPassword = empPassword;
	}

	public EmployeeUser() {
		super();
	}

	@Override
	public String toString() {
		return "EmployeeUser [empId=" + empId + ", empUsername=" + empUsername + ", empPassword=" + empPassword + "]";
	}
}
