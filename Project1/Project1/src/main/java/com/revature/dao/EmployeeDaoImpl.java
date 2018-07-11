package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Employee;
import com.revature.util.Connections;

public class EmployeeDaoImpl {
	
	
	
	public Employee selectEmployeeByUserId(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employee WHERE userid = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				return new Employee(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}

}
