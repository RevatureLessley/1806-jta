package com.revature.testrunners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
			features={"Features/MercuryLogin.feature"},
			glue={"com.revature.gluecode"}
		)
/*
 * A test runner is how you setup cucumber suites.
 * CucumberOptions is how we can set up the specific details of the 
 * suite.
 * 'features' should point to each feature file to utilze.
 * 'glue' should point to step implementations.
 * -NOTE: All files in the gluecode directory will be ran.
 */
public class TestRunner {

}
