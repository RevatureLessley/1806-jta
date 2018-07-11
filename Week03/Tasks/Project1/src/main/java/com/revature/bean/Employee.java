package com.revature.bean;

public class Employee {
	private Integer id;
	private String fname;
	private String lname;
	private String email;
	private Integer type;
	private Integer supervisedBy;
	private Double balance;
	private Integer department;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", type=" + type
				+ ", supervisedBy=" + supervisedBy + ", balance=" + balance + ", department=" + department + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSupervisedBy() {
		return supervisedBy;
	}

	public void setSupervisedBy(Integer supervisedBy) {
		this.supervisedBy = supervisedBy;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public Employee(Integer id, String fname, String lname, String email, Integer type, Integer supervisedBy,
			Double balance, Integer department) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.type = type;
		this.supervisedBy = supervisedBy;
		this.balance = balance;
		this.department = department;
	}

}
