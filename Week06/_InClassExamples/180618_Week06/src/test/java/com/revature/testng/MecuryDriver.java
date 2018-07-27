package com.revature.testng;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MecuryDriver {
	public static WebDriver driver;
	public final String url = "http://newtours.demoaut.com/";
	WebDriverWait wait;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		// We can configure our driver's implicit wait as soon as it is
		// instantiated.
		// For any element, wait 3 seconds before determining a fail.
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		/*
		 * Implicit waits are applied by default in this case. But should we
		 * decided that there is a specific point in time I want to wait, we
		 * have to use explicit waits. These are one time uses and must be
		 * executed at a specific point.
		 */
		wait = new WebDriverWait(driver, 7);

		driver.get(url);
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(2500);
		driver.quit();
	}

	@Test
	public void logInToMercury() {
		// sendKeys() simulates typing.
		// click() simulates clicking.

		/*
		 * Out findElement aims to reference an element on the page. It takes a
		 * parameter of the static class "By". "By" represents our element
		 * selector. Selenium, by default, offers several selectors.
		 * 
		 * -Name -Id -LinkText -PartialLinkText -TagName -ClassName --And most
		 * important (In my opinion) -Xpath -CssSelector
		 */
		// Implicit Wait test
		// driver.findElement(By.name("bobbert"));

		// Explicit Wait test
		// WebElement e1 = wait.until(
		// ExpectedConditions.visibilityOfElementLocated(By.name("bobbert")));

		// fluentWait
		/*
		 * Wait fwait = new FluentWait(driver) .withTimeout(7, TimeUnit.SECONDS)
		 * .pollingEvery(750, TimeUnit.SECONDS)
		 * .ignoring(NoSuchElementException.class); WebElement e1 =
		 * (WebElement)fwait.until(
		 * ExpectedConditions.visibilityOfElementLocated(By.name("bobbert")));
		 */
		/*
		 * Unlike the explicit wait, which will check to see if an element is
		 * available every 500 milliseconds, fluentWait offers us the ability to
		 * customize how frequent the element checks during the wait. If having
		 * check for element existence every half second is too frequent, I can
		 * use fluentwaits to change the poll times to be longer OR shorter.
		 */
		driver.findElement(By.name("userName")).sendKeys("bobbert");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("bobbert");
		driver.findElement(By.name("login")).click();
		assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");

		// This grabs a collection of elements.
		List<WebElement> els = driver.findElements(By.name("tripType"));
		for (WebElement e : els) {
			e.click();
		}
		driver.findElement(By.name("findFlights")).click();
		els = driver.findElements(By.name("outFlight"));
		for(WebElement e: els){
			e.click();
		}
		els = driver.findElements(By.name("inFlight"));
		for(WebElement e: els){
			e.click();
		}
		driver.findElement(By.name("reserveFlights")).click();
		//grab all items from a dropdown list.
		els = driver.findElements(By.xpath("//select[@name='cc_exp_dt_yr']/option"));
		for(WebElement e : els){
			e.click();
			
			if(ExpectedConditions.alertIsPresent().apply(driver)!=null){
				driver.switchTo().alert().accept();
			}
			
			//driver.getWindowHandles() Gets unique IDs for all open tabs
			//Useful for switching between the two.
		}
		
	}
}
