package com.revature.project0.bankaccount;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import org.apache.log4j.Logger;
/**
 * Class BankMenu sets up the first menu to take usser input and it extends
 * SecondMenu to grab the information form the Supper Class
 * @author Zachary Diaz
 *
 */
public class BankMenu extends SecondMenu{
	
	final static Logger logger = Logger.getLogger(BankMenu.class);
	private static double revatureTransUnion = 1_000_000;// The starting amount the bank has in its vault
	private static int i = 0;// sets up a count for the number of accounts that is currently set 
	Scanner in = new Scanner(System.in);// useer input
	/**
	 * menu is to take set up the three accounts ready to be save into an array 
	 * and then implements the first menu
	 */
	public static void menu() {
		Scanner in = new Scanner(System.in);// useer input
		int firstChoice =  0; // this int is for the switch statement
		
		//user accounts is only set up for a max of three, have not figured out how to make this easier
		
		NewAccount[] test = {
				new NewAccount(NewAccount.getFname(),NewAccount.getlName(),NewAccount.getAddress(),NewAccount.getPhone())		
				};
		NewAccount2[] test2 = {
				new NewAccount2(NewAccount2.getfName2(),NewAccount2.getlName2(),NewAccount2.getAddress2(),NewAccount2.getPhone2())
		};
		NewAccount3[] test3 = {
				new NewAccount3(NewAccount3.getfName3(),NewAccount3.getlName3(),NewAccount3.getAddress3(),NewAccount3.getPhone3())
		};
		
		TransUnion union = new TransUnion("RyantureTransUnion",test);
		logger.debug(test);
		TransUnion2 union2 = new TransUnion2("RyantureTransUnion",test2);
		logger.debug(test2);
		TransUnion3 union3 = new TransUnion3("RyantureTransUnion",test3);
		logger.debug(test3);
		
		
		try{	// all try catch methods here help serilize the information into arrays 
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
		
		intro();					// intro is at the bottom of SecondMenue class
		do {						// Do while statement sets up the first switch that will show the menu and its possibilities
		firstChoice = in.nextInt();
		switch(firstChoice){
		default: // default case
			System.err.println("Invalid entry please try again later. Pleaese try agan. ");
			logger.error(null);
			break; 
		case 0:// case 0 logs out of application for some reason i have to exit three times to complete get out of application 
			System.out.println("     Please come again.");
			logger.error(null);
			break;
		case 1:// case 1 logs into account must know account info that is displayed when creating the account
			System.out.println("What is you Account Number?");
			temp = in.nextInt();
			if( temp == 1) {
				System.out.println("PLease enter your password");
				int password = in.nextInt();
				if(password == 134) {
					SecondMenu.showSecondMenu();
				}
				else {
					System.err.println("Invaled entry returning to manin menu"+ "\n");
					logger.debug(password);
				}
			}else if(temp == 2) {
				System.out.println("PLease enter your password");
				int password = in.nextInt();
				if(password == 135) 
					SecondMenu.showSecondMenu();
				else { 
					System.out.println("Invaled entry returning to manin menu"+ "\n");
					logger.debug(password);
				}
			}else if(temp == 3){
				System.out.println("PLease enter your password");
				int password = in.nextInt();
				if(password == 136) 
					SecondMenu.showSecondMenu();
				else {
					System.err.println("Invaled entry returning to manin menu"+ "\n");
					logger.debug(password);
				}
			}else {
				System.err.println("Invaled entry returning to manin menu"+ "\n");
			}
		
			menu();
			break;
		case 2:// case 2 is to create account and for it to be approved by the admin			
			System.out.println(" Thanks for choseing Revature TransUnion.");
			if(i == 0){// creates account 1
				SecondMenu.temp = 1;
				i++;
				NewAccount.userInfor();
				System.out.println("Admin is checking you account info PLease stand by.....");
				int password = in.nextInt();
				if(password == adminPassword) {
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
				menu();
			}else if (i == 1) {// creates account 2
				SecondMenu.temp = 2;
				i++;
				NewAccount2.userInfor2();
				System.out.println("Admin is checking you account info PLease stand by.....");
				///NEED TO ADD ADMIN INFO
				int password = in.nextInt();
				if(password == adminPassword) {
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
				menu();
			}else if(i == 2){ //Creates account 3
				SecondMenu.temp = 3;
				i++;
				NewAccount3.userInfor3();
				System.out.println("Admin is checking you account info PLease stand by.....");
				int password = in.nextInt();
				if(password == adminPassword) {
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
				menu();
			}else {// since at this point there is already three accounts created and the bank can only have 3 accounts right know alerts the admin
				System.err.println("Contact Adnin back servers are out of sapce");
				logger.error(null);
				menu();
			}
			break;
		case 3:// case allows you into the admin area	
			intro3();
			int z = 0;
			System.out.println("============Hello Admin please enter you password============");
				int password = in.nextInt();
				if(password == adminPassword) {
					do{				
						System.out.println("=============What would you like to do Today boss=============");
						System.out.println("Press 0		How Much money does Revature TransUnionhave ");
						System.out.println("Press 1 	To see all accounts on the Server");
						System.out.println("Press 2  	To see all password");
						System.out.println("Press 3  	To log out");
						int menu2 = in.nextInt();
						switch(menu2){// this case statement needs to moved if i get time
						default: 
							System.err.println(" Invalid entry please try again later. ");
							break; 
						case 0:
							System.out.println("We currentle have: " );
							System.out.println("Account 1 has a laon for " + (loan - pay1));
							System.out.println("Account 2 has a laon for " + (loan2 - pay2));
							System.out.println("Account 3 has a laon for " + (loan3 - pay3));
							SecondMenu.addToBank(revatureTransUnion,balance, balance2, balance3);
							System.out.println(revatureTransUnion - (loan - pay1) - (loan2 - pay2) - (loan3 - pay3));
							System.out.println("\n");
							break;
						case 1:
							System.out.println("All acounts");
							System.out.println(union+"[Balance = "+balance+"]");
							System.out.println(union2+"[Balance = "+balance2+"]");
							System.out.println(union3+"[Balance = "+balance3+"]");
							System.out.println("\n");
							break;	
						case 3:
							System.out.println("Good bye Boss");
							System.out.println("\n");
							menu();
							break;	
						case 2:
							System.out.println("Please use securty password ");
							int password1 = in.nextInt();
							if(password1 == secrPassword) {
								System.out.println("All Passwords for the bank");
								System.out.println("The admine password is: " + adminPassword);
								System.out.println("Account 1 password is 134");
								System.out.println("Account 2 password is 135");
								System.out.println("Account 3 password is 136");
							}else
								System.err.println("Invaled entry");
						}
					}while(z == 0);
					
				}else {
					System.err.println("Access Denied!!!!!");
					logger.error(null);
					menu();
				}
					
			
			
			
		}
		
		
		}while(firstChoice != 0);
		
		System.out.println("    END OF EXECUTION");
		
	}
	
}
