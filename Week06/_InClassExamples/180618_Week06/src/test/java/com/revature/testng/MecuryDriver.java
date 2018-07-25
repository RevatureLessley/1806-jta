package com.revature.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MecuryDriver {
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException{
		Thread.sleep(2500);
		driver.quit();
	}

	@Test
	public void logInToMercury() {
		//sendKeys() simulates typing.
		//click() simulates clicking.
		driver.findElement(By.name("userName")).sendKeys("bobbert");
		driver.findElement(By.name("password")).sendKeys("bobbert");
		driver.findElement(By.name("login")).click();
		assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
	}
}
