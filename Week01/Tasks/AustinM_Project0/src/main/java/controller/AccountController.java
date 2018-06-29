package controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import model.Account;
import model.LoanAccount;
import model.User;
import java.time.temporal.ChronoUnit;

public final class AccountController implements Serializable {

	private static final long serialVersionUID = -1767835589025835300L;
	private ArrayList<Account> unvalidatedAccounts;
	private ArrayList<Account> validatedAccounts;
	private int nextAcctNum = 0;
	private static Logger logger = Logger.getLogger(AccountController.class);

	private LocalDateTime lastInterestDate;

	/**
	 * AccountController constructor generates two empty lists for validated and
	 * unvalidated accounts
	 */
	AccountController() {
		unvalidatedAccounts = new ArrayList<Account>();
		validatedAccounts = new ArrayList<Account>();

		lastInterestDate = LocalDateTime.now();
	}

	/**
	 * Marks an account as 'validated', removes the account from the unvalidated
	 * list and adds it to the validated list
	 * 
	 * @param account
	 */
	public void validateAccount(Account account) {
		account.validate();
		unvalidatedAccounts.remove(account);
		validatedAccounts.add(account);

		logger.info(
				"Account " + account.getName() + " belonging to " + account.getOwnerName() + " has been validated.");
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
			account.validate();
		} else
			unvalidatedAccounts.add(account);

		return account;
	}

	/**
	 * Creates and adds a new LoanAccount to the list of all accounts. If the
	 * account belongs to an admin, it is automatically validated. Also sets borrow
	 * and target account values to be used once the account has been validated
	 * 
	 * @param account
	 */
	public Account addNewLoan(User user, double borrow, Account a) {
		String name = Account.getTypeName(Account.LOAN) + " " + getNextNumber();
		Account account = new LoanAccount(user, name, borrow, a);
		user.addAccount(account);

		logger.info("New account " + name + " created for user " + user.getName());

		if (account.getOwner().isAdmin()) {
			validatedAccounts.add(account);
			account.validate();
		} else
			unvalidatedAccounts.add(account);

		return account;

	}

	/**
	 * Builds an array of accounts waiting to be validated. The account name is
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

	/**
	 * Calculate the number of days (using minutes for simulation purposes) since
	 * interest was last applied and apply interest to all validated accounts
	 */
	public void applyInterest() {
		LocalDateTime currDate = LocalDateTime.now();
		long days = ChronoUnit.MINUTES.between(lastInterestDate, currDate);

		if (days <= 0)
			return;

		logger.info("Applying interest; " + days + " compounding periods to all validated accounts");
		applyInterest(days);

		lastInterestDate = currDate;
	}

	/**
	 * Apply interest to all validated accounts
	 */
	public void applyInterest(long days) {
		for (Account a : validatedAccounts) {
			a.applyInterest(days);
		}
	}

	/**
	 * Remove an account from the controller. Also removes the account from its
	 * owner
	 * 
	 * @param a
	 */
	public void removeAccount(Account a) {
		if (unvalidatedAccounts.contains(a))
			unvalidatedAccounts.remove(a);
		else if (validatedAccounts.contains(a))
			validatedAccounts.remove(a);

		a.getOwner().removeAccount(a);
	}

}