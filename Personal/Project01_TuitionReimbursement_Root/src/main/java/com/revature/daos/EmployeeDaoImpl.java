package com.revature.daos;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.util.Connections;

public class EmployeeDaoImpl implements EmployeeDao {

	public Employee selectEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Employee> selectAllEmployees() {
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

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareCall("{call insertEmployee(?,?,?,?,?,?,?,?,?,?)}");

			stmt.setString(1, emp.getRole());
			stmt.setInt(2, emp.getSupVId());
			stmt.setString(3, emp.getFirstName());
			stmt.setString(4, emp.getLastName());
			stmt.setInt(5, emp.getPhone());
			stmt.setString(6, emp.getEmail());
			stmt.setString(7, emp.getAddress());
			stmt.setString(8, emp.getLocation());
			stmt.setString(9, emp.getUsername());
			stmt.setString(10, emp.getPassword());

			stmt.execute(); // Returns amount rows effected;
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return false;
	}

	public Employee selectEmployeeByUsername(String username) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employee WHERE username = ?";

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Employee(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		return null;
	}

	public List<Employee> selectAllEmployeesByRole(String role) {
		List<Employee> emps = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employee WHERE role = ?";

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, role);
			rs = ps.executeQuery();
			while (rs.next()) {
				emps.add(new Employee(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		return emps;
	}

}
