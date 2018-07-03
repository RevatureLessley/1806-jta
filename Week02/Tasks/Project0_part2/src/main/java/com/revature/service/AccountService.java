package com.revature.service;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.revature.beans.BAccount;
import com.revature.beans.BAccountType;
import com.revature.beans.BInterestStamp;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.AccountTypeDaoImpl;
import com.revature.dao.InterestStampDao;

public class AccountService {

	private AccountDaoImpl accountDao = new AccountDaoImpl();
	private static Logger logger = Logger.getLogger(AccountService.class);
	
	/**
	 * Marks an account as 'validated' within the DB If the account was a loan,
	 * transaction involving the loan are also applied
	 * 
	 * @param account
	 */
	public void validateAccount(Integer accountId) {
		BAccount bAccount = accountDao.selectById(accountId);
		if (bAccount.getType() != 3) {
			logger.info(
					"Account " + bAccount.getName() + " has been validated.");
			bAccount.setValidated(1);
			accountDao.update(bAccount);
		} else {
			accountDao.validateLoan(bAccount.getId());
		}
	}

	/**
	 * 
	 * @return number of accounts waiting to be validated
	 */
	public int getWaitAccountCount() {

		return accountDao.selectAll(false).size();
	}

	/**
	 * 
	 * @param i
	 * @return the ith account on the unvalidated set
	 */
	public Integer getWaitAccount(int i) {
		List<BAccount> ls = accountDao.selectAll(false);
		return ls.get(i).getId();
	}

	/**
	 * Creates and adds an account to the DB. If the account belongs to an admin, it
	 * is automatically validated
	 * 
	 * @param account
	 */
	public Integer addNewAccount(Integer userId, int type) {

		Integer id = accountDao.getMaxId() + 1;


		BAccount a = new BAccount(id, type, 0.0, 0, userId);

		logger.info("New account " + getAccountName(a) + " created for user " + userId);

		if (new UserService().isAdmin(userId))
			a.setValidated(1);

		accountDao.insert(a);

		return id;
	}

	/**
	 * Executes the loan request procedure int the DB. Creates and adds a new
	 * LoanAccount and loan request. If the account belongs to an admin, it is
	 * automatically validated. Also sets borrow and target account values to be
	 * used once the account has been validated
	 * 
	 * @param account
	 */
	public void addNewLoan(Integer userId, double borrow, Integer targetAcct) {
		accountDao.requestLoan(userId, borrow, targetAcct);
		logger.info("New loan request made by user " + userId + " for " + borrow);
	}

	/**
	 * Builds an array of accounts waiting to be validated. The account name is
	 * proceeded by the name of the user associated with the account
	 * 
	 * @return
	 */
	public String[] getWaitAccountNames() {
		List<BAccount> l = accountDao.selectAll(false);

		String[] acctNames = new String[l.size()];
		BAccount a;

		UserService userService = new UserService();

		for (int i = 0; i < acctNames.length; i++) {
			a = l.get(i);

			acctNames[i] = userService.getUserName(a.getUser()) + " - " + getAccountName(a);

		}

		return acctNames;
	}

	/**
	 * Creates an array of Strings. Each string is an account that belongs to the
	 * user followed by the balance if the account has been validated.
	 * 
	 * @return Array of the user's accounts' names
	 */
	public String[] getUserAccountNames(Integer userId) {

		List<String> acctNames = new ArrayList<>();

		for (BAccount a : accountDao.selectAll(userId))
			acctNames.add(getAccountName(a));

		String[] names = new String[acctNames.size()];
		acctNames.toArray(names);
		return names;
	}

	/**
	 * Creates a summary of all accounts that belong to the user by concatenating
	 * the toString of each account.
	 * 
	 * @return summary String
	 */
	public String getUserAccountSummary(Integer userId) {
		StringBuilder sb = new StringBuilder();

		for (BAccount a : accountDao.selectAll(userId)) {
			sb.append(getAccountSummary(a));
			sb.append('\n');
		}

		return sb.toString();
	}

	/**
	 * Remove an account from the DB. Also removes the account from its owner. Used
	 * for completed loans and denied accounts.
	 * 
	 * @param a
	 */
	public void removeAccount(Integer accountId) {
		logger.info("removed account " + accountId);
		accountDao.deleteById(accountId);
	}

