package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.revature.util.CloseStreams.close;
import com.revature.bean.Employee;
import com.revature.util.Connections;

public class EmployeeDaoImpl {
	
	public Employee selectEmployeeByEmail(String email) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employee WHERE email = ?";
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				return new Employee(
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getInt(4),
								rs.getString(5),
								rs.getString(6),
								rs.getLong(7),
								rs.getInt(8),
								rs.getInt(9),
								rs.getInt(10),
								rs.getInt(11),
								rs.getInt(12),
								rs.getInt(13)
								);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(stmt);
			close(rs);
		}		
		return null;
	}
	public Employee selectEmployeeById(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employee WHERE employee_id = ?";
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				return new Employee(
									rs.getInt(1),
									rs.getString(2),
									rs.getString(3),
									rs.getInt(4),
									rs.getString(5),
									rs.getString(6),
									rs.getLong(7),
									rs.getInt(8),
									rs.getInt(9),
									rs.getInt(10),
									rs.getInt(11),
									rs.getInt(12),
									rs.getInt(13)
								);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(stmt);
			close(rs);
		}		
		return null;
	}
}
