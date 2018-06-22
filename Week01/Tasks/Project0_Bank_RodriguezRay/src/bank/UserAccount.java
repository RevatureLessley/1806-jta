package bank;

import java.util.ArrayList;
import java.util.List;

public class UserAccount extends Account{
	List<Loan> loans = new ArrayList<Loan>();
	double balance;
	boolean banned;
	
	public UserAccount(String accType, String fName, String lName, String userName, String password, double balance) {
		super(accType, fName, lName, userName, password);
		this.balance = balance;
		this.banned = false;
	}
	
	public UserAccount(String accType, String fName, String lName, String userName, String password) {
		super(accType, fName, lName, userName, password);
		this.banned = false;
	}
	
	public UserAccount(String userName, String password) {
		super(userName, password);
	}
	
	public void DisplayBalance() {
		System.out.println("Balance: "+balance+"\n");
	}
	
	public void DisplayAccDetails() {
		super.DisplayAccDetails();
		System.out.format("%-10f%|%-9b|", balance, banned);
		System.out.println();
	}
	
	public void Withdrawal(double amount) {
		if (balance >= amount)
		{
			System.out.println("Withdawing...\n");
			System.out.println("Previous Balance: "+balance+"\n");
			balance -= amount;
			System.out.println("New Balance: "+balance+"\n");
		} else {
			System.out.println("Insufficient funds!\n");
		}
	}
	
	public void Deposit(double amount) {
		System.out.println("Depositing...\n");
		System.out.println("Previous Balance: "+balance+"\n");
		balance += amount;
		System.out.println("New Balance: "+balance+"\n");
	}
	
	public void ApplyForLoan(double loanAmount, int loanTerm) {
		Loan loan = new Loan(loanAmount, loanTerm);
		loans.add(loan);
		System.out.println("Loan submitted for approval.\n");
	}
	
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
}
