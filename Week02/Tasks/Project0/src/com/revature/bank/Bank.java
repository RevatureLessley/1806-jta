package com.revature.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.service.AccountService;
import com.revature.main.Driver;


/**
 * Lets a normal user login, logout, 
 * deposit and withdraw from their account.
 * <br>Lets an admin login, logout,
 * and approve accounts. 
 * <br>Fields:
 * <br>logger(Logger)
 * <br>accs(ArrayList<Account>)
 * <br>activeAccount(int)
 * <br>counter(int)
 * <br>accountsAmount(int)
 * <br>registered(String)
 * <br>USER(int)
 * <br>ADMIN(int)
 * <br>
 * <br>Methods:
 * <br>public Bank(ArrayList<Account>)
 * <br>public void register()
 * <br>public void login()
 * <br>public void userLogin()
 * <br>public void adminLogin()
 * <br>public void askUserInput(Account)
 * <br>public void askAdminInput(Account)
 * <br>public void userWithdraw(int, Account)
 * <br>public void userDeposit(int, Account)
 * <br>public void writeAccountsFile(ArrayList<Account>)
 * <br>public ArrayList<Account> readAccountsFile()
 * 
 * 
 * @author Logan Brewer
 */
public class Bank 
{
	final static Logger logger = Logger.getLogger(Bank.class);
	private static ArrayList<Account> accs = null;
	private int activeAccount = -1;
	private int enteredAccountType;
	private int counter;
	private static int accountsAmount;
	private String registered;
	private static final int USER = 0;
	private static final int ADMIN = 1;
	private static AccountService as = new AccountService();
	
	
	public Bank( ArrayList<Account> accs )
	{
		this.accs = accs;
		accountsAmount = accs.size();
		logger.info("Initializing Bank with existing accounts.");
	}
	
	
	public static void displayAccountGroup(ArrayList<Account> accs)
	{
		for(Account acc: accs)
		{
			System.out.println("Username " + acc.getUserName() +
								", password: " +
								acc.getPassword() +
								", currency: " +
								acc.getAccountValue() +
								", accountType: " +
								acc.getAccountType() +
								", accountApproved: " +
								acc.getApproved()
								);
		}
	}
	
	
	
	/**
	 * This is used when a user does not have an account.
	 * Has the user enter a username and password they want to use.
	 * Stores that username and password in the accs ArrayList<Account>
	 * and writes that ArrayList to a file named "accountsFile.txt"
	 */
	public void register()
	{
		accountsAmount++;
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(System.in) );
		try 
		{
			logger.info("Registering a new user.");
			System.out.println("What username would you like to use?: ");
			String newUsername = bufferedReader.readLine();
			System.out.println("What password would you like to use?: ");
			String newPassword = bufferedReader.readLine();
			Account newAccount = new Account(accountsAmount, newUsername, newPassword, 0, USER, 0);
			as.insertAccount(newAccount);
			accs = as.getAllAccounts();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Thank you for registering, now please login.\n");
		login();
	}
	
