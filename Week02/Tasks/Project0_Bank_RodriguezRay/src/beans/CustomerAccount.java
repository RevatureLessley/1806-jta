package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bank.Driver;

public class CustomerAccount extends Account implements Serializable{
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
	
	public CustomerAccount(int accNum, String accType, String fName, String lName, String userName, 
			String password, double balance, boolean approved, boolean banned) {
		super(accNum, accType, fName, lName, userName, password);
		this.balance = balance;
		this.banned = banned;
		this.approved = approved;
	}
	
	public CustomerAccount(int accNum, String accType, String fName, String lName, String userName, 
			String password, double balance) {
		super(accNum, accType, fName, lName, userName, password);
		this.balance = balance;
		this.banned = false;
		this.approved = false;
	}
	
	public CustomerAccount(int accNum, String accType, String fName, String lName, String userName, String password) {
		super(accNum, accType, fName, lName, userName, password);
		this.banned = false;
		this.approved = false;
	}
	
	public CustomerAccount(String accType, String fName, String lName, String userName, String password) {
		super(accType, fName, lName, userName, password);
		this.banned = false;
		this.approved = false;
	}
	
	public CustomerAccount(String userName, String password) {
		super(userName, password);
	}
	
	public CustomerAccount() {
		super();
	}
	
	public Boolean isApproved() {
		return approved;
	}
	
	public Boolean isBanned() {
		return banned;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double amt) {
		balance = amt;
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
		System.out.format("|%-15s|%-12s|%-20s|%-20s|%-15s|%-9s|%-9s|\n", "Account Number", "Account Type", "Fist Name", "Last Name", "Balance", "Banned", "Approved");
		System.out.println("----------------------------------------------------------------------------------------------------------------");
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
		Driver.as.insertLoan(loan, this.accNum);
		System.out.println("Loan submitted for approval.\n");
	}
	
	/**
	 * displays the details of all the loans the user has
	 */
	public void DisplayLoansDetails() {
		loans = Driver.as.selectAllLoansByAccId(this.accNum);
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
