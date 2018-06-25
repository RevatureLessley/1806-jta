package com.revature.project0;


import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class User{

	enum Roles{ADMIN, USER;}
	private String username;
	private transient String password;
	private Account account;
	//private boolean isApproved = false;
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
	    String accountNum = UUID.randomUUID().toString();
		this.account = new Account(accountNum);
		
	}
	
   //private static final long serialVersionUID = 269430945991255245L;

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

	@Override
	public String toString() {
		return "User [ username=" + username + "]";
	}
}
