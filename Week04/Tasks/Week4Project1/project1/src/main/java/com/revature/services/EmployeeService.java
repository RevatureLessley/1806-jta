package com.revature.services;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDao;


/**
 * Service class that makes calls to the EmployeeDao.
 * @author Logan Brewer
 *
 */
public class EmployeeService 
{
	/**
	 * Return an employees ID by entering their account name.
	 * @param accountname
	 * @return
	 */
	public Integer getEmpIdByAccountname(String accountname)
	{
		EmployeeDao ed = new EmployeeDao();
		return ed.getEmpIdByAccountnameDao(accountname);
	}
	
	/**
	 * Return a full employees information by entering their
	 * ID.
	 * @param id
	 * @return
	 */
	public Employee getEmployeeUsingEmpId(Integer id)
	{
		EmployeeDao ed = new EmployeeDao();
		return ed.getEmployeeViaEmpId(id);
	}
	
	/**
	 * Return the amount an employee has left to request for 
	 * given their employee ID. 
	 * @param empId
	 * @return
	 */
	public Integer getCurrencyByEmpId(Integer empId)
	{
		EmployeeDao ed = new EmployeeDao();
		return ed.getEmployeeAmountLeftViaSp(empId);
	}
	
	/**
	 * Return a JSON string that represents the amount of currency an
	 * employee has left to request for.
	 * @param accountname
	 * @return
	 */
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
	
	/**
	 * Return the calculation of the amount an employee has left in their account and the
	 * amount of pending reimbursements they have pending or approved.
	 * @param accountname
	 * @return
	 */
	public Integer getCurrencyByAccountname(String accountname)
	{
		EmployeeDao ed = new EmployeeDao();
		return ((getAmountInAccountByAccountname(accountname))-(ed.selectAmountLeftByAccountName(accountname)));
	}
	
	/**
	 * Return the amount an employee has left in their account
	 * by entering their account name.
	 * @param accountName
	 * @return
	 */
	public Integer getAmountInAccountByAccountname(String accountName)
	{
		EmployeeDao ed = new EmployeeDao();
		Integer amountInAccount = 0;
		amountInAccount = ed.selectAmountInAccount(accountName);
		return amountInAccount;
	}
	
	/**
	 * Used to update an employees amount left by entering an Employee object,
	 * that employees ID and the amount they will have left.
	 * @param employee
	 * @param id
	 * @param amountLeft
	 * @return
	 */
	public boolean updateCurrencyById(Employee employee, Integer id, Integer amountLeft)
	{
		EmployeeDao ed = new EmployeeDao();
		return ed.updateEmployeeAmountLeftViaSp(employee, amountLeft);
	}
	
	/**
	 * Used to return false if the account name + password doesn't exist,
	 * otherwise returns true.
	 * @param accountname
	 * @param password
	 * @return
	 */
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
	
	/**
	 * Used to get an integer representing the employees
	 * job type ID by entering a employees account name.
	 * @param accountname
	 * @return
	 */
	public int checkJobTypeId(String accountname)
	{
		EmployeeDao ed = new EmployeeDao();
		int empId = 0;
		
		empId = ed.selectJobTypeIdByAccountName(accountname);
		
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
