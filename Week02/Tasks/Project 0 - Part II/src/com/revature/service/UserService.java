package com.revature.service;

import com.revature.beans.User;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;

public class UserService {
	
	public void updateStateById(Integer id, Integer state){
		UserDaoImpl ud = new UserDaoImpl();
		User user = ud.selectUserById(id);
		
		if(user != null){
			user.setState(state);
		}
	}
	
	public boolean insertUser(User user){
		UserDao ud = new UserDaoImpl();
		return ud.insertUserViaSp(user);
	}
}
