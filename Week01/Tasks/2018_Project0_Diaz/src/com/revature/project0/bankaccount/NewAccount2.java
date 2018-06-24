package com.revature.project0.bankaccount;

import java.io.Serializable;
import java.util.Scanner;

public class NewAccount2 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4982115429345360237L;
	private static String fName2;
	private static String lName2;
	
	
	
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
	
	@Override
	public String toString() {
		return " [First Name =" + fName2 + ", Last Name =" + lName2 + "]";
	}
	
	public NewAccount2(String fName2, String lName2) {
		super();
		this.fName2 = fName2;
		this.lName2 = lName2;
		
	}
	public NewAccount2() {
		super();
		
	}
	
	public static void userInfor2() {
		System.out.println("What is your first name");
		fName2 = in.next();
		//System.out.println(fName2);
		System.out.println("What is your last name");
		lName2 = in.next();
		//System.out.println(lName2);

	}
	
	
	
}
