package com.revature.dao;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.ReimbursementForm;

public interface EmployeeDao {
	public void insertEmployee(Employee emp);
	public Employee selectEmployeeByUserName(String userName);
	public Employee selectEmployeeByRole(String role);
	public List<Employee> selectAllEmployee();
}
