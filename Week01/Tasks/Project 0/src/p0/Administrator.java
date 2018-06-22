package p0;

public class Administrator extends Account {

	private boolean worldFlagged;
	
	public Administrator(String name, String uname, String pword, Launch pgm)
	{
		super(name, uname, pword, pgm);
	}

	public void manageActive()
	{
		int selection = 0;
		while(selection != -1)
		{
			int count = 1;

			for(Account a: pgm.Active.getList())
			{
				System.out.println(count + ". " + a.getuName());
			}
			System.out.print("\n Select an account to manage or -1 to exit: ");
			selection = pgm.in.nextInt();
			
			Account temp = pgm.Active.getList().get(selection);
			int selection2 = 0;
			count = 1;
			while(selection2 !=-1)
			{
				System.out.println("Accou");
				System.out.println(count + ". ");
			}
		}
		
		
	}
	
	public void manageWaiting()
	{
		
	}
	
	public void deleteWorld()
	{
		System.out.println("You have chosen to erase this world...");
		System.out.print("Are you sure this is what you want to do? Y/N:");
		char selection = pgm.in.next().charAt(0);
		if (selection =='Y' || selection =='y')
		{
			worldFlagged = true;
			accountFlagged = true;
			System.out.println("Your choice has been set.");
			System.out.println("There will be nothing left of this pathetic existance");
			System.out.println("log out when you are ready to start the destruction.");
			pgm.in.nextLine();
			pgm.in.nextLine();
		}
	}
	
	public void saveWorld()
	{
		System.out.println("So you want to save the world...");
		System.out.print("Are you sure this is what you want to do? Y/N:");
		char selection = pgm.in.next().charAt(0);
		if (selection =='Y' || selection =='y')
		{
			worldFlagged = false;
			accountFlagged = false;
			System.out.println("You have chosen mercy for this world.");
			System.out.println("Be more careful in your decisions next time...");
			System.out.println("You can't always go back.");
			pgm.in.nextLine();
			pgm.in.nextLine();
		}
	}

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
			case 3: if(worldFlagged)
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
