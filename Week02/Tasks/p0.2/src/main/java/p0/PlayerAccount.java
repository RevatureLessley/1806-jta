package p0;

import p0.beans.Account;
import p0.beans.Player;
import p0.service.PlayerService;

public class PlayerAccount extends AccountClass
{
	private Player pAcc;
	
	/**
	 * Constructor class for the Player object, uses the super constructor to store 
	 * more general Account information such as name, uName, and pword.
	 * @param aBal the amount of money the player has on hand
	 * @param pBal the amount of money in the players back account
	 * @param lBal the value of the players loan
	 */
	public PlayerAccount(int pid, int aid, int aBal, int bBal, int lBal, boolean hasL, boolean lWaiting, boolean active, String name, String uName, String pword)
	{
		pAcc = new Player(pid, aid, aBal,lBal, bBal, hasL, lWaiting, active, name, uName, pword);
	}
	

	/**
	 * Informs the user about their on hand balance and bank balance, allowing them to take money
	 * from their bank into their personal balance
	 */
	public void withdraw()
	{

		pgm.dumpIn(pgm);
		pgm.clearScreen();
		System.out.println("You have chosen to withdraw gold. \n");
		if(pAcc.getAccountBalance() >0)
		{
			System.out.println("You currently have " + pAcc.getAccountBalance() + " Gold" + "\n" +
								"How much would you like to withdraw?");
			int ammount = pgm.in.nextInt();
			if(ammount > 0 && ammount < pAcc.getAccountBalance() * 1.50)
			{
				pAcc.setAccountBalance(pAcc.getAccountBalance()+ammount);
				pAcc.setBankBalance(pAcc.getBankBalance()-ammount);
			}
			if (pAcc.getAccountBalance() < 0)
			{
				System.out.println("Overdraft occured, pentalty applied");
				pAcc.setAccountBalance((int)(pAcc.getAccountBalance() *1.25));
			}
		}
		else
		{
			System.out.println("You are currently overdrawn by " + pAcc.getAccountBalance());
			System.out.println("Please make a deposit before attempting to withdraw again.");
		}
			
	}

