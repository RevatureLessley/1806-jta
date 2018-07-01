package com.revature.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.util.Connections;
import com.revature.util.SecondMenu;

public class Transactions {
	
	public static void transactions() {
		
		Scanner in = new Scanner(System.in);// useer input
		int transChoice =0;
		BanKAccountService.intro3();
		transChoice = in.nextInt();
		switch(transChoice){
			default: // default case
				System.err.println("Invalid entry please try again later. Pleaese try agan. ");
				SecondMenu.switchTwo();
				break; 
			case 0:// case 0 logs out of application for some reason i have to exit three times to complete get out of application 
				System.out.println("     Please come again.");
				SecondMenu.switchTwo();
			case 1:
				depositIntoAccount();
			case 2:
				withdrawfromAccount();
		
		}
		
	}
	
	
	public static void depositIntoAccount() {
		Scanner in = new Scanner(System.in);// useer input
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		PreparedStatement ps = null;
		CallableStatement callSt = null;
		int ab = 0;
		int accountId = 0;
		int aId = 0;
		int trID = 0;
		
		try {
			System.out.println("What is the account number that you want to deposit to?");
			accountId = in.nextInt();
			Connection conn = Connections.getConnection();
	        String query=("SELECT * FROM BANK_ACCOUNT WHERE ACCOUNT_ID = ?");
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1,accountId);
	        rs = pstmt.executeQuery();
	        ab = 0;
	        System.out.println("|Ac ID |Balance 	|TransationID");
	        System.out.println("----------------------------------------------------"); 
	        while (rs.next()) {
	           aId = rs.getInt("ACCOUNT_ID");
	           ab = rs.getInt("ACCOUNT_BALANCE");
	           trID= rs.getInt("TRAN_ID");
	           System.out.println("|"+aId+"   | "+"$"+ab+"	|"+ trID+ "|" );
	           System.out.println("----------------------------------------------------");
	         }
	        System.out.println("How much do you want to deposit?");
	        int amount = in.nextInt();
	        System.out.println(ab);
	        
	        amount = addToAccount(amount,ab);
	        System.out.println(amount);
	        
	        String query1=("UPDATE BANK_ACCOUNT SET ACCOUNT_BALANCE = ? WHERE ACCOUNT_ID = ?");
	        pstmt = conn.prepareStatement(query1);
	        pstmt.setInt(1,amount);
	        pstmt.setInt(2,accountId);
	        rs = pstmt.executeQuery();
	        
	        
	        String query3=("SELECT * FROM BANK_ACCOUNT WHERE ACCOUNT_ID = ?");
	        pstmt = conn.prepareStatement(query3);
	        pstmt.setInt(1,accountId);
	        rs = pstmt.executeQuery();
	        System.out.println("|Ac ID ||Balance 	|TransationID");
	        System.out.println("----------------------------------------------------"); 
	        while (rs.next()) {
	           aId = rs.getInt("ACCOUNT_ID");
	           ab = rs.getInt("ACCOUNT_BALANCE");
	           trID= rs.getInt("TRAN_ID");
	           System.out.println("|"+aId+"   | "+"$"+ab+"	|" + trID+ "|");
	           System.out.println("----------------------------------------------------");
	         }
	        
	        
	        // up load transaction info
	        callSt  = conn.prepareCall(" { ? = call GET_MAX_Tran_ID}");
			callSt.registerOutParameter(1,java.sql.Types.VARCHAR);
			callSt.execute();
			int result = callSt.getInt(1);
			trID = result +1;

	        ps = conn.prepareStatement("CALL Transations_test(?,?,?)");
	        ps.setInt(1, trID);
			ps.setInt(2, amount);
			ps.setInt(3, 0);
			ps.executeUpdate();
			
			
	        transactions();
	        
	     }catch(SQLException e) {
	        System.out.println("SQL exception occured" + e);
	     }
		
	}
	public static void withdrawfromAccount() {
		Scanner in = new Scanner(System.in);// useer input
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		PreparedStatement ps = null;
		CallableStatement callSt = null;
		int ab = 0;
		int accountId = 0;
		int aId = 0;
		int trID = 0;
		
		try {
			System.out.println("What is the account number that you want to withdraw to?");
			accountId = in.nextInt();
			Connection conn = Connections.getConnection();
	        String query=("SELECT * FROM BANK_ACCOUNT WHERE ACCOUNT_ID = ?");
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1,accountId);
	        rs = pstmt.executeQuery();
	        ab = 0;
	        System.out.println("|Ac ID |Balance 	|TransationID");
	        System.out.println("----------------------------------------------------"); 
	        while (rs.next()) {
	           aId = rs.getInt("ACCOUNT_ID");
	           ab = rs.getInt("ACCOUNT_BALANCE");
	           trID= rs.getInt("TRAN_ID");
	           System.out.println("|"+aId+"   | "+"$"+ab+"	|"+ trID+ "|" );
	           System.out.println("----------------------------------------------------");
	         }
	        System.out.println("How much do you want to deposit?");
	        int amount = in.nextInt();
	        System.out.println(ab);
	        
	        amount = subFromAccount(ab,amount);
	        System.out.println(amount);
	        
	        String query1=("UPDATE BANK_ACCOUNT SET ACCOUNT_BALANCE = ? WHERE ACCOUNT_ID = ?");
	        pstmt = conn.prepareStatement(query1);
	        pstmt.setInt(1,amount);
	        pstmt.setInt(2,accountId);
	        rs = pstmt.executeQuery();
	        
	        
	        String query3=("SELECT * FROM BANK_ACCOUNT WHERE ACCOUNT_ID = ?");
	        pstmt = conn.prepareStatement(query3);
	        pstmt.setInt(1,accountId);
	        rs = pstmt.executeQuery();
	        System.out.println("|Ac ID ||Balance 	|TransationID");
	        System.out.println("----------------------------------------------------"); 
	        while (rs.next()) {
	           aId = rs.getInt("ACCOUNT_ID");
	           ab = rs.getInt("ACCOUNT_BALANCE");
	           trID= rs.getInt("TRAN_ID");
	           System.out.println("|"+aId+"   | "+"$"+ab+"	|" + trID+ "|");
	           System.out.println("----------------------------------------------------");
	         }
	        
	        
	        // up load transaction info
	        callSt  = conn.prepareCall(" { ? = call GET_MAX_Tran_ID}");
			callSt.registerOutParameter(1,java.sql.Types.VARCHAR);
			callSt.execute();
			int result = callSt.getInt(1);
			trID = result +1;

	        ps = conn.prepareStatement("CALL Transations_test(?,?,?)");
	        ps.setInt(1, trID);
			ps.setInt(2, amount);
			ps.setInt(3, 0);
			ps.executeUpdate();
			
			
	        transactions();
	        
	     }catch(SQLException e) {
	        System.out.println("SQL exception occured" + e);
	     }
		
	}
	/**
	 * addToAccount takes the account and add together with the deposited amount  
	 * 
	 * @return
	 */
	public static int addToAccount(int a,int b){		
		return (a + b);
	}
	/**
	 * subFromAccount takes the account and subtracts together with the withdrawed amount  
	 * 
	 * @return
	 */
	public static int subFromAccount(int a,int b){		
		return (a - b);
	}
}
