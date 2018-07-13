package com.revature.service;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.bean.EmployeeUser;
import com.revature.bean.Event;
import com.revature.dao.EmployeeUserDaoImpl;
import com.revature.dao.EventDaoImpl;

public class UserService {

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
