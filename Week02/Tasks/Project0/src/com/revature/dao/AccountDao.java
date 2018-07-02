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


public class AccountDao 
{

	/**
	 * The purpose of this is to get an ArrayList of accounts from the
	 * bank_account table. This ArrayList is what will be used to ensure users
	 * accounts are durable and persist across applications.
	 * @return
	 */
	public ArrayList<Account> selectAllAccount() 
	{
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Account> accs = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT * FROM bank_account";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);	

			while(rs.next()){
				Account account = new Account(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6)
						);
				accs.add(account);
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
		
		return accs;
	}
	
	/**
	 * The purpose of this is to be called whenever a new account is being
	 * registered. It takes the new accounts information which
	 * comes in as an Account parameter and stores that all
	 * in the bank_account table using the stored procedure
	 * insert_into_bank_account.
	 * @param account
	 * @return
	 */
	public Boolean insertAccountViaSp(Account account) 
	{
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call insert_into_bank_account(?,?,?,?,?,?)}");
			
			stmt.setInt(1, account.getAccountNumber());
			stmt.setString(2, account.getUserName());
			stmt.setString(3, account.getPassword());
			stmt.setInt(4, account.getAccountValue());
			stmt.setInt(5, account.getAccountType());
			stmt.setInt(6, account.getApproved());
			
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
	 * The purpose of this is to be called whenever a withdraw or deposit transaction
	 * is occuring. It will take an Account and an int as parameters and then update the 
	 * correct account_id in the bank_account table with the newValue which is what their
	 * bank account value will be after the withdraw or deposit occurs.
	 * @param account
	 * @param newValue
	 * @return
	 */
	public Boolean updateCurrencyViaSp(Account account, int newValue) 
	{
		Statement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.createStatement();
			String sql = "UPDATE bank_account SET currency = " + newValue + " WHERE account_id = " + account.getAccountNumber();
			stmt.executeUpdate(sql);
			
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}
		return false;
	}
	
	/**
	 * The purpose of this is to be called whenever an admin is going through and approving or
	 * denying accounts that are waiting to be approved. If an account is approved, this is called
	 * and their approved_id is set to 1 which corresponds to true for the correct account_id.
	 * @param account
	 * @param newApproved
	 * @return
	 */
	public Boolean updateApprovedViaSp(Account account, int newApproved) 
	{
		Statement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.createStatement();
			String sql = "UPDATE bank_account SET approved_id = 1 WHERE account_id = " + account.getAccountNumber();
			stmt.executeUpdate(sql);
			
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}
		return false;
	}
	
}
