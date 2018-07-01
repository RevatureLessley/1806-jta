package main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class BankTest {

	Users users = null;
	Admin admin = null;
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
		users = new Users();
		admin = new Admin("testadmin","testpass","a","b");
		user = new User("testuser","testpass","c","d");
		lp = new LoginPrompt();
	}

	@After
	public void tearDown() throws Exception {
		user = null;
		admin = null;
		users = null;
		lp = null;
	}

	//Test for comfirm password
	@Test
	public void confirmPassTest() {
		assertTrue(user.confirmPassword("testpass"));
	}
	
	@Test
	public void addUserTest() {
		users.addUser(user);
		assertEquals(users.getUsers().get("testuser"),user);
	}
	
	@Test
	public void removeUserTest() {
		users.addUser(user);
		users.removeUser(user);
		assertEquals(users.getUsers().get("testuser"),null);
	}
}
