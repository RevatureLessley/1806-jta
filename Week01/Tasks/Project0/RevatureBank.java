package Project0;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import Project0.RevatureAccounts.*;
import Project0.RevatureDatabase.*;

public class RevatureBank implements ConsoleReference{
	public static RevatureBank entrance = new RevatureBank();
	private final String STORAGE = "./Project0/RevatureDatabase/PersistentStore.txt";
	private static HashMap<Integer, Account> accounts;

	@SuppressWarnings("unchecked")
	private RevatureBank() {
		try {
			FromDisk fd = new FromDisk(STORAGE);
			accounts = (HashMap<Integer, Account>) fd.read();
			fd.close();
		}

		catch(FileNotFoundException fnfe) {
			accounts = new HashMap<>();
			AdminAccount aa = AdminAccount.admin;
			accounts.put(aa.getID(), aa);
		}

		catch(IOException ioe) {
			System.err.println("Closing persistent storage failed!");
			ioe.printStackTrace();
		}
	}

	public static boolean accountExists(String s) {
		long n = accounts.values().stream()
			 	 .filter(a -> a.getUsername().compareTo(s) == 0).count();
		boolean exists = n > 0;

		if(exists) 
			System.out.println("Username already exists!");
		
		return exists;
	}

	private void createAccount(){
		System.out.println("Please create an account.");
		UserAccount a = new UserAccount();
		accounts.put(a.getID(), a);
		a.pending();
	}

	private void signIn() {
		System.out.println("Please sign into your account.");
		Account a = accounts.get(Account.signIn());
		
		if(a == null)
		{
			System.err.println("Incorrect Username or Password.");
			welcome();
		}
		else
			a.enter();
	}

	public void enter() {
		System.out.print("Welcome to RevatureBank. To Login as admin: ");
		System.out.println("Username - admin, Password - admin.");
		welcome();
	}

	public void exit() {
		try {
			ToDisk td = new ToDisk(STORAGE);
			td.write(accounts);
			td.close();
		}

		catch(FileNotFoundException fnfe) {
			System.err.println("Connection to persistent storage failed!");
			fnfe.printStackTrace();
		}

		catch(IOException ioe) {
			System.err.println("Closing persistent storage failed!");
			ioe.printStackTrace();
		}

	}

	public static List<Map.Entry<Integer, Account>> getUnapprovedAccounts() {
		return accounts.entrySet().stream()
			       .filter(e -> e.getValue().getStatus() == AccountStatus.DENIED || 
					    e.getValue().getStatus() == AccountStatus.PENDING)
			       .collect(Collectors.toList());
	}

	public void printAccounts() {
		accounts.values().stream().forEach(a -> a.print());
	}
	
	private void welcome() {
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
	}
}
