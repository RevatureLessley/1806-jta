package com.revature.project0.bankaccount;

import java.util.Scanner;

public class SecondMenu {
	static double balance;
	static double receipt;
	static String customerName;
	static String accountNumber;
	

	public SecondMenu(String cn, String an) {
		customerName = cn;
		accountNumber = an;
	}

	public static void deposit(double dc) {
		if(dc != 0) {
			balance = balance + dc;
			receipt = dc;
		}	
	}
	
	public static void withdraw(double dc ) {
		if(dc != 0) {
			balance = balance - dc;
			receipt = -dc;
		}
	}

	public double getReceipt() {
		if(receipt > 0){
			System.out.println("You Deposited " + receipt +  " today.");
		}else if(receipt < 0){
			System.out.println("You Withdraw " + Math.abs(receipt) + " today.");
		}else {
			System.out.println("You decided to do nothing today.");
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
		
		System.out.println("=====Hello " + customerName + accountNumber);
		System.out.println("");
		System.out.println("     Please select one of the following ");
		System.out.println("");
		System.out.println("     Deposit Press 1 ");		
		System.out.println("     Withdraw Press 2");		
		System.out.println("     Check Balance Press 3");
		System.out.println("     If you want to exit press 0");
		
		do {
		
		secondChoice = in.nextInt();
		switch(secondChoice){
		default: 
			System.out.println(" Invalid entry please try again later. Pleaese try agan. ");
			break; 
		case 0:
			System.out.println(" Please come again.");
			break;
		case 1:
			System.out.println(" How much would you like to deposit?");
			System.out.println("====================================");
			double dc = in.nextInt();
			deposit(dc);
			System.out.println("You deposited: " + dc + "");
			break;
		case 2:
			System.out.println(" How much do you want to Withdraw ");
			System.out.println("====================================");
			double dc2 = in.nextInt();
			withdraw(dc2);
			System.out.println("You deposited: " + dc2 + "");
			break;
		case 3:
			System.out.println(" Your Balance is:  " + (balance ) + "$");
		
		}
		
		
		}while(secondChoice != 0);
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
	
	
}
