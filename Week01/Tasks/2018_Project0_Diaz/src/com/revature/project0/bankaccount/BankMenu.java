package com.revature.project0.bankaccount;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class BankMenu extends SecondMenu{
	
	//BankMenu bankmenu = new BankMenu("zack","1180");
	
	public BankMenu(String cn, String an){
		super(cn, an);
	
	}
	
	
	
	public static void menu() {
		Scanner in = new Scanner(System.in);
		int firstChoice =  0;
		
		
		NewAccount[] test = {
				new NewAccount(NewAccount.getFname(),NewAccount.getlName())		
				};
		NewAccount2[] test2 = {
				new NewAccount2(NewAccount2.getfName2(),NewAccount2.getlName2(),NewAccount2.getaccountNummber2())
		};
		
		TransUnion union = new TransUnion("RyantureTransUnion",test);
		TransUnion2 union2 = new TransUnion2("RyantureTransUnion",test2);
		
		try{
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("TransUnion.ser"));
			oos.writeObject(union); 
		}catch(IOException e){
			e.printStackTrace();
		}
		
		try{
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("TransUnion2.ser"));
			oos.writeObject(union2); 
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
		
		int i = 0;
		intro();
		
		do {
		
		firstChoice = in.nextInt();
		switch(firstChoice){
		default: 
			System.out.println(" Invalid entry please try again later. Pleaese try agan. ");
			break; 
		case 0:
			System.out.println(" Please come again.");
			break;
		case 1:
			System.out.println(" What is you Account Number?");
			int temp = in.nextInt();
			if( temp == 1) {
				System.out.println("PLease enter your password");
				int password = in.nextInt();
				if(password == 135) {
					SecondMenu.showSecondMenu();
				}else
					System.out.println("Invaled entry returning to manin menu"+ "\n");
			}
			
			intro();
			break;
		case 2:
			System.out.println(" Thanks for choseing Revature TransUnion.");
			int  adminPassword = 0;
			if(i == 0){
				i++;
				NewAccount.userInfor();
				System.out.println("Admin is checking you account info PLease stand by.....");
				
				if(adminPassword == 118034) {
				System.out.println("This is your new account");
				System.out.println(union);
				System.out.println("Your account number is 1");
				System.out.println("Your account password is 135");
				System.out.println("What is your opening balance: ");
				SecondMenu.balance = in.nextDouble();
				System.out.println("Please remember you info and do not share it");
				}
				
				
				
				
			}else if (i == 1) {
				NewAccount2.userInfor2();
				System.out.println("This is your new account");
				System.out.println(union2);
			
			}else {		
				intro();
			}
			intro();
			break;
		case 3:
			System.out.println(" Hello Admin please enter you password. ");
			break;
		}
		
		
		}while(firstChoice != 0);
		
		System.out.println("END OF EXECUTION");
		System.out.println(union);
		System.out.println(union2);
	}
	
}
