package p0;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GenericTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("=== before Class===");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("=== After Class===");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("=== before===");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("=== After===");
	}

	@Test
	public void test1() {
		
		System.out.println("===Test 1===");
		fail("Not yet implemented");
	}

	@Test
	public void test2() {
		
		System.out.println("===Test 2===");
		fail("Not yet implemented");
	}
	@Test
	public void test3() {
		
		System.out.println("===Test 3===");
		fail("Not yet implemented");
	}
}
