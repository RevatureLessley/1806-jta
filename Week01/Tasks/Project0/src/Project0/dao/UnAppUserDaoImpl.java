package Project0.dao;

import static Project0.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Project0.bean.UnAppUser;
import Project0.util.Connections;

/**
 * The UnAppUserDaoImpl class is responsible for interacting with the unapp_user table in the database.
 * @author Vladimir Bukhalo
 *
 */
public class UnAppUserDaoImpl implements UnAppUserDao {
	
	/**
	 * The insertUser() inserts an unapproved user into the database.
	 * @param user The user to be inserted.
	 * @return True if the insertion was successful, false otherwise. 
	 */
	@Override
	public Boolean insertUser(UnAppUser user) {
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "{CALL insertIntoUnAppUser(?)";
			stmt = conn.prepareCall(sql);
			
			stmt.setString(1, user.getUsername());
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
	 * The getUserById() retrieves an unapproved user from the database based on the specified id.
	 * 
	 * @param id The id of the unapproved user.
	 * @return The unapproved user with the specified id.
	 */
	@Override
	public UnAppUser getUserById(Integer id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT * FROM unapp_user WHERE unapp_user_id = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			while(rs.next()) {
				return new UnAppUser(rs.getInt(1), rs.getString(2));
			}
					
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(stmt);
			close(rs);
		}
		return null;		
	}
	
	/**
	 * The deleteUserByUsername() deletes a specified unapproved user from the database. 
	 * 
	 * @param username The username of user to be deleted.
	 * @return Returns true if the delete was successful, false otherwise.
	 */
	@Override
	public Boolean deleteUserByUsername(String username) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "DELETE FROM unapp_user WHERE unapp_username = ?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, username);
			return stmt.execute();
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
	 * The selectAllUsers returns all the unapproved users in the database. 
	 * 
	 * @return A list of all unapproved users. 
	 */
	@Override
	public List<UnAppUser> selectAllUsers() {
		Statement stmt = null;
		ResultSet rs = null;
		
		List<UnAppUser> uaUsers = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT * FROM unapp_user";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				UnAppUser user = new UnAppUser(
												rs.getInt(1),
												rs.getString(2));
				uaUsers.add(user);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(stmt);
			close(rs);
		}
		return uaUsers;
		
	}

	/**
	 * The tablesize() returns the size of the unapp_user table.
	 * 
	 * @return The size of the unapp_user table. 
	 */
	@Override
	public Integer tableSize() {
		Statement stmt = null;
		ResultSet rs = null;
		Integer count = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT COUNT(unapp_username) FROM unapp_user";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(stmt);
		}
		return count;
	}

}
