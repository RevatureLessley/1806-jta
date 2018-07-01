package com.revature.beans;

import java.io.Serializable;
import java.util.Scanner;

public class NewAccount2 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4982115429345360237L;
	private static String fName2;
	private static String lName2;
	private static String address2;
	private static String phone2;
	
	
	static Scanner in = new Scanner(System.in);
	
	public static String getfName2() {
		return fName2;
	}
	public void setfName2(String fName2) {
		this.fName2 = fName2;
	}
	public static String getlName2() {
		return lName2;
	}
	public void setlName2(String lName2) {
		this.lName2 = lName2;
	}
	public static String getAddress2() {
		return address2;
	}
	public void setAddress(String address2) {
		this.address2 = address2;
	}
	public static String getPhone2() {
		return phone2;
	}
	public  void setPhone(String phone2) {
		this.phone2 = phone2;
	}
	@Override
	public String toString() {
		return " [First Name = " + fName2 + ", Last Name = " + lName2 + " Address = " + address2 + " Home Phone = " + phone2;
	}
	
	public NewAccount2(String fName2, String lName2, String address2, String phone2) {
		super();
		this.fName2 = fName2;
		this.lName2 = lName2;
		this.address2 = address2;
		this.phone2 = phone2;
		
	}
	public NewAccount2() {
		super();
		
	}
	
	public static void userInfor2() {
		System.out.println("=========================");
		System.out.println("What is your first name");
		fName2 = in.next();
		System.out.println("What is your last name");
		lName2 = in.next();
		System.out.println("What is you Home address ");
		address2 = in.next();
		System.out.println("What is your up todate phone# ");
		phone2 = in.next();
		System.out.println("=========================" + "\n");

	}
	
	
	
}
