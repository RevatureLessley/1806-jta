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
		new User("jack", "secretpassword", 1500, false, false)
		new User("admin", "password", 0, true, true);
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
							// go to admin "screen"
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


	
	
	
	


}
