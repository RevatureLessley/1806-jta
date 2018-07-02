package com.revature.project0.dao;

import static com.revature.project0.util.CloseResources.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.project0.Account;
import com.revature.project0.util.Connections;

public class AccountDaoImpl implements AccountDao {

	@Override
	public Integer insertAccount(Account acc) {
		String key[] = {"ACC_ID"};
		String insertTableSQL = "INSERT INTO ACCOUNT (IS_ACTIVE, BALANCE) VALUES (?,?)";
		PreparedStatement ps = null;
		try (Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(insertTableSQL,key);
			ps.setBoolean(1, acc.isActive());
			ps.setDouble(2, acc.getBal());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
			    return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return null;
	}

	@Override
	public Account selectAccountById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT ACC_ID, IS_ACTIVE, BALANCE, FROM ACCOUNT WHERE ACC_ID = ?";

		try (Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Account(rs.getInt(1), rs.getDouble(2), rs.getBoolean(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		return null;

	}

	@Override
	public Integer deleteAccountById(Integer id) {
		String insertTableSQL = "DELETE FROM ACCOUNT WHERE ACC_ID = ?";
		int status = 0;
		PreparedStatement ps = null;
		try (Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(insertTableSQL);
			ps.setInt(1, id);
			status = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return status;
	}

	@Override
	public Integer updateAccount(Account acc) {
		PreparedStatement stmt = null;
		int status = 0;
		try (Connection conn = Connections.getConnection()) {
			String sql = "UPDATE ACCOUNT SET IS_ACTIVE = ?,BALANCE = ? WHERE ACC_ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, acc.getIsActive());
			stmt.setDouble(2, acc.getBal());
			stmt.setInt(3, acc.getAccountId());
			return stmt.executeUpdate(); // Returns amount rows effected;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return status;

	}

	@Override
	public List<Account> getAccountByUser(Integer userId) {
		List<Account> accounts = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT a.ACC_ID, a.IS_ACTIVE, a.BALANCE FROM USER_ACCOUNT ua, ACCOUNT a "
				+ "WHERE ua.ACC_ID = a.ACC_ID AND ua.USER_ID = ?";
		try (Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				accounts.add(new Account(rs.getInt(1), rs.getDouble(3), rs.getBoolean(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		return accounts;
		
	}

	@Override
	public List<Account> getAccountByUserName(String userName) {
		List<Account> accounts = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT a.ACC_ID, a.IS_ACTIVE, a.BALANCE FROM BANK_USER ba, USER_ACCOUNT ua, ACCOUNT a "
				+ "WHERE ba.USER_ID = ua.USER_ID AND ua.ACC_ID = a.ACC_ID AND ba.USERNAME = ?";
		try (Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			while (rs.next()) {
				accounts.add(new Account(rs.getInt(1), rs.getDouble(2), rs.getBoolean(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		return accounts;
	}

}