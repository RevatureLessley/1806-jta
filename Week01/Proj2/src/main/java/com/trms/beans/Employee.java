package com.trms.beans;

public class Employee {
//    employeeID number(6),
//    userInfoID number(6) UNIQUE,
//    supervisorID number(6),
//    title varchar2(50),

	private Integer employeeID, userInfoID, supervisorID;
	private String title;
	private UserInfo userInfo = null;
	private Employee  supervisor = null;

	
	
	public Employee(Integer employeeID, String title, UserInfo userInfo) {
		super();
		this.employeeID = employeeID;
		this.title = title;
		this.userInfo = userInfo;
		this.userInfoID = userInfo.getID();
	}

	public Employee(Integer employeeID, String title, UserInfo userInfo, Employee supervisor) {
		super();
		this.employeeID = employeeID;
		this.title = title;
		this.userInfo = userInfo;
		this.supervisor = supervisor;
		this.userInfoID = userInfo.getID();
		this.supervisorID = supervisor.getEmployeeID();
	}

	public Employee() {
		super();
	}
	
	public Employee(Integer userInfoID, Integer supervisorID, String title) {
		super();
		this.userInfoID = userInfoID;
		this.supervisorID = supervisorID;
		this.title = title;
	}
	
	public Employee(Integer employeeID, Integer userInfoID, Integer supervisorID, String title) {
		super();
		this.employeeID = employeeID;
		this.userInfoID = userInfoID;
		this.supervisorID = supervisorID;
		this.title = title;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public Integer getUserInfoID() {
		return userInfoID;
	}

	public void setUserInfoID(Integer userInfoID) {
		this.userInfoID = userInfoID;
	}

	public Integer getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(Integer supervisorID) {
		this.supervisorID = supervisorID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", userInfoID=" + userInfoID + ", supervisorID=" + supervisorID
				+ ", title=" + title + "]";
	}
}
