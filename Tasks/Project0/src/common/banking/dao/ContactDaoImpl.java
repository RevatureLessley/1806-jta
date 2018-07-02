package common.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.banking.application.Connections;
import common.banking.application.customer;

public class ContactDaoImpl implements ContactDao{

	@Override
	public String getPhoneNumber(customer account) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT PHONE_NUM FROM PROJECT0_CONTACT WHERE SS_NUM = ?";
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
			return null;
	}

	@Override
	public String getAddress(customer account) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT ADDRESS FROM PROJECT0_CONTACT WHERE SS_NUM = ?";
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
			return null;
	}

	@Override
	public void updatePhone(String newPhone) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAddress(String newAddress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createContact(customer account) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "INSERT INTO PROJECT0_CONTACT VALUES(?,?,?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, account.getSs_number());
			stmt.setString(2, account.getPhonenum());
			stmt.setString(3, account.getAddress());

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

}
