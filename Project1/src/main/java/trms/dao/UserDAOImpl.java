package trms.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import oracle.jdbc.OracleTypes;
import trms.beans.User;
import trms.utilities.Connection;

public class UserDAOImpl extends Connection implements UserDAO {

	public List<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectAllUsers(?)}");
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.execute();
			ResultSet rs = (ResultSet) callableStatement.getObject(1);
			
			while(rs.next()) {
				User user = new User();
				user.setUuid(rs.getString("uuid"));
				user.setUsername(rs.getString("username"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setLoginPassword(rs.getString("login_password"));
				user.setEmail(rs.getString("email"));
				users.add(user);
			}
			
			return users;
				
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return null;
	}

	public User userLoginWithUsername(String username, String password) {
		try {
			User user = null;
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectWithUsername(?,?,?)}");
			callableStatement.setString(1, username);
			callableStatement.setString(2, password);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			ResultSet rs = (ResultSet) callableStatement.getObject(3);
			
			while (rs.next()) {
				user = new User();
				user.setUuid(rs.getString("uuid"));
				user.setUsername(rs.getString("username"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setLoginPassword(rs.getString("login_password"));
				user.setEmail(rs.getString("email"));
			}
			
			return user;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return null;
	}

	public User userLoginWithEmail(String email, String password) {
		try {
			User user = null;
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectWithEmail(?,?,?)}");
			callableStatement.setString(1, email);
			callableStatement.setString(2, password);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.execute();
			
			ResultSet rs = (ResultSet) callableStatement.getObject(3);
			
			while (rs.next()) {
				user = new User();
				user.setUuid(rs.getString("uuid"));
				user.setUsername(rs.getString("username"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setLoginPassword(rs.getString("login_password"));
				user.setEmail(rs.getString("email"));
			}
			
			return user;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return null;
	}

	public boolean updateUser(User user) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call updateUser(?,?,?,?,?,?,?)}");
			callableStatement.setString(1, user.getUuid());
			callableStatement.setString(2, user.getUsername());
			callableStatement.setString(3, user.getLoginPassword());
			callableStatement.setString(4, user.getFirstName());
			callableStatement.setString(5, user.getLastName());
			callableStatement.setString(6, user.getEmail());
			callableStatement.setString(7, user.getPhoneNumber());
			
			if (callableStatement.execute()) {
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return false;
	}

	public boolean registerUser(User user) {
		if (getUserByUsername(user.getUsername()).getUuid() == null) {
			try {
				java.sql.Connection connection = this.getConnection();
				CallableStatement callableStatement = connection.prepareCall("{call insertIntoUser(?,?,?,?,?,?,?)}");
				callableStatement.setString(1, user.getUuid());
				callableStatement.setString(2, user.getUsername());
				callableStatement.setString(3, user.getLoginPassword());
				callableStatement.setString(4, user.getFirstName());
				callableStatement.setString(5, user.getLastName());
				callableStatement.setString(6, user.getEmail());
				callableStatement.setString(7, user.getPhoneNumber());
				callableStatement.execute();

				return true;
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		return false;
	}

	public User getUserByUsername(String username) {
		try {
			User user = new User();
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectUserByUsername(?,?)}");
			callableStatement.setString(1, username);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			callableStatement.execute();
			ResultSet rs = (ResultSet) callableStatement.getObject(2);
			
			while (rs.next()) {
					user.setUuid(rs.getString("uuid"));
					user.setUsername(rs.getString("username"));
					user.setFirstName(rs.getString("first_name"));
					user.setLastName(rs.getString("last_name"));
					user.setLoginPassword(rs.getString("login_password"));
					user.setEmail(rs.getString("email"));
			}
			
			return user;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}	

	@Override
	public User getUserByUUID(String uuid) {
		try {
			User user = null;
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectUserByUUID(?,?)}");
			callableStatement.setString(1, uuid);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			callableStatement.executeQuery();
			ResultSet rs = (ResultSet) callableStatement.getObject(2);
		

			while (rs.next()) {
					user.setUuid(rs.getString("uuid"));
					user.setUsername(rs.getString("username"));
					user.setFirstName(rs.getString("first_name"));
					user.setLastName(rs.getString("last_name"));
					user.setLoginPassword(rs.getString("login_password"));
					user.setEmail(rs.getString("email"));
			}
			
			return user;
		} catch (SQLException sqle) {
			
		}
		return null;
	}

	
	public static void main(String[] args) {
		try {
			User testUser = new User();
			testUser.setUuid(String.valueOf(UUID.randomUUID()));
			testUser.setUsername("test");
			testUser.setFirstName("First");
			testUser.setLastName("Last");
			testUser.setEmail("test@user.mail");
			testUser.setPhoneNumber("(555) 555 - 5555"); 
			testUser.setLoginPassword("test");
			
			UserDAO userDAO = new UserDAOImpl();
		if (userDAO.getUserByUsername(testUser.getUsername()) == null) {
			System.out.println("No user found.");
			System.out.println("Adding test user.");
			userDAO.registerUser(testUser);
		} else {
			User registeredUser = userDAO.getUserByUsername(testUser.getUsername());
			String uuid = registeredUser.getUuid();
			System.out.println(uuid);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getUserUUIDByUsername(String username) {
		String uuid = null;
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectUserByUsername(?,?)}");
			callableStatement.setString(1, username);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			callableStatement.execute();
			ResultSet rs = (ResultSet) callableStatement.getObject(2);
			
			while(rs.next()) {
				uuid = rs.getString("uuid");
			}
			
			return uuid;
		} catch (SQLException sqle) {
			return uuid;
		}
	}
}
