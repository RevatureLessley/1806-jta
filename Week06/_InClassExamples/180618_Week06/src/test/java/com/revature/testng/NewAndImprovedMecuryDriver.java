package com.revature.testng;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.revature.pages.MercuryFlightFinder;
import com.revature.pages.MercuryLogin;

public class NewAndImprovedMecuryDriver {
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";
	public MercuryLogin loginPage;
	public MercuryFlightFinder findFlights;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(2500);
		driver.quit();
	}

	/*
	 * One way to ensure a specifc test execution order is to use the priority attribute
	 * of the annotation. Another strategy is to employ a dependency between two tests.
	 * Where, with priorities, if test1 fails, test2 still attempts execute. But with
	 * dependencies, test2 automatically fails if test1 fails.
	 */
	
	@Test
	public void confirmHomePage(){
		assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
	}
	
	//@Test(priority = 1)
	@Test (dependsOnMethods = {"confirmHomePage"})
	public void logInToMercury() {
		loginPage = new MercuryLogin(driver);
		loginPage.loginToMercury("bobbert", "bobbert");
		assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
	}
	
	//@Test(priority = 2)
	@Test(dependsOnMethods = {"logInToMercury"})
	public void findFlights() throws InterruptedException{
		findFlights = new MercuryFlightFinder(driver);
		findFlights.selectOneWay();
		findFlights.cyclePassengers();
		findFlights.selectFirstClass();
		Thread.sleep(1000);
		findFlights.submitData();
		assertEquals(driver.getTitle(), "Select a Flight: Mercury Tours");
	}
}
