package p0;

import java.io.Serializable;

public class Player extends Account implements Serializable
{
	private int accountBalance;
	private int loanBalance;
	private int personalBalance;
	private boolean hasLoan = false;
	private boolean loanWaiting = false;
	
	/**
	 * Constructor class for the Player object, uses the super constructor to store 
	 * more general Account information such as name, uName, and pword.
	 * @param aBal the amount of money the player has on hand
	 * @param pBal the amount of money in the players back account
	 * @param lBal the value of the players loan
	 */
	public Player(String name, String uName, String pword, int aBal, int pBal, int lBal, Launch pgm)
	{
		super(name, uName,pword,pgm);
		this.accountBalance = aBal;
		this.personalBalance = pBal;
		this.loanBalance =lBal;
	}
	
	/**
	 * Informs the user about their on hand balance and bank balance, allowing them to take money
	 * from their bank into their personal balance
	 */
	public void withdraw()
	{
		System.out.println("You have chosen to withdraw gold.");
		if(accountBalance >0)
		{
			System.out.println("You currently have " + accountBalance + " Gold" + "\n" +
								"How much would you like to withdraw?");
			int ammount = pgm.in.nextInt();
			if(ammount > 0 && ammount < accountBalance * 1.50)
			{
				accountBalance -= ammount;
				personalBalance += ammount;
			}
			if (accountBalance < 0)
			{
				System.out.println("Overdraft occured, pentalty applied");
				accountBalance = (int)(accountBalance *1.25);
			}
		}
		else
		{
			System.out.println("You are currently overdrawn by " + accountBalance);
			System.out.println("Please make a deposit before attempting to withdraw again.");
		}
			
	}

	/**
	 * Informs the user about their on hand balance and bank balance, allowing them to take money
	 * from their personal balance and put it into their bank
	 */
	public void deposit()
	{
		System.out.println("You have chosen to deposit gold.");
		System.out.println("How much would you like to deposit?");
		int ammount = pgm.in.nextInt();
		if(ammount > 0 && ammount <= personalBalance)
		{
			accountBalance += ammount;
			personalBalance -= ammount;
		}
		else
		{
			System.out.println("Improper ammount, come back to try again.");
		}
	}
	
	/**
	 * Player makes a bet on a randomly generated fight, if they win their bet is doubled and 
	 * returned to them, if they lose then the money is kept and they are sent back to their 
	 * personal menu
	 */
	public void bet()
	{	
		//TODO: improve how the computer selects winning, losing and odds.
		int bet = 0;
		System.out.print("Welcome to the Arena, how much would you like to bet on today's fight: ");
		boolean valid = false;
		while(!valid)
		{
			bet = pgm.in.nextInt();
			if(bet > personalBalance)
			{
				System.out.println("You do not have enough gold for that, please try again: ");
			}
			else if(bet ==-1)
			{
				System.out.println("Have a nice day, we hope you'll come back soon.");
				break;
			}
			else if (bet >0)
			{
				personalBalance -=bet;
				valid = true;
				this.incrementTime();
				System.out.println("Please enjoy the fight and good luck...");
				System.out.println();

				int win = pgm.rng.nextInt(2);
				if(win == 0)
				{
					System.out.println("Congradulations on your win, here is your reward of " + bet + " gold.");
					personalBalance +=bet*2;
				}
				else
				{
					System.out.println("Sorry on your loss, please do come again.");
				}
			}
			else
			{
				System.out.println("That is not a valid bet, please try again: ");
			}

		}
	}
	
	/**
	 * Player sets themselves as a waiting candidate for a loan to be approved by the 
	 * Loaner account and make a request for how much of a loan they are giving.
	 */
	public void loanApp()
	{
		System.out.print("How much money would you like to borrow: ");
		loanBalance = pgm.in.nextInt();
		loanWaiting = true;
		System.out.println("\n Your loan application has been submitted for approval");
	}
	
	/**
	 * Player selects a given amount from their personal holdings to pay towards their loan,
	 * if the paid amount exceeds the remaining loan balance then the loan is closed out and 
	 * no longer charges the player interest.
	 */
	public void payLoan()
	{
		System.out.println("You have chosen to pay on your loan.");
		System.out.println("You have " + personalBalance + " gold, how much would you like to pay?");
		int ammount = pgm.in.nextInt();
		if(ammount > 0 && ammount <= personalBalance)
		{
			if(ammount > loanBalance)
			{
				ammount = loanBalance;
			}
			loanBalance -= ammount;
			personalBalance -= ammount;
		}
		if(loanBalance == 0)
		{
			System.out.println("Congradulations on paying off your loan, we hope to see you again soon.");
			hasLoan = false;
			System.out.println();
		}
		else
		{
			System.out.println("Your current loan balance is now " + loanBalance + " we will be seeing you again soon.");
			System.out.println();
		}
			
	}
	
	/**
	 * method is called any time that interest should be applied, it increments both the 
	 * bank and loan balances by set amounts and sets the users balances to reflect such.
	 */
	public void incrementTime()
	{
		if(accountBalance >0)
		{
			accountBalance = (int)(accountBalance * pgm.Active.getBanker().getInterest());
		}
		if(loanBalance > 0 && hasLoan) 
		{
			loanBalance = (int)(loanBalance * pgm.Active.getLoaner().getInterest());
		}
	}
	
	/**
	 * method shared by each Account extender, view comment on parent class for explanation.
	 */
	@Override
	public void menu()
	{
		int selection = 0;
		System.out.println("Welcome " + this.Name + "\n");
		while(selection != 5)
		{

			System.out.println("Gold: " + personalBalance);
			System.out.println("Bank balance: " + accountBalance);
			if(hasLoan)
				System.out.println("Loan balance: " + loanBalance);
			int count = 1;
			System.out.println("What would you like to do today?");

			System.out.println(count + ". Bet on Arena fight");
			count++;
			if(hasLoan)
			{
				System.out.println(count +". Pay on loan");
				count++;
			}
			else
			{
				System.out.println(count + ". Apply for loan");
				count++;
			}
			System.out.println(count + ". Withdraw");
			count++;
			System.out.println(count + ". Deposit");
			count++;
			System.out.println(count + ". Logout");
			count++;
			if(accountFlagged)
			{
				System.out.println(count + ". Change your mind");
				count++;
			}
			else
			{
				System.out.println(count + ". Close account");
				count++;
			}
			selection = pgm.in.nextInt();
			switch (selection) {
			case 1: this.bet();
					break;
			case 2: if(hasLoan)
						this.payLoan();
					else
						this.loanApp();
					break;
			case 3: withdraw();
					break;
			case 4: deposit();
					break;
			case 5: logout();
			break;
			case 6: if(!accountFlagged)
						deleteAcc();
					else
						saveAcc();
					break;
			}
		}
	}

	/*
	 * General getters/setters bellow this point
	 */
	public boolean getHasLoan()
	{
		return hasLoan;
	}
	
	public boolean getLoanWaiting()
	{
		return loanWaiting;
	}
	
	public void setLoanWaiting(boolean s)
	{
		loanWaiting = s;
	}

	public int getbBalance()
	{
		return accountBalance;
	}

	public void setHasLoan(boolean s)
	{
		hasLoan = s;
	}
	
	public int getlBalance()
	{
		return loanBalance;
	}

}
