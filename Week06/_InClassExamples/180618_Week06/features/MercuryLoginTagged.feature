Feature: Mercy Tours Login
	I wish to login to Mercury tours using proper credentials
	
	Background: 
		Given I am at the Mercury Tours homepage
		
	#Use '@' to apply custom tag to the scenarios of your feature file.
	#Tags are the equivalent to 'groups of testNG
	
	@smoke @stuff
	Scenario: Logging into mercury tours
		When after inputting my username
		And password,
		But a user logs in
		Then the user is having a great time at the find flights page.
		
	@regression @stuff	
	Scenario Outline: Logging into mercury tours
		When a user inputs their "<username>" and "<password>"
		And then clicks submit
		Then the user finds themsleves at the find flight page.
		
		Examples: 
			|username		|password		|
			|bobbert		|bobbert		|
			|tropicana	|tropicana	|