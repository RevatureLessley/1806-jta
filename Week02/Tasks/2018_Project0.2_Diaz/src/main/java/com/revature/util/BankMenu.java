package com.revature.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.revature.service.BanKAccountService;
/**
 * Class BankMenu sets up the first menu to take user input and it extends
 * SecondMenu to grab the information form the Supper Class
 * @author Zachary Diaz
 *
 */
public class BankMenu extends SecondMenu{
	
	private static int i = 0;// sets up a count for the number of accounts that is currently set 
	Scanner in = new Scanner(System.in);// useer input
	/**
	 * menu is to take set up the three accounts ready to be save into an array 
	 * and then implements the first menu
	 */
	public static void menu() {
		Scanner in = new Scanner(System.in);// useer input
		int firstChoice =  0; // this int is for the switch statement
			
		BanKAccountService.intro();					// intro is at the bottom of SecondMenue class
		do {						// Do while statement sets up the first switch that will show the menu and its possibilities
			firstChoice = in.nextInt();
			switch(firstChoice){
				default: // default case
					System.err.println("Invalid entry please try again later. Pleaese try agan. ");
					break; 
				case 0:// case 0 logs out of application for some reason i have to exit three times to complete get out of application 
					System.out.println("     Please come again.");
					break;
				case 1:// takes you to the next menu that lets you enter your new database entry
					System.out.println("       What is you Admin ID?");
					int a = in.nextInt();
					if(a == admidId) {
						System.out.println("       What is you Admin Password?");
						int b = in.nextInt();
						if(b == adminPassword) {
							switchTwo();
						}else {
							System.err.println("Invalid entry please try again later. Pleaese try agan. ");
							BankMenu.menu();	
						}
					}else {
						System.err.println("Invalid entry please try again later. Pleaese try agan. ");
						BankMenu.menu();				
					}
					
					break;
				case 2:// case 2 is to create account and for it to be approved by the admin			
					System.out.println(" Thanks for choseing Revature TransUnion.");
				case 3:// case allows you into the admin area	
			}	
		}while(firstChoice != 0);
		
		System.out.println("    END OF EXECUTION");
		
	}
	
}
	

