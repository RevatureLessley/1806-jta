package com.revature.beans;

public class Supervisor extends Employee {

	public Supervisor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Supervisor(int id, String role, int supVId, String firstName, String lastName, int phone,
			String email, String address, String location, String username, String password) {
		super(id, role, supVId, firstName, lastName, phone, email, address, location, username, password);
		// TODO Auto-generated constructor stub
	}
	
	public Supervisor(String role, int supVId, String firstName, String lastName, int phone,
			String email, String address, String location, String username, String password) {
		super(role, supVId, firstName, lastName, phone, email, address, location, username, password);
		// TODO Auto-generated constructor stub
	}
}
