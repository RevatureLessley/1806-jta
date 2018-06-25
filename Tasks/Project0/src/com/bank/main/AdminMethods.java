package com.bank.main;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.ListIterator;

public class AdminMethods {
	
	/**
	 * Function available to administrators. It allows administrators to approve or reject 
	 * users that registered recently.
	 */
	public void approveUser() {
		Bank.logger.info("Beginning of approveUser method.");
		// Deserialize Users
		 ArrayList<User> existingUsers = Bank.generalMethods.deserializeUsers();

		int choice = 0;
		ListIterator<User> iter = existingUsers.listIterator(existingUsers.size());

		while (iter.hasPrevious()) {
		    User iterUser = iter.previous();
		    if (!iterUser.isApproved()) {
				do {
					System.out.println("User with email " + iterUser.getEmail() + " has not been approved. "
							+ "Enter 1 to approve it or 2 to reject it.");
					String input = Bank.sc.next();
					try {
						choice = Integer.parseInt(input);
					} catch (NumberFormatException e) {
						System.out.println("You didn't enter an integer.");
					}
				} while(choice != 1 && choice != 2);
				switch (choice) {
				case 1:
					iterUser.setApproved(true);
					Bank.logger.info("User has been approved.");
					break;

				case 2:
					iter.remove();
					Bank.logger.info("User has been rejected.");
					break;
					
				default:
					System.out.println("Please enter a valid argument");
					break;
				}
			}
		}
		Bank.generalMethods.serializeUsers(existingUsers);
		System.out.println("You are done! There are no users waiting to be approved!");
		Bank.logger.info("There are no users waiting to be approved.");
	}
	
	/**
	 * Updates a given user. This function deserializes the data first, 
	 * then stores the existing users in an array list, finds the given user and 
	 * updates its data. Finally, it serializes the data again.
	 * @param user the user to be updated
	 */
	public void updateUser(User user) {
		Bank.logger.info("Beginning of method to deserialize users.");
		// Deserialize Users
		ArrayList<User> existingUsers = Bank.generalMethods.deserializeUsers();
		// Check if user already exists (compare email address)
		for(User updateUser : existingUsers) {
			if(user.getEmail().equals(updateUser.getEmail())) {
				updateUser.setBalance(user.getBalance());
				updateUser.setApproved(user.isApproved());
				updateUser.setCardNumber(user.getCardNumber());
				updateUser.setRole(user.getRole());
				break;
			}
		}
		Bank.generalMethods.serializeUsers(existingUsers);
		Bank.logger.info("Users deserialized succesfully.");
	}
	
	/**
	 * Displays the menu designed for regular users.
	 * @param user current user's data, needed to update its information.
	 */
	public void adminMenu() {
		Bank.logger.info("Displaying administrator menu.");
		int choice = 0;
		do {
			System.out.println("Enter 1 if you want to approve users, "
					+ "2 if you want print the existing users, or 3 if you want lo log out.");
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
		Bank.logger.info("Printing users.");
		System.out.println("==========");
		Users myUserss = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream(Bank.file));
			myUserss = (Users)ois.readObject();
			ois.close();
		} catch (EOFException e) {
			System.out.println("File is empty.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(myUserss);
	}
}
