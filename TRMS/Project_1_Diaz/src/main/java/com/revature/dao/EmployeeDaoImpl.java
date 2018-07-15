package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Employee;
import com.revature.util.Connections;

public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public Boolean insertEmployeeViaSp(Employee emp) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertEmployee(?,?,?,?,?,?,?)}");
			
			stmt.setInt(1, emp.getRfId());
			stmt.setInt(2, emp.getEmpTypeId());
			stmt.setString(3, emp.getfName());
			stmt.setString(4, emp.getlName());
			stmt.setString(5, emp.getEmpPassword());
			stmt.setString(6, emp.getEmpPhone());
			stmt.setString(7, emp.getEmpEmail());
			
			stmt.execute(); 
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}

	@Override
	public Employee selectEmployeeByName(String first,String last) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM Employee WHERE Employee_Fname = ? AND Employee_Lname = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, first);
			ps.setString(2 ,last);
			rs = ps.executeQuery();
			
			while(rs.next()){
				return new Employee(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8)
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
