package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.Account;
import com.revature.util.Connections;


public class AccountDao 
{

	public void insertAccount(Account account) 
	{

		
		
	}

	public Account selectAccountById(Integer id) 
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM bank_account WHERE account_id = ?";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				return new Account(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6)
						);
			}		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
		return null;
	}

	/*
	 * Try-With-Resources will close any streams you create within the
	 * parenthesis' of the try blockm once the try-catch-finally has
	 * finished.
	 */
	public ArrayList<Account> selectAllAccount() 
	{
		Statement stmt = null; // Simple SQL query to be executed
		ResultSet rs = null; //Object that holds query results
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

	public Integer deleteAccountById(Integer id) 
	{
		
		
		return null;
	}

	public Integer updateAccount(Account account) 
	{
		PreparedStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection())
		{
			String sql = "Update bank_account set account_id = ?, account_username = ?, " +
						"account_password = ?, currency = ?, type_id = ?, " +
						"approved_id = ? WHERE account_id= ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, account.getAccountNumber());
			stmt.setString(2, account.getUserName());
			stmt.setString(3, account.getPassword());
			stmt.setInt(4, account.getAccountValue());
			stmt.setInt(5, account.getAccountType());
			stmt.setInt(6, account.getApproved());
			
			return stmt.executeUpdate(); //Returns amount rows effected;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(stmt);
		}
		return 0;
	}
	
	
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
