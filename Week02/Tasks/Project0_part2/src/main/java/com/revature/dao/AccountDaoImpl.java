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

import com.revature.beans.BAccount;
import com.revature.beans.BUser;
import com.revature.beans.Npc;
import com.revature.utils.Connections;

public class AccountDaoImpl implements GenericDao<BAccount> {

	@Override
	public void insert(BAccount t) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "INSERT INTO account_table VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getId());
			ps.setInt(2, t.getType());
			ps.setDouble(3, t.getBalance());
			ps.setInt(4, t.getValidated());
			ps.setInt(5, t.getUser());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

	}

	@Override
	public BAccount selectById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM account_table WHERE acct_id = ?";
		BAccount a = null;

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next())
				a = new BAccount(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getInt(4), rs.getInt(5));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return a;
	}

	@Override
	public List<BAccount> selectAll() {
		Statement stmt = null; // simple sql query to be executed
		ResultSet rs = null;

		String sql = "SELECT * FROM account_table";

		List<BAccount> ls = new ArrayList<BAccount>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BAccount a = new BAccount(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getInt(4), rs.getInt(5));

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

	public List<BAccount> selectAll(boolean validated) {
		PreparedStatement ps = null; // simple sql query to be executed
		ResultSet rs = null;

		String sql = "SELECT * FROM account_table WHERE acct_validated = ?";

		List<BAccount> ls = new ArrayList<BAccount>();

		try (Connection conn = Connections.getConnection()) {

			ps = conn.prepareStatement(sql);
			ps.setInt(1, validated ? 1 : 0);

			rs = ps.executeQuery();

			while (rs.next()) {
				BAccount a = new BAccount(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getInt(4), rs.getInt(5));

				ls.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}

		return ls;
	}
	
	public List<BAccount> selectAll(Integer userId) {
		PreparedStatement ps = null; // simple sql query to be executed
		ResultSet rs = null;

		String sql = "SELECT * FROM account_table WHERE user_id = ?";

		List<BAccount> ls = new ArrayList<BAccount>();

		try (Connection conn = Connections.getConnection()) {

			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);

			rs = ps.executeQuery();

			while (rs.next()) {
				BAccount a = new BAccount(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getInt(4), rs.getInt(5));

				ls.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}

		return ls;
	}

	@Override
	public Integer deleteById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "DELETE FROM account_table WHERE acct_id = ?";
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
	public Integer update(BAccount t) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "UPDATE account_table SET acct_type = ?, acct_balance = ?, acct_validated = ? WHERE acct_id = ?";
		int changes = 0;

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, t.getType());
			ps.setDouble(2, t.getBalance());
			ps.setInt(3, t.getValidated());
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
	


	public Integer getUserTotalBalance(Integer userId) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT SUM(acct_balance) FROM account_table WHERE user_id = ?";
		Integer total = 0;

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);

			rs = ps.executeQuery();

			if (rs.next())
				total += rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return total;
	}

	public List<String> selectJoinUserAccountNames(Integer userId) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT acct_id, atype_name FROM account_table LEFT JOIN account_type ON acct_type = atype_id WHERE user_id = ?";
		List<String> l = new ArrayList<>();

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);

			rs = ps.executeQuery();

			while (rs.next())
				l.add(rs.getString(2) + " " + rs.getString(1));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return l;
	}
	
	public List<String> selectJoinUserAccountSummary(Integer userId) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT acct_id, acct_validated, acct_balance, atype_name FROM account_table LEFT JOIN account_type ON acct_type = atype_id WHERE user_id = ?";
		List<String> l = new ArrayList<>();

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);

			rs = ps.executeQuery();

			while (rs.next())
				l.add(rs.getString(2) + " " + rs.getString(1));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return l;
	}
	

}
