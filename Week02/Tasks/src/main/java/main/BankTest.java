package main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class BankTest {

	User user = null;
	LoginPrompt lp = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		user = new User("testuser","testpass","c","d");
		lp = new LoginPrompt();
		lp.storeUserDB(user);
		user = lp.retrieveUserDB("testuser");
	}

	@After
	public void tearDown() throws Exception {
		lp.removeUserDB(user);
		user = null;
		lp = null;
	}

	//Test for comfirm password
	@Test
	public void confirmPassTest() {
		assertTrue(user.confirmPassword("testpass"));
	}
	
	@Test
	public void approveUserTest() {
		user = lp.retrieveUserDB("testuser");
		user.setAuth(5);
		lp.approveUserDB(user);
		assertEquals(lp.retrieveUserDB("testuser").getAuth(),user.getAuth());
	}
	
	@Test
	public void updataBalanceTest() {
		user = lp.retrieveUserDB("testuser");
		user.setBalance(10);
		lp.updateBalanceDB(user);
		assertEquals(lp.retrieveUserDB("testuser").getBalance(),user.getBalance(),0.01);
	}
}
