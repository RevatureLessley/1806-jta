package com.revature.dao;

import static com.revature.utils.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.beans.BAccountType;
import com.revature.utils.Connections;

public class AccountTypeDaoImpl implements GenericDao<BAccountType>{
	
	private static Map<Integer, BAccountType> typeMap; 
	
	public static Map<Integer, BAccountType> getTypeMap(){
		if(typeMap == null) {
			
			typeMap = new HashMap<>();
			List<BAccountType> l = new AccountTypeDaoImpl().selectAll();
			
			for(BAccountType t : l)
				typeMap.put(t.getId(), t);			
		}
		
		return typeMap;
	}

	@Override
	public void insert(BAccountType t) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "INSERT INTO account_type VALUES (?, ?, ?)";
		
		
		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getId());
			ps.setString(2, t.getName());
			ps.setDouble(3, t.getRate());

			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
	}

	@Override
	public BAccountType selectById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM account_type WHERE atype_id = ?";
		BAccountType a = null;
		
		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();
			
			if(rs.next())
				a = new BAccountType(rs.getInt(1), rs.getString(2), rs.getDouble(3));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		
		return a;
	}

	@Override
	public List<BAccountType> selectAll() {
		Statement stmt = null; // simple sql query to be executed
		ResultSet rs = null;

		String sql = "SELECT * FROM account_type";

		List<BAccountType> ls = new ArrayList<BAccountType>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BAccountType a = new BAccountType(rs.getInt(1), rs.getString(2), rs.getDouble(3));

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

		String sql = "DELETE FROM account_type WHERE atype_id = ?";

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return null;
	}

	@Override
	public Integer update(BAccountType t) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "UPDATE account_type SET atype_name = ?, atype_rate = ? WHERE atype_id = ?";

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, t.getName());
			ps.setDouble(2, t.getRate());
			ps.setInt(4, t.getId());

			rs = ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return null;
	}

}
