package Project0_PartII;

import java.util.*;
import java.util.stream.*;
import Project0_PartII.RevatureAccounts.*;
import Project0_PartII.RevatureDatabase.*;

/**
 * RevatureBank encapsulates the logic of the entire bank application.
 */
public class RevatureBank implements ConsoleReference, LogReference{
	/**
	 * entrance contains the singleton instance of RevatureBank.
	 */
	public static RevatureBank entrance = new RevatureBank();
	/**
	 * accounts contains the list of accounts RevatureBank possesses.
	 */
	private static HashMap<Integer, Account> accounts;
	
	private ToDisk td = new ToDisk();

	/**
	 * This constructor reads from persistent storage.
	 */
	private RevatureBank() {
		logger.debug("Project0/RevatureBank.java: " + 
					 "Constructing RevatureBank().");
		FromDisk fd = new FromDisk();
		accounts = fd.read();
		fd.close();
		AdminAccount aa = AdminAccount.admin;
		accounts.put(aa.getID(), aa);
		logger.debug("Project0/RevatureBank.java: " + 
				     "Constructed RevatureBank().");
	}

	/**
	 * accountExists checks if a Username exists.
	 * 
	 * @param username
	 * @return true if the username exists, false otherwise.
	 */
	public static boolean accountExists(String username) {
		logger.debug("Project0/RevatureBank.java: Entered accountExists().");
		long n = accounts.values().stream()
			 	 .filter(a -> a.getUsername().compareTo(username) == 0).count();
		boolean exists = n > 0;

		if(exists) {
			System.out.println("Username already exists!");
			logger.info("Project0/RevatureBank.java: " + 
						"User tried to create an existing username.");
		}
		
		logger.debug("Project0/RevatureBank.java: Exiting accountExists().");
		
		return exists;
	}

	/**
	 * createAccount() creates a new UserAccount() and adds it to accounts.
	 */
	private void createAccount(){
		logger.debug("Project0/RevatureBank.java: Entered createAccount().");
		System.out.println("Please create an account.");
		UserAccount a = new UserAccount();
		accounts.put(a.getID(), a);
		td.insert(a);
		a.pending();
		logger.debug("Project0/RevatureBank.java: Exiting createAccount().");
		logger.info("Project0/RevatureBank.java: New account created.");
	}

	/**
	 * signIn() handles the logic of user sign in.
	 */
	private void signIn() {
		logger.debug("Project0/RevatureBank.java: Entered signIn().");
		System.out.println("Please sign into your account.");
		Account a = accounts.get(Account.signIn());
		
		if(a == null)
		{
			System.err.println("Incorrect Username or Password.");
			logger.info("Project0/RevatureBank.java: " + 
						"User entered incorrect username or password.");
			welcome();
		}
		else
			a.enter();
		
		logger.debug("Project0/RevatureBank.java: Exiting signIn().");
	}
	
	/**
	 * enter() displays a welcome message to the user.
	 */
	public void enter() {
		logger.debug("Project0/RevatureBank.java:  Entered enter().");
		System.out.print("Welcome to RevatureBank. To Login as admin: ");
		System.out.println("Username - admin, Password - admin.");
		welcome();
		logger.debug("Project0/RevatureBank.java: Exiting enter().");
	}

	/**
	 * exit() leaves RevatureBank after saving account information to 
	 * persistent storage.
	 */
	public void exit() {
		logger.debug("Project0/RevatureBank.java: Entered exit().");
		ToDisk td = new ToDisk();
		td.write(accounts);
		td.close();
		logger.debug("Project0/RevatureBank.java: Exiting exit().");
	}

	/**
	 * getUnapprovedAccounts() filters accounts and collects those that are not
	 * approved.
	 * 
	 * @return a List of accounts that are DENIED and PENDING.
	 */
	public static List<Map.Entry<Integer, Account>> getUnapprovedAccounts() {
		logger.debug("Project0/RevatureBank.java: " + 
				     "Entered and exiting getUnapprovedAccounts().");
		
		return accounts.entrySet().stream()
			       .filter(e -> e.getValue()
			    		   		 .getStatus() == AccountStatus.DENIED || 
			    		   	    e.getValue()
			    		   	     .getStatus() == AccountStatus.PENDING)
			       .collect(Collectors.toList());
	}

	/**
	 * printAccounts() prints all accounts in accounts.
	 */
	public void printAccounts() {
		logger.debug("Project0/RevatureBank.java: Entered printAccounts().");
		accounts.values().stream().forEach(a -> a.print());
		logger.debug("Project0/RevatureBank.java: Exiting printAccounts().");
	}
	
	/**
	 * welcome() uses user input to decide whether to create a new account or
	 * sign into an existing account.
	 */
	private void welcome() {
		logger.debug("Project0/RevatureBank.java: Entered welcome().");
		String hasAccount;
		boolean y;
		boolean n;
		
		do{
			hasAccount = console.readLine("Do you have an account?(y/n):");
			y = hasAccount.equals("y");
			n = hasAccount.equals("n");
		}while(!(y || n));

		if(y) signIn();
		
		else createAccount();
		
		logger.debug("Project0/RevatureBank.java: Exiting welcome().");
	}
}
