package common.banking.application;

import java.util.Scanner;

public class Main_Menu {
//====================================Variables========================================================================//
	int INPUT = 0; 
	int LGN_LP = 0;
	float fINPUT = 0.0f;
	Scanner COMMAND = new Scanner(System.in);
	data_storage ACCOUNTER = new data_storage();
	String STRING = null;
	StringBuffer sBuff = new StringBuffer();
	customer CLIENT = new customer("ERROR", "CUSTOMER", "NOT FOUND", sBuff);
	final String WELCOM_MENU = "Hello, and welcome to your banking please input one of the following commands:"
						  + "\n 1 - Deposit"
						  + "\n 2 - Withdraw"
						  + "\n 3 - Logout"
						  + "\n 4 - Get Statement";

	final String COMMAND_MENU = "Please input a command:"
						  + "\n 1 - Deposit"
						  + "\n 2 - Withdraw"
						  + "\n 3 - Logout"
						  + "\n 4 - Get Statement";
//==================================================================================================================//
	
	//Constructor
	public Main_Menu() {}
	
	//Login Menu
	/**Starts the login menu as if the login is successful returns a
	 * object of the customer class with all the information in 
	 * the SER file*/
	public customer login_menu() {
		//====================================LOGIN/ACCOUNT CREATION=============================================//
		System.out.println("Hello press 1 to login and 2 to create an account and 3 to exit the program and 5 for admin activation");
		INPUT = COMMAND.nextInt();
		
		while(true) {
		if(INPUT != 0 && INPUT != 2 && INPUT !=1 && INPUT != 3 && INPUT != 5) {
			System.out.println("COMMAND not recognized please enter 1 to login or 2 to create an account or 3 to exit or 5 for admin activation");
			INPUT = COMMAND.nextInt();
		}
		if(INPUT == 0) {
			System.out.println("Hello press 1 to login and 2 to create an account and 3 to exit the program or 5 for admin activation");
			INPUT = COMMAND.nextInt();
		}
		if (INPUT == 1) {
			System.out.println("Welcome to login please enter your first name:");
			CLIENT.setFirst_name(COMMAND.next());
			System.out.println("Please enter your last name");
			CLIENT.setLast_name(COMMAND.next());
			System.out.println("Please enter your social security number no space or '-'");
			CLIENT.setSs_number(COMMAND.next());
			System.out.println("Please enter your password");
			CLIENT.setPassword(sBuff.replace(0, sBuff.length(), COMMAND.next()));
			if(ACCOUNTER.check_account(CLIENT)) {
				System.out.println("Account found loggin you in. . .");
				if(ACCOUNTER.open_account(CLIENT, CLIENT.getFirst_name(), CLIENT.getLast_name(), CLIENT.getSs_number(), CLIENT.getPassword())) {
					break;
				}
				else {
					  System.out.println("Error in logging you in please contact Admin");
				      INPUT = 0;
				     }
			}
		
			else {
				System.out.println("Account not found press 1 to try to relogin or 0 to enter main menu");
				INPUT = COMMAND.nextInt();
			}
		}
			
		if (INPUT == 2) {
			System.out.println("Welcome to account creation please enter your first name:");
			CLIENT.setFirst_name(COMMAND.next());
			System.out.println("Please enter your last name");
			CLIENT.setLast_name(COMMAND.next());
			System.out.println("Please enter your social security number no space or '-'");
			CLIENT.setSs_number(COMMAND.next());
			System.out.println("Please enter a password");
			CLIENT.setPassword(sBuff.replace(0, sBuff.length(), COMMAND.next()));
			
			if(ACCOUNTER.check_account(CLIENT)) {
				System.out.println("Account already made please contact an Admin for assistance");
				INPUT = 0;
			}
			
			else {
			System.out.println("Here is the information you entered");
			CLIENT.statement();
			System.out.println("Is this correct? Enter 4 for yes and 2 for no");
			INPUT = COMMAND.nextInt();
			ACCOUNTER.create_client(CLIENT);}
		}
		
		if (INPUT == 3) {
			System.out.println("Goodbye . . .");
			COMMAND.close();
			System.exit(0);
		}
		
		if (INPUT == 4) {
			System.out.println("Your account needs to be approved by an admin before activating, "
					+ "please wait for a confirmation from an admin");
			INPUT = 0;
		}
		
		if(INPUT == 5) {
			System.out.println("Welcome, please login to become an Admin:");
			System.out.println("Please enter your first name");
			CLIENT.setFirst_name(COMMAND.next());
			System.out.println("Please enter your last name");
			CLIENT.setLast_name(COMMAND.next());
			System.out.println("Please enter your social security number no space or '-'");
			CLIENT.setSs_number(COMMAND.next());
			System.out.println("Please enter your password");
			CLIENT.setPassword(sBuff.replace(0, sBuff.length(), COMMAND.next()));
			
			//LOGIN TO SET UP ADMIN
			if (ACCOUNTER.check_account(CLIENT)) {
				ACCOUNTER.open_account(CLIENT, CLIENT.getFirst_name(), CLIENT.getLast_name(), CLIENT.getSs_number(), CLIENT.getPassword());
				System.out.println("Please enter the Admin authorization password");
				STRING = COMMAND.next();
				CLIENT.setAdmin(STRING);
				CLIENT.setActivated(true);
				ACCOUNTER.close_account(CLIENT);
				INPUT = 0;
			}
			else {
				System.out.println("Account not found please create an account"); 
				INPUT = 0;
				}
		}
		
		}
			
		return CLIENT;
	}
	