	/**
	 * Informs the user about their on hand balance and bank balance, allowing them to take money
	 * from their personal balance and put it into their bank
	 */
	public void deposit()
	{
		pgm.dumpIn(pgm);
		pgm.clearScreen();
		System.out.println("You have chosen to deposit gold.");
		System.out.println("How much would you like to deposit?");
		int ammount = pgm.in.nextInt();
		if(ammount > 0 && ammount <= pAcc.getAccountBalance())
		{
			pAcc.setAccountBalance(pAcc.getAccountBalance()-ammount);
			pAcc.setBankBalance(pAcc.getBankBalance()-ammount);
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
		pgm.dumpIn(pgm);
		pgm.clearScreen();
		//TODO: improve how the computer selects winning, losing and odds.
		int bet = 0;
		System.out.print("Welcome to the Arena, how much would you like to bet on today's fight: ");
		boolean valid = false;
		while(!valid)
		{
			bet = pgm.in.nextInt();
			if(bet > pAcc.getAccountBalance())
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
				pAcc.setAccountBalance(pAcc.getAccountBalance()-bet);
				valid = true;
				this.incrementTime();
				System.out.println("Please enjoy the fight and good luck...");
				System.out.println();

				int win = pgm.rng.nextInt(2);
				if(win == 0)
				{
					System.out.println("Congradulations on your win, here is your reward of " + bet + " gold.");
					pAcc.setAccountBalance(pAcc.getAccountBalance()+(bet*2));
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
		pgm.dumpIn(pgm);
		pgm.clearScreen();
		System.out.print("How much money would you like to borrow: ");
		pAcc.setLoanBalance(pgm.in.nextInt());
		pAcc.setLoanWaiting(true);
		System.out.println("\n Your loan application has been submitted for approval");
	}
	
	/**
	 * Player selects a given amount from their personal holdings to pay towards their loan,
	 * if the paid amount exceeds the remaining loan balance then the loan is closed out and 
	 * no longer charges the player interest.
	 */
	public void payLoan()
	{
		pgm.dumpIn(pgm);
		pgm.clearScreen();
		System.out.println("You have chosen to pay on your " + pAcc.getLoanBalance() + " gold loan.");
		System.out.println("You have " + pAcc.getAccountBalance() + " gold, how much would you like to pay?");
		int ammount = pgm.in.nextInt();
		if(ammount > 0 && ammount <= pAcc.getAccountBalance())
		{
			if(ammount > pAcc.getLoanBalance())
			{
				ammount = pAcc.getLoanBalance();
			}
			pAcc.setLoanBalance(pAcc.getLoanBalance() - ammount);
			pAcc.setAccountBalance(pAcc.getAccountBalance()-ammount);
		}
		if(pAcc.getLoanBalance() == 0)
		{
			System.out.println("Congradulations on paying off your loan, we hope to see you again soon.");
			pAcc.setHasLoan(false);
			System.out.println();
		}
		else
		{
			System.out.println("Your current loan balance is now " + pAcc.getLoanBalance() + " we will be seeing you again soon.");
			System.out.println();
		}
			
	}
	
	/**
	 * method is called any time that interest should be applied, it increments both the 
	 * bank and loan balances by set amounts and sets the users balances to reflect such.
	 */
	public void incrementTime()
	{
		if(pAcc.getAccountBalance() >0)
		{
			pAcc.setAccountBalance((int)(pAcc.getAccountBalance() * pgm.Accounts.getBanker().getBankInfo().getInterest()));
		}
		if(pAcc.getLoanBalance() > 0 && pAcc.isHasLoan()) 
		{
			pAcc.setLoanBalance((int)(pAcc.getLoanBalance() * pgm.Accounts.getLoaner().getLoanerInfo().getInterest())); 
		}
	}
	
	/**
	 * method shared by each Account extender, view comment on parent class for explanation.
	 */
	@Override
	public void menu()
	{
		pgm.dumpIn(pgm);
		pgm.clearScreen();
		int selection = 0;
		System.out.println("Welcome " + pAcc.getAccountInfo().getName()+ "\n");
		
		while(selection != 5)
		{
			if(pAcc.isLoanWaiting()) {
				System.out.println("This account is waiting for a loan");
			}
			System.out.println("Gold: " + pAcc.getAccountBalance());
			System.out.println("Bank balance: " + pAcc.getBankBalance());
			if(pAcc.isHasLoan())
				System.out.println("Loan balance: " + pAcc.getLoanBalance());
			int count = 1;
			System.out.println("What would you like to do today?");

			System.out.println(count + ". Bet on Arena fight");
			count++;
			if(pAcc.isHasLoan())
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
			selection = pgm.in.nextInt();
			switch (selection) {
			case 1: this.bet();
					break;
			case 2: if(pAcc.isHasLoan())
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
			}
		}
	}
	
	@Override
	public Account getAccount() {
		return pAcc.getAccountInfo();
	}
	
	@Override
	public void logout() {
		update();
		System.out.println("Farewell " + pAcc.getAccountInfo().getName() + " we hope to see you again soon.");
		System.out.println();
	}
	
	public void update() {
		PlayerService ps = new PlayerService();

		Account tempA = pAcc.getAccountInfo();
		PlayerAccount temp = new PlayerAccount(pAcc.getPlayerID(),pAcc.getAccountID(), pAcc.getAccountBalance(), 
				pAcc.getBankBalance(), pAcc.getLoanBalance(), pAcc.isHasLoan(), pAcc.isLoanWaiting(), true,
				tempA.getName(), tempA.getuName(),tempA.getuPass());
		ps.updatePlayer(temp);
	}
	/*
	 * General getters/setters bellow this point
	 */

	public Player getPlayerInfo() {
		return pAcc;
	}
	
}
