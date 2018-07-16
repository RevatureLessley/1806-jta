package com.trms.beans;

public class Employee {
//    employeeID number(6),
//    userInfoID number(6) UNIQUE,
//    supervisorID number(6),
//    title varchar2(50),

	private int employeeID, userInfoID, supervisorID;
	private String title;

	public Employee() {
		super();
	}

	public Employee(int employeeID, int userInfoID, int supervisorID, String title) {
		super();
		this.employeeID = employeeID;
		this.userInfoID = userInfoID;
		this.supervisorID = supervisorID;
		this.title = title;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getUserInfoID() {
		return userInfoID;
	}

	public void setUserInfoID(int userInfoID) {
		this.userInfoID = userInfoID;
	}

	public int getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(int supervisorID) {
		this.supervisorID = supervisorID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", userInfoID=" + userInfoID + ", supervisorID=" + supervisorID
				+ ", title=" + title + "]";
	}
}
