package com.revature.dao;

import static com.revature.utils.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.BUser;
import com.revature.utils.Connections;

public class UserDaoImpl implements GenericDao<BUser>{

	@Override
	public void insert(BUser t) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "INSERT INTO user_table VALUES (?, ?, ?, ?)";
		
		
		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getId());
			ps.setInt(2, t.getType());
			ps.setString(3, t.getName());
			ps.setString(4, t.getPassword());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
	}

	@Override
	public BUser selectById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM user_table WHERE u_id = ?";
		BUser a = null;
		
		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();
			
			if(rs.next())
				a = new BUser(rs.getInt(1), 
						rs.getInt(2), 
						rs.getString(3), 
						rs.getString(4));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return a;
	}
	
	public BUser selectByName(String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM user_table WHERE u_name = ?";
		BUser a = null;
		
		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);

			rs = ps.executeQuery();
			
			if(rs.next())
				a = new BUser(rs.getInt(1), 
						rs.getInt(2), 
						rs.getString(3), 
						rs.getString(4));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return a;
	}

	@Override
	public List<BUser> selectAll() {
		Statement stmt = null; // simple sql query to be executed
		ResultSet rs = null;

		String sql = "SELECT * FROM user_table";

		List<BUser> ls = new ArrayList<BUser>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BUser a = new BUser(rs.getInt(1), 
						rs.getInt(2), 
						rs.getString(3), 
						rs.getString(4));

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

	@Override
	public Integer deleteById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "DELETE FROM user_table WHERE u_id = ?";
		int changes = 0; 
		
		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			changes = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return changes;
	}

	@Override
	public Integer update(BUser t) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "UPDATE user_table SET u_type = ?, u_name = ?, u_password = ? WHERE u_id = ?";
		int changes = 0; 
		
		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getType());
			ps.setString(2, t.getName());
			ps.setString(3, t.getPassword());
			ps.setInt(4, t.getId());

			changes = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return changes;
	}

}