	/**
	 * This is the main page that users will first see. 
	 * It will first ask if the user has an account. If they say no they are sent to register,
	 * if they say yes it then asks what type of account they have and sends them to the 
	 * correct login option, userLogin() or adminLogin().
	 */
	public void login()
	{
		activeAccount = -1;
		displayAccountGroup(accs);
		System.out.println("Welcome to the bank, do you have an account? (Yes/No): ");
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(System.in) );
		try
		{
			while(true)
			{
				registered = bufferedReader.readLine().toLowerCase();
				if ( registered.equals("no") )
				{
					System.out.println("Please register an account.");
					register();
				}
				else if ( registered.equals("yes") )
				{
					System.out.println("Are you a normal user(0) or an admin(1)?: ");
					while(true)
					{
						enteredAccountType = Integer.parseInt( bufferedReader.readLine() );
						if ( enteredAccountType == 0 ) 
						{
							userLogin();
						}
						else if ( enteredAccountType == 1 )
						{
							adminLogin();
						}
						else
						{
							System.out.println("Enter \"0\" for normal user, \"1\" for admin.");
						}
					}
				}
				else
				{
					System.out.println("Enter \"yes\" or \"no\".");
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Handles a normal user trying it login. It prompts the user for their username
	 * and password. If the account is approved, they are able to login.
	 * If the account hasn't been approved, they must wait for an admin account
	 * to approve their account first and they are sent back to the login screen.
	 */
	public void userLogin()
	{
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(System.in) );
		try 
		{
			System.out.println("Please enter your username: ");
			String enteredAccountName;
			enteredAccountName = bufferedReader.readLine();
			for ( int i = 0; i < accs.size(); i++ )
			{
				if ( accs.get(i).getUserName().equals(enteredAccountName) )
				{
					activeAccount = i;
					break;
				}
			}
			while ( activeAccount == -1 )
			{
				System.out.println("Not a valid user account. Try again.");
				enteredAccountName = bufferedReader.readLine();
				for (counter = 0; counter < accs.size(); counter++ )
				{
					if ( accs.get(counter).getUserName().equals(enteredAccountName) )
					{
						activeAccount = counter;
						System.out.println("Correct account name.");
						break;
					}
				}
			}
			
			System.out.println("Please enter your password: ");
			String enteredPassword;
			
			enteredPassword = bufferedReader.readLine();
			while(true)
			{
				if ( accs.get(activeAccount).getPassword().equals(enteredPassword) )
				{
					break;		
				}
				else
				{
					while(true)
					{
						System.out.println("That password does not exist. Try again.");
						enteredPassword = bufferedReader.readLine();
						if ( accs.get(activeAccount).getPassword().equals(enteredPassword) )
						{
							break;
						}
						
					}
				}
			}
			while(true)
			{
				if ( accs.get(activeAccount).getApproved() == 1 && accs.get(activeAccount).getAccountType() == 0 )
				{
					System.out.println("Successfully logged in.\n");
					logger.info("Normal user successfully logged in.");
					askUserInput(accs.get(activeAccount));
				}
				else if ( accs.get(activeAccount).getApproved() == 0 && accs.get(activeAccount).getAccountType() == 0 )
				{
					logger.info("Normal user succesfully logged in but account needs approval.");
					System.out.println("Your account is not activated.");
					System.out.println("Please wait until an admin activates your account. Thank you.\n");
					login();
				}
				else
				{
					System.out.println("Not a normal user account. Sending you back to the login screen.");
					login();
				}
			}
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}	
	}
	
	/**
	 * Handles an admin logging in. It prompts them for their username and password.
	 * If their account is approved, they login. Otherwise they need to wait for an
	 * existing admin to approve their account.
	 */
	public void adminLogin()
	{
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(System.in) );
		try 
		{
			System.out.println("Please enter your username: ");
			String enteredAccountName;
			enteredAccountName = bufferedReader.readLine();
			for ( int i = 0; i < accs.size(); i++ )
			{
				if ( accs.get(i).getUserName().equals(enteredAccountName) )
				{
					activeAccount = i;
					break;
				}
			}
			while ( activeAccount == -1 )
			{
				System.out.println("Not a valid admin account. Try again.");
				enteredAccountName = bufferedReader.readLine();
				for (counter = 0; counter < accs.size(); counter++ )
				{
					if ( accs.get(counter).getUserName().equals(enteredAccountName) )
					{
						activeAccount = counter;
						System.out.println("Correct account name.");
						break;
					}
				}
			}
			
			System.out.println("Please enter your password: ");
			String enteredPassword;
			
			enteredPassword = bufferedReader.readLine();
			while(true)
			{
				if ( accs.get(activeAccount).getPassword().equals(enteredPassword) )
				{
					break;		
				}
				else
				{
					while(true)
					{
						System.out.println("Incorrect password. Try again.");
						enteredPassword = bufferedReader.readLine();
						if ( accs.get(activeAccount).getPassword().equals(enteredPassword) )
						{
							break;
						}
						
					}
				}
			}
			
			if ( accs.get(activeAccount).getApproved() == 1 && accs.get(activeAccount).getAccountType() == 1 )
			{
				System.out.println("Successfully logged in.\n");
				logger.info("Admin successfully logged in.");
				askAdminInput(accs.get(activeAccount));
			}
			else if ( accs.get(activeAccount).getApproved() == 0 && accs.get(activeAccount).getAccountType() == 1 )
			{
				logger.info("Admin succesfully logged in but account needs approval.");
				System.out.println("Your account is not activated.");
				System.out.println("Please wait until an admin activates your account. Thank you.\n");
				login();
			}
			else
			{
				System.out.println("Not an admin account. Sending you back to the login screen.");
				login();
			}
		}
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}	
	}
	
