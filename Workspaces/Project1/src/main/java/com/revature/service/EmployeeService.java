package com.revature.service;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.bean.Employee;
import com.revature.dao.EmployeeDaoImpl;

public class EmployeeService {
	
	private static Logger logger = Logger.getLogger(ReimbursementService.class);
	
	public static boolean userLogin(String email, String password) {
		EmployeeDaoImpl ed = new EmployeeDaoImpl();
		Employee e = null;
		
		if((e = ed.selectEmployeeByEmail(email)) == null) {
			return false;
		}
		if(!e.getPassword().equals(password)) {
			return false;
		}
		logger.info("user with email: " + email + " logged in successfully");
		return true;
	}
	
	public static Employee getEmployeeByEmail(String email) {
		EmployeeDaoImpl ed = new EmployeeDaoImpl();
		Employee e = ed.selectEmployeeByEmail(email);
		
		logger.info("user with email: " + email + " retrieved");
		return e;
	}
	
	public static Employee getEmployeeById(int id) {
		EmployeeDaoImpl ed = new EmployeeDaoImpl();
		Employee e = ed.selectEmployeeById(id);
		
		logger.info("user with id: " + id + " retrieved");
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
		
		logger.info("user with email: " + email + " retrieved as JSON");
		return json;
	}

}
