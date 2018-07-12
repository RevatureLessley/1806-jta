package com.revature.services;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDaoImpl;

public class EmployeeService {
	public static boolean registerEmployee(
			int empid,
			String usern,
			String passw,
			String fname,
			String lname,
			int dirsupid,
			int depid,
			int emptype
		){
	Employee employee = new Employee(empid,usern,passw,fname,lname,dirsupid,depid,emptype);
	EmployeeDaoImpl empDao = new EmployeeDaoImpl();
	
	if(!(empDao.selectEmployeeByUserN(usern) == null)){
		return false;
	}
	if(empDao.insertEmployeeViaSp(employee)) return true;
	return false;
}
	public static boolean employeeLogin(String usern,String passw) {
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		Employee employee;
		if((employee = empDao.selectEmployeeByUserN(usern)) == null) {
			return false;
		}
		if(!employee.getPassW().equals(passw)) {
			return false;
		}
		return true;
	}
}
