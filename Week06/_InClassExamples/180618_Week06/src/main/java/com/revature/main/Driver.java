package com.revature.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

	static WebDriver driver;
	static String url = "http://newtours.demoaut.com/";
	
	public static void main(String[] args) throws InterruptedException {
		//CHROME
//		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
//		driver = new ChromeDriver();
//		
//		driver.get(url);
//		Thread.sleep(2000);
//		driver.quit(); //VERY IMPORTANT, 
		//you WILL have background processes you have to force quit if you do not
		//end an application with this.

		//FIREFOX
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.get(url);
		Thread.sleep(2000);
		
		checkTitle("Welcome: Mercury Tours");
		
		driver.quit();
	}
	
	public static void checkTitle(String expected){
		String testString = "TEST: ";
		String actual = driver.getTitle();
		
		if(!actual.equals(expected)){
			System.out.println(testString + "FAILED");
			System.out.println("EXPECTED: \'" + expected + "\'");
			System.out.println("ACTUAL: \'" + actual + "\'");
		}else{
			System.out.println(testString + "PASSED");
		}
	}

}
