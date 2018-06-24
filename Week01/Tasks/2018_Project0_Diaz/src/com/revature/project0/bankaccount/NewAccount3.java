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
	@Override
	public String toString() {
		return " [First Name =" + fName3 + ", Last Name =" + lName3 + "]";
	}
	
	public NewAccount3(String fName3, String lName3) {
		super();
		this.fName3 = fName3;
		this.lName3 = lName3;
	}
	public NewAccount3() {
		super();
		
	}
	
	public static void userInfor3() {
		System.out.println("What is your first name");
		fName3 = in.next();
		
		//System.out.println(fName3);
		System.out.println("What is your last name");
		lName3 = in.next();
		//System.out.println(lName3);
		System.out.println("=========================");

	}
	
	
	
}



























































