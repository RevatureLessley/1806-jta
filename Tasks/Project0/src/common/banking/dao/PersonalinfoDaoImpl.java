package common.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.banking.application.Connections;
import common.banking.application.customer;

public class PersonalinfoDaoImpl implements PersonalinfoDao {

	@Override
	public void createPersonalinfo(customer account) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "INSERT INTO PROJECT0_PERSONAL_INFO VALUES(?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getSs_number());
			stmt.setString(2, account.getFirst_name());
			stmt.setString(3, account.getLast_name());
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
	public String getFirstname(customer account) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT F_NAME FROM PROJECT0_PERSONAL_INFO WHERE SS_NUM = ?";
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
			return "Error: First Name not Found";
	}
	

	@Override
	public String getLastname(customer account) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT L_NAME FROM PROJECT0_PERSONAL_INFO WHERE SS_NUM = ?";
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
			return "Error: First Name not Found";
	}

}
