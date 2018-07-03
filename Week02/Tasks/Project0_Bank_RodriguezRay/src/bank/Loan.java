package bank;

import java.io.Serializable;

public class Loan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6042356402305866178L;
	
	public static int id = 1000;
	float interestRate;
	float apr;
	double oriFee;
	int loanTerm;
	double loanAmount;
	boolean approved;
	
	public Loan(double loanAmount, int loanTerm) {
		this.interestRate = 0.0f;
		this.apr = 0.0f;
		this.oriFee = 0.0f;
		this.loanTerm = loanTerm;
		this.loanAmount = loanAmount;
		approved=false;
		id++;
	}
	
	public void DisplayLoanDetails() {
		System.out.format("%-12d|%-15f|%-14f|%-12f|%-12d|%-9b\n\n"
						 , id, loanAmount, interestRate, apr, loanTerm, approved);
	}
	
	public boolean isApproved() {
		return approved;
	}
	
	public void setApproved(boolean val) {
		approved = val;
	}
	
	public double getAmount() {
		return loanAmount;
	}
}
