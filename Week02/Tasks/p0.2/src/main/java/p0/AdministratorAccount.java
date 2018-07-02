package p0;

import p0.beans.Account;
import p0.beans.Administrator;

public class AdministratorAccount extends AccountClass
{
	private Administrator aAcc;
	
	public AdministratorAccount(String name, String uname, String pword)
	{
		aAcc = new Administrator(name, uname, pword);
	}

	/**
	 * Method to print out the currently active player accounts and give
	 * administrative actions to take on one of their selection
	 */
	public void manageActive()
	{
		int selection = 0;
		if(pgm.Accounts.getList().size() >0)
		{
			while(selection != -1)
			{
				pgm.dumpIn(pgm);
				pgm.clearScreen();
				int count = 1;
				for(PlayerAccount a: pgm.Accounts.getList())
				{
					System.out.println(count + ". " + a.getPlayerInfo().getAccountInfo().getuName());
					count++;
				}
				System.out.print("\n Select an account to manage or -1 to exit: ");
				selection = pgm.in.nextInt();

				if(selection >1)
				{
					PlayerAccount temp = (PlayerAccount)pgm.Accounts.getList().get(selection-1); 
					int selection2 = 0;
					count = 1;
					while(selection2 !=2)
					{
						System.out.println("Account info");
						System.out.println("User Name: " + temp.getPlayerInfo().getAccountInfo().getuName());
						System.out.println("Player Name: " + temp.getPlayerInfo().getAccountInfo().getName());
						System.out.println("Bank Balance: " + temp.getPlayerInfo().getBankBalance());
						System.out.println("Loan Balance: " + temp.getPlayerInfo().getLoanBalance());
						System.out.println("");
						System.out.println(count + ". Shut down account");
						count++;
						System.out.println(count + ". Quit");

						selection2 = pgm.in.nextInt();
					}
				}
			}
		}
		else
		{
			System.out.println("No active accounts at this moment.");
		}
	}
	
	/**
	 * Method to print out the list of player accounts waiting approval
	 * Admin can approve, deny or delay their decision on the account
	 */
	public void manageWaiting()
	{
		int selection = 0;

		while(selection != -1)
		{
			if(pgm.Accounts.getWaitingList().size() >0)
			{
				pgm.dumpIn(pgm);
				pgm.clearScreen();
				int count = 1;
				for(PlayerAccount a: pgm.Accounts.getWaitingList())
				{
					System.out.println(count + ". " + a.getPlayerInfo().getAccountInfo().getuName());
					count++;
				}
				System.out.print("\n Select an account to manage or -1 to exit: ");
				selection = pgm.in.nextInt();
				PlayerAccount temp = (PlayerAccount)pgm.Accounts.getWaitingList().get(selection-1);
				count = 1;
				System.out.println("Account info");
				System.out.println("User Name: " + temp.getPlayerInfo().getAccountInfo().getuName());
				System.out.println("Player Name: " + temp.getPlayerInfo().getAccountInfo().getName());
				System.out.println("");
				System.out.println(count + ". Approve account");
				count++;
				System.out.println(count + ". Decline account");
				count++;
				System.out.println(count + ". Decide later");
				int selection2 = pgm.in.nextInt();
				switch (selection2)
				{
				case 1: temp.getPlayerInfo().setAccountActive(true);
						pgm.Accounts.update();
						break;
				case 2: //TODO Deny account
				case 3: break;
				default: System.out.println("Invalid choice, try again.");
				}
			}
			else
			{
				System.out.println("No waiting accounts at this moment.");
				selection = -1;
			}
			pgm.dumpIn(pgm);
		}
	}
	
	/**
	 * method shared by each Account extender, view comment on parent class for explination.
	 */
	@Override
	public void menu()
	{
		int selection = 0;
		System.out.println("Welcome Admin " + aAcc.getAccountInfo().getName() + "\n");
		while(selection != 3)
		{
			pgm.dumpIn(pgm);
			pgm.clearScreen();
			System.out.println("What would you like to do today?");
			int count = 1;
			System.out.println(count + ". Manage active accounts");
			count++;
			System.out.println(count + ". Manage waiting accounts");
			count++;
			System.out.println(count + ". Logout");
			count++;
			selection = pgm.in.nextInt();
			
			switch (selection) {
			case 1: manageActive();
					break;
			case 2: manageWaiting();
					break;
			case 3: logout();
					break;
			}
		}
	}
	
	@Override
	public Account getAccount() {
		return aAcc.getAccountInfo();
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	
	public Administrator getAdminInfo() {
		return aAcc;
	}
}
