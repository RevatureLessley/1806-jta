package p0;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import p0.dao.PlayerDaoImpl;
import p0.service.AccountListService;


public class Launcher {
	
	Scanner in = new Scanner(System.in);
	Random rng = new Random();
	AccountList Accounts;
	final String VERSION_NUM = "2.0";
	
	public static void main (String[] args) throws SQLException{
		Launcher pgm = new Launcher();
		pgm.mainMenu(pgm);
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
			if(pgm.Accounts.getAdmin().getAdminInfo().getAccountInfo().getuName().equals(tempUname))
			{

				successU = true;
				successP = pWordTest(pgm.Accounts.getAdmin(), pgm); 
			}
			else if(pgm.Accounts.getBanker().getBankInfo().getAccountInfo().getuName().equals(tempUname))
			{

				successU = true;
				successP = pWordTest(pgm.Accounts.getBanker(), pgm); 
			}
			else if(pgm.Accounts.getLoaner().getLoanerInfo().getAccountInfo().getuName().equals(tempUname))
			{

				successU = true;
				successP = pWordTest(pgm.Accounts.getLoaner(), pgm); 
			}
			else 
			{
				for(AccountClass a: pgm.Accounts.getList())
				{

					if(a.getAccount().getuName().equals(tempUname))
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
				for(PlayerAccount p: pgm.Accounts.getWaitingList())
				{
					if(p.getAccount().getuName().equals(tempUname))
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
		if(a.getAccount().getuPass().equals(tempPword))
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
		pgm.dumpIn(pgm);
		System.out.print("Select Name for new account: ");
		String tempN = in.nextLine();
		System.out.print("Select User Name for new account: ");
		String tempU = in.nextLine();
		System.out.print("Select Password for new account: ");
		String tempP = in.nextLine();
		
		int count = pgm.Accounts.getList().size()+pgm.Accounts.getWaitingList().size() +4;
		
		PlayerDaoImpl pdi = new PlayerDaoImpl();
		pdi.createPlayer(tempN, tempU, tempP, count);
	}

	public void load(Launcher pgm) {
		AccountListService als = new AccountListService();

		Accounts = new AccountList();
		ArrayList<PlayerAccount> temp = als.getPlayerArray();
		ArrayList<PlayerAccount> temp2 = als.getWaitingPlayerArray();
		Accounts.setAdmin(als.getAdmin());
		Accounts.setBanker(als.getBanker());
		Accounts.setLoaner(als.getLoaner());
		for(PlayerAccount p : temp) {
			Accounts.add(p);
		}
		for(PlayerAccount p : temp2){
			Accounts.addWaiting(p);
		}
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
