package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Employee;
import com.revature.util.Connections;
public class EmployeeDaoImpl {
	public Boolean insertEmployeeViaSp(Employee employee) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertIntoEmployee(?,?,?,?,?,?,?,?)}");
			
			stmt.setInt(1, employee.getEmpid());
			stmt.setString(2, employee.getUserN());
			stmt.setString(3, employee.getPassW());
			stmt.setString(4, employee.getFirstN());
			stmt.setString(5, employee.getLastN());
			stmt.setInt(6, employee.getDirSupId());
			stmt.setInt(7, employee.getDepId());
			stmt.setInt(8,employee.getEmpType());
			

			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}
	
	public Employee selectEmployeeByUserN(String userN) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Employee WHERE username = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, userN);
			rs = ps.executeQuery();
			while(rs.next()){
				return new Employee(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getDouble(8),
						rs.getDouble(9),
						rs.getDouble(10),
						rs.getInt(11),
						rs.getInt(12)
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
