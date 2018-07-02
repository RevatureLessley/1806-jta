package com.revature.main;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.beans.Balance;
import com.revature.beans.User;

public class UnitTests {
	
	GeneralMethods generalMethods = null;
	UserMethods userMethods = null;
	Bank bank = null;
	User user = null;
	Balance balance = null;

	@Before
	public void setUp() throws Exception {
		bank = new Bank();
		userMethods = new UserMethods();
		generalMethods = new GeneralMethods();
		//user = new User();
		balance = new Balance(new BigDecimal(20));
	}

	@After
	public void tearDown() throws Exception {
		bank = null;
		generalMethods = null;
		user = null;
		userMethods = null;
		balance = null;
	}

	@Test
	public void userEntersInvalidCardNumberShouldReturnFalse() {
		assertFalse(generalMethods.validCardNumber(13134));
	}
	
	@Test
	public void userEntersValidCardNumberShouldReturnTrue() {
		assertTrue(generalMethods.validCardNumber(1234567890));
	}
	
	@Test
	public void userTriesToWithdrawMoreMoneyThanItHasShouldReturnFalse() {
		assertFalse(userMethods.enoughMoneyToWithdraw(balance.getBalance(), BigDecimal.valueOf(100)));
	}
	
	@Test
	public void userTriesToWithdrawMoneyThatItHasShouldReturnTrue() {
		assertTrue(userMethods.enoughMoneyToWithdraw(balance.getBalance(), BigDecimal.valueOf(5)));
	}

}
