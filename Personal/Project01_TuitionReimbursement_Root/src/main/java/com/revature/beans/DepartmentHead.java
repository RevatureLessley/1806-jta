package com.revature.beans;

public class DepartmentHead extends Employee {

	public DepartmentHead() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepartmentHead(int id, String role, int supVId, String firstName, String lastName, int phone,
			String email, String address, String location, String username, String password) {
		super(id, role, supVId, firstName, lastName, phone, email, address, location, username, password);
		// TODO Auto-generated constructor stub
	}
	
	public DepartmentHead(String role, int supVId, String firstName, String lastName, int phone,
			String email, String address, String location, String username, String password) {
		super(role, supVId, firstName, lastName, phone, email, address, location, username, password);
		// TODO Auto-generated constructor stub
	}
}