	/**
	 * Calculate the number of days (using minutes for simulation purposes) since
	 * interest was last applied and apply interest to all validated accounts
	 */
	public void applyInterest() {
		LocalDateTime currDate = LocalDateTime.now();
		InterestStampDao interestDao = new InterestStampDao();

		BInterestStamp stamp = interestDao.selectNewest();

		if (stamp != null) {
			long days = ChronoUnit.MINUTES.between(stamp.getLocalDateTime(), currDate);
			//System.out.println("applying interest for " + days + " periods");

			if (days <= 0)
				return;

			// applyInterest(days);
			logger.info("applied interest for " + days + " periods");
			accountDao.applyInterest((int) days);

		}
		BInterestStamp newStamp = new BInterestStamp(LocalDateTime.now());
		interestDao.insert(newStamp);
	}

	/**
	 * Uses a naming convention to generate a name for the account based on its type
	 * and id.
	 * 
	 * @param accountId
	 * @return
	 */
	public String getAccountName(Integer accountId) {

		BAccount acc = accountDao.selectById(accountId);
		return getAccountName(acc);
	}

	/**
	 * Uses a naming convention to generate a name for the account based on its type
	 * and id.
	 * 
	 * @param acc
	 * @return
	 */
	public String getAccountName(BAccount acc) {

		Map<Integer, BAccountType> typeMap = AccountTypeDaoImpl.getTypeMap();

		String typeName = typeMap.get(acc.getType()).getName();

		return typeName + "_" + acc.getId();
	}

	/**
	 * Gets the account at the given index that belongs to the given user. Assumes
	 * the list is sorted by account id
	 * 
	 * @param userId
	 * @param index
	 * @return
	 */
	public Integer getUserAccount(Integer userId, int index) {
		List<BAccount> l = accountDao.selectAll(userId);
		try {
			return l.get(index).getId();
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Access the DB to determine whether an account has been validated.
	 * 
	 * @param accountId
	 * @return
	 */
	public boolean accountIsValidate(Integer accountId) {
		return (accountDao.selectById(accountId).getValidated() == 1);
	}

	/**
	 * Access the DB to determine the type of an account.
	 * 
	 * @param accountId
	 * @return
	 */
	public int getAccountType(Integer activeAccount) {
		return accountDao.selectById(activeAccount).getType();
	}

	/**
	 * Obtains an account from the DB, modifies the balance, and updates the DB.
	 * 
	 * @param accountId
	 * @param amt
	 */
	public void withdraw(Integer accountId, double amt) {
		BAccount bAccount = accountDao.selectById(accountId);
		bAccount.setBalance(bAccount.getBalance() - amt);
		
		logger.info("withdrew " + amt + " from " + getAccountName(bAccount));

		accountDao.update(bAccount);

	}

	/**
	 * Obtains an account from the DB, modifies the balance, and updates the DB.
	 * TODO completed loans should be removed from the DB
	 * 
	 * @param accountId
	 * @param amt
	 */
	public void deposit(Integer accountId, double amt) {
		BAccount bAccount = accountDao.selectById(accountId);
		bAccount.setBalance(bAccount.getBalance() + amt);
		
		logger.info("deposit " + amt + " to " + getAccountName(bAccount));

		accountDao.update(bAccount);

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
	 * Obtains a simple summary of the account which includes the name and balance
	 * 
	 * @param accountId
	 * @return
	 */
	public String getAccountSummary(Integer accountId) {
		return getAccountSummary(accountDao.selectById(accountId));
	}

	/**
	 * Obtains a simple summary of the account which includes the name and balance
	 * 
	 * @param accountId
	 * @return
	 */
	public String getAccountSummary(BAccount account) {
		if (account.getValidated() == 1)
			return getAccountName(account) + " - " + AccountService.formatCurrency(account.getBalance());
		else {
			if (account.getType() == 3)
				return getAccountName(account) + " - request for "
						+ AccountService.formatCurrency(-account.getBalance());
			else
				return getAccountName(account) + " - awaiting validation";
		}

	}

	/**
	 * Obtains the balance of an account from the DB.
	 * 
	 * @param accountId
	 * @return
	 */
	public Double getAccountBalance(Integer accountId) {
		BAccount bAccount = accountDao.selectById(accountId);

		return bAccount.getBalance();
	}

}
