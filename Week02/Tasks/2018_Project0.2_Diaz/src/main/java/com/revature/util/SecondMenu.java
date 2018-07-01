package com.revature.util;

import java.util.Scanner;
import java.util.logging.Logger;
/**
 * 
 * In Second Menu it has the bulk of the methods that make the bank work such as deposit, balance, withdraw and introuductin menus
 * @author Zachary Diaz
 *
 */
public class SecondMenu {
	
	final static int admidId = 5986;
	final static int adminPassword = 118034; // admin password s not completely set up need to fix
	final static int secrPassword = 203065; // admin password s not completely set up need to fix
	
	// all of these above is due to me having to hard code the amount of account that i have i plan to get ride of this at a latter time
	public SecondMenu() {
		
	}

	public static void intro() {
		System.out.println("\n");
		System.out.println("========Welcome To Revature TransUnion========");
		System.out.println("");
		System.out.println("      Please select one of the following ");
		System.out.println("");
		System.out.println("      To Log In Admin	   	press 1 ");		

	}
	
	public static void intro2() {
		System.out.println("\n");
		System.out.println("=====================Hello=====================");
		System.out.println("");
		System.out.println("     Please select one of the following ");
		System.out.println("");
		System.out.println("     Create new Admin				Press 1");		
		System.out.println("     Add new accounts to Database		Press 2");		
//		System.out.println("     Check Balance 				Press 3");
//		System.out.println("     Too take out a loan 			Press 4");
//		System.out.println("     Too pay offf a loan 			Press 5");
//		System.out.println("     Transfer to another account 		press 6");
		System.out.println("     If you want to exit 			press 0" + "\n");
	}
	public static void intro3(){
		System.out.println("\n");
		System.out.println("===============================================");
		System.out.println("");
		System.out.println("");
		System.out.println("");
	}

	
	
}
