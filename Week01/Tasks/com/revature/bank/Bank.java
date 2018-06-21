package com.revature.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Bank {
	private ArrayList<Account> accs = null;
	private int activeAccount = 999;
	private int counter;
	private static int accountsAmount = 2;
	private String registered;
	private static final int USER = 0;
	private static final int ADMIN = 1;
	
	public Bank(ArrayList<Account> accs)
	{
		this.accs = accs;
	}
	
	public void register()
	{
		accountsAmount++;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try 
		{
			System.out.println("What username would you like to use?: ");
			String newUsername = bufferedReader.readLine();
			System.out.println("What password would you like to use?: ");
			String newPassword = bufferedReader.readLine();
			Account newAccount = new Account(newUsername, newPassword, accountsAmount, 0, USER);
			accs.add(newAccount);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Thank you for registering, now please login.\n");
	}
	
	public void login()
	{
		activeAccount = 999;
		System.out.println("Welcome to the bank, do you have an account? (Yes/No): ");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			String registered = bufferedReader.readLine().toLowerCase();
			if ( registered.equals("no") )
			{
				System.out.println("Please register an account.");
				register();
			}
			else
			{
				System.out.println("Are you a normal user(0) or an admin(1)?: ");
				int enteredAccountType = Integer.parseInt(bufferedReader.readLine());
				if ( enteredAccountType == 0 ) 
				{
					userLogin();
				}
				else if ( enteredAccountType == 1 )
				{
					adminLogin();
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void userLogin()
	{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		try 
		{
			System.out.println("Please enter your username: ");
			String enteredAccountName;
			enteredAccountName = bufferedReader.readLine();
			for ( int i = 0; i < accs.size(); i++ )
			{
				if ( accs.get(i).getUserName().equals(enteredAccountName))
				{
					activeAccount = i;
				}
			}
			while ( activeAccount == 999 )
			{
				System.out.println("Not a valid user account. Try again.");
				enteredAccountName = bufferedReader.readLine();
				for (counter = 0; counter < accs.size(); counter++ )
				{
					if ( accs.get(counter).getUserName().equals(enteredAccountName))
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
			
			System.out.println("Successfully logged in.");
			askUserInput(accs.get(activeAccount));
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}	
	}
	
	public void adminLogin()
	{
		
	}
	
	public void askUserInput(Account account) 
	{
		while(true)
		{
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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
					System.out.println("You have been logged out.\n");
					login();
				}
				else
				{
					while ( true )
					{
						System.out.println("Not a valid command, enter \"deposit\" or \"withdraw\": ");
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
	
	public void userWithdraw(int withdrawAmount, Account account)
	{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Amount in the account before withdraw: $" + account.getAccountValue()); 
		float finalAmount = account.getAccountValue() - withdrawAmount;
		if ( finalAmount < 0 )
		{
			System.out.println("You cannot withdraw that much.");
			System.out.println("Please enter an amount between 0 and " + account.getAccountValue() + ".");
			while(true)
			{
				try 
				{
					withdrawAmount = Integer.parseInt(bufferedReader.readLine());
					if ( withdrawAmount >= 0 && withdrawAmount <= account.getAccountValue() )
					{
						account.setAccountValue(account.getAccountValue() - withdrawAmount);
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
			account.setAccountValue(account.getAccountValue() - withdrawAmount);
		}
		System.out.println("Amount in the account after deposit: $" + account.getAccountValue() + "\n");
	}
	
	public void userDeposit(int depositAmount, Account account)
	{
		System.out.println("Amount in the account before deposit: $" + account.getAccountValue());
		account.setAccountValue(account.getAccountValue() + depositAmount);
		System.out.println("Amount in the account after deposit: $" + account.getAccountValue() + "\n");
	}
	
	public static void main(String[] args)
	{
		ArrayList<Account> accs = new ArrayList<>();
		Account a1 = new Account("Logan", "Brewer", 1, 0, ADMIN);
		Account a2 = new Account("Test", "Guy", 2, 100, USER);
		accs.add(a1);
		accs.add(a2);
		
		Bank testBank = new Bank(accs);
		testBank.login();
	}
}
