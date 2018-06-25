package com.bank.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GeneralMethods {
	//public static File file = new File("users.ser");
	/**
	 * Runs the program. If there are no users in files, then it asks to create an
	 * administrator account. Else, it displays the initial menu (login, register).
	 */
	
	UserMethods userMethods =  new UserMethods();
	
	public void runProgram() {
		// If file containing users does not exist or is empty, create an admin account
		if(!Bank.file.isFile() || Bank.file.length() == 0) {
			Bank.logger.info("No users registered.");
			System.out.println("There are no users registered. You must register"
					+ "an admin account.");
			Bank.logger.info("Calling register method");
			userMethods.register();
		} 
		
		// if user exists, then give options to register or login
		else {
			initialMenu();
		}
	}
	
	/**
	 * Displays the initial menu (login, register, quit).
	 */
	public void initialMenu() {
		Bank.logger.info("Displaying initial menu.");
		int choice = 0;
		do {
			System.out.println("Enter 1 to login, enter 2 to register, 3 to exit the program.");
			String input = Bank.sc.next();
			try {
				choice = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("You didn't enter an integer.");
			}
			switch (choice) {
			case 1:
				login();
				break;

			case 2:
				Bank.userMethods.register();
				break;
				
			case 3:
				//System.exit(0);
				return;
				
			default:
				System.out.println("Please enter a valid argument");
				break;
			}
		} while (choice != 3);
	}
	
	/**
	 * Handles login operations. It checks if the email and password match with our records.
	 * It also checks the user's role. It will give different options to administrators and
	 * regular users.
	 */
	public void login() {
		Bank.logger.info("Beginning of login method");
		System.out.println("Enter your email:");
		String email = Bank.sc.next();
		System.out.println("Enter your password:");
		String password = Bank.sc.next();
		
		// Deserialize users
		ArrayList<User> existingUsers = deserializeUsers();
		// Check if email and password are in file
		for(User loginUser : existingUsers) {
			//System.out.println(newUser.getEmail());
			if(loginUser.getEmail().equals(email) && loginUser.getPassword().equals(password)) {
				if (!loginUser.isApproved()) {
					Bank.logger.info("User has not been approved yet.");
					System.out.println("Your account has not been approved, yet. Please "
							+ "come back later.");
				} else {
					Bank.logger.info("User has logged in succesfully.");
					System.out.println("Logged in successfully");
					// If user is admin, then approve users
					if(loginUser.getRole() == 2) {
						Bank.adminMethods.adminMenu();
					}
					//else give the user more options
					else {
						Bank.userMethods.userMenu(loginUser);
					}
				}
				return;
			}
		}
		System.out.println("Sorry, wrong user or password.");
	}
	
	/**
	 * Deserializes the data stored in files. 
	 * @return returns an array list containing all the users data
	 */
	public ArrayList<User> deserializeUsers(){
		Bank.logger.info("Deserializing users.");
		Users deserializedUsers = null;
		
		// Deserialize Users
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream(Bank.file));
			deserializedUsers = (Users)ois.readObject();
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Store all existing users in arrayList
		ArrayList<User> existingUsers = deserializedUsers.getUsers();
		return existingUsers;
	}
	
	/**
	 * Checks if a number is valid credit card number.
	 * @param cardNum the card number to be checked
	 * @return true if it is a valid card number, otherwise false
	 */
	public boolean validCardNumber(int cardNum) {
		String pattern = "\\d{10}";
		String numString = Integer.toString(cardNum);
	    if (numString.matches(pattern)) {
	    	Bank.logger.info("Card number is valid.");
			return true;
		}
	    Bank.logger.info("Card number is invalid.");
		return false;
	}
	
	/**
	 * Function that serializes the users. 
	 * @param userList array list containing the users to be serialized.
	 */
	public void serializeUsers(ArrayList<User> userList) {
		Bank.logger.info("Beginning of method that serializes users");
		ObjectOutputStream oos = null;
		try{
			oos = new ObjectOutputStream(
										new FileOutputStream(Bank.file));
			Users users = new Users(userList);
			// Serialize
			oos.writeObject(users);
			oos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		Bank.logger.info("Users serialized succesfully.");
	}
}
