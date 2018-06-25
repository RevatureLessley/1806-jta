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
	protected double balance;
	private int accountType = 0;
	private boolean validated;
	private User owner;

	public static final int CHECKING = 1;
	public static final int SAVINGS = 2;
	public static final int LOAN = 3;

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
	public void validate() {
		this.validated = true;

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
		case 3:
			return "loan";
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

	/**
	 * 
	 * @param accountType
	 * @return interest rate based on the accountType
	 */
	private static double getInterestRate(int accountType) {
		switch (accountType) {
		case Account.SAVINGS:
			return 0.05;
		case Account.LOAN:
			return 0.10;
		default:
			// return "checking";
			return 0.025;
		}
	}

	/**
	 * Calculates compounded interest using a rate based on the account's type. The
	 * interest is added to the account balance
	 * 
	 * @param periods
	 *            of compounding
	 */
	public void applyInterest(long periods) {
		double r = getInterestRate(accountType);

		balance = balance * Math.pow((1 + r / 365.0), periods);
	}

	/**
	 * 
	 * @return accountType
	 */
	public int getType() {
		return accountType;
	}
}
