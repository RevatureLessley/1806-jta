package com.revature.project0.bankaccount;

import java.io.Serializable;
import java.util.Scanner;

public class NewAccount3 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1270536441425038912L;
	/**
	 * 
	 */

	public static String fName3;
	private static String lName3;
	private static String address3;
	private static String phone3;
	
	static Scanner in = new Scanner(System.in);
	
	public static String getfName3() {
		return fName3;
	}
	public void setfName3(String fName3) {
		this.fName3 = fName3;
	}
	public static String getlName3() {
		return lName3;
	}
	public void setlName3(String lName3) {
		this.lName3 = lName3;
	}
	public static String getAddress() {
		return address3;
	}
	public void setAddress(String addres3s) {
		this.address3 = address3;
	}
	public static String getPhone() {
		return phone3;
	}
	public  void setPhone(String phone3) {
		this.phone3 = phone3;
	}
	@Override
	public String toString() {
		return " [First Name =" + fName3 + ", Last Name =" + lName3 +  "Address = " + address3 + "Home Phone = " + phone3;
	}
	
	public NewAccount3(String fName3, String lName3) {
		super();
		this.fName3 = fName3;
		this.lName3 = lName3;
		this.address3 = address3;
		this.phone3 = phone3;
	}
	public NewAccount3() {
		super();
		
	}
	
	public static void userInfor3() {
		System.out.println("=========================");
		System.out.println("What is your first name");
		fName3 = in.next();
		System.out.println("What is your last name");
		lName3 = in.next();
		System.out.println("What is you Home address ");
		address3 = in.next();
		System.out.println("What is your up todate phone# ");
		phone3 = in.next();
		System.out.println("=========================" + "\n");

	}
	
	
	
}



























































