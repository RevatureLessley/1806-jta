package com.rev.day3.serialization;

import java.io.Serializable;
import java.util.Arrays;

public class Company implements Serializable{

	private static final long serialVersionUID = -5784709725990180748L;
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

	public void setEmployees(Employee ...employees) {
		this.employees = employees;
	}

	public Company(String companyName, Employee ...employees) {
		super();
		this.companyName = companyName;
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Company [companyName=" + companyName + ", employees=" + Arrays.toString(employees) + "]";
	}
	
	

}
