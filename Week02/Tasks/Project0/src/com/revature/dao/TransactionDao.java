package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.bank.Account;
import com.revature.util.Connections;

public class TransactionDao 
{

	public Boolean insertTransactionViaSp(Account account, int transactionId, int depositAmount, int withdrawAmount) 
	{
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call insert_into_bank_transaction(?,?,?,?)}");
			
			stmt.setInt(1, transactionId);
			stmt.setInt(2, depositAmount);
			stmt.setInt(3, withdrawAmount);
			stmt.setInt(4, account.getAccountNumber());
			
			stmt.execute();
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}
		return false;
	}
	
	public int selectCountTransactions() 
	{
		int transactionsAmount = 0;
		Statement stmt = null; // Simple SQL query to be executed
		ResultSet rs = null; //Object that holds query results
		
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT COUNT(*) FROM bank_transaction";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);	

			while(rs.next()){
				transactionsAmount = rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(stmt);
			close(rs);
		}
		
		return transactionsAmount;
	}
	
}
