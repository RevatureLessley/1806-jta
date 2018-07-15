package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;

public class EmployeeService {
	
	
	public static boolean createEmployee(HttpServletRequest request){

		EmployeeDaoImpl edi = new EmployeeDaoImpl();

	
		if(!((edi.selectEmployeeByName((request.getParameter("first")),(request.getParameter("last")))) == null)){
			
			return false;
		}
		Employee emp = new Employee(
				request.getParameter("rfId"),
				request.getParameter("empTypeId"),
				request.getParameter("fname"),
				request.getParameter("lname"),
				request.getParameter("password1"),
				request.getParameter("empPhone"),
				request.getParameter("empEmail")
				);
		edi.insertEmployee(emp);
		
		return true;
}
	
	public static boolean employeeLogin(String first, String last, String password){
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		Employee emp = null;
		if(((emp = edi.selectEmployeeByName(first,last)) == null)){
			return false;
		}
		if(!emp.getEmpPassword().equals(password)){
			return false;
		}
		
		
		return true;
	}
}
