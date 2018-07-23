package com.revature.dao;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.RForm;

public interface EmployeeDao {
	public Boolean insertEmployeeViaSp(Employee employee);
	public Boolean selectEmployeeByUserN(String userN);
	public Boolean selectEmployeeById(int id);
	public List<RForm> selectRformByEmployeeId(int id);
	public List<RForm> selectRformBenHead(int id);
	public Boolean updatePending(double amount,int empid);
	public List<RForm> selectRformHead(int id,int depid);
	public Boolean updateAvailableReim(double amount,int empid);
	public List<Employee> selectEmployeeByDep(int dep);
	public Boolean updateAwardedReim(double amount,int empid);
}
