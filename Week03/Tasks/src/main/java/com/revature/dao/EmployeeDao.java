package com.revature.dao;

import com.revature.beans.Employee;

public interface EmployeeDao {
	public Boolean insertEmployeeViaSp(Employee employee);
	public Boolean selectEmployeeByUserN(String userN);
	public Boolean selectEmployeeById(int id);
}
