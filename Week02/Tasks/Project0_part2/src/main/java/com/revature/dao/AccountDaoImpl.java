package com.revature.dao;

import static com.revature.utils.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.beans.BAccount;
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
		Statement stmt = null;
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

	/**
	 * Creates list which contains all account beans in the database that have been
	 * validated
	 * 
	 * @param t
	 */
	public List<BAccount> selectAll(boolean validated) {
		PreparedStatement ps = null;
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

	/**
	 * Creates list which contains all account beans in the database that have are
	 * owned by the given user
	 * 
	 * @param t
	 */
	public List<BAccount> selectAll(Integer userId) {
		PreparedStatement ps = null;
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

	/**
	 * Aggregates the balances of all activated accounts that belong to the given
	 * user.
	 * 
	 * @param t
	 */
	public Double getUserTotalBalance(Integer userId) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT SUM(acct_balance) FROM account_table WHERE user_id = ? AND acct_validated = 1";
		Double total = 0.0;

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);

			rs = ps.executeQuery();

			if (rs.next())
				total += rs.getDouble(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

		return total;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
//	public List<String> selectJoinUserAccountSummary(Integer userId) {
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		String sql = "SELECT acct_id, acct_validated, acct_balance, atype_name FROM account_table LEFT JOIN account_type ON acct_type = atype_id WHERE user_id = ?";
//		List<String> l = new ArrayList<>();
//
//		try (Connection conn = Connections.getConnection()) {
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, userId);
//
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				if (rs.getInt(2) == 1)
//					l.add(rs.getString(4) + " " + rs.getString(1) + " - "
//							+ AccountService.formatCurrency(rs.getDouble(3)));
//				else
//					l.add(rs.getString(4) + " " + rs.getString(1) + " - awaiting validation");
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(ps);
//		}
//
//		return l;
//	}

	public Integer getMaxId() {
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT MAX(acct_id) FROM account_table";

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return 100000;
	}

	public boolean applyInterest(int days) {
		CallableStatement stmt = null;

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareCall("{CALL APPLYINTEREST(?)}");

			stmt.setInt(1, days);

			stmt.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return false;

	}

	public boolean requestLoan(Integer userId, Double amount, Integer targetAcct) {
		CallableStatement stmt = null;

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareCall("{CALL requestLoan(?, ?, ?)}");

			stmt.setInt(1, userId);
			stmt.setDouble(2, amount);
			stmt.setInt(3, targetAcct);

			stmt.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return false;

	}

	public boolean validateLoan(Integer loanAccount) {
		CallableStatement stmt = null;

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareCall("{CALL validateLoan(?)}");

			stmt.setInt(1, loanAccount);

			stmt.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return false;

	}

}
