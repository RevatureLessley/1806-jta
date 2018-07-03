package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.AccountDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.service.AccountService;
import com.revature.service.UserService;

public class ModelControllerTest {

	private static AccountService accountService;
	private static UserService userService;
	private static AccountDaoImpl accDao;
	private static UserDaoImpl userDao;
	private static int testID;
	private static int accountId;

	@BeforeClass
	public static void setUp() {
		accountService = new AccountService();
		userService = new UserService();
		accDao = new AccountDaoImpl();
		userDao = new UserDaoImpl();
		testID = userService.addUser("testUser", "password");

	}

	@AfterClass
	public static void tearDown() {
		accDao.deleteById(accountId);
		userDao.deleteById(testID);
	}

	@Test
	public void testUserService() {
		assertTrue(userService.checkUsernameAvailable("unusedName"));
		assertFalse(userService.checkUsernameAvailable("admin"));

		int adminId = userService.getUser("admin");
		assertEquals("admin", userService.getUserName(adminId));
	}

	@Test
	public void testAccountService() {

		accountId = accountService.addNewAccount(testID, 1);

		assertEquals(0.0, accountService.getAccountBalance(accountId), 0.0001);

		accountService.deposit(accountId, 500);
		assertEquals(500.0, accountService.getAccountBalance(accountId), 0.0001);

		accountService.withdraw(accountId, 750);
		assertEquals(-250.0, accountService.getAccountBalance(accountId), 0.0001);
	}

	@Test
	public void testUserType() {

		int adminId = userService.getUser("admin");

		assertTrue(userService.isAdmin(adminId));

		assertFalse(userService.isAdmin(testID));


	}

}
