package com.revature.dao;

import static com.revature.utils.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.bean.Notificaiton;
import com.revature.utils.Connections;
import com.revature.utils.StringManip;

public class NotificationDao {

	/**
	 * Adds a notification from a given user to a user that owns the given event.
	 * The notification is accompanied by a message and associated with an event
	 * 
	 * @param eventId
	 * @param userId
	 * @param message
	 */
	public void eventAddNotification(Integer eventId, Integer userId, String message) {
		CallableStatement stmt = null;
		ResultSet rs = null;

		String sql = "{CALL EVENT_ADD_NOTIFICATION(?, ?, ?)}";

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareCall(sql);

			stmt.setInt(1, eventId);
			stmt.setInt(2, userId);
			stmt.setString(3, message);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

	}

	/**
	 * Adds a notification from a given user to another user. The notification is
	 * accompanied by a message
	 * 
	 * @param targetId
	 * @param sourceId
	 * @param message
	 */
	public void employeeAddNotification(Integer targetId, Integer sourceId, String message) {
		CallableStatement stmt = null;
		ResultSet rs = null;

		String sql = "{CALL EMPLOYEE_ADD_NOTIFICATION(?, ?, ?)}";

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareCall(sql);

			stmt.setInt(1, targetId);
			stmt.setInt(2, sourceId);
			stmt.setString(3, message);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

	}

	/**
	 * Select all notifications that are associated with a given user. Stores data
	 * in a list of beans
	 * 
	 * @param userId
	 * @return
	 */
	public List<Notificaiton> selectUserNotifications(Integer userId) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM notification WHERE nt_emp_target = ?";

		List<Notificaiton> ls = new ArrayList<Notificaiton>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, userId);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Notificaiton a = new Notificaiton(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
						rs.getInt(5), rs.getInt(6), StringManip.getLocalDateTime(rs.getString(7)));
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
	 * Select all notifications that are associated with a given event. Stores data
	 * in a list of beans
	 * 
	 * @param eventId
	 * @return
	 */
	public List<Notificaiton> selectEventNotifications(Integer eventId) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM notification WHERE nt_event = ?";

		List<Notificaiton> ls = new ArrayList<Notificaiton>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, eventId);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Notificaiton a = new Notificaiton(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
						rs.getInt(5), rs.getInt(6), StringManip.getLocalDateTime(rs.getString(7)));
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
	 * Select all UNREAD notifications that are associated with a given event.
	 * Stores data in a list of beans
	 * 
	 * @param eventId
	 * @return
	 */
	public List<Notificaiton> selectUnreadEventUserNotifications(Integer eventId, Integer userId) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM notification WHERE nt_event = ? AND nt_emp_target = ? AND nt_read = 0";

		List<Notificaiton> ls = new ArrayList<Notificaiton>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, eventId);
			stmt.setInt(2, userId);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Notificaiton a = new Notificaiton(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
						rs.getInt(5), rs.getInt(6), StringManip.getLocalDateTime(rs.getString(7)));
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
	 * Generates a hashmap of all event ids and whether or not they have unread
	 * notifications.
	 * 
	 * @param userId
	 * @return
	 */
	public Map<Integer, Integer> selectUserEventUpdatedMap(Integer userId) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT event.ev_id, HAS_NOTIF(?, ev_id) FROM event";

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, userId);

			rs = stmt.executeQuery();

			while (rs.next()) {
				map.put(rs.getInt(1), rs.getInt(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return map;
	}

	/**
	 * Updates a notification with the given id and sets it as 'read'
	 * @param ntId
	 * @param userId 
	 */
	public void notificationMarkAsRead(Integer ntId, Integer userId) {
		PreparedStatement ps = null;

		String sql = "UPDATE notification SET nt_read = 1 WHERE nt_id = ? AND nt_emp_target = ?";

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, ntId);
			ps.setInt(2, userId);
			
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}

	}
}
