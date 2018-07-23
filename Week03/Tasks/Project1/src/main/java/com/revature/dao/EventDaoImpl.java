package com.revature.dao;

import static com.revature.utils.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Event;
import com.revature.utils.Connections;
import com.revature.utils.StringManip;

import oracle.jdbc.internal.OracleTypes;

public class EventDaoImpl {

	/**
	 * Creates a new event by using a stored procedure. Passes in values that are
	 * required and known at the time of event creation
	 * 
	 * @param empId
	 * @param type
	 * @param gradeScale
	 * @param name
	 * @param date
	 * @param location
	 * @param des
	 * @param jus
	 * @param cost
	 * @return
	 */
	public Integer addNewEvent(Integer empId, Integer type, Integer gradeScale, String name, LocalDateTime date,
			String location, String des, String jus, Double cost) {
		CallableStatement stmt = null;

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareCall(
					"{CALL ADD_NEW_EVENT(?, ?, ?, ?, TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS.FF'), ?, ?, ?, ?, ?)}");

			stmt.setInt(1, empId);
			stmt.setInt(2, type);
			stmt.setInt(3, gradeScale);
			stmt.setString(4, name);
			stmt.setString(5, StringManip.getTimestamp(date));
			stmt.setString(6, location);
			stmt.setString(7, des);
			stmt.setString(8, jus);
			stmt.setDouble(9, cost);
			stmt.registerOutParameter(10, java.sql.Types.INTEGER);

			System.out.println(stmt);

			stmt.execute();
			return stmt.getInt(10);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return null;

	}

	/**
	 * Selects all events associated with the given employee/user id. Data is placed
	 * in an event bean.
	 * 
	 * @param userId
	 * @return
	 */
	public List<Event> selectUserEvents(Integer userId) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM event WHERE emp_id = ?";

		List<Event> ls = new ArrayList<Event>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, userId);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Event a = new Event(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6), StringManip.getLocalDateTime(rs.getString(7)), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getDouble(11), rs.getDouble(12),
						StringManip.getLocalDateTime(rs.getString(13)), StringManip.getLocalDateTime(rs.getString(14)),
						StringManip.getLocalDateTime(rs.getString(15)), StringManip.getLocalDateTime(rs.getString(16)),
						rs.getString(17));

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

	/**
	 * Selects all events from the database. Data is placed in an event bean.
	 * 
	 * @param userId
	 * @return
	 */
	public List<Event> selectAllEvents() {
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM event";

		List<Event> ls = new ArrayList<Event>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Event a = new Event(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6), StringManip.getLocalDateTime(rs.getString(7)), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getDouble(11), rs.getDouble(12),
						StringManip.getLocalDateTime(rs.getString(13)), StringManip.getLocalDateTime(rs.getString(14)),
						StringManip.getLocalDateTime(rs.getString(15)), StringManip.getLocalDateTime(rs.getString(16)),
						rs.getString(17));

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

	/**
	 * Selects an event by id. Data is placed in an event bean.
	 * 
	 * @param userId
	 * @return
	 */
	public Event selectById(Integer id) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM event WHERE ev_id = ?";
		Event a = null;

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next())
				a = new Event(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6),
						StringManip.getLocalDateTime(rs.getString(7)), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getDouble(11), rs.getDouble(12),
						StringManip.getLocalDateTime(rs.getString(13)), StringManip.getLocalDateTime(rs.getString(14)),
						StringManip.getLocalDateTime(rs.getString(15)), StringManip.getLocalDateTime(rs.getString(16)),
						rs.getString(17));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return a;

	}

	/**
	 * Selects all events that belong to employees that are subordinates to the
	 * employee with the given id. The query is performed via stored procedure
	 * depending on the employee's type. Data is placed in an event bean.
	 * 
	 * @param userId
	 * @return
	 */
	public List<Event> selectSubordinateEvents(Integer userId) {
		CallableStatement stmt = null;
		ResultSet rs = null;

		String sql = "{CALL GET_SUBORDINATE_EVENTS(?, ?)}";

		List<Event> ls = new ArrayList<Event>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareCall(sql);

			stmt.setInt(1, userId);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);

			stmt.execute();

			rs = (ResultSet) stmt.getObject(2);

			while (rs.next()) {
				Event a = new Event(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6), StringManip.getLocalDateTime(rs.getString(7)), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getDouble(11), rs.getDouble(12),
						StringManip.getLocalDateTime(rs.getString(13)), StringManip.getLocalDateTime(rs.getString(14)),
						StringManip.getLocalDateTime(rs.getString(15)), StringManip.getLocalDateTime(rs.getString(16)),
						rs.getString(17));

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

	/**
	 * Calls a stored procedure that updates approval of an event. There are 3
	 * fields for approval (supervisor, head, benefits coordinator). The procedure
	 * determines the relationship that the user has to the event and updates
	 * appropriately
	 * 
	 * @param eventId
	 * @param userId
	 * @return
	 */
	public Integer eventUpdateApprovalFrom(Integer eventId, Integer userId) {

		CallableStatement stmt = null;

		String sql = "{CALL EVENT_UPDATE_APPROVAL_FROM(?, ?, ?)}";

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareCall(sql);

			stmt.setInt(1, eventId);
			stmt.setInt(2, userId);
			stmt.registerOutParameter(3, java.sql.Types.INTEGER);

			stmt.execute();

			return stmt.getInt(3);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}

		return 0;
	}

	/**
	 * Updates the reimbursement amount of an event.
	 * 
	 * @param eventId
	 * @param amount
	 */
	public void eventChangeAward(Integer eventId, Double amount) {
		PreparedStatement ps = null;

		String sql = "UPDATE event SET ev_r_amt = ? WHERE ev_id = ?";

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);

			ps.setDouble(1, amount);
			ps.setInt(2, eventId);

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}

	}

	/**
	 * Updates the final grade for an event.
	 * 
	 * @param userId
	 * @param eventId
	 * @param grade
	 */
	public void eventSubmitGrade(Integer userId, Integer eventId, Integer grade) {
		PreparedStatement ps = null;

		String sql = "UPDATE event SET ev_grade = ? WHERE ev_id = ? AND emp_id = ?";

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, grade);
			ps.setInt(2, eventId);
			ps.setInt(3, userId);

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
	}

	/**
	 * Updates confirmation of an event
	 * 
	 * @param eventId
	 * @param userId
	 */
	public void eventConfirm(Integer eventId, Integer userId) {
		CallableStatement stmt = null;

		String sql = "{CALL EVENT_CONFIRM(?, ?)}";

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareCall(sql);

			stmt.setInt(1, eventId);
			stmt.setInt(2, userId);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}

	}

}
