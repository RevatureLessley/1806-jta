package com.revature.service;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.revature.beans.BAccount;
import com.revature.beans.BAccountType;
import com.revature.beans.BInterestStamp;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.AccountTypeDaoImpl;
import com.revature.dao.InterestStampDao;

public class AccountService {

	private AccountDaoImpl accountDao = new AccountDaoImpl();

	public void validateAccount(Integer accountId) {
		BAccount bAccount = accountDao.selectById(accountId);
		if (bAccount.getType() != 3) {
			bAccount.setValidated(1);
			accountDao.update(bAccount);
		}else {
			accountDao.validateLoan(bAccount.getId());
		}
	}

	public int getWaitAccountCount() {

		return accountDao.selectAll(false).size();
	}

	public Integer getWaitAccount(int i) {
		List<BAccount> ls = accountDao.selectAll(false);
		return ls.get(i).getId();
	}

	public Integer addNewAccount(Integer userId, int type) {

		Integer id = accountDao.getMaxId() + 1;

		//String name = new AccountTypeDaoImpl().selectById(type).getName();
		BAccount a = new BAccount(id, type, 0.0, 0, userId);

		// logger.info("New account " + name + " created for user " + user.getName());

		// BUser u = new UserDaoImpl().selectById(userId);

		if (new UserService().isAdmin(userId))
			a.setValidated(1);

		accountDao.insert(a);

		return id;
	}

	public void addNewLoan(Integer userId, double borrow, Integer targetAcct) {
		accountDao.requestLoan(userId, borrow, targetAcct);
	}

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
		// List<BAccount> l = accountDao.selectAll(userId);

		List<String> l = accountDao.selectJoinUserAccountSummary(userId);

		for (String s : l) {
			sb.append(s);
			sb.append('\n');
		}

		return sb.toString();
	}

	public void removeAccount(Integer accountId) {
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
			System.out.println("applying interest for " + days + " periods");

			if (days <= 0)
				return;

			// applyInterest(days);
			accountDao.applyInterest((int) days);

		}
		BInterestStamp newStamp = new BInterestStamp(LocalDateTime.now());
		interestDao.insert(newStamp);
	}

	/**
	 * Apply interest to all validated accounts
	 */
	// public void applyInterest(long periods) {
	//
	// List<BAccount> accounts = accountDao.selectAll(true);
	// Map<Integer, BAccountType> typeMap = AccountTypeDaoImpl.getTypeMap();
	// //System.out.println(typeMap);
	//
	// double r;
	// double balance;
	//
	// for (BAccount a : accounts) {
	// // a.applyInterest(days);
	// r = typeMap.get(a.getType()).getRate();
	// //System.out.println(a.getType());
	// //System.out.println(r);
	//
	// balance = a.getBalance();
	// //System.out.println(balance);
	// //System.out.println(Math.pow((1 + r / 365.0), periods));
	//
	// balance = balance * Math.pow((1 + r / 365.0), periods);
	// //System.out.println(balance);
	//
	// a.setBalance(balance);
	//
	// accountDao.update(a);
	//
	// }
	// }

	public String getAccountName(Integer accountId) {

		BAccount acc = accountDao.selectById(accountId);
		return getAccountName(acc);
	}

	public String getAccountName(BAccount acc) {

		Map<Integer, BAccountType> typeMap = AccountTypeDaoImpl.getTypeMap();

		String typeName = typeMap.get(acc.getType()).getName();

		return typeName + "_" + acc.getId();
	}

	public Integer getUserAccount(Integer userId, int index) {
		List<BAccount> l = accountDao.selectAll(userId);
		try {
			return l.get(index).getId();
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public boolean accountIsValidate(Integer accountId) {
		return (accountDao.selectById(accountId).getValidated() == 1);
	}

	public int getAccountType(Integer activeAccount) {
		return accountDao.selectById(activeAccount).getType();
	}

	public void withdraw(Integer accountId, double amt) {
		BAccount bAccount = accountDao.selectById(accountId);
		bAccount.setBalance(bAccount.getBalance() - amt);

		accountDao.update(bAccount);

	}

	public void deposit(Integer accountId, double amt) {
		BAccount bAccount = accountDao.selectById(accountId);
		bAccount.setBalance(bAccount.getBalance() + amt);

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

	public String getAccountSummary(Integer accountId) {
		return getAccountName(accountId) + " - " + AccountService.formatCurrency(getAccountBalance(accountId));
	}

	public Double getAccountBalance(Integer accountId) {
		BAccount bAccount = accountDao.selectById(accountId);

		return bAccount.getBalance();
	}

}
