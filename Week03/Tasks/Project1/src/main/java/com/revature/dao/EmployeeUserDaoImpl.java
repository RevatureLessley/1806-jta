package com.revature.dao;

import static com.revature.utils.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bean.EmployeeUser;
import com.revature.utils.Connections;

public class EmployeeUserDaoImpl {
	
	
	/**
	 * Access DB to select an employeeUser by name, values stored in EmployeeUser bean
	 * @param username
	 * @return
	 */
	public EmployeeUser selectByUsername(String username) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM employee_user WHERE emp_username = ?";
		EmployeeUser user = null;


		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);

			rs = ps.executeQuery();

			if (rs.next())
				user = new EmployeeUser(rs.getInt(1), rs.getString(2), rs.getString(3));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return user;
	}

	/**
	 * Access DB to select an employeeUser by id, values stored in EmployeeUser bean
	 * @param id
	 * @return
	 */
	public EmployeeUser selectById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM employee_user WHERE emp_id = ?";
		EmployeeUser user = null;

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next())
				user = new EmployeeUser(rs.getInt(1), rs.getString(2), rs.getString(3));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return user;
	}

}
