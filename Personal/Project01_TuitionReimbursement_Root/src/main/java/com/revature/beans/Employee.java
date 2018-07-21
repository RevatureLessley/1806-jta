package com.revature.beans;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private int id;
	private int supVId;
	private String role;
	private String firstName;
	private String lastName;
	private int phone;
	private String email;
	private String address;
	private String location;
	private String username;
	private String password;
	List<Reimbursement> reims;
	
	public Employee(int id, String role, int supVId, String firstName, String lastName, int phone, String email, String address,
			String location, String username, String password) {
		super();
		this.id = id;
		this.supVId = supVId;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.location = location;
		this.username = username;
		this.password = password;
		reims = new ArrayList<Reimbursement>();
	}
	public Employee(String role, int supVId, String firstName, String lastName, int phone, String email, String address,
			String location, String username, String password) {
		super();
		this.supVId = supVId;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.location = location;
		this.username = username;
		this.password = password;
		reims = new ArrayList<Reimbursement>();
	}
	
	public Employee() {
		super();
	}
	
	public List<Reimbursement> getReims() {
		return reims;
	}
	public void setReims(List<Reimbursement> reims) {
		this.reims = reims;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getSupVId() {
		return supVId;
	}

	public void setSupVId(int supVId) {
		this.supVId = supVId;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", supVId=" + supVId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + ", location=" + location + "]";
	}
}
