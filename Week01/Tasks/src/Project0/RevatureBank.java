package Project0;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import Project0.RevatureAccounts.*;
import Project0.RevatureDatabase.*;

public class RevatureBank implements ConsoleReference, LogReference{
	public static RevatureBank entrance = new RevatureBank();
	private final String STORAGE = 
			"./Project0/RevatureDatabase/PersistentStore.txt";
	private static HashMap<Integer, Account> accounts;

	@SuppressWarnings("unchecked")
	private RevatureBank() {
		logger.debug("Project0/RevatureBank.java: " + 
					 "Constructing RevatureBank().");
		
		try {
			FromDisk fd = new FromDisk(STORAGE);
			accounts = (HashMap<Integer, Account>) fd.read();
			fd.close();
		}

		catch(FileNotFoundException fnfe) {
			accounts = new HashMap<>();
			AdminAccount aa = AdminAccount.admin;
			accounts.put(aa.getID(), aa);
			logger.info("Project0/RevatureBank.java: " + 
			            "Creating admin account.");
		}

		catch(IOException ioe) {
			logger.error("Project0/RevatureBank.java: " + 
						 "Closing persistent storage failed!");
			ioe.printStackTrace();
		}
		
		logger.debug("Project0/RevatureBank.java: " + 
				     "Constructed RevatureBank().");
	}

	public static boolean accountExists(String s) {
		logger.debug("Project0/RevatureBank.java: Entered accountExists().");
		long n = accounts.values().stream()
			 	 .filter(a -> a.getUsername().compareTo(s) == 0).count();
		boolean exists = n > 0;

		if(exists) {
			System.out.println("Username already exists!");
			logger.info("Project0/RevatureBank.java: " + 
						"User tried to create an existing username.");
		}
		
		logger.debug("Project0/RevatureBank.java: Exiting accountExists().");
		
		return exists;
	}

	private void createAccount(){
		logger.debug("Project0/RevatureBank.java: Entered createAccount().");
		System.out.println("Please create an account.");
		UserAccount a = new UserAccount();
		accounts.put(a.getID(), a);
		a.pending();
		logger.debug("Project0/RevatureBank.java: Exiting createAccount().");
		logger.info("Project0/RevatureBank.java: New account created.");
	}

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

	public void enter() {
		logger.debug("Project0/RevatureBank.java:  Entered enter().");
		System.out.print("Welcome to RevatureBank. To Login as admin: ");
		System.out.println("Username - admin, Password - admin.");
		welcome();
		logger.debug("Project0/RevatureBank.java: Exiting enter().");
	}

	public void exit() {
		logger.debug("Project0/RevatureBank.java: Entered exit().");
		
		try {
			ToDisk td = new ToDisk(STORAGE);
			td.write(accounts);
			td.close();
		}

		catch(FileNotFoundException fnfe) {
			logger.error("Project0/RevatureBank.java: " + 
						 "Connection to persistent storage failed!");
			fnfe.printStackTrace();
		}

		catch(IOException ioe) {
			logger.error("Project0/RevatureBank.java: " + 
		                 "Closing persistent storage failed!");
			ioe.printStackTrace();
		}
		
		logger.debug("Project0/RevatureBank.java: Exiting exit().");
	}

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

	public void printAccounts() {
		logger.debug("Project0/RevatureBank.java: Entered printAccounts().");
		accounts.values().stream().forEach(a -> a.print());
		logger.debug("Project0/RevatureBank.java: Exiting printAccounts().");
	}
	
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
