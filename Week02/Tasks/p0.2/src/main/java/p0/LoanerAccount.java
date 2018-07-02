package p0;

import java.util.ArrayList;

import p0.beans.Account;
import p0.beans.Loaner;

public class LoanerAccount extends AccountClass
{
	private Loaner lAcc;
	private ArrayList<PlayerAccount> active = new ArrayList<>();
	private ArrayList<PlayerAccount> waiting = new ArrayList<>();
	
	public LoanerAccount(int bal, double interest, String name, String uname, String pword)
	{
		lAcc = new Loaner(bal, interest,name, uname, pword);
	}
	
	/**
	 * Displays all players with an active loan and how much that loan's worth is.
	 */
	public void manageActiveLoans()
	{
		pgm.dumpIn(pgm);
		pgm.clearScreen();
		int selection = 0;
		updateLists();
		while(selection != -1)
		{
			if(active.size() >0)
			{
				for(PlayerAccount p: active)
				{
					System.out.println(p.getPlayerInfo().getAccountID() + ". " + p.getAccount().getuName() + ", " + p.getPlayerInfo().getLoanBalance());
				}
				pgm.in.next();
				//TODO: add managing accounts activities.
			}
			else
			{
				System.out.println("No waiting accounts at this moment. \n");
				selection = -1;
			}
		}
	}
	
	/**
	 * Displays all players with a pending loan and the value of the loan, allowing the loaner
	 * to approve or decline the loan amount.
	 */
	public void manageWaitingLoans()
	{
		pgm.dumpIn(pgm);
		pgm.clearScreen();
		int selection = 0;
		updateLists();
		while(selection != -1)
		{
			if(waiting.size() >0)
			{
				int count = 1;
				for(PlayerAccount p: waiting)
				{
					
					System.out.println(p.getPlayerInfo().getAccountID() + ". " + p.getAccount().getuName());

				}
				System.out.print("\n" + "Select an account to manage or -1 to exit: ");
				selection = pgm.in.nextInt();
				if(selection != -1)
				{
					PlayerAccount temp = (PlayerAccount)waiting.get(selection-1);
					int selection2 = 0;
					count = 1;
					System.out.println("Account info");
					System.out.println("User Name: " + temp.getAccount().getuName());
					System.out.println("Loan Ammount: " + temp.getPlayerInfo().getLoanBalance());
					System.out.println("");
					System.out.println(count + ". Approve account");
					count++;
					System.out.println(count + ". Decline account");
					count++;
					System.out.println(count + ". Decide later");
					selection2 = pgm.in.nextInt();
					switch (selection2)
					{
					case 1: temp.getPlayerInfo().setLoanWaiting(false);;
							temp.getPlayerInfo().setHasLoan(true);
							lAcc.setBalance(lAcc.getBalance() - temp.getPlayerInfo().getLoanBalance());
							break;
					case 2: temp.getPlayerInfo().setLoanWaiting(false);
					case 3: break;
					default: System.out.println("Invalid choice, try again.");
					//TODO: Loop selection in case of invalid input
				}
				}
			}
			else
			{
				System.out.println("No waiting accounts at this moment.");
				selection = -1;
			}
		}
	}
	
	/**
	 * Pings for a scan of all player accounts, adding those with waiting loans to the waiting
	 * list and those with active loans to the active list.
	 */
	public void updateLists()
	{
		active = new ArrayList<>();
		waiting = new ArrayList<>();
		for(PlayerAccount p: pgm.Accounts.getList()) 
		{
			if(p.getPlayerInfo().isHasLoan())
			{
				active.add(p);
			}
			if(p.getPlayerInfo().isLoanWaiting()) 
			{
				waiting.add(p);
			}
		}
	}
	
	/**
	 * Allows the Loaner to adjust the ammount of interest charged each time a player's 
	 * time increments.
	 */
	public void adjustInterestRates()
	{
		pgm.dumpIn(pgm);
		pgm.clearScreen();
		System.out.println("The current interest rate is (Format 00.00 times)" + lAcc.getInterest());
		System.out.print("What should the new interest rate be?: ");
		lAcc.setInterest(pgm.in.nextDouble());
		
		System.out.println("The new interest rate is: " + lAcc.getInterest());
	}
	
	/**
	 * method shared by each Account extender, view comment on parent class for explination.
	 */
	@Override
	public void menu() 
	{
		pgm.dumpIn(pgm);
		pgm.clearScreen();
		int selection = 0;
		System.out.println("Welcome Loaner " + lAcc.getAccountInfo().getName() + "\n");
		while(selection != 4)
		{
			System.out.println("What would you like to do today?");
			int count = 1;
			System.out.println(count + ". View active accounts");
			count++;
			System.out.println(count + ". View waiting accounts");
			count++;
			System.out.println(count + ". Adjust interest rates");
			count++;
			System.out.println(count + ". Logout");
			count++;
			selection = pgm.in.nextInt();
			
			switch (selection) 
			{
			case 1: manageActiveLoans();
					break;
			case 2: manageWaitingLoans();
					break;
			case 3: adjustInterestRates();
					break;
			case 4: logout();
					break;
			}
		}
	}

	@Override
	public Account getAccount() {
		return lAcc.getAccountInfo();
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub	
	}
	
	public Loaner getLoanerInfo() {
		return lAcc;
	}
}
