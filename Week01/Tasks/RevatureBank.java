import java.io.*;
import RevatureDatabase.*;

public class RevatureBank implements ConsoleReference{
	
	public static void main(String args[]){
		String hasAccount = console.ReadLine("Welcome to RevatureBank. Do you have an account?(y/n)");
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
