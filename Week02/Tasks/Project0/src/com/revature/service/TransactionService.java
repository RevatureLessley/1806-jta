package com.revature.service;

import com.revature.bank.Account;
import com.revature.dao.TransactionDao;

public class TransactionService 
{

	public boolean insertTransaction(Account account, int transactionId, int depositAmount, int withdrawAmount)
	{
		TransactionDao nd = new TransactionDao();
		return nd.insertTransactionViaSp(account, transactionId, depositAmount, withdrawAmount);
	}
	
	public int getTransactionsAmount()
	{
		TransactionDao nd = new TransactionDao();
		return nd.selectCountTransactions();
	}
	
}
