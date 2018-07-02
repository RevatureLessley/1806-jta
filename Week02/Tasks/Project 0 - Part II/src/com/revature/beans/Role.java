package com.revature.beans;

public class Role {
	private int id;
	private String role;
	
	public Role(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
