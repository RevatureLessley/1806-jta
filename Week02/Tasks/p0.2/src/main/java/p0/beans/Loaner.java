package p0.beans;

public class Loaner {
	private int balance;
	private double interest;
	private Account AccountInfo;

	public Loaner(String name, String uname, String pword, int balance, double interest) {
		AccountInfo = new Account(uname, pword, name);
		this.balance = balance;
		this.interest = interest;
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
