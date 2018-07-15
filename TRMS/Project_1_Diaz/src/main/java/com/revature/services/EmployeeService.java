package com.revature.services;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;

public class EmployeeService {
	
	
	public static boolean createEmployee(
			String[] rfId,
			String[] typeId,
			String fname,
			String lname,
			String password,
			String phone,
			String email
		){
	
		Employee emp = null;
		EmployeeDao ed = new EmployeeDaoImpl();
		EmployeeDaoImpl edi = new EmployeeDaoImpl();

	
		if(!((emp = edi.selectEmployeeByName(fname,lname)) == null)){
			return false;
		}
	
		return ed.insertEmployeeViaSp(emp);

}
	
	public static boolean employeeLogin(String first, String last, String password){
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		Employee emp = null;
		if((emp = edi.selectEmployeeByName(first,last)) == null){
			return false;
		}
		if(!emp.getEmpPassword().equals(password)){
			return false;
		}
		
		
		return true;
	}
}
