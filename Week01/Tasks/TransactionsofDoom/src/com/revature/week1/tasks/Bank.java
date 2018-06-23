package com.revature.week1.tasks;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Bank {
	// These are for creating a new User. Me and Admin are enough administrators.
	// We like to have total control. Not unlike a dictatorship.d
	String newName;
	String newPassword;
	float newbalance;
	boolean newisAdmin = false;   // I am aware of naming conventions, but I'm considering new as a modifier and not part of the actual name of the boolean
	boolean newisApproved = false; // Ditto
	
	
	
	String answer;
	float newBalance;
	
	private boolean isNameCorrect = false;
	private boolean isPasswordCorrect = false;
	private String name;
	private String password;
	Scanner sc = BankMain.sc;
	//Set up User array. May make this an ArrayList in the future and allow the user to add themselves.
	private ArrayList<User> users = new ArrayList<>();
	
		
	
	
	
	
	
	
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

	public ArrayList<User> getUsers()
	{
		return users;
	}
	public void setUsers(ArrayList<User> users)
	{
		this.users = users;
	}
	
	
	public void initialSetup()
	{
		// Note to self: User( "name", "password", balance, isAdmin, isApproved)
				// These users are here largely for testing, but also so that I have an administrator to approve people
				users.add(new User("chris", "password", 9999, true, true));
				users.add(new User("carol", "kitty", 356, false, true));
				users.add(new User("jack", "secretpassword", 1500, false, false));
				users.add(new User("admin", "password", 0, true, true));
				firstScreen();
	}
	
	void firstScreen()
	{
		
	
		
		while(true)
		{
			System.out.println("Hello. Do you have an account already? y/n");
			answer = sc.nextLine();
			if(answer.equals("y"))
			{
				System.out.println("Welcome Back!");
				Login();
				break;
			}
			else if(answer.equals("n"))
			{
				setupNewUser();
				firstScreen();
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
		for (int i = 0; i < users.size(); i++)
		{
			if(users.get(i).getName().toLowerCase().equals(name.toLowerCase()))
			{
				isNameCorrect = true;
				EnterPassword();
				if(users.get(i).getPassword().equals(password))
				{
					if(users.get(i).isApproved())
					{
						if(users.get(i).isAdmin())
						{
							adminWelcomeScreen();
						}
						else
						{
							userWelcomeScreen(users.get(i));
						}
					}
					else
					{
						System.out.println("Sorry, but your account is not approved yet.");
						firstScreen();
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
	
	
	
	
	
	
	
	
	
	
	///////////////////////////////////////
	// Helper Functions
	///////////////////////////////////////
	
	public void enterName()
	{
		System.out.println("Please enter your login name.");
		name = sc.nextLine();
	}	
	public void EnterPassword()
	{
		System.out.println("Please Enter Password:");
		password = sc.nextLine();
	}
	private void addUser()
	{
		users.add(new User(newName, newPassword, newBalance, newisAdmin, newisApproved));
	}
	
	///////////////////////////////////////
	// Extracted Functions
	///////////////////////////////////////
	
	
	
	private void setupNewUser()
	{
		//System.out.println("Sorry, but we are not taking new clients at this time.");
		System.out.println("Great! Let's get you set up!");
		System.out.println("First, what is your name?");
		newName = sc.nextLine();
		System.out.println("Now what would you like to be your password?");
		newPassword = sc.nextLine();
		System.out.println("And how much are you depositing to start?");
		while(true)
		{
			answer = sc.nextLine();
			// Verify that the string entered is a numerical value
			if(!Pattern.matches("[a-zA-Z]+", answer))
			{	
				newBalance = Integer.parseInt(answer);
				break;
			}
			else
			{
				System.out.println("Please enter a valid numerical amount.");
			}
		}
		addUser();
		System.out.println("Thank you! You will have access as soon as an adminstrator approves your account!");
		System.out.println("Have a nice day!");
	}
	private void adminWelcomeScreen()
	{
		System.out.println("Hello adminstrator!");
		System.out.println("Let's see if you have to approve anyone's account today!");
		
		// Iterate through the users array and see if any are not yet approved and then approve them if they aren't
		// Well, I guess you don't HAVE to approve them but there really isn't a reason not to
		adminCheckUsers(); // <<< Self commenting code! Say Whaaaaaaaaaaa?
	}	
	private void adminCheckUsers()
	{
		for (int i = 0; i < users.size(); i++)
		{
			if(!users.get(i).isApproved())
			{
				adminApproveUser(users.get(i));
			}
			else
			{
				continue;
			}
		}
		adminFinalScreen();
	}
	private void adminApproveUser(User user)
	{
		System.out.println(user.getName() + " is not yet approved. Approve them? y/n");
		if(BankMain.sc.nextLine().equals("y"))
		{
			user.setApproved(true);
			System.out.println(user.getName()+ " has been approved!");
		}
		else if(BankMain.sc.nextLine().equals("n"))
		{
			System.out.println("I mean.....alright then. Your call. Just sayin' that maybe you should reconsider.");
		}
	}
	

	public void adminFinalScreen()
	{
		System.out.println("No other users to approve");
		while(true)
		{
			System.out.println("Would you like to:");
			System.out.println("[check] the list again");
			System.out.println("or");
			System.out.println("[exit] back to the beginning? Maybe to show the rest of your project?");
			answer = sc.nextLine();
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
				break;
			}
		}
	}

	private void userWelcomeScreen(User user)
	{
		System.out.println("Welcome " + user.getName() + "!");
		System.out.println("What would you like to do today?");
		System.out.println("\n [W]ithdraw, [D]eposit, or [C]heck balance?");
		
		answer = sc.nextLine();
		
		if(answer.toLowerCase().equals("w"))
		{
			userWithdrawalScreen(user);
		}
		if(answer.toLowerCase().equals("d"))
		{
			userDepositScreen(user);
		}
		if(answer.toLowerCase().equals("c"))
		{
			userCheckBalance(user);
		}
	}
	private void userWithdrawalScreen(User user)
	{
		while(true)
		{
			System.out.println("Ok, how much would you like to withdraw?");
			System.out.println("(Please enter an amount to withdraw or exit to go back)");
			answer = sc.nextLine();
			if(answer.toLowerCase().equals("exit"))
			{
				userWelcomeScreen(user);
			}
			else
			{
				if(!Pattern.matches("[a-zA-Z]+", answer))
				{	
					userWithdrawAndDisplayNewBalance(user);
					break;
				}
				else
				{
					System.out.println("Please enter a valid numerical amount.");
					//userWithdrawRestart(user);
				}
			}
		}
		
		userWelcomeScreen(user);
	}
	private void userWithdrawAndDisplayNewBalance(User user)
	{
		System.out.println("Balance: " + user.getBalance());
		System.out.println("Withdrawal amount: " + answer);
		newBalance = user.getBalance() - Integer.parseInt(answer);
		user.setBalance(newBalance);
		System.out.println("Total Remaining: " + user.getBalance());
		//userWelcomeScreen(user);
	}	
	private void userDepositScreen(User user)
	{
		while(true)
		{
			System.out.println("Ok, how much would you like to deposit?");
			System.out.println("(Please enter an amount to deposit or exit to go back)");
			answer = sc.nextLine();
			if(answer.toLowerCase().equals("exit"))
			{
				userWelcomeScreen(user);
			}
			else
			{
				if(!Pattern.matches("[a-zA-Z]+", answer))
				{	
					userDepositAndDisplayNewBalance(user);
					break;
				}
				else
				{
					System.out.println("Please enter a valid numerical amount.");
				}
			}
		}
		userWelcomeScreen(user);
		
	}
	private void userDepositAndDisplayNewBalance(User user)
	{
		System.out.println("Balance: " + user.getBalance());
		System.out.println("Deposit amount: " + answer);
		newBalance = user.getBalance() + Integer.parseInt(answer);
		user.setBalance(newBalance);
		System.out.println("Total Remaining: " + user.getBalance());
		//userWelcomeScreen(user);
	}
	private void userCheckBalance(User user)
	{
		System.out.println(user.getBalance());
		userWelcomeScreen(user);
	}


}
