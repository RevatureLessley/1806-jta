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
		EmployeeDao ed = new EmployeeDao();
		Employee emp = null;
		
		if ( (emp = ed.selectEmployeeByAccountName(accountname)) == null )
		{
			return false;
		}
		if ( !emp.getEmployeePassword().equals(password) )
		{
			return false;
		}
		
		return true;
	}
	
	public int checkEmpId(String accountname)
	{
		EmployeeDao ed = new EmployeeDao();
		int empId = 0;
		
		empId = ed.selectEmpIdByAccountName(accountname);
		
		if ( empId == 1 )
		{
			return 1;
		}
		else if ( empId == 2 )
		{
			return 2;
		}
		else if ( empId == 3 )
		{
			return 3;
		}
		else if ( empId == 4 )
		{
			return 4;
		}
		else if ( empId == 5 )
		{
			return 5;
		}
		else
		{
			return 0;
		}
	}
	
}
