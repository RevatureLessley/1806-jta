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
		System.out.format("%-12s %-30s %s %s %s", );
		System.out.format("Loan ID: %-4d\nLoan Amount: %-30f\nInterest Rate: %-12f\nAPR: %-12f\nLoan Term: %-4d\nApproved: %b\n"
						 , id, loanAmount, interestRate, apr, loanTerm, approved);
	}
}
