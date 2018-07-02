package com.revature.service;

import java.util.ArrayList;

import com.revature.bank.Account;
import com.revature.dao.AccountDao;


public class AccountService 
{
	/**
	 * A helper method that calls the selectAllAccount method from the 
	 * AccountDao class.
	 * @return
	 */
	public ArrayList<Account> getAllAccounts()
	{
		AccountDao nd = new AccountDao();
		ArrayList<Account> accs = nd.selectAllAccount();
		
		return accs;
	}
	
	/**
	 * A helper method that calls the insertAccountViaSp method from the 
	 * AccountDao class.
	 * @param acc
	 * @return
	 */
	public boolean insertAccount(Account acc)
	{
		AccountDao nd = new AccountDao();
		return nd.insertAccountViaSp(acc);
	}
	
	/**
	 * A helper method that calls the updateCurrencyViaSp method from the
	 * AccountDao class.
	 * @param acc
	 * @param newValue
	 * @return
	 */
	public boolean updateAccountValue(Account acc, int newValue)
	{
		AccountDao nd = new AccountDao();
		return nd.updateCurrencyViaSp(acc, newValue);
	}
	
	
	/**
	 * A helper method that calls the updateApprovedViaSp method from the
	 * AccountDao class.
	 * @param acc
	 * @param newApproved
	 * @return
	 */
	public boolean updateApproved(Account acc, int newApproved)
	{
		AccountDao nd = new AccountDao();
		return nd.updateApprovedViaSp(acc, newApproved);
	}
	
}
