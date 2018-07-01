package com.revature.service;

public interface AccountServiceInterface {

	/**
	 * Marks an account as 'validated', removes the account from the unvalidated
	 * list and adds it to the validated list
	 * 
	 * @param account
	 */
	void validateAccount(Integer accountId);

	/**
	 * 
	 * @return number of accounts waiting to be validated
	 */
	int getWaitAccountCount();

	/**
	 * 
	 * @param i
	 * @return the ith account on the unvalidated list
	 */
	Integer getWaitAccount(int i);

	/**
	 * Creates and adds an account to the list of all accounts. If the account
	 * belongs to an admin, it is automatically validated
	 * 
	 * @param account
	 */
	Integer addNewAccount(Integer userId, int type);

	/**
	 * Creates and adds a new LoanAccount to the list of all accounts. If the
	 * account belongs to an admin, it is automatically validated. Also sets borrow
	 * and target account values to be used once the account has been validated
	 * 
	 * @param account
	 */
	Integer addNewLoan(Integer userId, double borrow, Integer accountId);

	/**
	 * Builds an array of accounts waiting to be validated. The account name is
	 * proceeded by the name of the user associated with the account
	 * 
	 * @return
	 */
	String[] getWaitAccountNames();

	/**
	 * Calculate the number of days (using minutes for simulation purposes) since
	 * interest was last applied and apply interest to all validated accounts
	 */
	void applyInterest();

	/**
	 * Apply interest to all validated accounts
	 */
	void applyInterest(long days);

	/**
	 * Remove an account from the controller. Also removes the account from its
	 * owner
	 * 
	 * @param a
	 */
	void removeAccount(Integer accountId);

}