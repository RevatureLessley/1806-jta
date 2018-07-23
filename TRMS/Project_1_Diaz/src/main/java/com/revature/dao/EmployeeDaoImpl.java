package com.revature.dao;

import static com.revature.util.CloseStreams.close;
import static com.revature.util.LogFourJ.log;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.ReimbursementForm;
import com.revature.util.Connections;

public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public void insertEmployee(Employee emp) {
		CallableStatement cs = null;
		
		try(Connection conn = Connections.getConnection()){
			
			cs = conn.prepareCall("{call insertEmployee(?,?,?,?,?,?,?,?,?,1000)}");
			cs.setString(1, emp.getfName());
			cs.setString(2, emp.getlName());
			cs.setString(3, emp.getUserName());
			cs.setString(4, emp.getEmpPassword());
			cs.setString(5, emp.getEmpPhone());
			cs.setString(6, emp.getEmpEmail());
			cs.setString(7, emp.getEmpDept());
			cs.setString(8, emp.getRole());
			cs.setString(9, emp.getRole2());
			
			cs.executeUpdate(); 
			
			log.info(cs + "INSERT IS GOOD");
		} catch (Exception e) {
			log.info("INSERT FAIL");
			e.printStackTrace();
		}finally {
			close(cs);
		}
		
	}

	@Override
	public Employee selectEmployeeByUserName(String userName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp = new Employee();
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT * FROM Employee WHERE Employee_UserName = ?";
			ps= conn.prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				return new Employee(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getInt(11),
						rs.getInt(12)
						);
			}
			log.info("CHECK IS GOOD");
		} catch (SQLException e) {
			log.info("CHECK FAIL");
			e.printStackTrace();
		}finally {
			close(rs);
			close(ps);
		}
		return null;
	}

	@Override
	public Employee selectEmployeeByRole(String Employee_Role) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp = new Employee();
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT * FROM Employee WHERE Employee_Role = ?";
			ps= conn.prepareStatement(sql);
			ps.setString(1, Employee_Role);
			rs = ps.executeQuery();
			String temp = null;
			
			while(rs.next()) {
				return new Employee(
						temp = rs.getString("Employee_Role")
						);
			}
			log.info(temp+"IS GOOD");
		} catch (SQLException e) {
			log.info("FAIL");
			e.printStackTrace();
		}finally {
			close(rs);
			close(ps);
		}
		return null;
	}

	@Override
	public List<Employee> selectAllEmployee() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Employee> emp = new ArrayList<>();

		try (Connection conn = Connections.getConnection()) {
			String sql = "Select * FROM V_Tes1t";

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Employee emp2 = new Employee(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						rs.getString(10),
						rs.getInt(11),
						rs.getInt(12));
				emp.add(emp2);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return emp;
	}

	
}
