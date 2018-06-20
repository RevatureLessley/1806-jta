package common.banking.application;

import java.util.Scanner;

public class MainMenu {
	
	public MainMenu() {}
	
	public void start_menu() {
		
		//Variables//
		int input = 0; 
		String welcomemenu = "Hello, and welcome to your banking please input one of the following commands:"
							  + "\n 1 - Deposit"
							  + "\n 2 - Withdraw"
							  + "\n 3 - Logout";
		
		String commandmenu = "Please input a command:"
							  + "\n 1 - Deposit"
							  + "\n 2 - Withdraw"
							  + "\n 3 - Logout";
		//========//
		System.out.println(welcomemenu);
		Scanner command = new Scanner(System.in);
		input = command.nextInt();
		
		switch(input) {
		default: 
			System.out.println("Command not recognized, please try again"); 
			System.out.println(commandmenu);
			input = command.nextInt();
		case 1:
			System.out.println("Deposit amount placeholder");
			System.out.println(commandmenu);
			input = command.nextInt();
		case 2:
			System.out.println("Withdraw amount placeholder");
			System.out.println(commandmenu);
			input = command.nextInt();
		case 3:
			System.out.println("Logging you out. . .");
			break;
			}
			
		}
}
