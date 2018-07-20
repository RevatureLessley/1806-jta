package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.RForm;
import com.revature.util.Connections;
public class EmployeeDaoImpl {
	public Boolean insertEmployeeViaSp(Employee employee) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertNewEmployee(?,?,?,?,?,?,?)}");
			
			stmt.setString(1, employee.getUserN());
			stmt.setString(2, employee.getPassW());
			stmt.setString(3, employee.getFirstN());
			stmt.setString(4, employee.getLastN());
			stmt.setInt(5, employee.getDirSupId());
			stmt.setInt(6, employee.getDepId());
			stmt.setInt(7,employee.getEmpType());
			

			
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
						rs.getInt(10)
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
	
	public Employee selectEmployeeById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Employee WHERE emp_id = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
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
						rs.getInt(10)
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
	
	public List<RForm> selectRformByEmployeeId(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT rform_id,event_name FROM RForm WHERE emp_id = ?";
		List<RForm> rforms = new ArrayList<>();
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				rforms.add(new RForm(rs.getInt(1),rs.getString(2)));
			}
			return rforms;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}
	
	
}
