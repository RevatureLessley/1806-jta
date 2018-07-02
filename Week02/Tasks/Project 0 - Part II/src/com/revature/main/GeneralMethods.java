package com.revature.main;
import java.util.List;

import com.revature.main.Bank;
import com.revature.beans.User;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;

public class GeneralMethods {
	//public static File file = new File("users.ser");
	/**
	 * Runs the program. If there are no users in files, then it asks to create an
	 * administrator account. Else, it displays the initial menu (login, register).
	 */
	
	UserMethods userMethods =  new UserMethods();
	
	public void runProgram() {
		initialMenu();
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
		
		UserDao ud = new UserDaoImpl();
		
		List<User> users = ud.selectAllUsers();
		
		for(User u:users) {
			if(u.getEmail().equals(email)) {
				if(u.getPassword().equals(password)) {
					if(u.getState() == 2) {
						System.out.println("You haven't been approved by an administrator, please check again later.");
						return;
					}
					if(u.getState() == 3) {
						System.out.println("You have been banned by an administrator.");
						return;
					}
					else {
						System.out.println("You have logged in successfully!");
						if(u.getRole() == 1) {
							Bank.adminMethods.adminMenu();
						}
						//else give the user more options
						else {
							Bank.userMethods.userMenu(u);
						}
					}
				}else {
					System.out.println("Wrong password or email");
				}
			}
		}
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
}
