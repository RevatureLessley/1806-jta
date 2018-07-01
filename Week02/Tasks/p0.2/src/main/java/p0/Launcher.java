package p0;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import p0.service.AccountListService;


public class Launcher {
	
	Scanner in = new Scanner(System.in);
	Random rng = new Random();
	AccountList Active;
	WaitingList Waiting;
	final String VERSION_NUM = "2.0";
	AccountListService als = new AccountListService();
	
	public static void main (String[] args) throws SQLException{
		Launcher pgm = new Launcher();
		//pgm.mainMenu(pgm);
		BankerAccount bank = pgm.als.getBanker();
		System.out.println(bank.getBankInfo().getAccountInfo().getName());
	}
	
	/**
	 * The main launch menu for the program
	 * refreshes account list every time it is entered and updates saved list every time it ends
	 * loops constantly until exited by user input.
	 * 
	 * @param pgm a copy of the general launch program provided to give variable access to all methods
	 */
	public void mainMenu(Launcher pgm){
		boolean cont = true;
		pgm.in.reset();
		
		while(cont){
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
			case 1: pgm.clearScreen();
					pgm.login(pgm);
					break;
			case 2: pgm.clearScreen();
					pgm.newAccount(pgm);
					break;
			case 3: pgm.clearScreen();
					System.out.println("Goodbye user, have a nice day");
					cont = false;
					break;
			default:System.out.println("That is not a valid selection, please select again");
			}
			
			if(Waiting.getWorldFlagged() && Active.getWorldFlagged())
			{
				//TODO: Turnicate list (Make SQL procedure to turnicate all tables)
			}
		}
	}
	
	/**
	 * Method to handle the logic and error checking for logging into an account.
	 * Checks if the provided name belongs to an account and that the password is correct.
	 * Offers option to create new user if the given name doesn't exist
	 * @param pgm a copy of the general launch program provided to give variable access to all methods
	 */
	public void login(Launcher pgm){
		boolean successU = false;
		boolean successP = false;
		while(!successP)
		{
			pgm.clearScreen();
			System.out.print("Input Username (-1 to return to main menu): ");
			String tempUname = pgm.in.next();
			successP = false;
			successU = false;
			if(pgm.Active.getAdmin().getAccountInfo().getuName().equals(tempUname))
			{

				successU = true;
				successP = pWordTest(pgm.Active.getAdmin(), pgm); 
			}
			else if(pgm.Active.getBanker().getBankInfo().getAccountInfo().getuName().equals(tempUname))
			{

				successU = true;
				successP = pWordTest(pgm.Active.getBanker(), pgm); 
			}
			else if(pgm.Active.getLoaner().getAccountInfo().getuName().equals(tempUname))
			{

				successU = true;
				successP = pWordTest(pgm.Active.getLoaner(), pgm); 
			}
			else 
			{
				for(AccountClass a: pgm.Active.getList())
				{

					if(a.getAccountInfo().getuName().equals(tempUname))
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
				for(PlayerAccount p: pgm.Waiting.getList())
				{
					if(p.getAccountInfo().getuName().equals(tempUname))
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
		pgm.clearScreen();
	}
	
	public boolean pWordTest(AccountClass a, Launcher pgm)
	{
		System.out.print("Input Password: ");
		String tempPword = pgm.in.next();
		if(a.getAccountInfo().getuPass().equals(tempPword))
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
	
	public void newAccount(Launcher pgm) {
		//TODO make DB safe account creation
	}

	public void load(Launcher pgm) {
		AccountListService als = new AccountListService();
		if(als.getAccSize() == 0)
		{
			System.out.println("There wasn't a vailid user list, starting new world list... \n");
			pgm.generateWorld(pgm);
		}
		else {
			Active = new AccountList();
			Waiting = new WaitingList();
			ArrayList<PlayerAccount> temp = als.getPlayerArray();
			Active.setAdmin(als.getAdmin());
			Active.setBanker(als.getBanker());
			Active.setLoaner(als.getLoaner());
			for(PlayerAccount p : temp) {
				if(p.getPlayerInfo().isAccountFlagged()){
					Active.add(p);
				}
				else {
					Waiting.add(p);
				}
			}
		}
	}
	
	/**
	 * Activated whenever there is an error in loading the database
	 * Collects information for the Administrator, Banker and Loaner accounts
	 * Sets these accounts as the basis for the world and saves upon exiting to make sure
	 * the information sticks.
	 * @param pgm a copy of the general launch program provided to give variable access to all methods
	 */
	public void generateWorld(Launcher pgm)
	{
//
//		pgm.dumpIn(pgm);
//		pgm.clearScreen();
//		System.out.println("Welcome to the Aeva Arena Simulater version: " + VERSION_NUM + "\n");
//		System.out.print("Please choose a user name for the administrator: ");
//		String uName = pgm.in.nextLine();
//		System.out.print("Now choose a password for the administator account: ");
//		String pWord = pgm.in.nextLine();
//		System.out.println("Finally what shall we call the administrator?: ");
//		String Name = pgm.in.nextLine();
//		Administrator tempA =new Administrator(Name,uName,pWord, pgm);
//
//		System.out.print("Please choose a user name for the banker: ");
//		uName = pgm.in.nextLine();
//		System.out.print("Now choose a password for the banker account: ");
//		pWord = pgm.in.nextLine();
//		System.out.println("Finally what shall we call the banker?: ");
//		Name = pgm.in.nextLine();
//		Banker tempB =new Banker(Name,uName,pWord, pgm);
//
//		System.out.print("Please choose a user name for the loaner: ");
//		uName = pgm.in.nextLine();
//		System.out.print("Now choose a password for the loaner account: ");
//		pWord = pgm.in.nextLine();
//		System.out.println("Finally what shall we call the loaner?: ");
//		Name = pgm.in.nextLine();
//		Loaner tempL =new Loaner(Name,uName,pWord, pgm);
//		
//		System.out.println("Accounts created, generating world.");
//		pgm.Active = new AccountList(new ArrayList<Player>(), tempA, tempB, tempL);
	}

	
	public void dumpIn(Launcher pgm)
	{
		System.out.println("Press Enter to Continue");
		if(pgm.in.hasNextLine()) {
			pgm.in.nextLine();
		}
	}
	
	public void clearScreen()
	{
		try 
		{
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
