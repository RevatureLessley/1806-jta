package com.revature.dao;

import com.revature.beans.Employee;

public interface EmployeeDao {
	public void insertEmployee(Employee emp);
	public Employee selectEmployeeByUserName(String userName);
}
