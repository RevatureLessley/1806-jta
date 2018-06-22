package bank;

public class Loan {
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
}
