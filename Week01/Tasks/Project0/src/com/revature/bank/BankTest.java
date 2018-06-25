package com.revature.bank;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.revature.bank.Bank;

public class BankTest 
{
	private static final int ADMIN = 1;
	private static final int USER = 0;
	
	@Test
	public void depositingWorks() 
	{
		ArrayList<Account> accounts = new ArrayList<>();
		Account account = new Account("Logan", "Brewer", 0, 0, USER, true);
		accounts.add(account);
		Bank bank = new Bank(accounts);
		bank.userDeposit(10, accounts.get(0));
		assertEquals(10, account.getAccountValue());
	}
	
	@Test
	public void withdrawingWorks() 
	{
		ArrayList<Account> accounts = new ArrayList<>();
		Account account = new Account("Logan", "Brewer", 0, 50, USER, true);
		accounts.add(account);
		Bank bank = new Bank(accounts);
		bank.userWithdraw(10, accounts.get(0));
		assertEquals(40, account.getAccountValue());
	}

}
