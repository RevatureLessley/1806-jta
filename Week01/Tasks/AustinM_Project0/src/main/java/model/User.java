package model;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * @author Austin Molina
 * @version 0.1
 * @since 0.1
 */
public class User implements Serializable {

	private static final long serialVersionUID = 7881493291594545515L;
	private ArrayList<Account> accounts;
	private String name;
	private String password;
	private boolean admin = false;
	private static Logger logger = Logger.getLogger(User.class);

	public User(String name, String password) {
		this.name = name;
		this.password = password;
		accounts = new ArrayList<Account>();
	}

	/**
	 * 
	 * @return User name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Checks if a given String matches the User's password
	 * 
	 * @param password
	 * @return true if valid, else false
	 */
	public int validateLogin(String password) {
		if (password.equals(this.password))
			return 1;

		return 0;
	}

	/**
	 * 
	 * @return true if the user is an admin, else false
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * Sets the user's admin permissions
	 * 
	 * @param admin
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * Add an account to the user's collection of accounts
	 * 
	 * @param account
	 */
	public void addAccount(Account account) {
		logger.info("Account " + account.getName() + " added to " + name + "'s account list");
		accounts.add(account);
	}

	/**
	 * Creates an array of Strings. Each string is an account that belongs to the
	 * user followed by the balance if the account has been validated.
	 * 
	 * @return Array of the user's accounts' names
	 */
	public String[] getAccountNames() {
		String[] acctNames = new String[accounts.size()];
		Account a;

		for (int i = 0; i < acctNames.length; i++) {
			a = accounts.get(i);

			if (a.isValidated())
				acctNames[i] = a.getName() + " - " + Account.formatCurrency(a.getBalance());
			else
				acctNames[i] = a.getName() + " - awaiting validation";
		}

		return acctNames;
	}

	/**
	 * @param i
	 * @return returns the ith account owned by this user.
	 */
	public Account getAccount(int i) {
		try {
			return accounts.get(i);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Creates a summary of all accounts that belong to the user by concatenating
	 * the toString of each account.
	 * 
	 * @return summary String
	 */
	public String accountSummary() {
		StringBuilder sb = new StringBuilder();

		for (Account a : accounts) {
			sb.append(a.toString());
			sb.append('\n');
		}

		return sb.toString();
	}

	/**
	 * Removes an account from the user's list of owner accounts. This is done when
	 * a loan has been paid off, or an account is denied.
	 * 
	 * @param a
	 */
	public void removeAccount(Account a) {
		accounts.remove(a);
	}

	/**
	 * Returns a balance which is the sum of all accounts belonging to the user.
	 * 
	 * @return
	 */
	public double totalBalance() {
		double total = 0;

		for (Account a : accounts) {
			total += a.getBalance();
		}

		return total;
	}

	/**
	 * Checks whether the username is long enough. Usernames also cannot contain any
	 * spaces.
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkUsernameValid(String name) {

		if (name.length() < 4)
			return false;
		if (name.indexOf(' ') != -1)
			return false;

		return true;
	}

	/**
	 * Checks whether the password is long enough.
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkPasswordValid(String password) {
		if (password.length() < 6)
			return false;

		return true;
	}

}
