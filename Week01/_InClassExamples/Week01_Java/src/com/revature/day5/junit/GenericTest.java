package com.revature.day5.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * JUnit  is a testing tool for unit tests.
 * A unit test is the most micro level of testing that you can perform on an application
 * The goal of unit testing is to have a test for every single method in the application
 * to ensure it works as expected before you even bother running it.
 */

public class GenericTest {

	//BeforeClass methods should aim to set up the global environment your tests
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("===BEFORE CLASS===");
	}

	//Should aim to perform a global tear down of all test data. (IE, here is where you
	//should delete any test data that adds data in the database.
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("===AFTER CLASS===");
	}

	//Before should be used to set up the environment for each individual test
	@Before
	public void setUp() throws Exception {
		System.out.println("===BEFORE===");
	}

	//After should be used to tear down the environment for each individual test
	@After
	public void tearDown() throws Exception {
		System.out.println("===AFTER===");
	}
	//indicates a specific test to be run
	@Test
	public void test1() {
		System.out.println("===TEST1===");
		fail("Not yet implemented");
	}
	@Test
	public void test2() {
		System.out.println("===TEST2===");
		fail("Not yet implemented");
	}
	@Test
	public void test3() {
		System.out.println("===TEST3===");
		fail("Not yet implemented");
	}

}
