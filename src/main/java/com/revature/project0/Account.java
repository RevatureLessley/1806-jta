package com.revature.project0;

public class Account {
	private double bal = 0.0;
	private boolean isApproved = false;
	private String accNum;
	private User user;
	
	public Account(String accNum) {
		this.accNum = accNum;
	}
	

	public Account(String accNum, double bal) {
		this.accNum = accNum;
		this.bal = bal;
	}
	
	public double getBal() {
		return bal;
	}

	public String getAccNum() {
		return accNum;
	}

	public void withdraw(double amnt) {
		if (bal >= amnt) {
			bal = bal - amnt;
			System.out.println("Your withdrawel was successful. "
					+ "Your balance is now: " + bal);
		} else {
			// overdraft
			System.out.println("Insufficient Funds");

		}
	}

	public void deposit(double amnt) {
		bal = bal + amnt;
		System.out.println("Your deposit of " + amnt
				+ " has been credited to your account. "
				+ "Your account balance is now: " + bal);
	}
}
