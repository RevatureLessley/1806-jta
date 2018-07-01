package com.revature.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.service.BanKAccountService;
import com.revature.service.Transactions;
/**
 * 
 * In Second Menu makes the bank work on moving between menus
 * @author Zachary Diaz
 *
 */
public class SecondMenu {
	
	final static int admidId = 5986;
	final static int adminPassword = 118034; // admin password s not completely set up need to fix
	final static int secrPassword = 203065; // admin password s not completely set up need to fix
	
	public static void switchTwo() {
		Scanner in = new Scanner(System.in);// useer input
		BanKAccountService.intro2();
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
			case 1://adds to trasaction table in database
				Transactions.transactions();	
			case 2://ads new account and customer ifomation to approrate tables
				ShowSecondMenu();
				switchTwo();
			case 3:// displays the current account info
				BanKAccountService.tableView();
				switchTwo();
			case 4://dis[alys the current customer info
				BanKAccountService.tableView2();
				switchTwo();
				
		}
	}
	
	
	/**
	 * ShowSecondMenu creates the new entry into the banking database contains calls, update and inserts
	 */
	public static void ShowSecondMenu() {
		try {
			Scanner in = new Scanner(System.in);// useer input
			PreparedStatement ps = null;
			CallableStatement callSt = null;
			CallableStatement callSt1 = null;
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
					+ "Press 2 for REVATURE TRANSUNION FL" + "\n"
					+ "Press 3 for REVATURE TRANSUNION VA "+ "\n");
			System.out.println("What is the Branch ID for this account: ");
			int branchId = in.nextInt();
			System.out.println("Press 1 for Saveing:" + "\n" 
					+ "Press 2 for checking:"+ "\n");
			String accounType = in.next();
			System.out.println("What is the customers opening balance");
			int balance = in.nextInt();
			// get result from tran id to create new one
			callSt  = conn.prepareCall(" { ? = call GET_MAX_Tran_ID}");
			callSt.registerOutParameter(1,java.sql.Types.VARCHAR);
			callSt.execute();
			int result3 = callSt.getInt(1);
			int temp3 = result3 +1;
			ps = conn.prepareStatement("CALL Transations_test(?,null,null)");
			ps.setInt(1, temp3);
			ps.executeUpdate();
			ps = conn.prepareStatement("CALL insertIntoBank(?,?,?,?)");
			ps.setInt(1, branchId);
			ps.setString(2, accounType);
			ps.setInt(3, balance);
			ps.setInt(4, temp3);
			ps.executeUpdate();
			// get result of ACCOUNT id to pass on the JOIN TABLE
			callSt  = conn.prepareCall(" { ? = call GET_MAX_ID_CUSTOMER}");
			callSt.registerOutParameter(1,java.sql.Types.VARCHAR);
			callSt.execute();
			int result1 = callSt.getInt(1);
			// get result of customer id to pass on the JOIN TABLE
			callSt  = conn.prepareCall(" { ? = call GET_MAX_ID_account}");
			callSt.registerOutParameter(1,java.sql.Types.VARCHAR);
			callSt.execute();
			int result12 = callSt.getInt(1);			
			// set the new id's 
			int temp = result1 + 1;
			int temp2 = result12 + 1;
			ps = conn.prepareStatement(" CALL INSERTINTOACCOUNT_2_CUSTOMER(?,?)");
			ps.setInt(1, temp);
			ps.setInt(2, temp2);
			ps.executeUpdate();

			
			
			
			
			System.out.println("REVATURE TRANSUNION New account confirmed As:"
				+ fName +" "+ lName +" With a starting balance of: " + "$" + balance+ "\n");
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		
			}
		Scanner in = new Scanner(System.in);// useer input
		System.out.println("Would you like to stay log press 1 for yes and any other number to log out");
		int x = in.nextInt();
		if(x == 1) {
			System.out.println("Test");
			switchTwo();
		}else 
			BankMenu.menu();
		
	}

	

	
	
}
