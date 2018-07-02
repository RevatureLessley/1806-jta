package com.revature.service;

import com.revature.bank.Account;
import com.revature.dao.TransactionDao;


public class TransactionService 
{

	/**
	 * This is a helper method which calls the insertTransactionViaSp method from the
	 * TransactionDao class taking in the below parameters. 
	 * @param account
	 * @param transactionId
	 * @param depositAmount
	 * @param withdrawAmount
	 * @return
	 */
	public boolean insertTransaction(Account account, int transactionId, int depositAmount, int withdrawAmount)
	{
		TransactionDao nd = new TransactionDao();
		return nd.insertTransactionViaSp(account, transactionId, depositAmount, withdrawAmount);
	}
	
	/**
	 * This is a helper method that calls the selectCountTransactions method from the
	 * TransactionDao class.
	 * @return
	 */
	public int getTransactionsAmount()
	{
		TransactionDao nd = new TransactionDao();
		return nd.selectCountTransactions();
	}
	
}
