package com.revature.project0.dao;


import com.revature.project0.User;

public interface UserDao {
	public Integer insertUser(User u);
	public User selectUserByName(String userName);
	public Integer deleteUserById(Integer id);
	public Integer updateUser(User u);
	public void saveUserAccount(Integer userId, Integer accountId);
	public Boolean insertUserViaSp(User u);

}
