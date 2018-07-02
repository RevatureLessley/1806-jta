package com.revature.week1.tasks;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.revature.week1.tasks.util.Connections;
public class Bank implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2699783004032967902L;
	// These are for creating a new User. Me and Admin are enough administrators.
	// We like to have total control. Not unlike a dictatorship.d
	String newName;
	String newPassword;
	float newbalance;
	boolean newisAdmin = false;   // I am aware of naming conventions, but I'm considering 'new' as a modifier and not part of the actual name of the boolean
	boolean newisApproved = false; // Ditto
	
	String answer;
	float newBalance;
	transient Logger logger = BankMain.logger;
	
	private boolean isNameCorrect = false;
	private boolean isPasswordCorrect = false;
	private String name;
	private String password;
	public transient static Scanner sc = new Scanner(System.in);
	//Set up User array. May make this an ArrayList in the future and allow the user to add themselves.
	private ArrayList<User> users = new ArrayList<User>();
	private int newId;
	
	
	
	


	///////////////////////////////////////
	// Getters/Setters, Constructor
	///////////////////////////////////////
	@Override
	public String toString()
	{
		return "Bank [users=" + users + "]";
	}
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
	
	public Bank(ArrayList<User> users)
	{
		super();
		this.users = users;
	}
	

	///////////////////////////////////////
	// Functionality
	///////////////////////////////////////
	
/**
 * Just testing out the java Docs.
 * This function just calls the first "screen" of the app	
 */
public void initialSetup()
	{			
	
	firstScreen();
	}
	
	void firstScreen()
	{
		
		//BankMain.testthis();	
		
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
	
	/**
	 * This function will iterate through the list of names until it finds one that matches the input to match it up with the proper password
	 * if it does not find one, it will send you back to the start screen
	 * It will then check and see if the use is an admin or just a regular user
	 */
	
	public void Login()
	{	
		
		enterName();
		isNameCorrect = false;
		
		for (int i = 0; i < users.size(); i++)
		{
			//System.out.println("name checked" + i + isNameCorrect);
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
				//break;	
			}
			else if(!isNameCorrect)
			{
				continue;
				//System.out.println("Sorry, your name was not found in our database");
			}
			//System.out.println("Sorry, your name is not in our database.");
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
		UserDaoImpl ud = new UserDaoImpl();
		User user = new User(newId, newName, newPassword, newBalance, newisAdmin, newisApproved);
		ud.insertUser(user);
		ud.insertUser(user, "unapprovedusers");
		// add the user to the general users list
		users.add(new User(newId, newName, newPassword, newBalance, newisAdmin, newisApproved));
		BankMain.logger.info("User created sucessfully!");
	}
	
	///////////////////////////////////////
	// Extracted Functions
	///////////////////////////////////////
	
	/**
	 * Sets up variables for a new User, then adds it to the users ArrayList
	 */
	private void setupNewUser()
	{
		BankMain.logger.info("Attempting to create a new user");
		//System.out.println("Sorry, but we are not taking new clients at this time.");
		System.out.println("Great! Let's get you set up!");
		System.out.println("First, what is your name?");
		newName = sc.nextLine();
		System.out.println("Now what would you like to be your password?");
		newPassword = sc.nextLine();
		System.out.println("And how much are you depositing to start?");
		
		 // This part is just to verify that they only enter numerical values
		 
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
		// Helper function to add the user to the users ArrayList
		addUser();
		// All OOS and OIS have been disabled due to the database serving as a way to "save the data"
		/*
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BankUsers.ser"));
			oos.writeObject(this); //Serialize
			oos.close();
		}catch(IOException e){
			e.printStackTrace();
		}*/
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
		try(Connection conn = Connections.getConnection())
		{
			Statement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM USERS2";
			 
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			
			
			int size = 0;  
            if (rs != null)   
            {  
              
            rs.last();  
            size = rs.getRow();
            rs.beforeFirst();  
            }
         
			while(rs.next())
			{
				if(rs.getInt(6) == 0)
				{
					newId = rs.getInt(1);
					newName = rs.getString(2);
					newPassword = rs.getString(3);
					newBalance = rs.getFloat(4);
					if(rs.getInt(5) == 1)
					{
						newisAdmin = true;
					}
					else
					{
						newisAdmin = false;
					}
					
					if(rs.getInt(6) == 1)
					{
						newisApproved = true;
					}
					else
					{
						newisApproved = false;
					}
					
					User user = new User(newId, newName, newPassword, newBalance, newisAdmin, newisApproved);
					adminApproveUser(user);
					//System.out.println("test admin check and approve");
					
				}
				else
				{
					System.out.println("test admin check its all good");
					continue;
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		adminFinalScreen();
		}
	/**
	 * just allows an admin to switch the isApproved bool to true
	 * @param user
	 */
	private void adminApproveUser(User user)
	{
		System.out.println(user.getName() + " is not yet approved. Approve them? y/n");
		if(sc.nextLine().equals("y"))
		{
			user.setApproved(true);
			UserDaoImpl ud = new UserDaoImpl();
			//User user = user.selectNpcById(id);
			
			if(user != null)
			{
				ud.updateUser(user);
			}
			System.out.println(user.getName()+ " has been approved!");
		}
		else if(sc.nextLine().equals("n"))
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

	/**
	 * This "screen" allows the user to pick an action they would like to take
	 * @param user
	 */
	private void userWelcomeScreen(User user)
	{
		System.out.println("Welcome " + user.getName() + "!");
		System.out.println("What would you like to do today?");
		System.out.println("\n [W]ithdraw, [D]eposit, [C]heck balance or [Exit]?");
		
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
		if(answer.toLowerCase().equals("exit"))
		{
			firstScreen();
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
				if(!Pattern.matches("[a-zA-Z]+", answer)) // Verifies that the input is a numerical value
				{	
					BankMain.logger.info(user.getName() + ": withdraw attempted.");
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
		BankMain.logger.info(user.getName() + " withdrew " + answer);
		UserDaoImpl ud = new UserDaoImpl();
		ud.updateUser(user);
		//userWelcomeScreen(user);
		
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BankUsers.ser"));
			oos.writeObject(this); //Serialize
			oos.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
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
					BankMain.logger.info(user.getName() + ": deposit attempted.");
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
		BankMain.logger.info(user.getName() + " deposited " + answer);
		UserDaoImpl ud = new UserDaoImpl();
		ud.updateUser(user);
		
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BankUsers.ser"));
			oos.writeObject(this); //Serialize
			oos.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * This just returns the current user's balance in the console.
	 * @param user
	 * @return
	 */
	public float userCheckBalance(User user)
	{
		System.out.println(user.getBalance());
		userWelcomeScreen(user);
		return user.getBalance();
	}

}
