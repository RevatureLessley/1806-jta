package com.bank.main;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitTests {
	
	Bank bank = null;
	User user = null;

	@Before
	public void setUp() throws Exception {
		bank = new Bank();
		user = new User("Test", "User", "test@user.com", "test", 1, true, BigDecimal.valueOf(10), 1234567890);
	}

	@After
	public void tearDown() throws Exception {
		bank = null;
		user = null;
	}

	@Test
	public void userEntersInvalidCardNumberShouldReturnFalse() {
		assertFalse(bank.validCardNumber(13134));
	}
	
	@Test
	public void userEntersValidCardNumberShouldReturnTrue() {
		assertTrue(bank.validCardNumber(1234567890));
	}
	
	@Test
	public void userTriesToWithdrawMoreMoneyThanItHasShouldReturnFalse() {
		assertFalse(bank.enoughMoneyToWithdraw(user, BigDecimal.valueOf(100)));
	}
	
	@Test
	public void userTriesToWithdrawMoneyThatItHasShouldReturnTrue() {
		assertTrue(bank.enoughMoneyToWithdraw(user, BigDecimal.valueOf(5)));
	}

}
