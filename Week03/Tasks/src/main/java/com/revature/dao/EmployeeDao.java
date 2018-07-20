package com.revature.dao;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.RForm;

public interface EmployeeDao {
	public Boolean insertEmployeeViaSp(Employee employee);
	public Boolean selectEmployeeByUserN(String userN);
	public Boolean selectEmployeeById(int id);
	public List<RForm> selectRformByEmployeeId(int id);
}
