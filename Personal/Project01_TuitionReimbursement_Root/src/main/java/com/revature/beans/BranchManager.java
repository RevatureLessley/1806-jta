package com.revature.beans;

public class BranchManager extends Employee {

	public BranchManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BranchManager(int id, String role, int supVId, String firstName, String lastName, int phone, String email,
			String address, String location, String username, String password) {
		super(id, role, supVId, firstName, lastName, phone, email, address, location, username, password);
		// TODO Auto-generated constructor stub
	}

	public BranchManager(String role, int supVId, String firstName, String lastName, int phone, String email,
			String address, String location, String username, String password) {
		super(role, supVId, firstName, lastName, phone, email, address, location, username, password);
		// TODO Auto-generated constructor stub
	}
}
