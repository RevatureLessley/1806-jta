package com.revature.service;

import com.revature.bean.EmployeeUser;
import com.revature.dao.EmployeeUserDaoImpl;

public class UserService {

	/**
	 * Checks whether a username and password combination is valid
	 * 
	 * @param username
	 * @param password
	 * @return employeeId on success
	 */
	public static Integer validateLogin(String username, String password) {

		EmployeeUserDaoImpl employeeUserDaoImpl = new EmployeeUserDaoImpl();
		EmployeeUser user = employeeUserDaoImpl.selectByUsername(username.toLowerCase().trim());

		if (user == null)
			return null;

		if (user.getEmpPassword().equals(password))
			return user.getEmpId();
		else
			return null;
	}

}
