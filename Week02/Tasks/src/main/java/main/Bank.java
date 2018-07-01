package main;

import java.sql.Connection;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

import util.Connections;
public class Bank{

	static final Logger logger = Logger.getLogger(Bank.class);
	
	public static void main(String args[]) throws Exception{
		//Establish connection to sql db
		Connection conn = Connections.getConnection();
		if(conn!=null){
			conn.close();
		}
		
		LoginPrompt lp = new LoginPrompt();
		
		User user = lp.enterLogin();
		if (user == null) return;
		
		Bank bank = new Bank();
		bank.options(user,lp);
	}
	
	/**
	 * This presents the option screen to the user and takes in user input
	 * @param user
	 * Currently logged in user
	 * @param users
	 * Hashmap containing the users
	 */
	public void options(User user,LoginPrompt lp) {
		System.out.println(
				"'0' Show balance \n"
				+ "'1' Deposit \n"
				+ "'2' Withdraw \n"
				+ "'3' Exit");
		
		String input = LoginPrompt.console.readLine(": ");
		
		switch(input) {
			case "0":
				System.out.println(user.getBalance());
				break;
			case "1":
				this.deposit(user,lp);
				break;
			case "2":
				this.withdraw(user,lp);
				break;
			case "3":
				return;
			case "authorize":
				if(user.getAuth() == 2) {
					this.approveUser(lp);
				}else {
					System.out.println("Only the Admin can approve users");
				}
				break;
			case "goodbye":
				if(user.getAuth() == 2) {
					this.removeUser(lp);
				}else {
					System.out.println("Only the Admin can approve users");
				}
				break;
			default:
				System.out.println("Invalid Command");
				break;
		}
		this.options(user,lp);
	}
	
	/**
	 * Deposits into the user's account
	 * @param user
	 * User that is depositing
	 */
	public void deposit(User user,LoginPrompt lp) {
		System.out.println("How much would you like to deposit?");
		String amount = LoginPrompt.console.readLine(": ");
		double d = Double.parseDouble(amount);
		amount = new DecimalFormat("##.##").format(d);
		d = Double.parseDouble(amount);
		if (d <= 0) { 
			System.out.println("Invalid amount");
			return;
		}
		user.setBalance(user.getBalance() + d);
		lp.updateBalanceDB(user);
		System.out.println("Current balance: " + user.getBalance());
		logger.info(user.getUserid() + " Deposited " + d);
		
	}
	
	/**
	 * Withdraws from the user's account
	 * @param user
	 * User that is withdrawing
	 */
	public void withdraw(User user,LoginPrompt lp) {
		if(user.getBalance() == 0) {
			System.out.println("You need a job");
			return;
		}
		System.out.println("How much would you like to withdraw?");
		String amount = LoginPrompt.console.readLine(": ");
		double d = Double.parseDouble(amount);
		amount = new DecimalFormat("##.##").format(d);
		d = Double.parseDouble(amount);
		if (d > user.getBalance()) { 
			System.out.println("Insufficient Funds");
			return;
		}
		user.setBalance(user.getBalance() - d);
		lp.updateBalanceDB(user);
		System.out.println("Current balance: " + user.getBalance());
		logger.info(user.getUserid() + " Withdrew " + d);
	}
	
	
	/**
	 * Approves a user's account, method can only be accessed by the admin
	 * @param users
	 * Hashmap where users are stored
	 */
	public void approveUser(LoginPrompt lp) {
		String username = LoginPrompt.console.readLine(": ");
		User user = lp.retrieveUserDB(username);
		if(user == null) System.out.println("User does not exist");
		else {
			System.out.println("User successfully approved");
			user.setAuth(1);
			lp.approveUserDB(user);
			logger.info(user.getUserid() + " Approved");
		}
	}
	
	/**
	 * Removes a user's account, method can only be accessed by the admin
	 * @param users
	 * Hashmap where users are stored
	 */
	public void removeUser(LoginPrompt lp) {
		String username = LoginPrompt.console.readLine(": ");
		User user = lp.retrieveUserDB(username);;
		if(user == null) System.out.println("User does not exist");
		else {
			System.out.println("User successfully removed");
			lp.removeUserDB(user);
			logger.info(user.getUserid() + " Removed");
		}
	}
}
