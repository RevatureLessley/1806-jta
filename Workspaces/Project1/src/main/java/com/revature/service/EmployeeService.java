package com.revature.service;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.bean.Employee;
import com.revature.dao.EmployeeDaoImpl;

public class EmployeeService {
	public static boolean userLogin(String email, String password) {
		EmployeeDaoImpl ed = new EmployeeDaoImpl();
		Employee e = null;
		
		if((e = ed.selectEmployeeByEmail(email)) == null) {
			return false;
		}
		if(!e.getPassword().equals(password)) {
			return false;
		}
		return true;
	}
	
	public static Employee getEmployeeByEmail(String email) {
		EmployeeDaoImpl ed = new EmployeeDaoImpl();
		Employee e = ed.selectEmployeeByEmail(email);
		
		return e;
	}
	
	public static Employee getEmployeeById(int id) {
		EmployeeDaoImpl ed = new EmployeeDaoImpl();
		Employee e = ed.selectEmployeeById(id);

		return e;
	}
	
	public String getEmployeeJSON(String email) {
		Employee e = getEmployeeByEmail(email);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try {
			json = mapper.writeValueAsString(e);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return json;
	}

}
