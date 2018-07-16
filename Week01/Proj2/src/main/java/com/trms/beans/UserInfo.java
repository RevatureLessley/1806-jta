package com.trms.beans;

import java.sql.Date;

public class UserInfo {
//    userInfoID NUMBER(6),
//    username VARCHAR2(100) UNIQUE,
//    fName VARCHAR2(100),
//    lName VARCHAR2(100),
//    birthDate DATE,
//    hireDate DATE,
//    address VARCHAR2(200),
//    postalCode number(7),

	private int ID, postalCode;
	private String username, fName, lName, address;
	private Date birthDate, hireDate;
	
	public UserInfo() {
		super();
	}

	public UserInfo(int iD, int postalCode, String username, String fName, String lName, String address, Date birthDate,
			Date hireDate) {
		super();
		ID = iD;
		this.postalCode = postalCode;
		this.username = username;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public String toString() {
		return "UserInfo [ID=" + ID + ", postalCode=" + postalCode + ", username=" + username + ", fName=" + fName
				+ ", lName=" + lName + ", address=" + address + ", birthDate=" + birthDate + ", hireDate=" + hireDate
				+ "]";
	}
	
	
}
