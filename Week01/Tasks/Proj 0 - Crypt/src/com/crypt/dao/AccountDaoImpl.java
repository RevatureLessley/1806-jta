package com.crypt.dao;

import static com.crypt.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crypt.beans.Account;
import com.crypt.util.Connections;

public class AccountDaoImpl implements AccountDao{

	@Override
	public List<Account> selectAll() throws SQLException{
		return select("SELECT * FROM acct");
	}

	@Override
	public List<Account> selectConditional(String sql) throws SQLException {
		return select(sql);
	}

	private List<Account> select(String sql) throws SQLException{
		List<Account> accounts = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;

		try(Connection conn = Connections.getConnection()){

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Account a = new Account(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getInt(3), 
						rs.getInt(4), 
						rs.getInt(5)
						);
				accounts.add(a);			
			}

			return accounts;
		}
	}
	@Override
	public Boolean insertNewAccount(Account a) {
		CallableStatement stmt = null; 

		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertIntoAccount(?,?,?,?)}");

			stmt.setString(1, a.getUsername());
			stmt.setInt(2, a.isApproved());
			stmt.setInt(3, a.getRole());
			stmt.setInt(4, a.getDefaultSeed());

			stmt.execute(); //Returns amount rows effected;
			return true;

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}
	@Override
	public Boolean approveAccount(Integer index) {
		PreparedStatement stmt = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "UPDATE acct SET acct_approved=1 WHERE acct_id=" + index;
			stmt = conn.prepareStatement(sql);
			stmt.executeQuery();
			conn.commit();
			return true;
		}catch(SQLException e) {
			System.err.println("ACCOUNT SQL APPROVAL UPDATE FAILED");
		}
		return false;
	}
	@Override
	public Integer updateAccount(Account a) {
		PreparedStatement stmt = null; 

		try(Connection conn = Connections.getConnection()){
			String sql = "UPDATE acct SET username = ?, acct_approved = ?, "
					+ "acct_role = ?, acct_default_seed = ? WHERE acct_id=?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(2, a.getUsername());
			stmt.setInt(3, a.isApproved());
			stmt.setInt(4, a.getRole());
			stmt.setInt(5, a.getDefaultSeed());

			return stmt.executeUpdate(); //Returns amount rows effected;

		}catch(SQLException e){
//			System.err.println("ACCOUNT SQL UPDATE FAILED");
//			e.printStackTrace();
		}finally{
			close(stmt);
		}
		return null;
	}
}