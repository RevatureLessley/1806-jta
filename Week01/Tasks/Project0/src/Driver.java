import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * The Driver class is the class from which the application will run.
 * @author Vladimir Bukhalo
 *
 */
public class Driver {
	final static Logger logger = Logger.getLogger(Driver.class);
	
	private static Scanner in;
	private static Map<String, User> users;
	private static Map<String, User> unAppUsers;
	private static List<Map<String, User>> list; 

	/**
	 * The main() method is responsible for initializing fields calling methods for the application to run.
	 * @param args String array passed in if running application from the command line.
	 */
	public static void main(String[] args) {		
		list = new ArrayList<>();
		
		File f = new File("users.ser");
		
		if(f.exists()) {
			users = deserializeUsers().get(0);
			unAppUsers = deserializeUsers().get(1);
			logger.info("users.ser file found");
		}
		else {
			logger.info("users.ser file not found");
			Account adminAccount = new Account();
			users = new HashMap<>();
			User admin = new User("admin", "password", adminAccount,true,true);
			unAppUsers = new HashMap<>();
			users.put(admin.getUserName(), admin);
		}			
		
		in = new Scanner(System.in);		

		run();
		list.add(users);
		list.add(unAppUsers);
		serialize();
		in.close();
	}
	
	/**
	 * The run() method will run the application in the console.
	 */
	public static void run() {	
		welcome();
		
		while(true) {					
			String input = in.nextLine().toLowerCase();
			while(!(input.equals("register") || input.equals("login"))) {
				System.out.println("Invalid command. To login type 'login'. To register type 'register' ");
				input = in.nextLine().toLowerCase();
			}
			
			if(input.toLowerCase().equals("register")){
				register();
				break;
					
			}
			else if(input.toLowerCase().equals("login")) {
				System.out.print("Please enter username: ");
				String uName = in.nextLine();
				
				while(!users.containsKey(uName)) {
					System.out.println("Username not found. Please try again");
					System.out.print("Please enter username: ");
					 uName = in.nextLine();
				}
				if(users.get(uName).isApproved() && !users.get(uName).isAdmin()){
					System.out.print("Please enter password: ");
					String pass = in.nextLine();
					
					while(!pass.equals(users.get(uName).getPassword())) {
						System.out.println("Sorry wrong password. Please try again");
						System.out.print("Please enter password: ");
						pass = in.nextLine();
					}
				}
				else if(!users.get(uName).isApproved()) {
					System.out.println("Sorry your account has not been approved");
					System.out.println();
					break;
					}
				
				else if(users.get(uName).isAdmin()) {
					System.out.print("Please enter password: ");
					String pass = in.nextLine();
					
					while(!pass.equals(users.get(uName).getPassword())) {
						System.out.println("Sorry wrong password. Please try again");
						System.out.print("Please enter password: ");
						pass = in.nextLine();
					}
					
					User admin = users.get(uName);
					System.out.println("Welcome");
					System.out.println("Your balance is $" + admin.getUserBalance());						
					System.out.println();
					
					boolean activeAdmin = true;
					while(activeAdmin) {
						int unAppUserSize = unAppUsers.size();
						System.out.println("Accounts awating approval " + unAppUserSize);
						System.out.println("To approve users, type 'Approve', OR " 
								+  "Select from the following commands");		
						System.out.println("'Deposit' 'Withdraw' 'Balance' 'Logout'");
						
						String command = in.nextLine().toLowerCase();
						activeAdmin = adminMenu(command, admin);
					}
					break;
				}
				
				User u = users.get(uName);				
				System.out.println("Welcome");
				System.out.println("Your balance is $" + u.getUserBalance());
				
				System.out.println();
				
				
				boolean activeUser = true;
				while(activeUser) {
					System.out.println("Please select from the following commands:");
					System.out.println("'Deposit' 'Withdraw' 'Balance' 'Logout'");
					
					String command = in.nextLine().toLowerCase();
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
		Account acc = new Account();
		
		System.out.print("Please enter username: ");
		String uName = in.nextLine();
		
		while(users.containsKey(uName)) {
			System.out.println("Sorry username already exists.");
			System.out.println();
			System.out.print("Please enter new username: ");
			uName = in.nextLine();
		}
		
		System.out.print("Please enter password: ");
		String pass = in.nextLine();
		System.out.println();
		
		User u = new User(uName,pass,acc,false,false);
		users.put(u.getUserName(),u);
		unAppUsers.put(uName, u);
		logger.info(u.getUserName() + " registered for new account");
		
		System.out.println("Thank you for registering.");
		System.out.println("An administrator will approve your account");
		System.out.println();		
	}

	/**
	 * The userMenu() method will allow a user to deposit, withdraw, get balance and logout
	 * of their account.
	 * @param command The action taken by the user.
	 * @param u The user for which action is to be performed.
	 * @return Returns false if the user logs out, otherwise returns true.
	 */
	public static boolean userMenu(String command, User u) {
		boolean active = true;
		
		try {
			if(command.equals("deposit")) {
				System.out.print("How much would you like to deposit? $");
				int amount = Integer.parseInt(in.nextLine());
				System.out.println("Balance after deposit $" + u.getAccount().deposit(amount));
				logger.info(u.getUserName() + " deposited $" + amount);
			}
			else if(command.equals("withdraw")) {
				System.out.print("How much would you like to withdraw? $");
				int amount = Integer.parseInt(in.nextLine());
				System.out.println("Balance after withdrawal $" + u.getAccount().withdrawal(amount));
				logger.info(u.getUserName() + " withdrew $" + amount);
			}
			else if(command.equals("balance")) {
				System.out.println("Current balance $" + u.getUserBalance());
			}
			else if(command.equals("logout")) {
				active = false;
				logger.info(u.getUserName() + " logged out");
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
	 * options to deposit, withdraw and get balance, administrators can also choose to approve
	 * user accounts.
	 * @param command The command chosen by the administrator.
	 * @param u The user for which the action is to be performed.
	 * @return Returns false if the user logs out, otherwise returns true.
	 */
	public static boolean adminMenu(String command, User u) {
		boolean active = true;
		
		
		if(command.equals("approve") && !unAppUsers.isEmpty()) {
			System.out.println("Accounts awaiting approval:");
			for(String userName: unAppUsers.keySet()) {
				System.out.println(userName);								
			}
			System.out.println();
			System.out.println("Select User to Approve");
			String user = in.nextLine();
			if(users.containsKey(user)){
				users.get(user).setApproved(true);
				unAppUsers.remove(user);
				System.out.println(user + " Approved");
				System.out.println();
				logger.info(user + " approved by administrator");
			}								
		}
		else if(command.equals("approve") && unAppUsers.isEmpty()) {
			System.out.println("No users to approve");
			System.out.println();
		}
		else {
			active = userMenu(command, u);
		}
		return active;
		
	}	
	
	/**
	 * The serialize() method will serialize user data to a 'users.ser' file for data 
	 * persistence.
	 */
	public static void serialize() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.ser"));
			oos.writeObject(list);			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The deserializeUsers() will deserialize the 'users.ser' file and load user information
	 * from the previous application run.
	 * @return Returns a list of maps which hold information about users and unapproved users.
	 */
	public static List<Map<String, User>> deserializeUsers() {
		List<Map<String, User>> users = new ArrayList<>();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.ser"));
			users = (List<Map<String, User>>)ois.readObject();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return users;
		
	}

		
	

}
