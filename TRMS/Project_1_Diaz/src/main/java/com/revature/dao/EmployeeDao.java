package com.revature.dao;

import com.revature.beans.Employee;

public interface EmployeeDao {
	public Boolean insertEmployeeViaSp(Employee emp);
	public Employee selectEmployeeByName(String first,String last);
}
