package com.revature.keywordframework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.DataTable;

public class Keywords {
	public static void preformAction(WebDriver driver, DataTable table) throws Exception{
		List<List<String>> data = table.raw();
		
		for(List<String> string : data) {
			if(string.get(2).equals("text")) {
				driver.findElement(By.name(string.get(0))).sendKeys(string.get(1));
			}else if(string.get(2).equals("text")) {
				driver.findElement(By.name(string.get(0))).click();
			}else if(string.get(2).equals("text")) {
				driver.findElement(By.xpath("//select[@name='" + string.get(0) +
						"']/option[@value='" + string.get(1) + "']"
						)).click();
			}
		}
	}
	
}
