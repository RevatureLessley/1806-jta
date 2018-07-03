package Project0.main;

import java.sql.Connection;
import java.util.Scanner;

import org.apache.log4j.Logger;


import Project0.bean.*;
import Project0.service.AccountService;
import Project0.service.UnAppUserService;
import Project0.service.UserService;
import Project0.util.Connections;

/**
 * The Driver class is the class from which the application will run. A user will be able to 
 * register and create an account. After which, an administrator will need to login and approve
 * the user. After the user is approved, they will be able to login and deposit and withdraw. 
 * When a user logs out, the application will close and will need to be restarted to login the 
 * next user. 
 * 
 * @author Vladimir Bukhalo
 *
 */
public class Driver {
	final static Logger logger = Logger.getLogger(Driver.class);
	
	private static Scanner in; 
	private static AccountService as;
	private static UserService us;
	private static UnAppUserService uns;
	
	/**
	 * The main() method is responsible for initializing fields 
	 * and calling methods for the application to run.
	 * 
	 * @param args String array passed in if running application from the command line.
	 */
	public static void main(String[] args) {
		in = new Scanner(System.in);
		
		as = new AccountService();
		us = new UserService();
		uns = new UnAppUserService();
		
		if(us.getNumberOfUsers() == 0) {
			Account adminAcc = new Account(0);
			as.insertAccount(adminAcc);
			User admin = new User("admin", "password", true, true);
			us.insertUser(admin);
		}
		
		run();
	}
	
	/**
	 * The run() method will run the application in the console.
	 */
	public static void run() {
		welcome();
		
		while(true) {					
			String input = in.nextLine().toLowerCase().trim();
			while(!(input.equals("register") || input.equals("login"))) {
				System.out.println("Invalid command. To login type 'login'. To register type 'register' ");
				input = in.nextLine().toLowerCase().trim();
			}			
			if(input.equals("register")){
				register();
				break;
			}	
			else if(input.equals("login")) {
				System.out.print("Please enter username: ");
				String uName = in.nextLine();
				
				while(us.getUserByUsername(uName) == null) {
					System.out.println("Username not found. Please try again");
					System.out.print("Please enter username: ");
					 uName = in.nextLine();
				}
				
				if(!us.getUserByUsername(uName).isApproved()) {
					System.out.println("Sorry your account has not been approved");
					System.out.println();
					break;
					}
				else if(us.getUserByUsername(uName).isApproved() && !us.getUserByUsername(uName).isAdmin()){
					System.out.print("Please enter password: ");
					String pass = in.nextLine();
					
					while(!pass.equals(us.getUserByUsername(uName).getPassword())) {
						logger.info(uName + " entered wrong password.");
						
						System.out.println("Sorry wrong password. Please try again");
						System.out.print("Please enter password: ");
						pass = in.nextLine();
					}
				}
				else if(us.getUserByUsername(uName).isAdmin()) {
					System.out.print("Please enter password: ");
					String pass = in.nextLine();
					
					while(!pass.equals(us.getUserByUsername(uName).getPassword())) {
						logger.info(uName + " entered the wrong password");
						
						System.out.println("Sorry wrong password. Please try again");
						System.out.print("Please enter password: ");
						pass = in.nextLine();
					}

					logger.info(uName + " successfully logged in");
					
					User admin = us.getUserByUsername(uName);
					Account adAcc = as.getAccountById(admin.getAccount());
					System.out.println("Welcome");
					System.out.println("Your balance is $" + adAcc.getBalance());						
					System.out.println();
					
					boolean activeAdmin = true;
					while(activeAdmin) {
						int unAppUserSize = uns.getNumberOfUsers();
						System.out.println("Accounts awating approval " + unAppUserSize);
						System.out.println("To approve users, type 'Approve', OR " 
								+  "Select from the following commands");		
						System.out.println("'Deposit' 'Withdraw' 'Balance' 'Logout'");
						
						String command = in.nextLine().toLowerCase().trim();
						activeAdmin = adminMenu(command, admin);
					}
					break;					

				}
				logger.info(uName + " successfully logged in");
				
				User u = us.getUserByUsername(uName);	
				Account uAcc = as.getAccountById(u.getAccount());
				System.out.println();
				System.out.println("Welcome " + u.getUserName());
				System.out.println("Your balance is $" + uAcc.getBalance());
				
				System.out.println();
				
				
				boolean activeUser = true;
				while(activeUser) {
					System.out.println("Please select from the following commands:");
					System.out.println("'Deposit' 'Withdraw' 'Balance' 'Logout'");
					
					String command = in.nextLine().toLowerCase().trim();
					activeUser = userMenu(command, u);
					
					}	
				break;				
			}

		}
	}
	
