package com.revature.service;

import java.util.ArrayList;

import com.revature.bank.Account;
import com.revature.dao.AccountDao;

public class AccountService 
{

	public ArrayList<Account> getAllAccounts()
	{
		AccountDao nd = new AccountDao();
		ArrayList<Account> accs = nd.selectAllAccount();
		
		return accs;
	}
	
	public Account getAccountById(Integer id)
	{
		AccountDao nd = new AccountDao();
		Account acc = nd.selectAccountById(id);
		
		return acc;
	}
	
	public boolean updateCurrencyById(Integer id, Integer value)
	{
		AccountDao nd = new AccountDao();
		Account acc = nd.selectAccountById(id);
		
		if(acc != null)
		{
			acc.setAccountValue(value);
			if(nd.updateAccount(acc) > 0)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean insertAccount(Account acc)
	{
		AccountDao nd = new AccountDao();
		return nd.insertAccountViaSp(acc);
	}
	
	public boolean updateAccountValue(Account acc, int newValue)
	{
		AccountDao nd = new AccountDao();
		return nd.updateCurrencyViaSp(acc, newValue);
	}
	
	public boolean updateApproved(Account acc, int newApproved)
	{
		AccountDao nd = new AccountDao();
		return nd.updateApprovedViaSp(acc, newApproved);
	}
	
}
