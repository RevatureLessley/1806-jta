package p0.beans;

public class Loaner {
	private int balance;
	private double interest;
	private Account AccountInfo;

	public Loaner( int balance, double interest, String name, String uname, String pword) {
		this.balance = balance;
		this.interest = interest;
		AccountInfo = new Account(name, uname, pword);
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public Account getAccountInfo() {
		return AccountInfo;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