	/**
	 * Takes in the normal user account that logged in and goes into their main menu of options.
	 * <br>Their three options are to deposit, withdraw or logout. 
	 * <br>If they choose deposit, they are sent to the userDeposit() method.
	 * <br>If they choose withdraw, they are sent to the userWithdraw() method.
	 * <br>If they choose logout, they are logged out of their account and sent to the login() screen. 
	 * @param account
	 */
	public void askUserInput(Account account) 
	{
		while(true)
		{
			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(System.in) );
			System.out.println("What would you like to do?(Deposit, Withdraw, Logout): ");
			String userOption;
			try 
			{
				userOption = bufferedReader.readLine().toLowerCase();
				if ( userOption.equals("withdraw") )
				{
					System.out.println("How much would you like to withdraw?: ");
					int withdrawAmount = Integer.parseInt(bufferedReader.readLine());
					userWithdraw(withdrawAmount, account);
				}
				else if ( userOption.equals("deposit") )
				{
					System.out.println("How much would you like to deposit?: ");
					int depositAmount = Integer.parseInt(bufferedReader.readLine());
					userDeposit(depositAmount, account);
				}
				else if ( userOption.equals("logout") )
				{
					logger.info("Logging out of users account.");
					System.out.println("You have been logged out.\n");
					login();
				}
				else
				{
					while ( true )
					{
						logger.info("User entered incorrect input.");
						System.out.println("Not a valid command, enter \"deposit\" or \"withdraw\" or \"logout\": ");
						userOption = bufferedReader.readLine().toLowerCase();
						if ( userOption.equals("withdraw") )
						{
							System.out.println("How much would you like to withdraw?: ");
							int withdrawAmount = Integer.parseInt(bufferedReader.readLine());
							userWithdraw(withdrawAmount, account);
							break;
						}
						else if ( userOption.equals("deposit") )
						{
							System.out.println("How much would you like to deposit?: ");
							int depositAmount = Integer.parseInt(bufferedReader.readLine());
							userDeposit(depositAmount, account);
							break;
						}
						else if ( userOption.equals("logout") )
						{
							System.out.println("You have been logged out.\n");
							login();
						}
					}
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Takes an admin account as the parameter and goes into their main menu. 
	 * An admin is only able to approve accounts, they don't have any other functionality in this
	 * method. They loop through all of the unapproved accounts and have the option to change those accounts
	 * to be approved or leave them as unapproved.
	 * @param account
	 */
	public void askAdminInput(Account account)
	{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Accounts left to approve: ");
		try
		{
			for ( int i = 0; i < accs.size(); i++ )
			{
				if ( accs.get(i).getApproved() == 0 )
				{
					System.out.println("AccountName: " + accs.get(i).getUserName());
					System.out.println("Do you want to approve this account? (yes/no): ");
					while(true)
					{
						String enteredAnswer = bufferedReader.readLine().toLowerCase();
						if ( enteredAnswer.equals("yes") )
						{
							logger.info("Admin approved account: " + accs.get(i) + ".");
							as.updateApproved(accs.get(i), 1);
							accs = as.getAllAccounts();
							System.out.println("AccountName: " + accs.get(i).getUserName() + " has been approved.\n");
							break;
						}
						else if ( enteredAnswer.equals("no"))
						{
							logger.info("Admin DID NOT approve account: " + accs.get(i) + ".");
							System.out.println("AccountName: " + accs.get(i).getUserName() + " has NOT been approved.\n");
							break;
						}
						else
						{
							System.out.println("Please type \"yes\" or \"no\".");
						}
					}
				}
			}
			System.out.println("Done going through all accounts.");
			System.out.println("Logging you out, switch to user account to perform transactions.\n");
			logger.info("Admin finished going through accounts to approve, now logging out.");
			//displayAccountGroup(accs);
			login();
		}
		catch ( IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Used when an account, that is passed in as a parameter, tries to withdraw from their account.
	 * If they withdraw an amount that is less than zero or greater than the amount that
	 * is in their account, it prompts them to enter a valid amount. After entering a valid
	 * amount, it sends them to their askUserInput() menu page.
	 * @param withdrawAmount
	 * @param account
	 */
	public void userWithdraw(int withdrawAmount, Account account)
	{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Amount in the account before withdraw: $" + account.getAccountValue()); 
		int finalAmount = account.getAccountValue() - withdrawAmount;
		if ( finalAmount < 0 )
		{
			logger.info("User tried to withdraw too much.");
			System.out.println("You cannot withdraw that much.");
			System.out.println("Please enter an amount between 0 and " + account.getAccountValue() + ".");
			while(true)
			{
				try 
				{
					withdrawAmount = Integer.parseInt(bufferedReader.readLine());
					if ( withdrawAmount >= 0 && withdrawAmount <= account.getAccountValue() )
					{
						logger.info("User withdrew: " + withdrawAmount + " dollars from their account.");
						finalAmount = account.getAccountValue() - withdrawAmount;
						as.updateAccountValue(account, finalAmount);
						accs = as.getAllAccounts();
						break;
					}
					else
					{
						System.out.println("That is not a valid amount.");
						System.out.println("Please enter an amount between 0 and " + account.getAccountValue() + ".");
					}
				} 
				catch (NumberFormatException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		else
		{
			logger.info("User withdrew: " + withdrawAmount + " dollars from their account.");
			finalAmount = account.getAccountValue() - withdrawAmount;
			as.updateAccountValue(account, finalAmount);
			accs = as.getAllAccounts();
			//account.setAccountValue(account.getAccountValue() - withdrawAmount);
		}
		logger.info("User now has: " + account.getAccountValue() + " dollars from their account.");
		System.out.println("Amount in the account after deposit: $" + account.getAccountValue() + "\n");
		//displayAccountGroup(accs);
		login();
	}
	
	/**
	 * Used when an account, that is passed in as a parameter, tries to deposit into their account.
	 * After entering a valid amount, it sends them to their askUserInput() menu page.
	 * @param depositAmount
	 * @param account
	 */
	public void userDeposit(int depositAmount, Account account)
	{
		System.out.println("Amount in the account before deposit: $" + account.getAccountValue());
		//account.setAccountValue(account.getAccountValue() + depositAmount);
		int newTotal = account.getAccountValue() + depositAmount;
		as.updateAccountValue(account, newTotal);
		accs = as.getAllAccounts();
		logger.info("User now has: " + account.getAccountValue() + " dollars from their account.");
		System.out.println("Amount in the account after deposit: $" + account.getAccountValue() + "\n");
		//displayAccountGroup(accs);
		login();
	}
	
}