	//Checks to see if the account is activated and what kind of account it is
	/**This method checks the paramter and sees if the account is activated
	 * and which type of CLIENT it is (admin or not)*/
	public void start_menu(customer CLIENT) 
	{
		
		if(!CLIENT.isActivated()){
			System.out.println("Your account is not activated. Please have an admint activate it");
		}
		
		else if(CLIENT.isAdmin()) {
			admin_menu(CLIENT);
		}
		
		else if(!CLIENT.isAdmin()){
			customer_menu(CLIENT);
		}

			
	}
	
	//Customer Menu
	/**This is the menu a customer gets to see,
	 * it does not allow them to access the activate account*/
	public void customer_menu(customer CLIENT) {
		
		//========Main menu if statements to go to different parts of the menu==============//
		System.out.println(WELCOM_MENU);
		INPUT = COMMAND.nextInt();


		while(true) 
			{
			if ( INPUT == 1) 
				{
				System.out.println("Enter an amount to be deposited");
				fINPUT = COMMAND.nextFloat();
				CLIENT.deposit(fINPUT);
				System.out.println(COMMAND_MENU);
				INPUT = COMMAND.nextInt();
				}
			if (INPUT == 2) 
				{
				System.out.println("Enter an amount to be withdrawn");
				fINPUT = COMMAND.nextFloat();
				CLIENT.withdraw(fINPUT);
				System.out.println(COMMAND_MENU);
				INPUT = COMMAND.nextInt();
				}
			if (INPUT == 3) 
				{
				ACCOUNTER.close_account(CLIENT);
				break;
				}
			if (INPUT == 4)
				{
				CLIENT.statement();
				System.out.println(COMMAND_MENU);
				INPUT = COMMAND.nextInt();	
				}
			if (INPUT != 3 && INPUT != 2 && INPUT !=1 ) 
				{
				System.out.println("COMMAND not recognized, please try again"); 
				System.out.println(COMMAND_MENU);
				INPUT = COMMAND.nextInt();	
				}
			}
		}

	//Admin menu
	/**This is the admin menu, it has the same functions
	 * as the CLIENT except it also has the added ability
	 * to activate accounts, so long as they have the proper knowledge*/
	public void admin_menu(customer CLIENT) {
		//========Main menu for admin if statements to go to different parts of the menu==============//
			System.out.println(WELCOM_MENU);
			System.out.println("5 - Authorize Account");
			INPUT = COMMAND.nextInt();

			while(true) 
				{
				if (INPUT == 0) 
					{
					System.out.println(WELCOM_MENU);
					System.out.println("5 - Authorize Account");
					INPUT = COMMAND.nextInt();
					}
				if ( INPUT == 1) 
					{
					System.out.println("Enter an amount to be deposited");
					fINPUT = COMMAND.nextFloat();
					CLIENT.deposit(fINPUT);
					System.out.println(COMMAND_MENU);
					INPUT = COMMAND.nextInt();
					}
				if (INPUT == 2) 
					{
					System.out.println("Enter an amount to be withdrawn");
					fINPUT = COMMAND.nextFloat();
					CLIENT.withdraw(fINPUT);
					System.out.println(COMMAND_MENU);
					INPUT = COMMAND.nextInt();
					}
				if (INPUT == 3) 
					{
					ACCOUNTER.close_account(CLIENT);
					System.out.println("Logging you out. . .");
					break;
					}
					if (INPUT == 4)
					{
					CLIENT.statement();
					System.out.println(COMMAND_MENU);
					INPUT = COMMAND.nextInt();	
					}
				
				if (INPUT == 5)
					{
						authorize_account();
						INPUT = 0;
					}
					
				if (INPUT != 5 && INPUT != 4 && INPUT != 3 && INPUT != 2 && INPUT != 1 && INPUT != 0)
					{
					System.out.println("COMMAND not recognized, please try again"); 
					System.out.println(COMMAND_MENU);
					INPUT = COMMAND.nextInt();	
					}
				}
}

	//Method to authorize accounts
	/**This is the method the admin menu calls on
	 * to activate an account*/
	public void authorize_account() {
		System.out.println("Please enter the first name of the account . . ");
		String fname = COMMAND.next();
		System.out.println("Please enter the last name of the account . . ");
		String lname = COMMAND.next();
		System.out.println("Please enter the social security number (no spaces or dashes) of the account . . ");
		String ssnum = COMMAND.next();
		ACCOUNTER.activate(fname, lname, ssnum);
		
	}







}



