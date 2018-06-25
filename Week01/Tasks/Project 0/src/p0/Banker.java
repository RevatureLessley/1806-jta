package p0;

import java.io.Serializable;

public class Banker extends Account implements Serializable
{

	private double interestRate = 1.10;
	
	/**
	 * Constructor used for the banker class, does little more than call the super constructor
	 * since there are no more variables than required for a generic account on creation.
	 */
	public Banker(String name, String uname, String pword, Launch pgm)
	{
		super(name, uname, pword, pgm);
	}
	
	/**
	 * Displays all active players and their current bank account balances
	 */
	public void viewActive()
	{
		int count = 1;
		System.out.println("List of currently active members");
		for(Player p: pgm.Active.getList())
		{
			System.out.println(count + ". " + p.getuName() + " Account Balance: " + p.getbBalance());
		}
		pgm.dumpIn(pgm);
	}
	
	/**
	 * Displays current interest rate and asks banker for a new offer, storing that as the interest
	 * rate for all player accounts.
	 */
	public void adjustInterestRates()
	{
		System.out.println("The current interest rate is (Format 00.00 times)" + interestRate);
		System.out.print("What should the new interest rate be?: ");
		interestRate = pgm.in.nextDouble();
		
		System.out.println("The new interest rate is: " + interestRate);
	}
	
	public double getInterest()
	{
		return interestRate;
	}
	
	/**
	 * method shared by each Account extender, view comment on parent class for explination.
	 */
	@Override
	public void menu() {
		int selection = 0;
		System.out.println("Welcome Banker " + this.Name + "\n");
		while(selection != 3)
		{
			System.out.println("What would you like to do today?");
			int count = 1;
			System.out.println(count + ". View active accounts");
			count++;
			System.out.println(count + ". Adjust interest rates");
			count++;
			System.out.println(count + ". Logout");
			count++;
			selection = pgm.in.nextInt();
			
			switch (selection) {
			case 1: viewActive();
					break;
			case 2: adjustInterestRates();
					break;
			case 3: logout();
					break;
			}
		}
		
	}

}
