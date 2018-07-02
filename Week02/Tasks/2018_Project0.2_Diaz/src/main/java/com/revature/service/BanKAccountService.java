package com.revature.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.revature.util.CloseStreams;
import com.revature.util.Connections;
import com.revature.util.SecondMenu;
/**
 * this class sets up the bulk of consul outputs for the users eyes
 * @author Zachary DIaz
 *
 */
public class BanKAccountService extends SecondMenu{
	
	public static void tableView() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Connections.getConnection();
			stmt = conn.createStatement();
	        rs = stmt.executeQuery("SELECT * FROM BANK_ACCOUNT ORDER BY ACCOUNT_ID");
	        System.out.println("|Ac ID | Bra ID	 |  ACT Type	|Balance 	|");
	        System.out.println("----------------------------------------------------"); 
	        while (rs.next()) {
	           int aId = rs.getInt("ACCOUNT_ID");
	           int bId = rs.getInt("BRANCH_ID");
	           int aTId = rs.getInt("ACCOUNT_TYPE_ID");
	           int ab = rs.getInt("ACCOUNT_BALANCE");
	           System.out.println("|"+aId+"   |  "+bId+"  	 |  "+aTId+"  		| "+"$"+ab+"	|" );
	           System.out.println("----------------------------------------------------");
	         }
	     }catch(SQLException e) {
	        System.out.println("SQL exception occured" + e);
	     }finally {
				CloseStreams.close(rs);
				CloseStreams.close(stmt);
				CloseStreams.close(conn);
			}
	}
	public static void tableView2() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Connections.getConnection();
			stmt = conn.createStatement();
	        rs = stmt.executeQuery("SELECT * FROM CUSTOMER ORDER BY CUSTOMER_ID");
	        System.out.println("|Cu Id | Last	 	 |  First		|AddressID 	|");
	        System.out.println("----------------------------------------------------------------"); 
	        while (rs.next()) {
	           int CId = rs.getInt("CUSTOMER_ID");
	           String lName = rs.getString("CUSTOMER_LNAME");
	           String fName = rs.getString("CUSTOMER_FNAME");
	           int adId = rs.getInt("ADRESS_ID");
	           System.out.println("|"+CId+"   |  "+lName+"  	 |  "+fName+"  	 	|"+adId+"		|" );
	           System.out.println("----------------------------------------------------------------");
	         }
	     }catch(SQLException e) {
	        System.out.println("SQL exception occured" + e);
	     }finally {
			CloseStreams.close(rs);
			CloseStreams.close(stmt);
			CloseStreams.close(conn);
		}
	}
	public static void tableView3() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Connections.getConnection();
			stmt = conn.createStatement();
	        rs = stmt.executeQuery("SELECT * FROM TRANSATIONS ORDER BY TRAN_ID");
	        System.out.println("|T ID  | Amount Deposited| Amount Withdrew |");
	        System.out.println("----------------------------------------------------------------"); 
	        while (rs.next()) {
	           int tId = rs.getInt("TRAN_ID");
	           String depo = rs.getString("DEPOSITED");
	           String with = rs.getString("WITHDRAW");
	           System.out.println("|"+tId+"   |  "+depo+"  	 	|  "+with+"  	 |" );
	           System.out.println("----------------------------------------------------------------");
	         }
	     }catch(SQLException e) {
	        System.out.println("SQL exception occured" + e);
	     }finally {
				CloseStreams.close(rs);
				CloseStreams.close(stmt);
				CloseStreams.close(conn);
			}
	}
	public static void intro() {
		System.out.println("\n");
		System.out.println("========Welcome To Revature TransUnion==========");
		System.out.println("");
		System.out.println("      Please select one of the following ");
		System.out.println("");
		System.out.println("      To Log In Admin	   	press 1 ");		

	}
	
	public static void intro2() {
		System.out.println("\n");
		System.out.println("=================================================");
		System.out.println("");
		System.out.println("     Please select one of the following ");
		System.out.println("");
		System.out.println("     Add Ttransaction to Databas	Press 1");		
		System.out.println("     Add New Accounts to Database	Press 2");		
		System.out.println("     Check Current Accounts 		Press 3");
		System.out.println("     Check Customer Info 		Press 4");
		System.out.println("     Check all Transacctions 		Press 5");
		System.out.println("     If you want to exit 		press 0" + "\n");
	}
	public static void intro3(){
		System.out.println("\n");
		System.out.println("===============================================");
		System.out.println("      Please select one of the following ");
		System.out.println("");
		System.out.println("		Press 1:	Deposit");
		System.out.println("		Press 2:	Withdraw");
		System.out.println("     		Press 0:	If you want to exit 			" + "\n");
	}
	
}
