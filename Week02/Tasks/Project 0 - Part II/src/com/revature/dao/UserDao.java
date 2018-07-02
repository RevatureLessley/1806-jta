package com.revature.dao;

import java.util.List;

import com.revature.beans.User;

public interface UserDao {
	public void insertUser(User user);
	public User selectUserById(Integer id);
	public List<User> selectAllUsers();
	public List<User> selectUsersByState(int state);
	public Integer deleteUserById(Integer id);
	public Integer updateUser(User user);
	public Boolean insertUserViaSp(User user);
	public void commit();
	public void updateUserStateById(Integer id, int state);
}
