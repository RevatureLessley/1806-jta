package com.revature.gluecode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class MercuryDriverUtility {
	public static WebDriver driver;
	
	/*
	 * 
	 */
	@Before
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://newtours.demoaut.com");
	}
	
	@After
<<<<<<< HEAD
	public void teardown(){
		if(driver!=null) {
=======
	public void teardown() throws InterruptedException{
		if(driver!=null){
>>>>>>> 748d8c1495f57f0f0c20192f003183437416677c
			driver.quit();
		}
	}
}
