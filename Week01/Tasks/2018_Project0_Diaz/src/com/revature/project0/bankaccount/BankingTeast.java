package com.revature.project0.bankaccount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class BankingTeast {
	static SecondMenu tt = null;
	@Before
	static void setUp() throws Exception {
		tt =new SecondMenu();
	}
	@After
	static void tearDowns() throws Exception {
		tt = null;
	}
	@Test
	public void addToBank() {
		assertEquals(150, SecondMenu.addToBank(10, 30,50,60));
	}	
	@Test
	public void addToBank2() {
		assertEquals(19000, SecondMenu.addToBank(1000,1000,5000,12000));
	}
	@Test
	public void loanIntrest() {
		assertEquals(150, SecondMenu.loanIntrest(100, 0.5));
	}
	@Test
	public void transferOut() {
		assertEquals(-400, SecondMenu.transferOut(100, 500));
	}
	@Test
	public void transferIn() {
		assertEquals(600, SecondMenu.transferIn(100, 500));
	}
}
