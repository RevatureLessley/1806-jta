package bank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAccount extends Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1014500256126988742L;
	List<Loan> loans = new ArrayList<Loan>();
	final int MAX_WITHDRAWALS_PER_DAY = 3;
	int withdrawalCnt = 0;
	double balance;
	boolean approved;
	boolean banned;
	
	public UserAccount(String accType, String fName, String lName, String userName, String password, double balance) {
		super(accType, fName, lName, userName, password);
		this.balance = balance;
		this.banned = false;
		this.approved = false;
	}
	
	public UserAccount(String accType, String fName, String lName, String userName, String password) {
		super(accType, fName, lName, userName, password);
		this.banned = false;
		this.approved = false;
	}
	
	public UserAccount(String userName, String password) {
		super(userName, password);
	}
	
	public UserAccount() {
		super();
	}
	
	/**
	 * Displays the balance of the account to the console
	 * @return double balance
	 */
	public double DisplayBalance() {
		System.out.println("Balance: "+balance+"\n");
		return balance;
	}
	
	/**
	 * Displays the account details to the console
	 */
	public void DisplayAccDetails() {
		super.DisplayAccDetails();
		System.out.format("%-15f|%-9b|%-9b|", balance, banned, approved);
		System.out.println();
	}
	
	/**
	 * Withdrawals ammount from the user's balance if there is enough balance to cover the withdraw
	 * @param amount holds the amount to be withdrawn
	 * @return
	 */
	public double Withdrawal(double amount) {
		if (balance >= amount)
		{
			System.out.println("Withdawing...\n");
			System.out.println("Previous Balance: "+balance+"\n");
			balance -= amount;
			
			withdrawalCnt++;
			
			System.out.println("New Balance: "+balance+"\n");
		} else {
			System.out.println("Insufficient funds!\n");
			Driver.logger.error("insufficient funds");
		}
		return balance;
	}
	
	/**
	 * Deposits ammount to the user's balance
	 * @param amount is the amount to be deposited
	 * @return the balance for JUnit testing
	 */
	public double Deposit(double amount) {
		System.out.println("Depositing...\n");
		System.out.println("Previous Balance: "+balance+"\n");
		balance += amount;
		System.out.println("New Balance: "+balance+"\n");
		return balance;
	}
	
	/**
	 * Submits a loan to the user's loan list to be later approved by a banker account
	 * @param loanAmount the amount to be loaned
	 * @param loanTerm the amount of time in months for the loan
	 */
	public void ApplyForLoan(double loanAmount, int loanTerm) {
		Loan loan = new Loan(loanAmount, loanTerm);
		loans.add(loan);
		System.out.println("Loan submitted for approval.\n");
	}
	
	/**
	 * displays the details of all the loans the user has
	 */
	public void DisplayLoansDetails() {
		if (loans.isEmpty()) {
			System.out.println("No loans.");
			return;
		}
		System.out.format("%-12s|%-15s|%-14s|%-12s|%-12s|%-9s", "Loan ID", "Loan Amount", 
				"Interest Rate", "APR", "Loan Term", "Approved\n");
		System.out.println("-------------------------------------------------------------"
				+ "----------------------");
		for(Loan loan : loans)
			loan.DisplayLoanDetails();
		System.out.println();
	}
	
	public int getAccNumber() {
		return accNum;
	}
	
	public List<Loan> getLoans() {
		return loans;
	}

	@Override
	public String toString() {
		return "UserAccount [loans=" + loans + ", MAX_WITHDRAWALS_PER_DAY=" + ", withdrawalCnt=" + withdrawalCnt + ", balance=" + balance 
				+ ", approved=" + approved + ", banned=" + banned + ", accType=" + accType + ", fName=" + fName + ", lName=" + lName + ", userName=" + userName
				+ ", password=" + password + "]";
	}
	
}