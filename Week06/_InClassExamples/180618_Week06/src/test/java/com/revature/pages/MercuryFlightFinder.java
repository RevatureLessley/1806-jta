package com.revature.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MercuryFlightFinder {

	/*
	 * PageFactory is a class that aids in implementing Page Object Model.
	 * It seeks to inject the proper instances of web elements upon instantiation of
	 * a class. It provides a more efficient way to implement a POM as ooposed to 
	 * implementing it by manual means.
	 */
	@FindBy(xpath="//input[@name='tripType']")
	List<WebElement> rbTripType;
	@FindBy(xpath="//input[@name='servClass']")
	List<WebElement> rbClassType;
	@FindBy(xpath="//select[@name='passCount']/option")
	List<WebElement> pCount;
	@FindBy(xpath="//input[@name='findFlights']")
	WebElement submitFindFlights;
	
	public MercuryFlightFinder(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	public void selectOneWay(){
		rbTripType.get(1).click();
	}
	
	public void selectFirstClass(){
		rbClassType.get(2).click();
	}
	
	public void cycleClass(){
		for(WebElement e: rbTripType){
			e.click();
		}
	}
	
	public void selectTripType(int type){
		rbTripType.get(type).click();
	}
	
	public void selectClassType(int type){
		rbClassType.get(type).click();
	}
	
	public void cyclePassengers(){
		for(WebElement e: pCount){
			e.click();
		}
	}
	
	public void submitData(){
		submitFindFlights.click();
	}
}
