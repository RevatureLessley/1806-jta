import java.io.*;
import java.util.*;
import RevatureDatabase.*;

public class RevatureBank implements ConsoleReference{
	
	private HashMap<Integer, Account> accounts = new HashMap<Integer, Account>(); 

	private void createAccount(){
		Account a = new Account();
	}

	public static void main(String args[]){
		System.out.println("Welcome to RevatureBank.");
		String hasAccount;
		boolean y;
		boolean n;
		
		do{
			hasAccount = console.readLine("Do you have an account?(y/n):");
			y = hasAccount.equals("y");
			n = hasAccount.equals("n");
		}while(!(y || n));

		/*
		Account a = new Account();
		try{
			ToDisk td = new ToDisk("PersistentStore.txt");
			td.write(a);
			td.close();
			FromDisk fd = new FromDisk("PersistentStore.txt");
			a = (Account)fd.read();
			td.close();
			a.print();
		}
		catch(FileNotFoundException fnfe){
			System.out.println("Connection to persisent storage failed!");
			fnfe.getMessage();
		}

		catch(IOException ioe){
			System.out.println("Closing persistent storage failed!");
			ioe.getMessage();
		}
		*/
	}
}
