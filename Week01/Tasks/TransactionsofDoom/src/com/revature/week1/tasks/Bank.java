package com.revature.week1.tasks;

public class Bank {
	
	String answer;
	
	private boolean isNameCorrect = false;
	private boolean isPasswordCorrect = false;
	private String name;
	private String password;
	//Set up User array. May make this an ArrayList in the future and allow the user to add themselves.
	private User[] users =
	{
		new User("chris", "password", 9999, true, true),
		new User("carol", "kitty", 356, false, true),
		new User("jack", "secretpassword", 1500, false, false),
		new User("admin", "password", 0, true, true)
	};
	
	
	
	
	
	
	
	public boolean isNameCorrect()
	{
		return isNameCorrect;
	}
	public void setNameCorrect(boolean isNameCorrect)
	{
		this.isNameCorrect = isNameCorrect;
	}
	
	public boolean isPasswordCorrect()
	{
		return isPasswordCorrect;
	}
	public void setPasswordCorrect(boolean isPasswordCorrect)
	{
		this.isPasswordCorrect = isPasswordCorrect;
	}
	
	public User[] getUsers()
	{
		return users;
	}
	public void setUsers(User[] users)
		{
			this.users = users;
		}







	void firstScreen()
	{
		while(true)
		{
			System.out.println("Hello. Do you have an account already? y/n");
			answer = BankMain.sc.nextLine();
			if(answer.equals("y"))
			{
				System.out.println("Welcome Back!");
				Login();
				break;
			}
			else if(answer.equals("n"))
			{
				System.out.println("Sorry, but we are not taking new clients at this time.");
			}
			else
			{
				System.out.println("Please enter either y or n.");
			}
		}
	}
	
	public void Login()
	{	
		enterName();
		
		// For now all the checks are going here, but soon I will move most of this out of the 'for' loop. 
		for (int i = 0; i < users.length; i++)
		{
			if(users[i].getName().equals(name))
			{
				isNameCorrect = true;
				EnterPassword();
				if(users[i].getPassword().equals(password))
				{
					if(users[i].isApproved())
					{
						if(users[i].isAdmin())
						{
							adminScreen();
						}
						else
						{
							// go to deposit/withdrawal "screen"
						}
						// create deposit/withdrawal "screen"
					}
					else
					{
						
					}
				}
				break;	
			}
			else
			{
				continue;
			}
		}
		
		if(!isNameCorrect)
		{
			System.out.println("Sorry, your name wasn't found in our database. :(");
			System.out.println("Unfortunately, this means you will have to start all over.");
			firstScreen();
		}
	}
	
	
	void adminScreen()
	{
		System.out.println("Hello adminstrator!");
		System.out.println("Let's see if you have to approve anyone's account today!");
		// Iterate through the users array and see if any are not yet approved and then approve them if they aren't
		// Well, I guess you don't HAVE to approve them but there really isn't a reason not to
		
		adminCheckUsers();
	}
	
	
	
	
	
	
	///////////////////////////////////////
	// Helper Functions
	///////////////////////////////////////
	
	public void enterName()
	{
		System.out.println("Please enter your login name.");
		name = BankMain.sc.nextLine();
	}
	
	public void EnterPassword()
	{
		System.out.println("Please Enter Password:");
		password = BankMain.sc.nextLine();
	}
	
	private void adminCheckUsers()
	{
		for (int i = 0; i < users.length; i++)
		{
			if(!users[i].isApproved())
			{
				System.out.println(users[i].getName() + " is not yet approved. Approve them? y/n");
				if(BankMain.sc.nextLine().equals("y"))
				{
					users[i].setApproved(true);
					System.out.println(users[i].getName()+ " has been approved!");
				}
				else if(BankMain.sc.nextLine().equals("n"))
				{
					System.out.println("I mean.....alright then. Your call. Just sayin' that maybe you should reconsider.");
				}
			}
			else
			{
				continue;
			}
		}
		endOfAdmin();
	}
	

	public void endOfAdmin()
	{
		System.out.println("No other users to approve");
		while(true)
		{
			System.out.println("Would you like to:");
			System.out.println("[check] the list again");
			System.out.println("or");
			System.out.println("[exit] back to the beginning? Maybe to show the rest of your project?");
			answer = BankMain.sc.nextLine();
			if(!answer.equals("check") && !answer.equals("exit"))
			{
				System.out.println("Siiiiiiiiigh. Obviously your options are the words in the brackets. Try again");
			}
			else if(answer.equals("check"))
			{
				adminCheckUsers();
			}
			else if(answer.equals("exit"))
			{
				firstScreen();
			}
		}
	}

	
	
	
	


}
