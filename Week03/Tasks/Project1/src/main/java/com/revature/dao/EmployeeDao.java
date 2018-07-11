package com.revature.dao;

import static com.revature.utils.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bean.Employee;
import com.revature.utils.Connections;

public class EmployeeDao {

	public Employee selectById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM employee WHERE emp_id = ?";
		Employee a = null;

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next())
				a = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return a;
	}
}
