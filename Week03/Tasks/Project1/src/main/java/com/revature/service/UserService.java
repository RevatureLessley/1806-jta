package com.revature.service;

import com.revature.bean.EmployeeUser;
import com.revature.dao.EmployeeUserDaoImpl;

public class UserService {

	public Integer validateLogin(String username, String password) {
		
		EmployeeUserDaoImpl employeeUserDaoImpl = new EmployeeUserDaoImpl();
		EmployeeUser user = employeeUserDaoImpl.selectByUsername(username.toLowerCase().trim());
		
		if (user == null)
			return null;
		
		if(user.getEmpPassword().equals(password))
			return user.getEmpId();
		else
			return null;
	}

}
