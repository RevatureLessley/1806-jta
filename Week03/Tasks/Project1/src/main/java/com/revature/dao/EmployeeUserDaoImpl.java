package com.revature.dao;

import static com.revature.utils.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

import com.revature.bean.EmployeeUser;
import com.revature.utils.Connections;

public class EmployeeUserDaoImpl {
	
	

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
