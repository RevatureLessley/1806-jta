package com.revature.services;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDaoImpl;


public class EmployeeService {
	
	public static boolean employeeLogin(String userId, String password)
	{
		EmployeeDaoImpl ed = new EmployeeDaoImpl();
		Employee emp = null;
		if((emp = ed.selectUserByUsername(userId)) == null)
		{
			return false;
		}
		if(!emp.getPassword().equals(password)){
			return false;
		}
		
		
		return true;
	}
}