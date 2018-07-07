package com.revature.services;

import com.revature.beans.Employee;
import com.revature.daos.EmployeeDaoImpl;

public class EmployeeService {
	public boolean insertEmployee(Employee emp) {
		EmployeeDaoImpl empd = new EmployeeDaoImpl();
		return empd.insertEmployeeViaSp(emp);
	}
}
