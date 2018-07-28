package com.revature.testng;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.pages.MercuryFlightFinder;
import com.revature.pages.MercuryLogin;

public class NewAndImprovedMecuryDriver2 {
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
	
	@Test(dependsOnMethods={"findFlights"}, dataProvider="mercData")
	public void doEverything(String username,
			String password,
			double tripType,
			double classType){
		try{
			driver.findElement(By.xpath("//a[text()='SIGN-OFF']")).click();
		}catch(Exception e){
			System.out.println("woops.");
		}
		
		driver.findElement(By.xpath("//a[text()='Home']")).click();
		
		assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
		MercuryLogin mc = new MercuryLogin(driver);
		mc.loginToMercury(username, password);
		assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");
		MercuryFlightFinder mff = new MercuryFlightFinder(driver);
		mff.selectClassType((int)classType);
		mff.selectTripType((int)tripType);
		mff.submitData();
		assertEquals(driver.getTitle(), "Select a Flight: Mercury Tours");
		
	}
	
	
	@DataProvider(name="mercData")
	public Object[][] provideAccountDetailsDynamic() throws Exception{
		Object[][] data = null;
		File file = new File("src/test/resources/mercuryData.xlsx");
		FileInputStream fis = new FileInputStream(file);
		
		try(Workbook workbook = new XSSFWorkbook(fis)){
			Sheet sheet = workbook.getSheet("Sheet1");
			int rowcount = sheet.getLastRowNum();
			data = new Object[rowcount][4];
			
			for(int i = 1; i <= rowcount; i++){
				Row row = sheet.getRow(i);
				data[i-1] = new Object[]{
						row.getCell(0).getStringCellValue(),
						row.getCell(1).getStringCellValue(),
						row.getCell(2).getNumericCellValue(),
						row.getCell(3).getNumericCellValue()
				};
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return data;
	}
}
