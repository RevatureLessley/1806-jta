package project01Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import project01util.*;


public class UsernameAndPasswordDaoImpl implements UsernameAndPasswordDao{
	final static Logger logger = Logger.getLogger(UsernameAndPasswordDaoImpl.class);


	public Boolean checkPassword(String username, String password) {
		logger.info("Checking password connecting to DB");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT PASSWORD FROM PROJECT1_ACCOUNTS WHERE USERNAME = ?";
			ResultSet rs = null;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			rs.next();
			return rs.getString(1).equals(password);
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("Can't connect to database");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Can't close connection");
			}
		}
		return false;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPassword() {
		// TODO Auto-generated method stub
		
	}

	public void setUsername() {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * Creates an account with username and password
	 * */
	public void createAccount(String username, String password) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "call insertAccount (?,?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, username);
			stmt.setString(2, password);

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
	public Boolean checkExistence(String username, String password) {
		logger.info("Checks existence connecting to db");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT * FROM PROJECT1_ACCOUNTS WHERE USERNAME = ? AND PASSWORD = ?";
			ResultSet rs = null;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			return rs.next();
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("Couldn't connect to DB");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Couldn't close the connection");
			}
		}
		
			return false;
	}

}
