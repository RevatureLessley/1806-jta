import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class Bank {
	
	// amount in the account
	private int finalAmount;
	// filename for storing accounts
	private String fileName = "accounts.txt";
	// hashtable that holds the list of accounts
	private Hashtable<String, String> accounts = new Hashtable<String, String>();
	
	// constructor that initializes the account to what is chosen on run
	public Bank(int starterAmount)
	{
		this.finalAmount = starterAmount;
	}
	
	// register an account in the hashtable and write the username/password to the file
	public void register() throws IOException
	{
		// setup the file writing and the reader for user input
		FileWriter fileWriter = new FileWriter(fileName);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// get the users input
		System.out.println("Enter the username you want to use: ");
		String userAccountName = bufferedReader.readLine();
		System.out.println("Enter the password you want to use: ");
		String userPassword = bufferedReader.readLine();
		// add the users input into the hashtable
		accounts.put(userAccountName, userPassword);
		// add the users input into the file
		bufferedWriter.write(userAccountName);
		bufferedWriter.write(" ");
		bufferedWriter.write(userPassword);
		bufferedWriter.newLine();
		System.out.println("Succesfully registered.");
	}
	
	// log the user in if they have a matching username/password
	// if they fail, send them to register
	public void login() throws IOException
	{
		System.out.println("Welcome to the bank, please login to continue.");
		// set up the reader for user input
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// get user input 
		System.out.println("Please enter your username: ");
		String enteredAccountName = bufferedReader.readLine();
		
		// if the entered name is in the hashtable, continue
		if ( accounts.containsKey(enteredAccountName) )
		{
		}
		// if the entered name isn't in the hashtable, make them register
		else
		{
			System.out.println("That username does not exist.");
			System.out.println("Please register an account.");
			// send user to register method
			register();
		}
		// get user input
		System.out.println("Please enter your username to login: ");
		enteredAccountName = bufferedReader.readLine();
		System.out.println("Please enter your password: ");
		String enteredPassword = bufferedReader.readLine();
		// if password exists, continue
		if ( accounts.containsValue(enteredPassword) )
		{
		}
		// else keep prompting them for the correct password
		else
		{
			System.out.println("Wrong password. Try again");
			// allow user to enter their password again
			enteredPassword = bufferedReader.readLine();
			// loop to continue until they enter the right password
			while(true)
			{
				// if they enter the right password, break out of the while loop
				if ( accounts.containsValue(enteredPassword) )
				{
					break;
				}
			}
		}
		// after entering the account name and password, send them to askUserInput
		if ( accounts.containsKey(enteredAccountName) && accounts.containsValue(enteredPassword) )
		{
			System.out.println("Successfully logged in.");
			askUserInput();
		}
	}
	
	// asking a logged in user what they would like to do
	public void askUserInput() throws NumberFormatException, IOException 
	{
		// keep running as long as they are logged in
		while(true)
		{
			// set up reader for user input
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("What would you like to do?(deposit, withdraw, logout): ");
			String userOption = bufferedReader.readLine();
			// if they enter "withdraw"
			if ( userOption.equals("withdraw") )
			{
				// ask user how much to withdraw
				System.out.println("How much would you like to withdraw?: ");
				int withdrawAmount = Integer.parseInt(bufferedReader.readLine());
				// send them to the userWithdraw method
				userWithdraw(finalAmount, withdrawAmount);
			}
			// if they enter "deposit"
			else if ( userOption.equals("deposit") )
			{
				// ask how much to deposit
				System.out.println("How much would you like to deposit?: ");
				int depositAmount = Integer.parseInt(bufferedReader.readLine());
				// send them to the userDeposit method
				userDeposit(finalAmount, depositAmount);
			}
			// if they enter "logout"
			else if ( userOption.equals("logout") )
			{
				System.out.println("You have been logged out.");
				// send them to the login menu
				login();
			}
			// if they enter anything other than "deposit", "withdraw" or "logout"
			else
			{
				// keep looping until they enter one of the 3 options
				while ( true )
				{
					System.out.println("Not a valid command, enter \"deposit\" or \"withdraw\": ");
					// get user input
					userOption = bufferedReader.readLine();
					// if they finally type withdraw, send them to userWithdraw and break out of the loop
					if ( userOption.equals("withdraw") )
					{
						System.out.println("How much would you like to withdraw?: ");
						int withdrawAmount = Integer.parseInt(bufferedReader.readLine());
						userWithdraw(finalAmount, withdrawAmount);
						break;
					}
					// if they finally type deposit, send them to userDeposit and break out of the loop
					else if ( userOption.equals("deposit") )
					{
						System.out.println("How much would you like to deposit?: ");
						int depositAmount = Integer.parseInt(bufferedReader.readLine());
						userDeposit(finalAmount, depositAmount);
						break;
					}
					// if they finally type logout, send them to login and break out of the loop
					else if ( userOption.equals("logout") )
					{
						System.out.println("You have been logged out.");
						login();
					}
				}
			}
		}
	}
	
	// used to calculate account balance after the user chooses an amount to withdraw
	public int userWithdraw(int accountAmount, int withdrawAmount) throws NumberFormatException, IOException
	{
		// setup reader for user input
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Amount in the account before withdraw: $" + accountAmount);
		// check the amount that will be in the account 
		finalAmount = accountAmount - withdrawAmount;
		// if the amount is less than 0
		if ( finalAmount < 0 )
		{
			// tell user they cant take out that much
			System.out.println("You cannot withdraw that much.");
			// prompt them to enter a value between 0 and the amount in their account
			System.out.println("Please enter an amount between 0 and " + accountAmount + ".");
			// loop until they enter a value between 0 and the amount in their account
			while(true)
			{
				withdrawAmount = Integer.parseInt(bufferedReader.readLine());
				// if they enter the right amount
				// break out of the loop and calculate the new amount thatll be in their account
				if ( withdrawAmount >= 0 && withdrawAmount <= accountAmount )
				{
					finalAmount = accountAmount - withdrawAmount;
					break;
				}
				// else keep asking for them to enter a correct amount
				else
				{
					System.out.println("That is not a valid amount.");
					System.out.println("Please enter an amount between 0 and " + accountAmount + ".");
				}
			}
		}
		// print out the new amount that is in their account
		System.out.println("Amount in the account after deposit: $" + finalAmount);
		return finalAmount;
	}
	
	// used to calculate the difference in the users account after depositing
	public int userDeposit(int accountAmount, int depositAmount)
	{
		// print the amount of money in the account before the deposit goes through
		System.out.println("Amount in the account before deposit: $" + accountAmount);
		// calculate the new amount
		finalAmount = accountAmount + depositAmount;
		// print the amount of money in the account after the deposit goes through
		System.out.println("Amount in the account after deposit: $" + finalAmount);
		return finalAmount;
	}
	
	// main
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		Bank testBank = new Bank(100);
		testBank.login();
	}
}
