package common.banking.application;

import java.util.Scanner;

public class MainMenu {
	//Variables//
	int input = 0; 
	int login_loop = 0;
	float finput = 0.0f;
	Scanner command = new Scanner(System.in);
	data_storage accounter = new data_storage();
	StringBuffer sbuff = new StringBuffer();
	customer client = new customer("ERROR", "CUSTOMER", "NOT FOUND", sbuff);
	StringBuffer sbuffer = new StringBuffer();
	final String welcomemenu = "Hello, and welcome to your banking please input one of the following commands:"
						  + "\n 1 - Deposit"
						  + "\n 2 - Withdraw"
						  + "\n 3 - Logout"
						  + "\n 4 - Get Statement";

	
	final String commandmenu = "Please input a command:"
						  + "\n 1 - Deposit"
						  + "\n 2 - Withdraw"
						  + "\n 3 - Logout"
						  + "\n 4 - Get Statement";

	
	//Constructor
	public MainMenu() {}
	
	public customer login_menu() {
		//====================================LOGIN/ACCOUNT CREATION=============================================//
		System.out.println("Hello press 1 to login and 2 to create an account and 3 to exit the program");
		input = command.nextInt();
		
		while(true) {
		if(input != 0 && input != 2 && input !=1 && input != 3) {
			System.out.println("Command not recognized please enter 1 to login or 0 to create an account");
			input = command.nextInt();
		}
		if(input == 0) {
			System.out.println("Hello press 1 to login and 2 to create an account and 3 to exit the program");
			input = command.nextInt();
		}
		if (input == 1) {
			System.out.println(input);
			System.out.println("Welcome to login please enter your first name:");
			client.setFirst_name(command.next());
			System.out.println("Please enter your last name");
			client.setLast_name(command.next());
			System.out.println("Please enter your social security number no space or '-'");
			client.setSs_number(command.next());
			System.out.println("Please enter your password");
			client.setPassword(sbuff.replace(0, sbuff.length(), command.next()));
			if(accounter.check_account(client)) {
				System.out.println("Account found loggin you in. . .");
				if(accounter.open_account(client, client.getFirst_name(), client.getLast_name(), client.getSs_number(), client.getPassword())) {
					break;
				}
				else {input = 0;}
			}
		
			else {
				System.out.println("Account not found press 1 to try to relogin or 0 to enter main menu");
				input = command.nextInt();
			}
		}
			
		if (input == 2) {
			System.out.println("Welcome to account creation please enter your first name:");
			client.setFirst_name(command.next());
			System.out.println("Please enter your last name");
			client.setLast_name(command.next());
			System.out.println("Please enter your social security number no space or '-'");
			client.setSs_number(command.next());
			System.out.println("Please enter a password");
			client.setPassword(sbuff.replace(0, sbuff.length(), command.next()));
			
			if(accounter.check_account(client)) {
				System.out.println("Account already made please contact an Admin for assistance");
				input = 0;
			}
			
			else {
			System.out.println("Here is the information you entered");
			client.statement();
			System.out.println("Is this correct? Enter 4 for yes and 2 for no");
			input = command.nextInt();
			accounter.create_client(client);}
		}
		
		if (input == 3) {
			System.out.println("Goodbye . . .");
			command.close();
			System.exit(0);
		}
		
		if (input == 4) {
			System.out.println("Your account needs to be approved by an admin before activating, "
					+ "please wait for a confirmation from an admin");
			input = 0;
		}
		
		}
			
		return client;
	}
	
	
	public void start_menu(customer client) 
	{
	//========Main menu switch statements to go to different parts of the menu==============//
	System.out.println(welcomemenu);
	input = command.nextInt();
	
	
	while(true) 
		{
		if ( input == 1) 
			{
			System.out.println("Enter an amount to be deposited");
			finput = command.nextFloat();
			client.deposit(finput);
			System.out.println(commandmenu);
			input = command.nextInt();
			}
		if (input == 2) 
			{
			System.out.println("Enter an amount to be withdrawn");
			finput = command.nextFloat();
			client.withdraw(finput);
			System.out.println(commandmenu);
			input = command.nextInt();
			}
		if (input == 3) 
			{
			accounter.close_account(client);
			break;
			}
		if (input == 4)
			{
			client.statement();
			System.out.println(commandmenu);
			input = command.nextInt();	
			}
		if (input != 3 && input != 2 && input !=1 ) 
			{
			System.out.println("Command not recognized, please try again"); 
			System.out.println(commandmenu);
			input = command.nextInt();	
			}
		}
			
	}
}
