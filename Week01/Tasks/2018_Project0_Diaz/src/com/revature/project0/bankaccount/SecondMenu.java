package com.revature.project0.bankaccount;

import java.util.Scanner;

public class SecondMenu {
	static double balance;
	static double balance2;
	static double balance3;
	static double receipt;
	static double receipt2;
	static double receipt3;
	static double temp;
	static String customerName;
	static String accountNumber;
	

	public SecondMenu(String cn, String an) {
		customerName = cn;
		accountNumber = an;
	}

	public static void deposit(double dc) {
		if(temp ==1) {	
		
			if(dc != 0) {
				balance = balance + dc;
				receipt = dc;
			}	
		}else if(temp == 2) {
			if(dc != 0) {
				balance2 = balance2 + dc;
				receipt2 = dc;
			}
		}else if(temp == 3) {
			if(dc != 0) {
				balance3 = balance3 + dc;
				receipt3 = dc;
			}
		}
	}
	
	public static void withdraw(double dc ) {
		if(temp == 1) {
			if(dc != 0) {
			balance = balance - dc;
			receipt = -dc;
			}
		}else if(temp == 2) {
			if(dc != 0) {
			balance2 = balance2 - dc;
			receipt2 = -dc;
			}
		}else if(temp == 3) {
			if(dc != 0) {
			balance3 = balance3 - dc;
			receipt3 = -dc;
			}
		}
		
	}

	public double getReceipt() {
		if(temp == 1) {
			if(receipt > 0){
				System.out.println("You Deposited " + receipt +  " today.");
			}else if(receipt < 0){
				System.out.println("You Withdraw " + Math.abs(receipt) + " today.");
			}else {
				System.out.println("You decided to do nothing today.");
			}
			
		}else if(temp == 2) {
			if(receipt2 > 0){
				System.out.println("You Deposited " + receipt +  " today.");
			}else if(receipt2 < 0){
				System.out.println("You Withdraw " + Math.abs(receipt2) + " today.");
			}else {
				System.out.println("You decided to do nothing today.");
			}
		}else if(temp == 3) {
			if(receipt3 > 0){
				System.out.println("You Deposited " + receipt +  " today.");
			}else if(receipt3 < 0){
				System.out.println("You Withdraw " + Math.abs(receipt3) + " today.");
			}else {
				System.out.println("You decided to do nothing today.");
			}
		}
		
		return receipt;
	}

	public void setReceipt(double receipt) {
		this.receipt = receipt;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public static void showSecondMenu() {
		
		Scanner in = new Scanner(System.in);
		int secondChoice =  0;
		int secondChoice2 = 0;
		
	
		
		
			intro2();
		secondChoice = in.nextInt();
		switch(secondChoice){
		default: 
			System.out.println(" Invalid entry please try again later. Pleaese try agan. ");
			break; 
		case 0:
			System.out.println(" Please come again.");
			BankMenu.menu();
			break;
		case 1:
			System.out.println(" How much would you like to deposit?");
			System.out.println("====================================");
			if(temp ==1) {
				double dc = in.nextDouble();
				deposit(dc);
				System.out.println("You deposited: " + dc + "\n");
				showSecondMenu();
				break;		
			}else if(temp == 2) {
				double dc3 = in.nextDouble();
				deposit(dc3);
				System.out.println("You deposited: " + dc3 + "\n");
				showSecondMenu();
				break;	
			}else if(temp == 3) {
				double dc5 = in.nextDouble();
				deposit(dc5);
				System.out.println("You deposited: " + dc5 + "\n");
				showSecondMenu();
				break;
			}
		case 2:
			System.out.println(" How much do you want to Withdraw ");
			System.out.println("====================================");
			double dc2 = in.nextDouble();
			if(temp ==1) {
				if(dc2 < balance) {
					withdraw(dc2);
					System.out.println("You Withdraw: " + dc2 + "");
				}else
					System.out.println("Insuffcient funds");			
				showSecondMenu();
			}else if(temp == 2) {
				if(dc2 < balance2) {
					withdraw(dc2);
					System.out.println("You Withdraw: " + dc2 + "");
					}
				showSecondMenu();
			}else if(temp == 3) {
				if(dc2 < balance3) {
					withdraw(dc2);
					System.out.println("You Withdraw: " + dc2 + "");
					}
				}else
					System.out.println("Insuffcient funds");			
				showSecondMenu();	
			break;
		case 3:
			if(temp ==1) {
				System.out.println(" Your Balance is:  " + (balance ) + "$");	
				showSecondMenu();
				break;
			}else if(temp == 2) {
				System.out.println(" Your Balance is:  " + (balance2 ) + "$");
				showSecondMenu();
				break;
			}else if(temp == 3) {
				System.out.println(" Your Balance is:  " + (balance3 ) + "$");
				showSecondMenu();
				break;
			}
		}

		
	}
	
	public static void intro() {
		System.out.println("=====Welcome To Revature TransUnion=====");
		System.out.println("");
		System.out.println("     Please select one of the following ");
		System.out.println("");
		System.out.println("     To Log In press 1 ");		
		System.out.println("     To create an account press 2");		
		System.out.println("     If you are an admin please press 3");
		System.out.println("     If you want to exit press 0");
	}
	
	public static void intro2() {
		System.out.println("=====Hello " + customerName + accountNumber);
		System.out.println("");
		System.out.println("     Please select one of the following ");
		System.out.println("");
		System.out.println("     Deposit Press 1 ");		
		System.out.println("     Withdraw Press 2");		
		System.out.println("     Check Balance Press 3");
		System.out.println("     If you want to exit press 0");
	}
	
	
}
