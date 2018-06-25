package com.revature.week1.tasks;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.apache.log4j.Logger;




public class BankMain {
	
	
	
	
	private static ArrayList<User> users = new ArrayList<>();
	final static Logger logger = Logger.getRootLogger();
	
	
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		
		Bank bank = null;
		try 
		{
			ObjectInputStream ois = new ObjectInputStream(
			new FileInputStream("BankUsers.ser"));
			bank = (Bank)ois.readObject();
			ois.close();
		} 
		catch (FileNotFoundException e) 
		{
			intialSetup();
		}
		
		bank.initialSetup();
		
		
		
		
		
	}
	
	static void intialSetup()
	{
		// Note to self: User( "name", "password", balance, isAdmin, isApproved)
		// These users are here largely for testing, but also so that I have an administrator to approve people
		// hey also serve as an initial setup for the first time the "app" is run
		
		users.add(new User("chris", "password", 9999, true, true));
		users.add(new User("carol", "kitty", 356, false, true));
		users.add(new User("jack", "secretpassword", 1500, false, false));
		users.add(new User("admin", "password", 0, true, true));
		
		
		Bank bank = new Bank(users);
		
		System.out.println("made it through the initial setup!!");
		
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BankUsers.ser"));
			oos.writeObject(bank); //Serialize
			oos.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Yep did it great!"); // just for testing
		}
		
		System.out.println("END OF EXECUTION");
		
		bank.initialSetup();
	}
	

}
