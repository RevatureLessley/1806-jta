package common.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.banking.application.Connections;
import common.banking.application.customer;

public class AccountDaoImpl implements AccountDao {

	@Override
	public void depositAccount(customer account, float amount) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "UPDATE PROJECT0_ACCOUNT SET BALANC = ? WHERE SS_NUM = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setFloat(1, (account.getBalance()));
			System.out.println("DEPOSITING: " + amount);
			stmt.setString(2, account.getSs_number());
			stmt.executeQuery();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Couldn't Close the Connection");
			}
		}
					
	}

	@Override
	public void withdrawAccount(customer account, float amount) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{

			String sql = "UPDATE PROJECT0_ACCOUNT SET BALANC = ? WHERE SS_NUM = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setFloat(1, (account.getBalance()));
			System.out.println("WITHDRAWING: " + amount);
			stmt.setString(2, account.getSs_number());
			System.out.println("FIRST INSERT:" + account.getSs_number());

			stmt.executeQuery();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Couldn't Close the Connection");
			}
		}			
	}		

	@Override
	public void createAccount(customer account) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "CALL insertIntoAccount(?,?,?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, account.getSs_number());
			System.out.println("FIRST INSERT:" + account.getSs_number());
			stmt.setFloat(2, account.getbalance());
			System.out.println("SECOND INSERT:" + account.getbalance());
			stmt.setString(3, account.getPassword());
			System.out.println("THIRD INSERT:" + account.getPassword());

			stmt.executeQuery();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Couldn't Close the Connection");
			}
		}		
	}


	@Override
	public float getBalance(customer account) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{

			String sql = "SELECT BALANC FROM PROJECT0_ACCOUNT WHERE SS_NUM = ?";
			ResultSet rs = null;
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, account.getSs_number());
			System.out.println("FIRST INSERT:" + account.getSs_number());

			rs = stmt.executeQuery();
			rs.next();
			return rs.getFloat(1);
			
			
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Something went wrong with connecting");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Couldn't Close the Connection");
			}
		}
		
			System.out.println("Error in getting your account. . .");
			return 999;
	}

	public String getPassword(customer account) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{

			String sql = "SELECT PASS FROM PROJECT0_ACCOUNT WHERE SS_NUM = ?";
			ResultSet rs = null;
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, account.getSs_number());
			rs = stmt.executeQuery();
			rs.next();
			return rs.getString(1);
			
			
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Something went wrong with connecting");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Couldn't Close the Connection");
			}
		}
		
		System.out.println("Error in getting your account. . .");
		return "Error in getting your password";
	}
}
