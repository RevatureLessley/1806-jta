package com.revature.project0.bankaccount;

import java.io.Serializable;
import java.util.Scanner;

public class NewAccount implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4982115429345360237L;
	public static String fName;
	private static String lName;
	private static String accountNummber;
	

	
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
	public static String getAccountNummber() {
		return accountNummber;
	}
	public void setAccountNummber(String accountNummber) {
		this.accountNummber = accountNummber;
	}
	@Override
	public String toString() {
		return " [First Name =" + fName + ", Last Name =" + lName + "]";
	}
	
	public NewAccount(String fname, String lname) {
		super();
		this.fName = fname;
		this.lName = lname;
		this.accountNummber = accountNummber;
	}
	public NewAccount() {
		super();
		
	}
	
	public static void userInfor() {
		System.out.println("What is your first name");
		fName = in.next();
		
		//System.out.println(fName);
		System.out.println("What is your last name");
		lName = in.next();
		//System.out.println(lName);
		//System.out.println("What is your Account Nummber");
		//accountNummber = in.next();
		//System.out.println(accountNummber);
		System.out.println("=========================");

	}
	
	
	
}



























































