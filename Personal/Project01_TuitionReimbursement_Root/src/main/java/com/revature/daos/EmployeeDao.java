package com.revature.daos;

import java.util.List;

import com.revature.beans.Employee;

public interface EmployeeDao {
	public Employee selectEmployeeById(Integer id);
	public List<Employee> selectAllEmployee();
	public Integer deleteEmployeeById(Integer id);
	public Integer updateEmployee(Employee emp);
	public Boolean insertEmployeeViaSp(Employee emp);
	public Employee selectEmployeeByUsername(String name);
}
