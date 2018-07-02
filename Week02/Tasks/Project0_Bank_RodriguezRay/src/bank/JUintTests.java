package bank;

// A static import brings in an entire class and lets you use it
//as if the methods were defined within this class itself.
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.CustomerAccount;

public class JUintTests {

	CustomerAccount user = null;
	CustomerAccount user2 = null;
	CustomerAccount user3 = null;
	
	@Before
	public void setUp() throws Exception {
		user = new CustomerAccount("user", "Test", "Tester", "test", "1234", 2000);
		user2 = new CustomerAccount("user", "Test", "Tester", "test", "1234", 2000);
		user3 = new CustomerAccount("user", "Test", "Tester", "test", "1234", 2000);
	}

	@After
	public void tearDown() throws Exception {
		user = null;
		user2 = null;
		user3 = null;
	}

	@Test
	public void deposit() {
		assertEquals(2100.0, user.Deposit(100.0), 0.0);
	}
	
	@Test
	public void withdraw() {
		assertEquals(1900.0, user2.Withdrawal(100.0), 0.0);
	}
	
	@Test
	public void balance() {
		assertEquals(2000.0, user3.DisplayBalance(), 0.0);
	}
}
