package Project0.dao;

import java.util.List;

import Project0.bean.*;;

public interface UserDao {
	public Boolean insertUser(User user);
	public User selectUserByUsername(String username);
	public List<User> selectAllUsers();
	public Integer updateUser(User u);
	public Integer tableSize();

}
