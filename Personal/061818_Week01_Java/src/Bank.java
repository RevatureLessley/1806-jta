import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bank {
	
	public Bank()
	{
		
	}
	
	public void askUserInput() throws NumberFormatException, IOException 
	{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("What would you like to do?: ");
		String userOption = bufferedReader.readLine();
		System.out.println(userOption);
		if ( userOption.equals("withdraw") )
		{
			System.out.println("How much would you like to withdraw?: ");
			int withdrawAmount = Integer.parseInt(bufferedReader.readLine());
			userWithdraw(withdrawAmount);
		}
		else if ( userOption.equals("deposit") )
		{
			System.out.println("How much would you like to deposit?: ");
			int depositAmount = Integer.parseInt(bufferedReader.readLine());
			userDeposit(depositAmount);
		}
		else
		{
			do
			{
				System.out.println("Not a valid command, enter \"deposit\" or \"withdraw\": ");
				userOption = bufferedReader.readLine();
				if ( userOption.equals("withdraw") )
				{
					System.out.println("How much would you like to withdraw?: ");
					int withdrawAmount = Integer.parseInt(bufferedReader.readLine());
					userWithdraw(withdrawAmount);
				}
				else if ( userOption.equals("deposit") )
				{
					System.out.println("How much would you like to deposit?: ");
					int depositAmount = Integer.parseInt(bufferedReader.readLine());
					userDeposit(depositAmount);
				}
			}
			while ( !userOption.equals("withdraw") || !userOption.equals("deposit") );
		}
	}
	
	public int userWithdraw(int withdrawAmount)
	{
		return 0;
	}
	
	public int userDeposit(int depositAmount)
	{
		return 0;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		Bank testBank = new Bank();
		testBank.askUserInput();
	}
}
