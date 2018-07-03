package com.revature.project0;


import java.io.Serializable;


public class User implements Serializable{

	private static final long serialVersionUID = 6294927486514826168L;
	private Integer userid;
	private String username;
	private String password;
	private boolean isAdmin;
	
	public User() {		
	}
	
	public User(String username, String password, boolean isAdmin){
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public User(Integer userid, String username, String password, boolean isAdmin){	
		this(username, password, isAdmin);
		this.userid = userid;
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
		
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isAdmin() {
		return isAdmin;
	}

	
	public Integer getUserid() {
		return userid;
	}

	@Override
	public String toString() {
		return "User [ username=" + username + "]";
	}
}
