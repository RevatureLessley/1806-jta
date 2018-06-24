package com.revature.project0.bankaccount;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class BankMenu extends SecondMenu{
	final static Logger logger = Logger.getLogger(BankMenu.class);
	
	private static int adminPassword = 118034;
	private static double revatureTransUnion = 1_000_000;
	private static int i = 0;
	public BankMenu(){
		
	}
	
	public static void menu(int j) {
		Scanner in = new Scanner(System.in);
		int firstChoice =  0;
		
		
		NewAccount[] test = {
				new NewAccount(NewAccount.getFname(),NewAccount.getlName(),NewAccount.getAddress(),NewAccount.getPhone())		
				};
		NewAccount2[] test2 = {
				new NewAccount2(NewAccount2.getfName2(),NewAccount2.getlName2())
		};
		NewAccount3[] test3 = {
				new NewAccount3(NewAccount3.getfName3(),NewAccount3.getlName3())
		};
		
		TransUnion union = new TransUnion("RyantureTransUnion",test);
		logger.debug(test);
		TransUnion2 union2 = new TransUnion2("RyantureTransUnion",test2);
		logger.debug(test2);
		TransUnion3 union3 = new TransUnion3("RyantureTransUnion",test3);
		logger.debug(test3);
		
		
		try{
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("TransUnion.txt"));
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
		try{
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("TransUnion3.ser"));
			oos.writeObject(union3); 
		}catch(IOException e){
			e.printStackTrace();
		}
		
		intro();
		do {
		firstChoice = in.nextInt();
		switch(firstChoice){
		default: 
			System.err.println(" Invalid entry please try again later. Pleaese try agan. ");
			logger.error(null);
			break; 
		case 0:
			System.out.println("     Please come again.");
			logger.error(null);
			break;
		case 1:
			System.out.println(" What is you Account Number?");
			temp = in.nextInt();
			if( temp == 1) {
				System.out.println("PLease enter your password");
				int password = in.nextInt();
				if(password == 134) {
					SecondMenu.showSecondMenu();
				}
			}else if(temp == 2) {
				System.out.println("PLease enter your password");
				int password2 = in.nextInt();
				if(password2 == 135) 
					SecondMenu.showSecondMenu();
				else { 
					System.out.println("Inasdvaled entry returning to manin menu"+ "\n");
				}
			}else if(temp == 3){
				System.out.println("PLease enter your password");
				int password = in.nextInt();
				if(password == 136) 
					SecondMenu.showSecondMenu();
			}else {
				
				System.err.println("Invaled entry returning to manin menu"+ "\n");
			}
		
			menu(1);
			break;
		case 2:
			
			System.out.println(" Thanks for choseing Revature TransUnion.");
			if(i == 0){
				SecondMenu.temp = 1;
				i++;
				NewAccount.userInfor();
				System.out.println("Admin is checking you account info PLease stand by.....");
				///NEED TO ADD ADMIN INFO
				adminPassword = in.nextInt();
				if(adminPassword == 118034) {
				System.out.println("This is your new account");
				System.out.println(union);
				System.out.println("Your account number is 1");
				System.out.println("Your account password is 134");
				System.out.println("What is your opening balance: ");
				SecondMenu.balance = in.nextDouble();
				System.out.println("Please remember you info and do not share it");
				}else {
					System.out.println("You have not been aproved, try agin at a latter date");
					i = 0;
				}
				menu(1);
			}else if (i == 1) {
				SecondMenu.temp = 2;
				i++;
				NewAccount2.userInfor2();
				System.out.println("Admin is checking you account info PLease stand by.....");
				///NEED TO ADD ADMIN INFO
				adminPassword = in.nextInt();
				if(adminPassword == 118034) {
				System.out.println("This is your new account");
				System.out.println(union2);
				System.out.println("Your account number is 2");
				System.out.println("Your account password is 135");
				System.out.println("What is your opening balance: ");
				SecondMenu.balance2 = in.nextDouble();
				System.out.println("Please remember you info and do not share it");
				}else {
					System.out.println("You have not been aproved, try agin at a latter date");
					i = 1;
				}
				menu(1);
			}else if(i == 2){
				SecondMenu.temp = 3;
				i++;
				NewAccount3.userInfor3();
				System.out.println("Admin is checking you account info PLease stand by.....");
				///NEED TO ADD ADMIN INFO
				adminPassword = in.nextInt();
				if(adminPassword == 118034) {
				System.out.println("This is your new account");
				System.out.println(union3);
				System.out.println("Your account number is 3");
				System.out.println("Your account password is 136");
				System.out.println("What is your opening balance: ");
				SecondMenu.balance3 = in.nextDouble();
				System.out.println("Please remember you info and do not share it");
				}else {
					System.err.println("You have not been aproved, try agin at a latter date");
					i = 2;
				}
				menu(1);
			}else {
				System.err.println("Contact Adnin back servers are out of sapce");
				logger.error(null);
				menu(1);
			}
			break;
		case 3:
			
			
			System.out.println("=====Hello Admin please enter you password=====");
			adminPassword = in.nextInt();
			if(adminPassword == 118034) {
			System.out.println("====What would you like to do Today boss====");
			System.out.println("Press 0		How Much money does Revature TransUnionhave ");
			System.out.println("Press 1 	To see all accounts on the Server");
			int menu2 = in.nextInt();
			switch(menu2){
			default: 
				System.out.println(" Invalid entry please try again later. Pleaese try agan. ");
				break; 
			case 0:
				
				System.out.println("We currentle have: " );
				System.out.println(SecondMenu.addToBank(revatureTransUnion,balance, balance2, balance3));
				System.out.println("\n");
				menu(1);
				break;
			case 1:
				System.out.println("All acounts");
				System.out.println(union+"[Balance = "+balance+"]");
				System.out.println(union2+"[Balance = "+balance2+"]");
				System.out.println(union3+"[Balance = "+balance3+"]");
				menu(1);
				break;	
				}
			}else {
				System.err.println("Access Denied!!!!!");
				logger.error(null);
				menu(1);
			}
			break;
		}
		
		
		}while(firstChoice != 0);
		
		System.out.println("    END OF EXECUTION");
		
	}
	
}