package com.revature.services;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDao;

public class EmployeeService 
{
	
	public boolean updateCurrencyById(Employee employee, Integer id, Integer amountLeft)
	{
		EmployeeDao ed = new EmployeeDao();
		return ed.updateEmployeeAmountLeftViaSp(employee);
	}
	
	public static boolean employeeLogin(String accountname, String password)
	{
		EmployeeDao empDao = new EmployeeDao();
		Employee emp = null;
		
		if ( (emp = empDao.selectEmployeeByAccountName(accountname)) == null )
		{
			return false;
		}
		if ( !emp.getEmployeePassword().equals(password) )
		{
			return false;
		}
		
		return true;
	}
	
}
