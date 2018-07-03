package com.revature.week1.tasks;

import java.util.List;

public interface UserDao {
	public void insertUser(User user, String string);
	public User selectUserById(Integer id);
	public List<User> selectAllUser();
	public Integer deleteUserById(User user);
	public Integer updateUser(User user);
}
