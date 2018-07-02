package common.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.banking.application.Connections;
import common.banking.application.customer;



public class ClientDaoImpl implements ClientDao{
	

	@Override
	public void insertClient(customer newCustomer) {
		PreparedStatement stmt = null; 
		System.out.println("CHECK AT CLIENTDAO 1");
		try(Connection conn = Connections.getConnection())
		{
			System.out.println("CHECK AT CLIENTDAO 2");

			String sql = "INSERT INTO PROJECT0_CLIENT VALUES(?,?,?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, newCustomer.isActivated());
			System.out.println("FIRST INSERT:" + newCustomer.isActivated());
			stmt.setInt(2, newCustomer.isAdmin());
			System.out.println("SECOND INSERT:" + newCustomer.isAdmin());
			stmt.setString(3, newCustomer.getSs_number());
			System.out.println("THIRD INSERT:" + newCustomer.getSs_number());

			stmt.executeQuery();
			System.out.println("CHECK AT CLIENTDAO 3");

			
			
			
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
	public void activateClient(String ssnum) {
		PreparedStatement stmt = null; 
		System.out.println("CHECK AT CLIENTDAO 7");
		try(Connection conn = Connections.getConnection())
		{
			System.out.println("CHECK AT CLIENTDAO 8");

			String sql = "UPDATE PROJECT0_CLIENT SET ACTIVATED = 1 WHERE SS_NUM = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, ssnum);

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
	public void adminClient(customer newCustomer) {
		PreparedStatement stmt = null; 
		System.out.println("CHECK AT CLIENTDAO 10");
		try(Connection conn = Connections.getConnection())
		{
			System.out.println("CHECK AT CLIENTDAO 11");

			String sql = "UPDATE PROJECT0_CLIENT SET ADMIN = 1 WHERE SS_NUM = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, newCustomer.getSs_number());
			System.out.println("FIRST INSERT:" + newCustomer.getSs_number());

			stmt.executeQuery();
			System.out.println("CHECK AT CLIENTDAO 12");

			
			
			
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
	public boolean doesExist(customer checkCustomer) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT * FROM PROJECT0_CLIENT WHERE SS_NUM = ?";
			ResultSet rs = null;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, checkCustomer.getSs_number());
			System.out.println("FIRST INSERT:" + checkCustomer.getSs_number());
			rs = stmt.executeQuery();
			return rs.next();
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
		
			return false;
	}


	
	@Override
	public int isActivated(customer checkCustomer) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT ACTIVATED FROM PROJECT0_CLIENT WHERE SS_NUM = ?";
			ResultSet rs = null;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, checkCustomer.getSs_number());
			rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);
			
			
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
			return 0;
	}


	@Override
	public int isAdmin(customer checkCustomer) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT ADMIN FROM PROJECT0_CLIENT WHERE SS_NUM = ?";
			ResultSet rs = null;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, checkCustomer.getSs_number());
			rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);
			
			
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
			return 0;
	}

	
	public boolean doesExist(String ss_num) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT * FROM PROJECT0_CLIENT WHERE SS_NUM = ?";
			ResultSet rs = null;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ss_num);
			System.out.println("FIRST INSERT:" + ss_num);
			rs = stmt.executeQuery();
			return rs.next();
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
		
			return false;
	}
}
