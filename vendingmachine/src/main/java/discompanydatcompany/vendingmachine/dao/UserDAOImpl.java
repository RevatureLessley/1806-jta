package discompanydatcompany.vendingmachine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import discompanydatcompany.vendingmachine.beans.Inventory;
import discompanydatcompany.vendingmachine.beans.User;
import discompanydatcompany.vendingmachine.beans.VendingMachine;
import discompanydatcompany.vendingmachine.utilities.Connection;

public class UserDAOImpl extends Connection implements UserDAO {

	@Override
	public boolean addUser(User user) {
		
		try {
			java.sql.Connection connection = getConnection();
			String sql = "insert into vending_machine values (?, ?)"; // uuid, serialized object
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLoginUUID());
			statement.setObject(2, user);
			
			if (statement.executeUpdate() != 0) {
				return true;
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}

	@Override
	public User selectUserById(String loginUUID) {
		
		try {
			User result = null;
			java.sql.Connection connection = getConnection();
			String sql = "select * from vending_machine_user where uuid=?"; // uuid, serialized object
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, loginUUID);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				result = (User) rs.getObject("serialized_object");
			}
			
			return result;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}
	
	@Override
	public User selectUser(User user) {
		try {
			User result = null;
			java.sql.Connection connection = getConnection();
			String sql = "select * from vending_machine_user where uuid=?"; // uuid, serialized object
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLoginUUID());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				result = (User) rs.getObject("serialized_object");
			}
			
			return result;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> selectAllUsers() {
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			java.sql.Connection connection = this.getConnection();
			String sql = "select * from vending_machine_user";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				User user = (User) rs.getObject("serialized_object");
				users.add(user);
			}
			
			return users;
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean deleteUser(String loginUUID) {

		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "delete vending_machine_user where uuid=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, loginUUID);

			if (statement.executeUpdate() != 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}

	@Override
	public boolean deleteUser(User user) {

		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "delete vending_machine_user where uuid=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, user.getLoginUUID());

			if (statement.executeUpdate() != 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}

	@Override
	public boolean updateUser(User user) {

		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "update vending_machine_user set serialized_object=? where uuid=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setObject(1, user);
			statement.setString(2, user.getLoginUUID());

			if (statement.executeUpdate() != 0) {
				
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
