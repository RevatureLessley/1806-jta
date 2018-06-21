package com.revature.week1.tasks;

import java.util.Scanner;

public class Login {
	
	boolean isNameCorrect = false;
	boolean isPasswordCorrect = false;
	boolean isApproved = false;
	String storedName = "Chris";
	String storedPassword = "Password";
	String name;
	String password;
	
	Scanner sc = new Scanner(System.in);
	
	
	
	public void EnterNameAndPassword()
	{	
		do
		{
			EnterName();
			if(name.equals(storedName))
			{
				isNameCorrect = true;
			}
			else
			{
				System.out.println("How did you forget your own name!?! Try again!");
				isPasswordCorrect = false; //This is just to make absolutely sure that the user must get the name right and then be allowed to try the password
			}
		}
		while(!isNameCorrect && !isPasswordCorrect);
		
		do
		{
			EnterPassword();
			if(password.equals(storedPassword))
			{
				System.out.println("Conrats! You're in!");
				isPasswordCorrect = true;
			}
			else
			{
				System.out.println("Nope! Try again!");
			}
		}
		while(isNameCorrect && !isPasswordCorrect);
		
		System.out.println(this.name + " " + this.password);
	}
	
	
	public void EnterName()
	{
		System.out.println("Please Enter Name:");
		name = sc.nextLine();
	}
	
	public void EnterPassword()
	{
		System.out.println("Please Enter Password:");
		password = sc.nextLine();
	}

}
