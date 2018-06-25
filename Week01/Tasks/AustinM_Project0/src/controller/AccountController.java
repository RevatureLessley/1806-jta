package controller;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import model.Account;
import model.User;

public final class AccountController implements Serializable {

	private static final long serialVersionUID = -1767835589025835300L;
	private ArrayList<Account> unvalidatedAccounts;
	private ArrayList<Account> validatedAccounts;
	private int nextAcctNum = 0;
	private static Logger logger = Logger.getLogger(AccountController.class);

	/**
	 * AccountController constructor generates two empty lists for validated and
	 * unvalidated accounts
	 */
	AccountController() {
		unvalidatedAccounts = new ArrayList<Account>();
		validatedAccounts = new ArrayList<Account>();
	}

	/**
	 * Marks an account as 'validated', removes the account from the unvalidated
	 * list and adds it to the validated list
	 * 
	 * @param account
	 */
	public void validateAccount(Account account) {
		account.setValidated(true);
		unvalidatedAccounts.remove(account);
		validatedAccounts.add(account);
		
		logger.info("Account " + account.getName() + " belonging to " + account.getOwnerName() + " has been validated.");
	}

	/**
	 * 
	 * @return number of accounts waiting to be validated
	 */
	public int getWaitAccountCount() {
		return unvalidatedAccounts.size();
	}

	/**
	 * 
	 * @param i
	 * @return the ith account on the unvalidated list
	 */
	public Account getWaitAccount(int i) {
		return unvalidatedAccounts.get(i);
	}

	/**
	 * Creates and adds an account to the list of all accounts. If the account
	 * belongs to an admin, it is automatically validated
	 * 
	 * @param account
	 */
	public Account addNewAccount(User user, int type) {

		String name = Account.getTypeName(type) + " " + getNextNumber();
		Account account = new Account(user, name, type);
		user.addAccount(account);
		
		logger.info("New account " + name + " created for user " + user.getName());

		if (account.getOwner().isAdmin()) {
			validatedAccounts.add(account);
			account.setValidated(true);
		} else
			unvalidatedAccounts.add(account);
		
		return account;
	}

	/**
	 * Builds an array of accounts waiting to be validated. He account name is
	 * proceeded by the name of the user associated with the account
	 * 
	 * @return
	 */
	public String[] getWaitAccountNames() {
		String[] names = new String[unvalidatedAccounts.size()];
		int i = 0;

		for (Account a : unvalidatedAccounts) {
			names[i++] = a.getOwnerName() + " - " + a.getName();
		}

		return names;
	}

	/**
	 * Each account is numbered using a global counter
	 * 
	 * @return next account number
	 */
	public int getNextNumber() {
		return nextAcctNum++;
	}
}