	/**
	 * The welcome() method will print a welcome message when the application starts.
	 */
	public static void welcome() {
		System.out.println("Welcome");
		System.out.println("To login type 'login'. To register type 'register'" );
	}
	
	/**
	 * The register() method will register a new user.
	 */
	public static void register() {
		Account acc = new Account(0);
		as.insertAccount(acc);
		
		System.out.print("Please enter username: ");
		String uName = in.nextLine();
		
		
		System.out.print("Please enter password: ");
		String pass = in.nextLine();
		System.out.println();
		
		User u = new User(uName,pass,false,false);
		UnAppUser uu = new UnAppUser(uName);
		us.insertUser(u);
		uns.insertUnAppUser(uu);
		
		logger.info(u.getUserName() + " registered for new account");
		
		System.out.println("Thank you for registering.");
		System.out.println("An administrator will approve your account");
		System.out.println();		
	}
	
	/**
	 * The userMenu() method will allow a user to deposit, withdraw, get balance and logout
	 * of their account. If the user chooses to logout, the application will close.
	 * 
	 * @param command The action taken by the user.
	 * @param u The user for which action is to be performed.
	 * @return Returns false if the user logs out, otherwise returns true.
	 */
	public static boolean userMenu(String command, User u) {
		boolean active = true;
		
		int accNum = u.getAccount();
		
		try {
			if(command.equals("deposit")) {
				System.out.print("How much would you like to deposit? $");
				int amount = Integer.parseInt(in.nextLine().trim());
				System.out.println("Balance after deposit $" + as.deposit(accNum, amount));
				
				logger.info(u.getUserName() + " deposited $" + amount);
			}
			else if(command.equals("withdraw")) {
				System.out.print("How much would you like to withdraw? $");
				int amount = Integer.parseInt(in.nextLine().trim());
				System.out.println("Balance after withdrawal $" + as.withdraw(accNum, amount));
				
				logger.info(u.getUserName() + " withdrew $" + amount);
			}
			else if(command.equals("balance")) {
				System.out.println("Current balance $" + as.balance(accNum));
			}
			else if(command.equals("logout")) {
				active = false;
				
				logger.info(u.getUserName() + " successfully logged out");
			}
			else {
				System.out.println("Not a valid command");
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid input. Please try again.");
			
			logger.error(u.getUserName() + " entered invalid amount");
		}
		System.out.println();
		return active;
	}
	
	/**
	 * The adminMenu is the menu presented to an administrator. In addition to providing 
	 * options to deposit, withdraw, get balance, and logout, 
	 * administrators can also choose to approve user accounts.
	 * If the administrator logs out, the application will close.
	 * 
	 * @param command The command chosen by the administrator.
	 * @param u The user for which the action is to be performed.
	 * @return Returns false if the user logs out, otherwise returns true.
	 */
	public static boolean adminMenu(String command, User u) {
		boolean active = true;
		
		
		if(command.equals("approve") && uns.getNumberOfUsers() != 0) {
			System.out.println("Accounts awaiting approval:");
			for(UnAppUser user: uns.getAllUnAppUsers()) {
				System.out.println(user.getUsername());								
			}
			System.out.println();
			System.out.println("Select User to Approve");
			String username = in.nextLine();
			
			User user = us.getUserByUsername(username);
			if(user != null){
				uns.deleteUserByUsername(username);
				us.updateApproval(user.getUserName());
				
				System.out.println(username + " Approved");
				System.out.println();
				logger.info(username + " approved by administrator");
			}								
		}
		else if(command.equals("approve") && uns.getNumberOfUsers() == 0) {
			System.out.println("No users to approve");
			System.out.println();
		}
		else {
			active = userMenu(command, u);
		}
		return active;
		
	}	

}
