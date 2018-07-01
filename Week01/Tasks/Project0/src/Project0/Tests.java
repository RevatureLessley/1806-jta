package Project0;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Tests {
	
	Account acc = null;

	@Before
	public void setUp() throws Exception {
		acc = new Account();
	}

	@After
	public void tearDown() throws Exception {
		acc =null;
	}

	@Test
	public void depositTest() {
		assertEquals(100,acc.deposit(100));
	}
	
	@Test
	public void withdrawalTest() {
		assertEquals(-100,acc.withdrawal(100));
	}
	
	@Test
	public void balanceTest() {
		assertEquals(0,acc.getBalance());
	}
	
}
