package com.revature.project0.dao;

import static com.revature.project0.util.CloseResources.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.project0.Account;
import com.revature.project0.User;
import com.revature.project0.util.Connections;

public class UserDaoImpl implements UserDao {

	@Override
	public Integer insertUser(User user) {
/*		String key[] = {"USER_ID"};
		String insertTableSQL = "INSERT INTO BANK_USER (USERNAME, PASS, IS_ADMIN) VALUES (?,?,?)";
		PreparedStatement ps = null;
		try (Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(insertTableSQL, key);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setBoolean(3, user.isAdmin());
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
		return null;*/
		return null;
	}

	@Override
	public User selectUserByName(String userName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT USER_ID,USERNAME, PASS, IS_ADMIN FROM BANK_USER WHERE USERNAME = ?";
		try (Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getBoolean(4));
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
	public Integer deleteUserById(Integer id) {
		String insertTableSQL = "DELETE FROM BANK_USER WHERE USER_ID = ?";
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
	public Integer updateUser(User user) {
		PreparedStatement stmt = null;
		int status = 0;
		try (Connection conn = Connections.getConnection()) {
			String sql = "UPDATE BANK_USER SET Username = ?,Pass = ?, IS_ADMIN = ? WHERE USER_ID=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setBoolean(3, user.isAdmin());
			return stmt.executeUpdate(); // Returns amount rows effected;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return status;

	}

	@Override
	public void saveUserAccount(Integer userId, Integer accountId) {
		String insertTableSQL = "INSERT INTO USER_ACCOUNT (USER_ID, ACC_ID) VALUES (?,?)";
		PreparedStatement ps = null;
		try (Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(insertTableSQL);
			ps.setInt(1, userId);
			ps.setInt(2, accountId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
	}
	@Override
	public Boolean insertUserViaSp(User u) {

		CallableStatement stmt = null;
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call insertIntoBankUser(?,?,?)}");
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setBoolean(3, u.isAdmin());
			stmt.execute();
			User u1 = this.selectUserByName(u.getUsername());
			Account acc = new Account();
			AccountDao accDao = new AccountDaoImpl();
			Integer accId = accDao.insertAccount(acc);
			this.saveUserAccount(u1.getUserid(), accId);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return true;
	}

}
