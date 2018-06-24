package com.revature.bank;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.day5.junit.Arithmetic;

public class AccountTest 
{

	private static final int ADMIN = 1;
	private static final int USER = 0;
	
	@Test
	public void createNewAdminAccount() 
	{
		Account account = new Account("Logan", "Brewer", 0, 0, ADMIN, true);
		assertEquals(1, account.getAccountType());
	}
	
	@Test
	public void createNewUserAccount() 
	{
		Account account = new Account("Logan", "Brewer", 0, 0, USER, true);
		assertEquals(0, account.getAccountType());
	}
	
	@Test
	public void newAccountBalanceIsZero() 
	{
		Account account = new Account("Logan", "Brewer", 0, 0, USER, true);
		assertEquals(0, account.getAccountValue());
	}

}
