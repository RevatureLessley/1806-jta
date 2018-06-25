package com.revature.day5.junit;

//A static import brings in an entire class and lets you use it
//as if the methods were defined within this class itself.
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ArithmeticTest {

	Arithmetic ari = null;
	
	@Before
	public void setUp() throws Exception {
		ari = new Arithmetic();
	}

	@After
	public void tearDown() throws Exception {
		ari = null;
	}

	@Test
	public void addition() {
		assertEquals(40, ari.addition(10, 30));
		/*
		 * Assert methods:
		 * -assertEquals
		 * -assertNotEquals
		 * -assertNull
		 * -assertNotNull
		 * -assertArrayEquals
		 */
	}
	@Test
	public void subtraction() {
		assertEquals(-20, ari.subtraction(10, 30));
	}
	@Test
	public void division() {
		assertEquals(0, ari.division(10, 30));
	}
	@Test
	public void multiply() {
		assertEquals(300, ari.multiplication(10, 30));
	}
	
	@Ignore //Ignore will skip a test
	@Test
	public void dayOffThisMonday() {
		fail("I got no money!");
	}

	/*
	 * Should you WANT a certain exception to be shown, you can 
	 * configure a test to pass for specific exceptions using the
	 * @Test's (expected = exception.class) configuration.
	 */
	@Test(expected=ArithmeticException.class)
	public void imagonnadividebyzero() {
		ari.division(10, 0);
	}
	
	//If you want a test to fail, for taking too long, use:
	//(timeout=milliseconds)
	//The test will fail if it passes the time threshold.
	@Test(timeout=3000)
	public void timerTest() throws Exception{
		Thread.sleep(4000);
	}
	
}
