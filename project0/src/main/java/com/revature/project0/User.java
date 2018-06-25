package com.revature.project0;


import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable{

	private static final long serialVersionUID = 6294927486514826168L;
	
	private String username;
	private String password;
	private Account account;
	private boolean isAdmin;
	
	//private boolean isApproved = false;
	
	public User(String username, String password, boolean isAdmin){
		this.username = username;
		this.password = password;
	    String accountNum = UUID.randomUUID().toString();
		this.account = new Account(accountNum);
		this.isAdmin = isAdmin;
		
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
		
	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [ username=" + username + "]";
	}
}
