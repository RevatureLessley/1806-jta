package com.revature.main;

import java.math.BigDecimal;
import java.util.List;

import com.revature.main.Bank;
import com.revature.beans.Balance;
import com.revature.beans.User;
import com.revature.dao.BalanceDao;
import com.revature.dao.BalanceDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.service.UserService;

public class UserMethods {
	
	public UserDao ud = new UserDaoImpl();
	public UserService uService = new UserService();
	public BalanceDao bDao = new BalanceDaoImpl();
	
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
		
		List<User> existingUsers = ud.selectAllUsers();
		for (User user:existingUsers) {
			if(user.getEmail().equals(email)) {
				System.out.println("User already exists.");
				return;
			}
		}
		User user = new User(fname, lname, password, email, 2, 2);
		if(uService.insertUser(user)) {
			System.out.println("Your account was created successfully. It has to be "
					+ "approved by an administrator before you can use it.");
		} else {
			System.out.println("Sorry, we couldn't create your account.");
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
		Balance balance = null;
		balance = bDao.selectBalanceByUserId(user.getId());
		BigDecimal currentBalance = bDao.selectBalanceViaFnc(user.getId());
		System.out.println("Your current balance is " + currentBalance);
		int cardNum = 0;
		if(balance == null) {
			System.out.println("You need to add your credit card first. "
					+ "Enter 1 to enter your credit card or 2 if you want to go back.");
			int choice = Bank.sc.nextInt();
			switch (choice) {
			case 1:
				do {
					System.out.println("Enter a valid credit card number.");
					cardNum = Bank.sc.nextInt();
				} while (!Bank.generalMethods.validCardNumber(cardNum));
				Balance newBalance = new Balance(new BigDecimal(0), cardNum, user.getId());
				bDao.insertIntoBalanceViaSp(newBalance);
				Bank.logger.info("Balance inserted successfully.");
				break;
			case 2:
				return;

			default:
				break;
			}
		}
		System.out.println("How much money do you want to add?:");
		BigDecimal amount = Bank.sc.nextBigDecimal();
		balance = bDao.selectBalanceByUserId(user.getId());
		bDao.updateBalanceAmountById(user.getId(), amount.add(balance.getBalance()));
		bDao.commit();
		
		System.out.println("Your current balance is " + bDao.selectBalanceViaFnc(user.getId()));
		
		Bank.logger.info("Added money successfully.");
	}
	
	/**
	 * Subtracts money from the user's account. If the user doesn't have enough funds 
	 * it won't update anything, else it will update the user's balance.
	 * @param user
	 */
	public void withdrawMoney(User user) {
		Bank.logger.info("Beginning of function to withdraw money.");
		BigDecimal currentBalance = bDao.selectBalanceViaFnc(user.getId());
		System.out.println("Your current balance is " + currentBalance);
		System.out.println("How much money do you want to withdraw?");
		BigDecimal amount = Bank.sc.nextBigDecimal();
		if(enoughMoneyToWithdraw(currentBalance, amount)) {
			currentBalance = currentBalance.subtract(amount);
			bDao.updateBalanceAmountById(user.getId(), currentBalance);
			bDao.commit();
		}else {
			System.out.println("You don't have enough money to withdraw that amount.");
		}
		
		System.out.println("Your current balance is " + bDao.selectBalanceViaFnc(user.getId()));
		
	}
	
	/**
	 * Checks if an user has enough funds to withdraw the amount requested.
	 * @param user the user's information to access current balance
	 * @param amount requested amount to withdraw
	 * @return true if the user has enough funds, else false
	 */
	public boolean enoughMoneyToWithdraw(BigDecimal currentBalance, BigDecimal amount) {
		
		if(currentBalance == null || currentBalance.compareTo(amount) < 0) {
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
