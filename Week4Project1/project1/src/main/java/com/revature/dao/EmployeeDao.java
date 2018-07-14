package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Employee;
import com.revature.util.Connections;

public class EmployeeDao 
{
	public Boolean updateEmployeeAmountLeftViaSp(Employee employee) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call update_amount_left(?,?)}");
			
			stmt.setInt(1, employee.getEmployeeId());
			stmt.setInt(2, employee.getEmployeeAmountLeft());

			stmt.execute();
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}
	
	public Employee selectEmployeeByAccountName(String accountName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employee WHERE emp_accountname = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, accountName);
			rs = ps.executeQuery();
			while(rs.next()){
				return new Employee(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getInt(8)
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
	
	public int selectEmpIdByAccountName(String accountName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT job_type_id FROM employee WHERE emp_accountname = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, accountName);
			rs = ps.executeQuery();
			while(rs.next()){
				return rs.getInt(1);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return 0;
	}
}
