package com.revature.gluecode;

import static com.revature.gluecode.MercuryDriverUtility.driver;
import static org.junit.Assert.assertEquals;

import com.revature.pages.MercuryLogin;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MercuryLoginTaggedGlueCode {
	public static MercuryLogin lp;
	@Given("^I am at the Mercury Tours homepage$")
	public void i_am_at_the_Mercury_Tours_homepage() throws Throwable {
		assertEquals("Welcome: Mercury Tours", driver.getTitle());
	}

	@When("^after inputting my username$")
	public void after_inputting_my_username() throws Throwable {
	   lp = new MercuryLogin(driver);
	   
	   lp.inputUsername("bobbert");
	   assertEquals("bobbert", "bobbert");

	}

	@When("^password,$")
	public void password() throws Throwable {
		   lp.inputPassword("bobbert");
	}

	@When("^a user logs in$")
	public void a_user_logs_in() throws Throwable {
	    lp.submitLogic();
	}

	@Then("^the user is having a great time at the find flights page\\.$")
	public void the_user_is_having_a_great_time_at_the_find_flights_page() throws Throwable {
		assertEquals("Find a Flight: Mercury Tours:", driver.getTitle());
	}

	@When("^a user inputs their \"([^\"]*)\" and \"([^\"]*)\"$")
	public void a_user_inputs_their_and(String username, String password) throws Throwable {
	    lp.inputUsername(username);
	    lp.inputPassword(password);
	}

	@When("^then clicks submit$")
	public void then_clicks_submit() throws Throwable {
	    lp.submitLogic();
	}

	@Then("^the user finds themselves at the find flight page\\.$")
	public void the_user_finds_themselves_at_the_find_flight_page() throws Throwable {
		assertEquals("Find a Flight: Mercury Tours:", driver.getTitle());
	}
}
