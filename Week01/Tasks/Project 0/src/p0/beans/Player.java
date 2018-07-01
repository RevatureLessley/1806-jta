package p0.beans;

public class Player {
	private int accountBalance;
	private int loanBalance;
	private int personalBalance;
	private boolean hasLoan = false;
	private boolean loanWaiting = false;
	public int getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}
	public int getLoanBalance() {
		return loanBalance;
	}
	public void setLoanBalance(int loanBalance) {
		this.loanBalance = loanBalance;
	}
	public int getPersonalBalance() {
		return personalBalance;
	}
	public void setPersonalBalance(int personalBalance) {
		this.personalBalance = personalBalance;
	}
	public boolean isHasLoan() {
		return hasLoan;
	}
	public void setHasLoan(boolean hasLoan) {
		this.hasLoan = hasLoan;
	}
	public boolean isLoanWaiting() {
		return loanWaiting;
	}
	public void setLoanWaiting(boolean loanWaiting) {
		this.loanWaiting = loanWaiting;
	}
	
	
}
