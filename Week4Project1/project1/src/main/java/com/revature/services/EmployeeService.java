package com.revature.services;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDao;

public class EmployeeService 
{
	public Employee getEmployeeUsingEmpId(Integer id)
	{
		EmployeeDao ed = new EmployeeDao();
		return ed.getEmployeeViaEmpId(id);
	}
	
	public Integer getCurrencyByEmpId(Integer empId)
	{
		EmployeeDao ed = new EmployeeDao();
		return ed.getEmployeeAmountLeftViaSp(empId);
	}
	
	public String getCurrencyWithJSON(String accountname)
	{
		Integer currency = getCurrencyByAccountname(accountname);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try
		{
			json = mapper.writeValueAsString(currency);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return json;
	}
	
	public Integer getCurrencyByAccountname(String accountname)
	{
		EmployeeDao ed = new EmployeeDao();
		return ed.selectAmountLeftByAccountName(accountname);
	}
	
	public boolean updateCurrencyById(Employee employee, Integer id, Integer amountLeft)
	{
		EmployeeDao ed = new EmployeeDao();
		return ed.updateEmployeeAmountLeftViaSp(employee, amountLeft);
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
	
//	public String getEmpCurrencyJSON()
//	{
//		HttpSession session = null;
//		session = request.
//		Integer currency = getAllNpcsWithClass();
//		ObjectMapper mapper = new ObjectMapper();
//		String json = "";
//		
//		try{
//			json = mapper.writeValueAsString(npcs);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		return json;
//	}
	
}
