package com.revature.service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import com.revature.beans.BAccount;
import com.revature.beans.BAccountType;
import com.revature.beans.BUser;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.AccountTypeDaoImpl;
import com.revature.dao.GenericDao;
import com.revature.dao.UserDaoImpl;

public class AccountService implements AccountServiceInterface {

	private AccountDaoImpl accountDao = new AccountDaoImpl();

	@Override
	public void validateAccount(Integer accountId) {
		BAccount bAccount = accountDao.selectById(accountId);
		bAccount.setValidated(1);
		accountDao.update(bAccount);
	}

	@Override
	public int getWaitAccountCount() {

		return accountDao.selectAll(false).size();
	}

	@Override
	public Integer getWaitAccount(int i) {
		List<BAccount> ls = accountDao.selectAll(false);
		return ls.get(i).getId();
	}

	@Override
	public Integer addNewAccount(Integer userId, int type) {
		AccountDaoImpl aDao = new AccountDaoImpl();

		Integer id = aDao.selectAll().size() + 1;

		String name = new AccountTypeDaoImpl().selectById(type).getName();
		BAccount a = new BAccount(id, type, 0.0, 0, userId);

		// logger.info("New account " + name + " created for user " + user.getName());

		// BUser u = new UserDaoImpl().selectById(userId);

		if (new UserService().isAdmin(userId))
			a.setValidated(1);

		aDao.insert(a);

		return id;
	}

	@Override
	public Integer addNewLoan(Integer userId, double borrow, Integer accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getWaitAccountNames() {
		List<BAccount> l = accountDao.selectAll(false);

		String[] acctNames = new String[l.size()];
		BAccount a;

		UserService userService = new UserService();

		for (int i = 0; i < acctNames.length; i++) {
			a = l.get(i);

			acctNames[i] = userService.getUserName(a.getUser()) + " " + a.getName();

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
//		List<BAccount> l = accountDao.selectAll(userId);
//
//		String[] acctNames = new String[l.size()];
//		BAccount a;
//
//		for (int i = 0; i < acctNames.length; i++) {
//			a = l.get(i);
//
//			acctNames[i] = accountDao.get
//					
//			if (a.getValidated() == 1)
//				acctNames[i] = a.getName() + " - " + formatCurrency(a.getBalance());
//			else
//				acctNames[i] = a.getName() + " - awaiting validation";
//		}
		
		List<String> acctNames = accountDao.selectJoinUserAccountNames(userId);
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
	public String UserAccountSummary(Integer userId) {
		StringBuilder sb = new StringBuilder();
		List<BAccount> l = accountDao.selectAll(userId);

		for (BAccount a : l) {
			sb.append(a.toString());
			sb.append('\n');
		}

		return sb.toString();
	}

	@Override
	public void removeAccount(Integer accountId) {
		accountDao.deleteById(accountId);
	}

	@Override
	public void applyInterest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void applyInterest(long days) {
		// TODO Auto-generated method stub

	}

	public String getAccountName(Integer accountId) {
		System.out.println("looking up: " + accountId);
		BAccount acc = accountDao.selectById(accountId);
		String typeName = new AccountTypeDaoImpl().selectById(acc.getId()).getName();

		return typeName + acc.getId();
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

}
