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
		//setup users hashmap for the first time
		//Users users = new Users();
		
		LoginPrompt lp = new LoginPrompt();
		Users users = lp.retrieveUsers();
		User user = lp.enterLogin(users);
		if (user == null) return;
		
		Bank bank = new Bank();
		bank.options(user,users);
		lp.storeUsers(users);
	}
	
	/**
	 * This presents the option screen to the user and takes in user input
	 * @param user
	 * Currently logged in user
	 * @param users
	 * Hashmap containing the users
	 */
	public void options(User user,Users users) {
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
				this.deposit(user);
				break;
			case "2":
				this.withdraw(user);
				break;
			case "3":
				return;
			case "authorize":
				if(user instanceof Admin) {
					this.approveUser(users);
					input = "options";
				}else {
					System.out.println("Only the Admin can approve users");
					input = "options";
				}
				break;
			case "goodbye":
				this.removeUser(users);
				break;
			default:
				System.out.println("Invalid Command");
				break;
		}
		this.options(user,users);
	}
	
	/**
	 * Deposits into the user's account
	 * @param user
	 * User that is depositing
	 */
	public void deposit(User user) {
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
		System.out.println("Current balance: " + user.getBalance());
		logger.info(user.getUserid() + " Deposited " + d);
		
	}
	
	/**
	 * Withdraws from the user's account
	 * @param user
	 * User that is withdrawing
	 */
	public void withdraw(User user) {
		if(user.getBalance() == 0) {
			System.out.println("Sorry, you're poor");
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
		System.out.println("Current balance: " + user.getBalance());
		logger.info(user.getUserid() + " Withdrew " + d);
	}
	
	
	/**
	 * Approves a user's account, method can only be accessed by the admin
	 * @param users
	 * Hashmap where users are stored
	 */
	public void approveUser(Users users) {
		String username = LoginPrompt.console.readLine(": ");
		User user = users.getUsers().get(username);
		if(user == null) System.out.println("User does not exist");
		else {
			System.out.println("User successfully approved");
			user.setAuth(true);
			logger.info(user.getUserid() + " Approved");
		}
	}
	
	/**
	 * Removes a user's account, method can only be accessed by the admin
	 * @param users
	 * Hashmap where users are stored
	 */
	public void removeUser(Users users) {
		String username = LoginPrompt.console.readLine(": ");
		User user = users.getUsers().get(username);
		if(user == null) System.out.println("User does not exist");
		else {
			System.out.println("User successfully removed");
			users.removeUser(user);
			logger.info(user.getUserid() + " Removed");
		}
	}
}
