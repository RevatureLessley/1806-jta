import java.io.*;
import java.util.*;
import RevatureDatabase.*;

public class RevatureBank implements ConsoleReference{
	public static RevatureBank entrance = new RevatureBank();
	private final String STORAGE = "PersistentStore.txt";
	private HashMap<Integer, Account> accounts;

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

	private void createAccount(){
		UserAccount a = new UserAccount();
		accounts.put(a.getID(), a);
	}

	private void signIn() {
	
	}

	public void enter() {
		System.out.println("Welcome to RevatureBank.");
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

	public void printAccounts() {
		for(Account a: accounts.values())
			a.print();
	}
}
