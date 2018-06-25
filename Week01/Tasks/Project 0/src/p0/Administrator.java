package p0;

import java.io.Serializable;

public class Administrator extends Account implements Serializable
{

	private boolean worldFlagged = false;
	
	public Administrator(String name, String uname, String pword, Launch pgm)
	{
		super(name, uname, pword, pgm);
	}

	/**
	 * Method to print out the currently active player accounts and give
	 * administrative actions to take on one of their selection
	 */
	public void manageActive()
	{
		int selection = 0;
		if(pgm.Active.getList().size() >0)
		{
			while(selection != -1)
			{
				int count = 1;
				for(Player a: pgm.Active.getList())
				{
					System.out.println(count + ". " + a.getuName());
				}
				System.out.print("\n Select an account to manage or -1 to exit: ");
				selection = pgm.in.nextInt();

				if(selection >1)
				{
					Player temp = (Player)pgm.Active.getList().get(selection-1); 
					int selection2 = 0;
					count = 1;
					while(selection2 !=2)
					{
						System.out.println("Account info");
						System.out.println("User Name: " + temp.getuName());
						System.out.println("Player Name: " + temp.getName());
						System.out.println("Bank Balance: " + temp.getbBalance());
						System.out.println("Loan Balance: " + temp.getlBalance());
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
			if(pgm.Waiting.getList().size() >0)
			{
				int count = 1;
				for(Player a: pgm.Waiting.getList())
				{
					System.out.println(count + ". " + a.getuName());
					count++;
				}
				System.out.print("\n Select an account to manage or -1 to exit: ");
				selection = pgm.in.nextInt();
				Player temp = (Player)pgm.Waiting.getList().get(selection-1);
				int selection2 = 0;
				count = 1;
				System.out.println("Account info");
				System.out.println("User Name: " + temp.getuName());
				System.out.println("Player Name: " + temp.getName());
				System.out.println("");
				System.out.println(count + ". Approve account");
				count++;
				System.out.println(count + ". Decline account");
				count++;
				System.out.println(count + ". Decide later");
				int selection3 = pgm.in.nextInt();
				switch (selection3)
				{
				case 1: pgm.Active.add(new Player(temp.getName(), temp.getuName(), temp.getuPass(), 100, 100, 0, pgm));
						temp.setFlagged(true);
						break;
				case 2: temp.setFlagged(true);
				case 3: break;
				default: System.out.println("Invalid choice, try again.");
				}
			}
			else
			{
				System.out.println("No waiting accounts at this moment.");
				selection = -1;
			}
			pgm.save(pgm);
		}
	}
	/** 
	 * Gives the administrator the option to mark the database to be completely erased
	 * making a full restart for the game.
	 */
	public void deleteWorld()
	{
		System.out.println("You have chosen to erase this world...");
		System.out.print("Are you sure this is what you want to do? Y/N:");
		char selection = pgm.in.next().charAt(0);
		if (selection =='Y' || selection =='y')
		{
			pgm.Active.setWorldFlagged(true);
			pgm.Waiting.setWorldFlagged(true);
			System.out.println("Your choice has been set.");
			System.out.println("There will be nothing left of this pathetic existance");
			System.out.println("log out when you are ready to start the destruction.");
			pgm.in.nextLine();
			pgm.in.nextLine();
		}
		else if(selection == 'N' || selection == 'n')
		{
			System.out.println("Don't have the stomach for it? \n Oh well, maybe next time.");
		}
		else 
		{
			System.out.println("That wasn't something I recognize. \n Try that again when you want to give a true answer");
		}
	}
	/** 
	 * Gives the administrator the ability to revert their decision and keep the database
	 * rather than deleting on next logout.
	 */
	public void saveWorld()
	{
		System.out.println("So you want to save the world...");
		System.out.print("Are you sure this is what you want to do? Y/N:");
		char selection = pgm.in.next().charAt(0);
		if (selection =='Y' || selection =='y')
		{
			pgm.Active.setWorldFlagged(false);
			pgm.Waiting.setWorldFlagged(false);
			System.out.println("You have chosen mercy for this world.");
			System.out.println("Be more careful in your decisions next time...");
			System.out.println("You can't always go back.");
			pgm.in.nextLine();
			pgm.in.nextLine();
		}
		else if(selection == 'N' || selection == 'n')
		{
			System.out.println("Show conviction in your decisions. \n Log out so I can erase this world.");
		}
		else 
		{
			System.out.println("That wasn't something I recognize. \n Try that again when you want to give a true answer");
		}
	}
	
	/**
	 * method shared by each Account extender, view comment on parent class for explination.
	 */
	@Override
	public void menu()
	{
		int selection = 0;
		System.out.println("Welcome Admin " + this.Name + "\n");
		while(selection != 4)
		{
			System.out.println("What would you like to do today?");
			int count = 1;
			System.out.println(count + ". Manage active accounts");
			count++;
			System.out.println(count + ". Manage waiting accounts");
			count++;
			if(worldFlagged)
			{
				System.out.println(count + ". Change your mind");
			}
			else
			{
				System.out.println(count + ". Erase the world");
			}
			count++;
			System.out.println(count + ". Logout");
			count++;
			selection = pgm.in.nextInt();
			
			switch (selection) {
			case 1: manageActive();
					break;
			case 2: manageWaiting();
					break;
			case 3: if(pgm.Active.getWorldFlagged())
						saveWorld();
					else
						deleteWorld();
					break;
			case 4: logout();
					break;
			}
		}
	}

	@Override
	public String toString() {
		return "Administrator [uName=" + uName + ", uPass=" + uPass + ", Name=" + Name + "]";
	}
}
