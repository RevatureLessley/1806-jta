package com.revature.bean;

public class Employee {
	private int id;
	private String email;
	private String password;
	private int role;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private int availReim;
	private int pendingReim;
	private int awardedReim;
	private int superVisorId;
	private int dHeadId;
	private int bencoId;	
	
	public Employee(int id, String email, String password, int role, String firstName, String lastName,
			long phoneNumber, int availReim, int pendingReim, int awardedReim, int superVisorId, int dHeadId,
			int bencoId) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.availReim = availReim;
		this.pendingReim = pendingReim;
		this.awardedReim = awardedReim;
		this.superVisorId = superVisorId;
		this.dHeadId = dHeadId;
		this.bencoId = bencoId;
	}
	
	public Employee() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAvailReim() {
		return availReim;
	}

	public void setAvailReim(int availReim) {
		this.availReim = availReim;
	}

	public int getPendingReim() {
		return pendingReim;
	}

	public void setPendingReim(int pendingReim) {
		this.pendingReim = pendingReim;
	}

	public int getAwardedReim() {
		return awardedReim;
	}

	public void setAwardedReim(int awardedReim) {
		this.awardedReim = awardedReim;
	}

	public int getSuperVisorId() {
		return superVisorId;
	}

	public void setSuperVisorId(int superVisorId) {
		this.superVisorId = superVisorId;
	}

	public int getdHeadId() {
		return dHeadId;
	}

	public void setdHeadId(int dHeadId) {
		this.dHeadId = dHeadId;
	}

	public int getBencoId() {
		return bencoId;
	}

	public void setBencoId(int bencoId) {
		this.bencoId = bencoId;
	};
}
