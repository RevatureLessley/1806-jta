package com.revature.daos;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.util.Connections;

public class EmployeeDaoImpl implements EmployeeDao {

	public Employee selectEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Employee> selectAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer deleteEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean insertEmployeeViaSp(Employee emp) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertEmployee(?,?,?,?,?,?,?)}");
			
			stmt.setInt(1, emp.getSupVId());
			stmt.setString(2, emp.getFirstName());
			stmt.setString(3, emp.getLastName());
			stmt.setInt(4, emp.getPhone());
			stmt.setString(5, emp.getEmail());
			stmt.setString(6, emp.getAddress());
			stmt.setString(7, emp.getLocation());

			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}

	public Employee selectEmployeeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
