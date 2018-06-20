package com.revature.day3.serialization;

import java.io.Serializable;
import java.util.Arrays;

public class Company implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8407811892202117448L;
	private String companyName;
	private Employee[] employees;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Employee[] getEmployees() {
		return employees;
	}
	public void setEmployees(Employee[] employees) {
		this.employees = employees;
	}
	
	public Company(){
		super();
	}
	public Company(String companyName, Employee[] employees){
		this.companyName = companyName;
		this.employees = employees;
	}
	
	@Override
	public String toString() {
		return "Company [companyName=" + companyName + ", employees=" + Arrays.toString(employees) + "]";
	}
	
	
}
