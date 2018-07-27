package com.revature.testrunners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
			features = {"Features/MercuryLoginTagged.feature"},
			glue={"com.revature.gluecode"},
			tags={"@smoke,@regression"}
		)
public class TestRunnerTagged {
	/*
	 * When saying which tags to use in cucumber options,
	 * you have the following options:
	 * Excluding tags: tag={"~@tagname"}
	 * Running tests that have ALL the indicated tags only
	 * 	tags={"@tag1", "@tag2"}
	 * Running tests that have either/or tags:
	 * tags={"@tag1,@tag2"}
	 */
}
