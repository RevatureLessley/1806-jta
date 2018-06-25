package com.bank.main;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable{

	private static final long serialVersionUID = -6780634874114839842L;
	private String fname;
	private String lname;
	private BigDecimal balance;
	private String password;
	private int cardNumber;
	private String email;
	private int role; // 1 for regular user, 2 for admin
	private boolean approved;
	
	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
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
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public User(String fname, String lname, String email, String password, int role, 
			boolean approved,BigDecimal balance, int cardNumber) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.email = email;
		this.balance = balance;
		this.cardNumber = cardNumber;
		this.role = role;
		this.approved = approved;
	}

	public User(String fname, String lname, String email, String password, int role, boolean approved) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.approved = approved;
	}
	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "fname = " + fname + ", lname = " + lname + ", email = " + email + ", password = " + password
				+ " balance = " + balance + " card number = " + cardNumber + 
				" approved = " + approved + " role = " + role + "\n";
	}
	
}
