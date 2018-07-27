package com.revature.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/*
 * With TestNG, we get access to more before/after options than our jUnit predecessor.
 * 
 * should you utilize other classes within the test file, the before/after class annotation
 * will trigger. 
 * 
 * should you utilize multiple test classes within the same calss file (IE SUITE)
 * the before/after test annotation will trigger.
 * 
 * Should you utilize multiple suites, the before/after suite annotations trigger.
 * 
 * Note: There is a 5th before/after annotation called before/after group.
 * TestNg allows you to make a custom group tag and apply them to groupings of methods.
 * Before and after each specific group executes, these annotations trigger.
 */
public class NewTest {
  @Test
  public void f() {
	  System.out.println("TESTf");
  }
  @Test(enabled = false) //SKIP A TEST
  public void g() {
	  System.out.println("TESTg");
  }
  @Test
  public void h() {
	  System.out.println("TESTh");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("BEFORE METHOD");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("AFTER METHOD");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("BEFORE CLASS");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("AFTER CLASS");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("BEFORE TEST");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("AFTER TEST");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("BEFORE SUITE");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("AFTER SUITE");
  }

}
