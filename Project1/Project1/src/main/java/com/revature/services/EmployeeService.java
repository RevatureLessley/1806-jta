package com.revature.services;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDaoImpl;


public class EmployeeService {
	
	public static Employee employeeLogin(int userId, String password)
	{
		EmployeeDaoImpl ed = new EmployeeDaoImpl();
		Employee emp = null;
		if((emp = ed.selectEmployeeByUserId(userId)) == null)
		{
			System.out.println("User Id has no match! :(");
			return null;
		}
		if(!emp.getPassword().equals(password)){
			return null;
		}
		return emp;
	}
}