package model;

import java.io.Serializable;

import org.apache.log4j.Logger;

import controller.MasterController;

public class LoanAccount extends Account implements Serializable {

	private Account toAccount;
	private double borrow;
	private static Logger logger = Logger.getLogger(LoanAccount.class);

	/**
	 * 
	 * @param owner
	 * @param name
	 * @param type
	 * @param borrow
	 * @param toAccount
	 */
	public LoanAccount(User owner, String name, int type, double borrow, Account toAccount) {
		super(owner, name, type);

		this.toAccount = toAccount;
		this.borrow = borrow;
	}

	/**
	 * Marks the account as validated. This action also deposits the amount
	 * requested for the loan into the target account and sets the initial balance
	 * of the loan.
	 */
	@Override
	public void validate() {

		if (!this.isValidated()) {
			toAccount.deposit(borrow);
			this.withdraw(borrow);
		}

		super.validate();
	}

	/**
	 * Modifies the balance in the account by adding a given amount. If the balance
	 * ends up greater than or equal to 0, then the accountController is told to
	 * nullify the loanAccount.
	 */
	@Override
	public void deposit(double amount) {
		balance += amount;

		if (balance >= 0) {
			MasterController.getAccountController().removeAccount(this);
			logger.info("Payment of " + formatCurrency(amount) + " made to " + getOwnerName() + "'s " + this.getName()
					+ ". account has been paid in full");

		} else {
			logger.info("Payment of " + formatCurrency(amount) + " made to " + getOwnerName() + "'s " + this.getName()
					+ ". Final balance: " + formatCurrency(balance));

		}
	}

	@Override
	public String toString() {
		if (this.isValidated())
			return super.toString();
		else
			return this.getName() + " pending - request for " + formatCurrency(borrow);
	}
}
