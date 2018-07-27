package com.revature.gluecode;

import static com.revature.gluecode.MercuryDriverUtility.driver;
import static org.junit.Assert.assertEquals;

import com.revature.pages.MercuryLogin;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MercuryLoginGlue {
	public static MercuryLogin lp;
	
	@Given("^a user is at the login screen for mercury tours\\.$")
	public void a_user_is_at_the_login_screen_for_mercury_tours() throws Throwable {
	    assertEquals("Welcome: Mercury Tours", driver.getTitle());
	}

	@When("^a user shall input a \"([^\"]*)\" and a \"([^\"]*)\" and click submit\\.$")
	public void a_user_shall_input_a_and_a_and_click_submit(String username, String password) throws Throwable {
	    lp = new MercuryLogin(driver);
	    lp.loginToMercury(username, password);
	}

	@Then("^a user shall be redirected to the find flights page\\.$")
	public void a_user_shall_be_redirected_to_the_find_flights_page() throws Throwable {
	    assertEquals("Find a Flight: Mercury Tours:", driver.getTitle());
	}
}
