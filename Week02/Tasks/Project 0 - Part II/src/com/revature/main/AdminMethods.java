package com.revature.main;

import com.revature.main.Bank;
import com.revature.service.UserService;

import java.util.List;

import com.revature.beans.User;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;

public class AdminMethods {
	public UserService uService = new UserService();
	public UserDao ud = new UserDaoImpl();
	
	/**
	 * Function available to administrators. It allows administrators to approve or reject 
	 * users that registered recently.
	 */
	public void approveUser() {
		//Bank.logger.info("Beginning of approveUser method.");
		List<User> users = null;
		users = ud.selectUsersByState(2);
		for(User u:users) {
			int choice = 0;
			do {
				System.out.println("User " + u.getFname() + " " + u.getLname() + " with email " + u.getEmail()
				+ " is waiting to be approved.");
				System.out.println("Enter 1 to approve or 2 to reject the user.");
				String input = Bank.sc.next();
				try {
					choice = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("You didn't enter an integer.");
				}
			} while(choice != 1 && choice != 2);
			switch (choice) {
			case 1:
				ud.updateUserStateById(u.getId(), 1);
				ud.commit();
				//Bank.logger.info("User has been approved.");
				break;

			case 2:
				ud.updateUserStateById(u.getId(), 3);
				ud.commit();
				//Bank.logger.info("User has been rejected.");
				break;
				
			default:
				System.out.println("Please enter a valid argument");
				break;
			}
		}
		//Bank.logger.info("End of list of users waiting to be approved.");
		System.out.println("There are no more users waiting to be approved.");
	}
	
	/**
	 * Displays the menu designed for regular users.
	 * @param user current user's data, needed to update its information.
	 */
	public void adminMenu() {
		//Bank.logger.info("Displaying administrator menu.");
		int choice = 0;
		do {
			System.out.println("Enter 1 if you want to approve users, "
					+ "2 if you want to print the existing users, or 3 if you want lo log out.");
			choice = Bank.sc.nextInt();
			switch (choice) {
			case 1:
				approveUser();
				break;
			case 2:
				printUsers();
				break;
				
			case 3:
				return;
			
			default:
				break;
			}
		} while (choice != 3);
	}
	
	/**
	 * Method only available to administrators, it prints all existing users.
	 */
	public void printUsers() {
		//Bank.logger.info("Printing users.");
		System.out.println("==========");
		List<User> users = ud.selectAllUsers();
		for(User u:users) {
			System.out.println(u + "\n");
		}
		//Bank.logger.info("End of list of users.");
	}
}
