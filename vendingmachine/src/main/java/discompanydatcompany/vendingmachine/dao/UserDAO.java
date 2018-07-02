package discompanydatcompany.vendingmachine.dao;

import java.util.List;

import discompanydatcompany.vendingmachine.beans.User;

public interface UserDAO {

	public boolean addUser(User user);
	public User selectUserById(String loginUUID);
	public List<User> selectAllUsers();
	public boolean deleteUser(String loginUUID);
	public boolean deleteUser(User user);
	public boolean updateUser(User user);
	public User selectUser(User user);
}
