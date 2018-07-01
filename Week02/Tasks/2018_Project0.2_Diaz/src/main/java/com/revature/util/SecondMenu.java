package com.revature.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public static void switchTwo() {
		Scanner in = new Scanner(System.in);// useer input
		intro2();
		int choice =0;
		choice = in.nextInt();
		switch(choice){
			default: 
				System.err.println("Invalid entry please try again later. Pleaese try agan. ");
				break; 
			case 0:
				System.out.println("     Please come again.");
				BankMenu.menu();
				break;
			case 1://creates new admin
					
			case 2:
				ShowSecondMenu();
				switchTwo();
		}
	}
	
	
	
	public static void ShowSecondMenu() {
		try {
			Scanner in = new Scanner(System.in);// useer input
			PreparedStatement ps = null;
			PreparedStatement ps1 = null;
			CallableStatement callSt = null;
			Statement stmt = null; 
			ResultSet rs = null;
			Connection conn = Connections.getConnection();
			// adding to customer address table
			System.out.println("What is the customers street number?");
			int streetNum = in.nextInt();
			System.out.println("What is the customers street name?");
			String strreName = in.next();
			ps = conn.prepareStatement("CALL INSERTINTOADDRESS(?,?)");
			ps.setInt(1, streetNum);
			ps.setString(2, strreName);		
			ps.executeUpdate();
			// get result of address id to pass on the customer
			callSt  = conn.prepareCall(" { ? = call GET_MAX_ADD_ID}");
			callSt.registerOutParameter(1,java.sql.Types.VARCHAR);
			callSt.execute();
			int result = callSt.getInt(1);
			// adding to customer table
			System.out.println("What is the customers Last Name");
			String lName = in.next();
			System.out.println("What is the customers First Name");
			String fName = in.next();
			System.out.println("What is the customers password");
			String password = in.next();
			ps = conn.prepareStatement("CALL INSERTIntoCUSTOMER(?,?,?,?)");
			ps.setString(1, lName);
			ps.setString(2, fName);
			ps.setString(3, password);
			ps.setInt(4, result);
			ps.executeUpdate();
			
			// Adding to bank account table
			System.out.println("Press 1 for REVATURE TRANSUNION TX " + "\n"
					+ "Press 2 for  REVATURE TRANSUNION FL" + "\n"
					+ "Press 3 for  REVATURE TRANSUNION VA "+ "\n");
			System.out.println("What is the Branch ID for this account: ");
			int branchId = in.nextInt();
			System.out.println("Press 1 for Saveing:" + "\n" 
					+ "Press 2 forfor checking:"+ "\n");
			String accounType = in.next();
			System.out.println("What is the customers opening balance");
			int balance = in.nextInt();
			ps = conn.prepareStatement("CALL insertIntoBank(?,?,?)");
			ps.setInt(1, branchId);
			ps.setString(2, accounType);
			ps.setInt(3, balance);
			ps.executeUpdate();
			
			
			System.out.println("REVATURE TRANSUNION New account confirmed as:"
				+ fName +" "+ lName +" With a starting balance of: " + "$" + balance);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		
			}
		Scanner in = new Scanner(System.in);// useer input
		System.out.println("Would you like to stay log y/n");
		String x = in.next();
		if(x == "y") {
			switchTwo();
		}else 
			BankMenu.menu();
		
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
