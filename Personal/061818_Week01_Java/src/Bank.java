import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Hashtable;

public class Bank {
	
	private int finalAmount;
	
	private Hashtable<String, String> accounts = new Hashtable<String, String>();
	
	public Bank(int starterAmount)
	{
		this.finalAmount = starterAmount;
	}
	
	public void register() throws IOException
	{
		PrintWriter writer = new PrintWriter("accounts.txt", "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the username you want to use: ");
		String userAccountName = bufferedReader.readLine();
		System.out.println("Enter the password you want to use: ");
		String userPassword = bufferedReader.readLine();
		writer.println(userAccountName + " " + userPassword);
		writer.close();
		accounts.put(userAccountName, userPassword);
		System.out.println("Succesfully registered.");
		
	}
	
	public void login() throws IOException
	{
		System.out.println("Welcome to the bank, please login to continue.");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter your username: ");
		String enteredAccountName = bufferedReader.readLine();
		BufferedReader br = new BufferedReader(new FileReader("accounts.txt"));
		
		System.out.println("reading from accounts.txt");
		while(br.readLine() != null)
		{
			System.out.println(br.readLine());
		}
		
		if ( accounts.containsKey(enteredAccountName) )
		{
		}
		else
		{
			System.out.println("That username does not exist.");
			System.out.println("Please register an account.");
			register();
		}
		System.out.println("Please enter your username to login: ");
		enteredAccountName = bufferedReader.readLine();
		System.out.println("Please enter your password: ");
		String enteredPassword = bufferedReader.readLine();
		if ( accounts.containsValue(enteredPassword) )
		{
		}
		else
		{
			System.out.println("Wrong password. Try again");
			enteredPassword = bufferedReader.readLine();
			while(true)
			{
				if ( accounts.containsValue(enteredPassword) )
				{
					break;
				}
			}
		}
		if ( accounts.containsKey(enteredAccountName) && accounts.containsValue(enteredPassword) )
		{
			System.out.println("Successfully logged in.");
			askUserInput();
		}
	}
	
	public void askUserInput() throws NumberFormatException, IOException 
	{
		while(true)
		{
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("What would you like to do?(deposit, withdraw, logout): ");
			String userOption = bufferedReader.readLine();
			System.out.println(userOption);
			if ( userOption.equals("withdraw") )
			{
				System.out.println("How much would you like to withdraw?: ");
				int withdrawAmount = Integer.parseInt(bufferedReader.readLine());
				userWithdraw(finalAmount, withdrawAmount);
			}
			else if ( userOption.equals("deposit") )
			{
				System.out.println("How much would you like to deposit?: ");
				int depositAmount = Integer.parseInt(bufferedReader.readLine());
				userDeposit(finalAmount, depositAmount);
			}
			else if ( userOption.equals("logout") )
			{
				System.out.println("You have been logged out.");
				login();
			}
			else
			{
				while ( true )
				{
					System.out.println("Not a valid command, enter \"deposit\" or \"withdraw\": ");
					userOption = bufferedReader.readLine();
					if ( userOption.equals("withdraw") )
					{
						System.out.println("How much would you like to withdraw?: ");
						int withdrawAmount = Integer.parseInt(bufferedReader.readLine());
						userWithdraw(finalAmount, withdrawAmount);
						break;
					}
					else if ( userOption.equals("deposit") )
					{
						System.out.println("How much would you like to deposit?: ");
						int depositAmount = Integer.parseInt(bufferedReader.readLine());
						userDeposit(finalAmount, depositAmount);
						break;
					}
					else if ( userOption.equals("logout") )
					{
						System.out.println("You have been logged out.");
						login();
					}
				}
			}
		}
	}
	
	public int userWithdraw(int accountAmount, int withdrawAmount) throws NumberFormatException, IOException
	{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Amount in the account before withdraw: $" + accountAmount);
		finalAmount = accountAmount - withdrawAmount;
		if ( finalAmount < 0 )
		{
			System.out.println("You cannot withdraw that much.");
			System.out.println("Please enter an amount between 0 and " + accountAmount + ".");
			while(true)
			{
				withdrawAmount = Integer.parseInt(bufferedReader.readLine());
				if ( withdrawAmount >= 0 && withdrawAmount <= accountAmount )
				{
					finalAmount = accountAmount - withdrawAmount;
					break;
				}
				else
				{
					System.out.println("That is not a valid amount.");
					System.out.println("Please enter an amount between 0 and " + accountAmount + ".");
				}
			}
		}
		System.out.println("Amount in the account after deposit: $" + finalAmount);
		return finalAmount;
	}
	
	public int userDeposit(int accountAmount, int depositAmount)
	{
		System.out.println("Amount in the account before deposit: $" + accountAmount);
		finalAmount = accountAmount + depositAmount;
		System.out.println("Amount in the account after deposit: $" + finalAmount);
		return finalAmount;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		Bank testBank = new Bank(100);
		testBank.login();
	}
}
