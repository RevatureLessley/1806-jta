package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.AccountController;
import controller.MasterController;
import controller.UserController;
import model.Account;
import model.User;

public class ModelControllerTest {

	private AccountController accountController;
	private UserController userController;
	private Account act1, act2;
	private User user, admin;

	@Before
	public void setUp() throws Exception {
		MasterController.setFreeData();
		MasterController.shutdown();
		user = new User("user", "xxxxxx");
		admin = new User("admin", "xxxxxx");
		admin.setAdmin(true);

		act1 = new Account(user, "act1", 0);
		act2 = new Account(user, "act2", 0);

		userController = MasterController.getUserController();
		accountController = MasterController.getAccountController();
	}

	@After
	public void tearDown() throws Exception {
		MasterController.setFreeData();
		MasterController.shutdown();
	}

	@Test
	public void testAccountController() {

		assertEquals(0, accountController.getNextNumber());
		assertEquals(1, accountController.getNextNumber());

		assertEquals(0, accountController.getWaitAccountCount());

		accountController.addNewAccount(user, 1);
		assertEquals(1, accountController.getWaitAccountCount());

		// admin account never goes on wait
		accountController.addNewAccount(admin, 1);
		assertEquals(1, accountController.getWaitAccountCount());

		accountController.validateAccount(user.getAccount(0));
		assertEquals(0, accountController.getWaitAccountCount());

	}

	@Test
	public void testInterest() {

		// admin account never goes on wait
		Account a = accountController.addNewAccount(admin, 1);
		a.deposit(5000);

		accountController.applyInterest();
		assertEquals(5000.0, a.getBalance(), .0001);
		
		accountController.applyInterest(365);
		System.out.println(a.getBalance());
		
		
		assertTrue(5000.0 < a.getBalance());

		System.out.println(a.getBalance());
	}

	@Test
	public void testUserController() {
		assertTrue(userController.checkUsernameAvailable("user"));
		userController.addUser("user", "xxxxxx");
		assertFalse(userController.checkUsernameAvailable("user"));
	}

	@Test
	public void testAccount() {

		assertEquals(0.0, act1.getBalance(), 0.0001);

		act1.deposit(500.0);
		assertEquals(500.0, act1.getBalance(), 0.0001);

		act1.withdraw(750.0);
		assertEquals(-250.0, act1.getBalance(), 0.0001);
	}

	@Test
	public void testUser() {

		assertEquals(0.0, user.totalBalance(), 0.0001);

		user.addAccount(act1);
		user.addAccount(act2);

		act1.deposit(500.0);
		act2.deposit(500.0);

		assertEquals(1000.0, user.totalBalance(), 0.0001);

	}

}
