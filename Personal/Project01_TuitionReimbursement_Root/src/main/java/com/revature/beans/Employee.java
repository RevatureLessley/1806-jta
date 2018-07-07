package com.revature.beans;

public class Employee {
	private int id;
	private int supVId;
	private String firstName;
	private String lastName;
	private int phone;
	private String email;
	private String address;
	private String location;
	
	public Employee(int id, int supVId, String firstName, String lastName, int phone, String email, String address,
			String location) {
		super();
		this.id = id;
		this.supVId = supVId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.location = location;
	}
	
	public Employee() {
		super();
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", supVId=" + supVId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + ", location=" + location + "]";
	}
}
