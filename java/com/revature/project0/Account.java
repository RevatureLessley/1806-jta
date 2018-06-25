package com.revature.project0;

import java.io.Serializable;

public class Account implements Serializable{

	private static final long serialVersionUID = 6419218113577619357L;
	private double bal = 0.0;
	private Boolean isActive = Boolean.FALSE;
	public Boolean isActive() {
		return isActive;
	}
	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}
	private String accNum;
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
/**
 * 
 * @param amnt the amount that is to be withdrawn
 * @return the balance that is left after the withdrawal occurs; checking of course
 * 			if there are sufficient funds
  */
	public double withdraw(double amnt) {
		if (bal >= amnt) {
			bal = bal - amnt;
			System.out.println("Your withdrawal was successful. "
				+ "\nYour balance is now: " + bal);
		} else {
			// overdraft
			System.out.println("Insufficient Funds");
		}
		return bal;
	}
/**
 * 
 * @param amnt the amount that is to be deposited
 * @return the balance that is left after the deposit occurs
 */
	public double deposit(double amnt) {
		bal = bal + amnt;
		System.out.println("Your deposit of " + amnt
				+ " has been credited to your account. "
				+ "Your account balance is now: " + bal);
		return bal;
	}
}
