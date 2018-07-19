package com.revature.dao;

import static com.revature.utils.CloseStreams.close;

import java.io.InputStream;
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

	public boolean addDocument(Integer evId, String docName, InputStream fileData, Integer isApproval) {
		CallableStatement stmt = null;

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareCall("{CALL ADD_DOCUMENT(?, ?, ?, ?)}");

			stmt.setInt(1, evId);
			stmt.setString(2, docName);
			stmt.setBlob(3, fileData);
			stmt.setInt(4, isApproval);

			stmt.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return false;
	}

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

			// StringManip.getLocalDateTime(rs.getString(6))

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

	public Integer eventUpdateApprovalFrom(Integer eventId, Integer userId) {

		CallableStatement stmt = null;
		ResultSet rs = null;

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
			close(rs);
		}

		return 0;
	}

}
