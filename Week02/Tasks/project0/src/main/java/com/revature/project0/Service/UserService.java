package com.revature.project0.Service;

import com.revature.project0.User;
import com.revature.project0.dao.UserDao;
import com.revature.project0.dao.UserDaoImpl;

public class UserService {
	public boolean insertUser(User u){
		UserDao nd = new UserDaoImpl();
		return nd.insertUserViaSp(u);
	}
}
