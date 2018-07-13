package com.revature.service;

import com.revature.bean.Employee;
import com.revature.dao.EmployeeDao;

public class EmployeeService {

	public static String getEmployeeName(Integer employeeId) {
		
		Employee employee = new EmployeeDao().selectById(employeeId);
		String name = employee.getFname() + " " + employee.getLname();
		
		return name;
	}
}
