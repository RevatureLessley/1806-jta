package com.revature.beans;

import java.io.Serializable;
import java.util.Scanner;
/**
 * NewAccount sets up account number one and uses Serializable to store the account information 
 * also uses a toString to ouput the data
 * @author Zachary Diaz
 *
 */
public class NewAccount implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4982115429345360237L;
	private static String fName;
	private static String lName;
	private static String address;
	private static String phone;
	

	
	static Scanner in = new Scanner(System.in);
	
	public static String getFname() {
		return fName;
	}
	public void setFname(String fname) {
		this.fName = fname;
	}
	public static String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public static String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public static String getPhone() {
		return phone;
	}
	public  void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return " First Name = " + fName + ", Last Name = " + lName + " Address = " + address + " Home Phone = " + phone ;
	}
	public NewAccount(String fname, String lname, String address, String phone) {
		super();
		this.fName = fname;
		this.lName = lname;
		this.address = address;
		this.phone = phone;
	}
	public NewAccount() {
		super();
		
	}
	/**
	 * userInfo is the text menu for taking in the account information 
	 */
	public static void userInfor() {
		System.out.println("=========================");
		System.out.println("What is your first name ");
		fName = in.next();
		System.out.println("What is your last name ");
		lName = in.next();
		System.out.println("What is you Home address ");
		address = in.next();
		System.out.println("What is your up todate phone# ");
		phone = in.next();
		System.out.println("=========================" + "\n");

	}
	
	
	
}



























































