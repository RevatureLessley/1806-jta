package com.revature.week1.tasks;
import java.util.HashMap;
import java.util.Scanner;

public class SignUp {
	
	public static HashMap<String, String> usersMap = new HashMap<String, String>();
	String userName;
	String userPassword;
	Scanner sc = new Scanner(System.in);
	
	
	public void EnterNameAndPassword()
	{
		EnterName();
		EnterPassword();
	}
	
	void EnterName()
	{
		System.out.println("Please choose a Name:");
		userName = sc.nextLine();
	}
	
	void EnterPassword()
	{
		System.out.println("Please choose a Password:");
		userPassword = sc.nextLine();
	}
	
	void AddToHashMap()
	{
		usersMap.put(userName, userPassword);
		System.out.println("Thank You! You will have access as soon as you are approved!");
	}
}
