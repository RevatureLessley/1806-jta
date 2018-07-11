package com.revature.services;

import com.revature.beans.Employee;
import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;

public class UserService {
	public static boolean createUser(String role, int supVId, String fname, String lname, int phone,
			String email, String address, String location, String username, String password) {
		Employee user = null;
		EmployeeDao empd = new EmployeeDaoImpl();
		
		if(!(empd.selectEmployeeByUsername(username)==null)) return false;
		
		empd.insertEmployeeViaSp(new Employee(role, supVId, fname, lname, phone, email, address,
				location, username, password));
		
		return true;
	}
	
	public static boolean userLogin(String username, String password){
		EmployeeDaoImpl empd = new EmployeeDaoImpl();
		Employee emp = null;
		if((emp = empd.selectEmployeeByUsername(username)) == null){
			return false;
		}
		if(!emp.getPassword().equals(password)){
			return false;
		}
		
		
		return true;
	}
}
