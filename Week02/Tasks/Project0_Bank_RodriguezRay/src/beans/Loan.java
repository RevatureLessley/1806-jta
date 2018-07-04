package beans;

import java.io.Serializable;

public class Loan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6042356402305866178L;
	
	public int id;
	float interestRate;
	float apr;
	double oriFee;
	int loanTerm;
	double loanAmount;
	boolean approved;
	
	public Loan(int id, float interestRate, float apr, double oriFee, int loanTerm, double loanAmount, boolean approved) {
		this.id = id;
		this.interestRate = interestRate;
		this.apr = apr;
		this.oriFee = oriFee;
		this.loanTerm = loanTerm;
		this.loanAmount = loanAmount;
		this.approved = approved;
	}
	
	public Loan(double loanAmount, int loanTerm) {
		this.interestRate = 0.0f;
		this.apr = 0.0f;
		this.oriFee = 0.0f;
		this.loanTerm = loanTerm;
		this.loanAmount = loanAmount;
		approved=false;
	}
	
	public void DisplayLoanDetails() {
		System.out.format("%-12d|%-15f|%-14f|%-12f|%-12d|%-9b"
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	public float getApr() {
		return apr;
	}

	public void setApr(float apr) {
		this.apr = apr;
	}

	public double getOriFee() {
		return oriFee;
	}

	public void setOriFee(double oriFee) {
		this.oriFee = oriFee;
	}

	public int getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}
	
	
}
