package com.revature.beans;

public class Employee 
{
	private Integer employeeId;
	private String employeeAccountName;
	private String employeePassword;
	private String employeeFName;
	private String employeeLName;
	private String employeeEmail;
	private Integer employeeAmountLeft;
	private Integer employeeJobTypeId;
	
	public Employee(Integer employeeId, String employeeAccountName, String employeePassword, String employeeFName,
			String employeeLName, String employeeEmail,Integer employeeAmountLeft, Integer employeeJobTypeId) 
	{
		super();
		this.employeeId = employeeId;
		this.employeeAccountName = employeeAccountName;
		this.employeePassword = employeePassword;
		this.employeeFName = employeeFName;
		this.employeeLName = employeeLName;
		this.employeeEmail = employeeEmail;
		this.employeeAmountLeft = employeeAmountLeft;
		this.employeeJobTypeId = employeeJobTypeId;
	}
	
	public Employee() 
	{
		super();
	}
	
	public Integer getEmployeeId() 
	{
		return employeeId;
	}
	
	public void setEmployeeId(Integer employeeId) 
	{
		this.employeeId = employeeId;
	}
	
	public String getEmployeeAccountName() 
	{
		return employeeAccountName;
	}
	
	public void setEmployeeAccountName(String employeeAccountName) 
	{
		this.employeeAccountName = employeeAccountName;
	}
	
	public String getEmployeePassword() 
	{
		return employeePassword;
	}
	
	public void setEmployeePassword(String employeePassword) 
	{
		this.employeePassword = employeePassword;
	}
	
	public String getEmployeeFName() 
	{
		return employeeFName;
	}
	
	public void setEmployeeFName(String employeeFName) 
	{
		this.employeeFName = employeeFName;
	}
	
	public String getEmployeeLName() 
	{
		return employeeLName;
	}
	
	public void setEmployeeLName(String employeeLName) 
	{
		this.employeeLName = employeeLName;
	}
	
	public String getEmployeeEmail() 
	{
		return employeeEmail;
	}
	
	public void setEmployeeEmail(String employeeEmail) 
	{
		this.employeeEmail = employeeEmail;
	}
	
	public Integer getEmployeeJobTypeId() 
	{
		return employeeJobTypeId;
	}
	
	public void setEmployeeJobTypeId(Integer employeeJobTypeId) 
	{
		this.employeeJobTypeId = employeeJobTypeId;
	}
	
	public Integer getEmployeeAmountLeft() 
	{
		return employeeAmountLeft;
	}
	
	public void setEmployeeAmountLeft(Integer employeeAmountLeft) 
	{
		this.employeeAmountLeft = employeeAmountLeft;
	}
	
	@Override
	public String toString() 
	{
		return "Employee [employeeId=" + employeeId + ", employeeAccountName=" + employeeAccountName
				+ ", employeePassword=" + employeePassword + ", employeeFName=" + employeeFName + ", employeeLName="
				+ employeeLName + ", employeeEmail=" + employeeEmail + ", employeeJobTypeId=" + employeeJobTypeId + "]";
	}
	
}
