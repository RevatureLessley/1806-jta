package com.revature.day3.serialization;

import java.io.Serializable;
/*
 * Serializable is a MARKER INTERFACE, which means it is an interface
 * with no methods whatsoever. They only serve to 'mark' an object
 * as valid for serialization. (In this example) Another example of a
 * marker interface would be cloneable.
 */
public class Employee implements Serializable{
	/**
	 * The point of a serialVersionUID is to prevent another user
	 * from deserializing a serialized version of Employee if their
	 * implementation of the employee class doesnt have this exact
	 * id in it as well.
	 */
	private static final long serialVersionUID = 6733289469163709473L;
	//Transient will replace any information with the default value
	//during serialization
	private String fname;
	private transient String lname;
	private String email;
	private String title;
	private transient String phone;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/*
	 * toString is a method that serves to help Java understand a String
	 * representation of a specific object. Normally, if you syso an object,
	 * you will get the className@address. Which isn't particularly useful.
	 * We can use toString, to instead generate something more useful
	 */
	@Override
	public String toString() {
		return "Employee [fname=" + fname + ", lname=" + lname + ", email=" + email + ", title=" + title + ", phone="
				+ phone + "]";
	}
	
	public Employee(String fname, String lname, String email, String title, String phone) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.title = title;
		this.phone = phone;
	}
	public Employee() {
		super();
	}
	
	
	
	
}
