package Project0.dao;

import static Project0.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Project0.bean.User;
import Project0.util.Connections;

/**
 * The UserDaoImpl class is responsible for interacting with the user table in the database.
 * @author Vladimir Bukhalo
 *
 */
public class UserDaoImpl implements UserDao {
	
	/**
	 * The insertUser() inserts a new user into the database.
	 * 
	 * @param user The user to be inserted.
	 * @return Returns true if the insertion was successful, false otherwise.
	 * 
	 */
	@Override
	public Boolean insertUser(User user) {
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "{CALL insertIntoUsers(?,?,?,?)}";
			stmt = conn.prepareCall(sql);
			
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPassword());
			stmt.setBoolean(3, user.isApproved());
			stmt.setBoolean(4, user.isAdmin());
			
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
	 * The selectUserByUsername() retrieves a user from the database based on a specified username.
	 * 
	 * @param username The username of the user to be retrieved. 
	 * @return Returns the retrieved user.
	 * 
	 */
	@Override
	public User selectUserByUsername(String username) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM users WHERE username = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				return new User(
								rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getInt(4),
								rs.getBoolean(5),
								rs.getBoolean(6));
			}		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(ps);
		}
		return null;
	}

	/**
	 * The selectAllUsers() returns all the users in the database.
	 * 
	 * @return Returns a list of all users.
	 */
	@Override
	public List<User> selectAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * The updateUser() updates a specified user in the database.
	 * 
	 * @param user The user to be updated.
	 * @return Returns 1 if the update was successful, 0 otherwise. 
	 * 
	 */
	@Override
	public Integer updateUser(User user) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "UPDATE users SET is_Approved = ? WHERE user_id = ?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setBoolean(1, user.isApproved());
			stmt.setInt(2, user.getId());
			
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
	 * The tableSize() method return the size of the user table
	 * 
	 * @return Returns the size of the user table. 
	 */
	@Override
	public Integer tableSize() {
		Statement stmt = null;
		ResultSet rs = null;
		Integer count = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT COUNT(username) FROM users";
			
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
