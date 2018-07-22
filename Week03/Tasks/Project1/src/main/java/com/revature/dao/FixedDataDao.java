package com.revature.dao;

import static com.revature.utils.CloseStreams.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Department;
import com.revature.bean.EmployeeType;
import com.revature.bean.EventType;
import com.revature.bean.GradeScale;
import com.revature.bean.GradeValue;
import com.revature.utils.Connections;

public class FixedDataDao {

	public List<EventType> selectAllEventTypes() {
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM event_type";

		List<EventType> ls = new ArrayList<EventType>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				EventType a = new EventType(rs.getInt(1), rs.getString(2), rs.getInt(3));

				ls.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return ls;
	}

	public List<EmployeeType> selectAllEmployeeTypes() {
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM employee_type";

		List<EmployeeType> ls = new ArrayList<EmployeeType>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				EmployeeType a = new EmployeeType(rs.getInt(1), rs.getString(2));

				ls.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return ls;
	}

	public List<Department> selectAllDepartments() {
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM department";

		List<Department> ls = new ArrayList<Department>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Department a = new Department(rs.getInt(1), rs.getString(2), rs.getInt(3));

				ls.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return ls;
	}

	public List<GradeScale> selectAllGradeScales() {
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM grade_scale";

		List<GradeScale> ls = new ArrayList<GradeScale>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				GradeScale a = new GradeScale(rs.getInt(1), rs.getString(2), rs.getInt(3));

				ls.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return ls;
	}
	
	public List<GradeValue> selectAllGradeValues() {
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM grade_value";

		List<GradeValue> ls = new ArrayList<GradeValue>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				GradeValue a = new GradeValue(rs.getInt(1), rs.getInt(2), rs.getString(3));

				ls.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return ls;
	}
}
