package p0;

import java.io.Serializable;
import java.util.ArrayList;

public class Loaner extends Account implements Serializable
{
	private int balance;
	private double interestRate;
	
	public Loaner(String name, String uname, String pword, Launch pgm)
	{
		super(name, uname, pword, pgm);
	}
	
	public void manageAactiveLoans()
	{
		int selection = 0;
		
		while(selection != -1)
		{
			if(pgm.Waiting.getList().size() >0)
			{
				int count = 1;
				for(Account a: pgm.Waiting.getList())
				{
					System.out.println(count + ". " + a.getuName());
					count++;
				}
				System.out.print("\n Select an account to manage or -1 to exit: ");
				selection = pgm.in.nextInt();
				Player temp = (Player)pgm.Waiting.getList().get(selection-1);
				int selection2 = 0;
				count = 1;
				System.out.println("Account info");
				System.out.println("User Name: " + temp.getuName());
				System.out.println("Loan Ammount: " + temp.getlBalance());
				System.out.println("");
				System.out.println(count + ". Approve account");
				count++;
				System.out.println(count + ". Decline account");
				count++;
				System.out.println(count + ". Decide later");
				int selection3 = pgm.in.nextInt();
				switch (selection3)
				{
				case 1: temp.setLoanWaiting(false);
						temp.setHasLoan(true);
						break;
				case 2: temp.setLoanWaiting(false);
				case 3: break;
				default: System.out.println("Invalid choice, try again.");
				}
			}
			else
			{
				System.out.println("No waiting accounts at this moment.");
				selection = -1;
			}
			pgm.save(pgm);
		}
	}
	
	public void manageWaitingLoans()
	{
		int selection = 0;
		
		while(selection != -1)
		{
			if(pgm.Waiting.getList().size() >0)
			{
				int count = 1;
				int count2 = 1;
				for(Player p: pgm.Waiting.getList())
				{
					if(p.getLoanWaiting())
					{
						System.out.println(count + ". " + p.getuName());
						count++;
					}
				}
				System.out.print("\n Select an account to manage or -1 to exit: ");
				selection = pgm.in.nextInt();
				Player temp = (Player)pgm.Waiting.getList().get(selection-1);
				int selection2 = 0;
				count = 1;
				System.out.println("Account info");
				System.out.println("User Name: " + temp.getuName());
				System.out.println("Loan Ammount: " + temp.getlBalance());
				System.out.println("");
				System.out.println(count + ". Approve account");
				count++;
				System.out.println(count + ". Decline account");
				count++;
				System.out.println(count + ". Decide later");
				int selection3 = pgm.in.nextInt();
				switch (selection3)
				{
				case 1: temp.setLoanWaiting(false);
						temp.setHasLoan(true);
						break;
				case 2: temp.setLoanWaiting(false);
				case 3: break;
				default: System.out.println("Invalid choice, try again.");
				}
			}
			else
			{
				System.out.println("No waiting accounts at this moment.");
				selection = -1;
			}
			pgm.save(pgm);
		}
	}
	
	
	public double getInterest()
	{
		return interestRate;
	}
	
	@Override
	public void menu() {
		// TODO Auto-generated method stub
		
	}

}
