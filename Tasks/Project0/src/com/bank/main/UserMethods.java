package com.bank.main;

import java.math.BigDecimal;
import java.util.ArrayList;

public class UserMethods {
	/**
	 * Function that handles registration. If file is empty or does not exist, creates a new file.
	 * Otherwise it deserializes the data, adds the new data, and finally serializes again.
	 */
	public void register() {
		Bank.logger.info("Beginning of method to register an user.");
		System.out.println("Enter your first name:");
		String fname = Bank.sc.next();
		System.out.println("Enter your last name:");
		String lname = Bank.sc.next();
		System.out.println("Enter your email:");
		String email = Bank.sc.next();
		System.out.println("Enter a password:");
		String password = Bank.sc.next();
		
		// If file does not exist or file is empty
		if(!Bank.file.isFile() || Bank.file.length() == 0) {	
			// The first user will be admin by default
			User newUser = new User(fname, lname, email, password, 2, true);
			// Add user to arrayList
			ArrayList<User> listUsers = new ArrayList<>();
			listUsers.add(newUser);
			// Serialize users
			Bank.generalMethods.serializeUsers(listUsers);
			Bank.logger.info("Created admin account.");
		}
		
		// Otherwise, if file already has users
		else {
			// Deserialize Users
			ArrayList<User> existingUsers = Bank.generalMethods.deserializeUsers();
			// Check if user already exists (compare email address)
			for(User newUser : existingUsers) {
				if(newUser.getEmail().equals(email)) {
					System.out.println("An user with that email already exists.");
					return;
				}
			}
			
			// If user doesn't exist, then create it
			User registeringUser = new User(fname, lname, email, password, 1, false);
			existingUsers.add(registeringUser);
			Bank.generalMethods.serializeUsers(existingUsers); // serialize users
			System.out.println("Thank you for registering! You have to be approved by an "
					+ "admin first. Check back later!");
			Bank.logger.info("Created user account.");
		}
	}
	
	/**
	 * Adds money to the given user's account. If the user doesn't have a valid 
	 * card number in the database, it prompts the user to update its card number.
	 * If user already has a valid card number it will use that card.
	 * @param user the user that will be updated
	 */
	public void addMoney(User user) {
		Bank.logger.info("Beginning of function to add money.");
		BigDecimal currentBalance = user.getBalance();
		if(currentBalance == null) {
			currentBalance = new BigDecimal(0);
		}
		System.out.println("Your current balance is " + currentBalance);
		System.out.println("How much money do you want to add?");
		BigDecimal amount = Bank.sc.nextBigDecimal();
		if (!Bank.generalMethods.validCardNumber(user.getCardNumber())) {
			Bank.logger.info("There's not a valid card registered.");
			int cardNum = 0;
			do{
				System.out.println("Please enter a valid card number: ");
				cardNum = Bank.sc.nextInt();
			} while(!Bank.generalMethods.validCardNumber(cardNum));
			user.setCardNumber(cardNum);
			Bank.logger.info("Set new card number.");
		}
		
		currentBalance = currentBalance.add(amount);
		user.setBalance(currentBalance);
		Bank.adminMethods.updateUser(user);
		System.out.println("Your new balance is " + user.getBalance());
		Bank.logger.info("Added money successfully.");
	}
	
	/**
	 * Subtracts money from the user's account. If the user doesn't have enough funds 
	 * it won't update anything, else it will update the user's balance.
	 * @param user
	 */
	public void withdrawMoney(User user) {
		Bank.logger.info("Beginning of function to withdraw money.");
		BigDecimal currentBalance = user.getBalance();
		if(currentBalance == null) {
			currentBalance = new BigDecimal(0);
		}
		System.out.println("Your current balance is " + currentBalance);
		System.out.println("How much money do you want to withdraw?");
		BigDecimal amount = Bank.sc.nextBigDecimal();
		if (!enoughMoneyToWithdraw(user, amount)) {
			System.out.println("You don't have enough funds.");
			Bank.logger.info("User has not enough funds to withdraw money.");
		} else {
			currentBalance = currentBalance.subtract(amount);
			user.setBalance(currentBalance);
			Bank.adminMethods.updateUser(user);
			System.out.println("Please, take your money.");
			System.out.println("Your new balance is " + user.getBalance());
			Bank.logger.info("User has withdrawn money successfully.");
		}
	}
	
	/**
	 * Checks if an user has enough funds to withdraw the amount requested.
	 * @param user the user's information to access current balance
	 * @param amount requested amount to withdraw
	 * @return true if the user has enough funds, else false
	 */
	public boolean enoughMoneyToWithdraw(User user, BigDecimal amount) {
		if(user.getBalance().compareTo(amount) < 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Displays the menu designed for regular users.
	 * @param user current user's data, needed to update its information.
	 */
	public void userMenu(User user) {
		Bank.logger.info("Displaying menu for regular users.");
		int choice = 0;
		do {
			System.out.println("Enter 1 if you want to add money to your account, "
					+ "2 if you want to withdraw money, or 3 if you want lo log out.");
			choice = Bank.sc.nextInt();
			switch (choice) {
			case 1:
				addMoney(user);
				break;
			case 2:
				withdrawMoney(user);
				break;
				
			case 3:
				return;
			
			default:
				break;
			}
		} while (choice != 3);
	}
}
