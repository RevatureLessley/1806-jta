package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.util.Connections;

public class EmployeeDaoImpl implements EmployeeDao{
	
	
	
	
	
	@Override
	public Employee selectEmployeeByUserId(int id) {
		System.out.println(id);
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employee WHERE emp_id = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			
			while(rs.next()){
				System.out.println("I got one!");
				return new Employee(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getFloat(6)
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

	@Override
	public List<Employee> selectAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

}
