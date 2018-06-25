package p0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;


public class Launch 
{
	final static Logger logger = Logger.getLogger(Launch.class);
	Scanner in = new Scanner(System.in);
	Random rng = new Random();
	AccountList Active;
	WaitingList Waiting;
	final String VERSION_NUM = "0.1";
	
	public static void main (String[] args)
	{
		Launch pgm = new Launch();
		pgm.mainMenu(pgm);
		
	}
	
	/**
	 * The main launch menu for the program
	 * refreshes account list every time it is entered and updates saved list every time it ends
	 * loops constantly until exited by user input.
	 * 
	 * @param pgm a copy of the general launch program provided to give variable access to all methods
	 */
	public void mainMenu(Launch pgm)
	{
		boolean cont = true;
		pgm.in.reset();

		System.out.println("Game loaded, press Enter to continue...");
		while(cont)
		{
			pgm.load(pgm);
			pgm.dumpIn(pgm);
			System.out.println("Welcome to the Aeva Areana Simulator");
			System.out.println("Please select an option");
			System.out.println();
			System.out.println("1. Login");
			System.out.println("2. Request Account");
			System.out.println("3. Quit Application");
			System.out.println();

			int selection = pgm.in.nextInt();
			switch(selection) {
			case 1: pgm.login(pgm);
					break;
			case 2: pgm.newAccount(pgm);
					break;
			case 3: System.out.println("Goodbye user, have a nice day");
					cont = false;
					break;
			default:System.out.println("That is not a valid selection, please select again");
			}

			pgm.save(pgm);
			if(Waiting.getWorldFlagged() && Active.getWorldFlagged())
			{
				try {
				Files.delete(new File("Active.ser").toPath());
				Files.delete(new File("Waiting.ser").toPath());
				cont = false;
				break;
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * Method to handle the logic and error checking for logging into an account.
	 * Checks if the provided name belongs to an account and that the password is correct.
	 * Offers option to create new user if the given name doesn't exist
	 * @param pgm a copy of the general launch program provided to give variable access to all methods
	 */
	public void login(Launch pgm)
	{
		boolean successU = false;
		boolean successP = false;
		while(!successP)
		{
			System.out.print("Input Username (-1 to return to main menu): ");
			String tempUname = pgm.in.next();
			successP = false;
			successU = false;
			if(pgm.Active.getAdmin().getuName().equals(tempUname))
			{

				successU = true;
				successP = pWordTest(pgm.Active.getAdmin(), pgm); 
			}
			else if(pgm.Active.getBanker().getuName().equals(tempUname))
			{

				successU = true;
				successP = pWordTest(pgm.Active.getBanker(), pgm); 
			}
			else if(pgm.Active.getLoaner().getuName().equals(tempUname))
			{

				successU = true;
				successP = pWordTest(pgm.Active.getLoaner(), pgm); 
			}
			else 
			{
				for(Account a: pgm.Active.getList())
				{

					if(a.getuName().equals(tempUname))
					{
						successU = true;
						successP = pWordTest(a, pgm); 
					}
				}
			}
			if(tempUname.equals("-1"))
			{
				break;
			}
			else if (!successU)
			{
				boolean test = false;
				for(Player p: pgm.Waiting.getList())
				{
					if(p.getuName().equals(tempUname))
						test = true;
				}
				if(test)
				{
					System.out.println("That username is for a waiting account, please try again.");
				}
				else
				{
					System.out.println("Not a valid username, would you like to \n"
							+ "1. Make a new account or \n"
							+ "2. Attempt to sign in again");
					int selection = pgm.in.nextInt();
					boolean valid = false;
					while(!valid)
					{
						switch (selection){
						case 1: newAccount(pgm);
						valid = true;
						successP = true;
						break;
						case 2: valid = true;
						break;
						default: System.out.println("Not a valid selction, would you like to \n"
								+ "1. Make a new account or \n"
								+ "2. Attempt to sign in again");
						selection = pgm.in.nextInt();
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * A separated password tester, in future iterations will handle encoding and decoding for password
	 * implementation.
	 * @param a the account attempting to be tested
	 * @param pgm a copy of the general launch program provided to give variable access to all methods
	 * @return returns success or failure to login loop
	 */
	public boolean pWordTest(Account a, Launch pgm)
	{
		System.out.print("Input Password: ");
		String tempPword = pgm.in.next();
		if(a.getuPass().equals(tempPword))
		{
			a.setPgm(pgm);
			a.menu();
			return true;
		}
		else
		{
			System.out.println("Password Incorrect, restarting login.");
			return false;
		}
	}
	
	/**
	 * Loads serialized information to build the AccountList storage class
	 * Allows for data to remain continuous between runs, is called any time that the database might have changed.
	 * @param pgm a copy of the general launch program provided to give variable access to all methods
	 */
	public void load(Launch pgm)
	{
		if(pgm.Active == null)
		{
			pgm.Active = new AccountList();
			pgm.Waiting = new WaitingList();
		}
		try
		{
			ObjectInputStream ois = new ObjectInputStream(
										new FileInputStream("Active.ser"));
			pgm.Active = (AccountList) ois.readObject();
			ois.close();
			
		}
		catch(IOException e)
		{
			System.out.println("There wasn't a vailid user list, starting new world list... \n");
			pgm.generateWorld(pgm);
		}
		catch(ClassNotFoundException e)
		{
			logger.error("Complete crash, ActiveList class missing");
		}
		try
		{
			ObjectInputStream ois = new ObjectInputStream(
										new FileInputStream("Waiting.ser"));
			pgm.Waiting = (WaitingList) ois.readObject();
			ois.close();
		}
		catch(IOException e)
		{
			System.out.println("Couldn't load waiting list");
		}
		catch(ClassNotFoundException e)
		{
			logger.error("Complete crash, WaitingList class missing");
		}
		if(pgm.Active.getAdmin() == null)
		{

			pgm.Active = new AccountList();
			System.out.println("World not available, generating new world. \n");
			pgm.generateWorld(pgm);
		}
		
	}
	
	/**
	 * Saves serialized information to build the AccountList storage class
	 * Allows for data to remain continuous between runs, is called any time that the database needs to be secured.
	 * @param pgm a copy of the general launch program provided to give variable access to all methods
	 */
	public void save(Launch pgm)
	{
		pgm.Active.updateList();
		pgm.Waiting.updateList();
		try{
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("Active.ser"));
			oos.writeObject(pgm.Active);
			oos.close();
		}
		catch(IOException e)
		{
			logger.error("Complications writing methods to file.");
		}
		try{
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("Waiting.ser"));
			oos.writeObject(pgm.Waiting); 
			oos.close();
		}
		catch(IOException e)
		{
		}
		
		System.out.println("Successfully Saved");
	}
	
	/**
	 * Creates a new waiting account to be approved by the Administrator.
	 * Collects Username Password and preferred name from the user for the new account.
	 * @param pgm a copy of the general launch program provided to give variable access to all methods
	 */
	public void newAccount(Launch pgm)
	{
		pgm.dumpIn(pgm);
		System.out.print("Please choose a user name for the account: ");
		String uName = pgm.in.nextLine();
		System.out.print("Now choose a password for the account: ");
		String pWord = pgm.in.nextLine();
		System.out.print("What shall we call you?: ");
		String Name = pgm.in.nextLine();
		
		pgm.Waiting.add(new Player(Name, uName, pWord, 100, 0, 0, pgm));
	}
	
	/**
	 * Activated whenever there is an error in loading the database
	 * Collects information for the Administrator, Banker and Loaner accounts
	 * Sets these accounts as the basis for the world and saves upon exiting to make sure
	 * the information sticks.
	 * @param pgm a copy of the general launch program provided to give variable access to all methods
	 */
	public void generateWorld(Launch pgm)
	{

		pgm.dumpIn(pgm);
		System.out.println("Welcome to the Aeva Arena Simulater version: " + VERSION_NUM + "\n");
		System.out.print("Please choose a user name for the administrator: ");
		String uName = pgm.in.nextLine();
		System.out.print("Now choose a password for the administator account: ");
		String pWord = pgm.in.nextLine();
		System.out.println("Finally what shall we call the administrator?: ");
		String Name = pgm.in.nextLine();
		Administrator tempA =new Administrator(Name,uName,pWord, pgm);

		System.out.print("Please choose a user name for the banker: ");
		uName = pgm.in.nextLine();
		System.out.print("Now choose a password for the banker account: ");
		pWord = pgm.in.nextLine();
		System.out.println("Finally what shall we call the banker?: ");
		Name = pgm.in.nextLine();
		Banker tempB =new Banker(Name,uName,pWord, pgm);

		System.out.print("Please choose a user name for the loaner: ");
		uName = pgm.in.nextLine();
		System.out.print("Now choose a password for the loaner account: ");
		pWord = pgm.in.nextLine();
		System.out.println("Finally what shall we call the loaner?: ");
		Name = pgm.in.nextLine();
		Loaner tempL =new Loaner(Name,uName,pWord, pgm);
		
		System.out.println("Accounts created, generating world.");
		pgm.Active = new AccountList(new ArrayList<Player>(), tempA, tempB, tempL);
	}

	/**
	 * A method to pause text and make sure that System.in is clear before continuing
	 * @param pgm a copy of the general launch program provided to give variable access to all methods
	 */
	public void dumpIn(Launch pgm)
	{
		if(pgm.in.hasNextLine()) {
			pgm.in.nextLine();
		}
	}
}
