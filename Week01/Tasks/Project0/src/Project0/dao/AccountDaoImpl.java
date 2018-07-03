package Project0.dao;

import static Project0.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Project0.bean.Account;
import Project0.util.Connections;;

/**
 * The AccountDaoImpl class is responsible for interacting with the account table in the database.
 * @author Vladimir Bukhalo
 *
 */
public class AccountDaoImpl implements AccountDao {

	/**
	 * The insertAccount() inserts an account into the database using a CallableStatement.
	 * 
	 * @param account The account to insert.
	 * @return Returns true if the insert was successful, false otherwise.
	 */
	@Override
	public Boolean insertAccount(Account account) {
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "{CALL insertIntoAccount(?)";
			
			stmt = conn.prepareCall(sql);
			
			stmt.setInt(1, account.getBalance());
			stmt.execute();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(stmt);
			close(rs);
		}
		return false;
	}
	
	/**
	 * The updateAccount() updates the balance of a specified account in the database.
	 * 
	 * @param account The account to update.
	 * @return Return 1 if the update was successful, 0 otherwise. 
	 */
	@Override
	public Integer updateAccount(Account account) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "UPDATE account SET balance = ? "
					+ "WHERE account_id = ?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, account.getBalance());
			stmt.setInt(2, account.getAccountId());
			
			return stmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(stmt);
			close(rs);
		}
		return 0;		
	}

	/**
	 * The selectAccountById() retrieves an account with the specified id from the database. 
	 * 
	 * @param id The id of the account to retrieve. 
	 * @return The retrieved account, null otherwise. 
	 */
	@Override
	public Account selectAccountById(Integer id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT * FROM account WHERE account_id = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				return new Account(rs.getInt(1),rs.getInt(2));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(stmt);
		}
		return null;
	}

}
