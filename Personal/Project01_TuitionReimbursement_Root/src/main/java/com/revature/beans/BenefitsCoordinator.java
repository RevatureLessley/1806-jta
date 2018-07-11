package com.revature.beans;

public class BenefitsCoordinator extends Employee {

	public BenefitsCoordinator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BenefitsCoordinator(int id, String role, int supVId, String firstName, String lastName, int phone,
			String email, String address, String location, String username, String password) {
		super(id, role, supVId, firstName, lastName, phone, email, address, location, username, password);
		// TODO Auto-generated constructor stub
	}
	
	public BenefitsCoordinator(String role, int supVId, String firstName, String lastName, int phone,
			String email, String address, String location, String username, String password) {
		super(role, supVId, firstName, lastName, phone, email, address, location, username, password);
		// TODO Auto-generated constructor stub
	}
}
