package com.revature.dao;

import java.util.List;

import com.revature.beans.Employee;

public interface EmployeeDao {	
	public Employee selectEmployeeByUserId(int id);
	public List<Employee> selectAllEmployee();


}
