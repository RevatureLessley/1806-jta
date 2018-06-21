import java.io.*;
import RevatureDatabase.*;

public class Project0 implements ConsoleReference{
	public static void main(String args[]){

		if(console == null) {
			System.err.print("Thank you for choosing RevatureBank. ");
			System.err.print("Please note that this is a console application. ");
			System.err.println("Please exit out of your IDE, including 'git bash', and open your command prompt.");
		}

		else {
			RevatureBank bank = RevatureBank.entrance;
			bank.enter();
			System.out.println("--------------------------");
			bank.printAccounts();
			System.out.println("--------------------------");
			bank.exit();
		}
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
