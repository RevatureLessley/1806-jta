package p0.beans;

import p0.beans.Account;

public class Player {
	
	private int accountBalance;
	private int loanBalance;
	private int bankBalance;
	private int AccountID;
	private boolean hasLoan;
	private boolean loanWaiting;
	private boolean accountFlagged = false;
	private Account AccountInfo;
	
	public Player(int id, int accountBalance, int bankBalance, int loanBalance, boolean hasLoan, boolean loanWaiting,
			String Name, String Uname, String Pword) {
		this.AccountID= id;
		this.accountBalance = accountBalance;
		this.loanBalance = loanBalance;
		this.bankBalance = bankBalance;
		this.hasLoan = hasLoan;
		this.loanWaiting = loanWaiting;
		AccountInfo = new Account(Name,Uname,Pword);
	}
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
	public int getBankBalance() {
		return bankBalance;
	}
	public void setBankBalance(int bankBalance) {
		this.bankBalance = bankBalance;
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
	public boolean isAccountFlagged() {
		return accountFlagged;
	}
	public void setAccountFlagged(boolean accountFlagged) {
		this.accountFlagged = accountFlagged;
	}
	public int getAccountID() {
		return AccountID;
	}
	public Account getAccountInfo() {
		return AccountInfo;
	}
}
