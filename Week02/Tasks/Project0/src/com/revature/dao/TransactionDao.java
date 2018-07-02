package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.bank.Account;
import com.revature.util.Connections;


public class TransactionDao 
{
	/**
	 * The purpose of this function is to take a deposit or withdraw transaction and store
	 * the what happened into the bank_transaction database. It opens the connection,
	 * calls the insert_into_bank_transaction stored procedure and executes it with
	 * the entered parameters.
	 * @param account
	 * @param transactionId
	 * @param depositAmount
	 * @param withdrawAmount
	 * @return
	 */
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
	
	/**
	 * The purpose of this is to get the count of all transactions that have occurred 
	 * and return that value to be used so the transaction ID for each 
	 * transaction doesn't get overwritten or duplicated which would cause issues.
	 * @return
	 */
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
