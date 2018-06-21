import java.io.*;
import RevatureDatabase.*;

public class Project0{
	public static void main(String args[]){
		RevatureBank bank = RevatureBank.entrance;
		bank.enter();
		System.out.println("--------------------------");
		bank.printAccounts();
		System.out.println("--------------------------");
		bank.exit();
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
