package p0;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * Functionality
 * 
 * Setup
 * 	Check for accounts
 * 	if DB exist
 * 		Build array of Account Objects
 * 	else
 * 		Create new Admin
 * 		Build generic member list
 * 
 * Section 1
 * 	1. Login
 * 	2. Request Account
 * 	3. Quit
 * 
 * Account Verification
 * 	Ask for User name
 * 	Ask for Password
 * 	Determine if valid login
 * 	Determine account type
 * 		If password is wrong but User name is correct loop back to password input
 * 		If User name isn't found prompt for account sign up or back to user name
 * 
 * Activities Menu (standard user)
 * 	Welcome (Player Name)
 * 	Account balance
 * 	On hand balance
 * 	Status
 * 	1. Withdraw/Get account
 * 	2. Deposit/ N/A
 * 	3. Fight in Arena
 * 	4. Bet in Arena
 * 	5. Beg for loan/Pay on loan
 * 	7. Logout
 * 
 * Activities Menu (Banker)
 * 	Welcome (Banker Name)
 * 	# of accounts awaiting approval
 * 	# of overdrawn accounts
 * 	time since last charge
 * 	1. Review accounts for approval
 * 	2. Check accounts
 * 	3. Charge service tax
 * 	
 * Activities Menu (Loan Shark)
 * 	Welcome (Loan Name)
 * 	Available money
 * 	# of active loans
 * 	# of $ owed
 * 	1. Review loans for approval
 * 	2. Check accounts
 * 	3. Charge interest (All)
 * 	4. Charge interest (Directed)
 * 
 * Activities Menu (Administrator)
 * 	Welcome (Admin Name)
 * 	# of accounts awaiting approval
 * 	1. Approve new players
 * 	2. Approve new Bankers
 * 	3. Approve new Loaners
 * 	4. Hire new fighters
 * 	5. Reset the world
 */

public class Launch 
{
	Scanner in = new Scanner(System.in);
	Random rng = new Random();
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Account> Active;
	ArrayList<Account> Waiting;
	final String VERSION_NUM = "0.1";
	
	public static void main (String[] args)
	{
		Launch pgm = new Launch();
		pgm.load(pgm);
		//pgm.mainMenu(pgm);
		
	}
	
	public void mainMenu(Launch pgm)
	{
		boolean cont = true;
		while(cont)
		{
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
		}
	}
	
	public void login(Launch pgm)
	{
		Player log = null;
		boolean successU = false;
		boolean successP = false;
		while(!successP)
		{
			System.out.print("Input Username (-1 to return to main menu): ");
			String tempUname = pgm.in.next();
			for(Account a: pgm.Active)
			{
				successP = false;
				successU = false;
				if(a.getuName().equals(tempUname))
				{
					successU = true;
					System.out.print("Input Password: ");
					String tempPword = pgm.in.next();
					if(a.getuPass().equals(tempPword))
					{
						a.menu();
						successP = true;
						break;
					}
					else
					{
						System.out.println("Password Incorrect, restarting login.");
						break;
					}
				}
			}
			if(tempUname.equals(-1))
			{
				break;
			}
			else if (!successU)
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
	
	public void newAccount(Launch pgm)
	{
		System.out.println("Not implimented yet (New Account)");
		System.out.println();
	}
	
	public void load(Launch pgm)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("Active.ser"));
			oos.writeObject(pgm.Active);
			
		}
		catch(IOException e)
		{
			System.out.println("There wasn't a vailid user list, starting new world list... \n");
			pgm.generateWorld(pgm);
		}
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("Waiting.ser"));
			oos.writeObject(pgm.Waiting);

			System.out.println("Successfully loaded Active accounts");
		}
		catch(IOException e)
		{
		}
		if(pgm.Active == null)
		{
			System.out.println("World not available, generating new world.");
			pgm.generateWorld(pgm);
		}
		
	}
	
	public void save(Launch pgm)
	{
		
		try{
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("Active.ser"));
			oos.writeObject(pgm.Active); //Serialize
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("Waiting.ser"));
			oos.writeObject(pgm.Waiting); //Serialize
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}

	public void generateWorld(Launch pgm)
	{
		System.out.println("Welcome to the Aeva Arena Simulater version: " + VERSION_NUM);
		System.out.print("Please choose a user name for the administrator: ");
		String uName = pgm.in.next();
		System.out.print("Now choose a password for the administator account: ");
		String pWord = pgm.in.next();
		System.out.println("This has been a test to make sure this works at this point.");
	}

}
