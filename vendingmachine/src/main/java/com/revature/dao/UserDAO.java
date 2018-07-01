package com.revature.dao;

import java.util.List;

import discompanydatcompany.vendingmachine.beans.User;

public interface UserDAO {

	public void InsertUserDAO(User user);
	public User selectUserbyId(User user);
	public List<User> selectAllUsers();
	public String deleteUser(String loginUUID);
	public String deleteUser(User user);
}
