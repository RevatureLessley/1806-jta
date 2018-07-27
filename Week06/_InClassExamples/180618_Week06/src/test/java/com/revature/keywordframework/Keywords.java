package com.revature.keywordframework;

import java.util.List;

import org.openqa.selenium.WebDriver;

import cucumber.api.DataTable;

public class Keywords {
	public static void performAction(WebDriver driver, DataTable table) throws Exception{
		List<List<String>> data = table.raw();
	}
	
	
}
