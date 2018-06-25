package model;

import java.io.Serializable;
import java.text.NumberFormat;

import org.apache.log4j.Logger;

/**
 * @author Austin Molina
 * @version 0.1
 * @since 0.1
 */
public class Account implements Serializable {

	private static final long serialVersionUID = 451996026957339012L;
	private String name;
	private double balance;
	private int accountType = 0;
	private boolean validated;
	private User owner;

	private static Logger logger = Logger.getLogger(Account.class);

	/**
	 * 
	 * @return whether the account is validated
	 */
	public boolean isValidated() {
		return validated;
	}

	/**
	 * Sets the account to be marked as 'validated'
	 * 
	 * @param validated
	 */
	public void setValidated(boolean validated) {
		this.validated = validated;

		
	}

	/**
	 * Constructor for an account associates the account with a User. The name and
	 * type of the account are also set.
	 * 
	 * @param owner
	 * @param name
	 * @param type
	 */
	public Account(User owner, String name, int type) {
		this.name = name;
		this.accountType = type;
		this.owner = owner;
	}

	/**
	 * 
	 * @return balance in the account
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * 
	 * @return name of the account
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return name of the User that 'owns' the account
	 */
	public String getOwnerName() {
		return owner.getName();
	}

	/**
	 * Modifies the balance in the account by adding a given amount
	 * 
	 * @param amount
	 */
	public void deposit(double amount) {
		balance += amount;
		logger.info("Deposit of " + formatCurrency(amount) + " made to " + getOwnerName() + "'s " + name
				+ ". Final balance: " + formatCurrency(balance));
	}

	/**
	 * Modifies the balance in the account by subtracting a given amount
	 * 
	 * @param amount
	 */
	public void withdraw(double amount) {
		balance -= amount;
		logger.info("Withdrawal of " + formatCurrency(amount) + " made from " + getOwnerName() + "'s " + name
				+ ". Final balance: " + formatCurrency(balance));
	}

	/**
	 * Gives a summary of the account that includes the name and balance
	 */
	@Override
	public String toString() {
		String s = name + " - bal: " + formatCurrency(balance);
		return s;
	}

	/**
	 * Formats doubles to resemble a proper currency format
	 * 
	 * @param a
	 * @return
	 */
	public static String formatCurrency(double a) {

		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String s = nf.format(a);

		return s;
	}

	/**
	 * Returns the name of the account by type
	 * 
	 * @param input
	 * @return
	 */
	public static String getTypeName(int input) {
		switch (input) {
		case 2:
			return "savings";
		default:
			return "checking";
		}

	}

	/**
	 * 
	 * @return the User that 'owns' this account
	 */
	public User getOwner() {
		return owner;
	}
}
