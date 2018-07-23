package trms.dao;

import java.util.List;

import trms.beans.User;

public interface UserDAO {
	public List<User> getAllUsers();
	public User getUserByUsername(String username);
	public User getUserByUUID(String uuid);
	public User userLoginWithUsername(String username, String password);
	public User userLoginWithEmail(String email, String password);
	public boolean updateUser(User user);
	public boolean registerUser(User user);
	public List<String> getRolesUserHas(String uuid);
	public String getUserUUIDByUsername(String username);
}
