package com.revature.displaywrapper;

import com.revature.bean.Employee;
import com.revature.service.EmployeeService;
import com.revature.service.FixedDataService;
import com.revature.utils.StringManip;

public class EmployeeDisplay {

	private String name;
	private String email;
	private String departmentName;
	private String supervisorName;
	private String balance;
	private String reimbursementAvailable;
	private String typeName;
	private Employee employee;
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getReimbursementAvailable() {
		return reimbursementAvailable;
	}

	public void setReimbursementAvailable(String reimbursementAvailable) {
		this.reimbursementAvailable = reimbursementAvailable;
	}

	public EmployeeDisplay(Employee employee) {
		name = employee.getFname() + " " + employee.getLname();
		email = employee.getEmail();
		departmentName = FixedDataService.getDepartment(employee.getDepartment()).getName();
		supervisorName = EmployeeService.getEmployeeName(employee.getSupervisedBy());
		balance = StringManip.formatCurrency(employee.getBalance());
		reimbursementAvailable = StringManip.formatCurrency(employee.getReimbursementAvailable());
		typeName = FixedDataService.getEmployeeType(employee).getName();
		this.employee = employee;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "EmployeeDisplay [name=" + name + ", email=" + email + ", departmentName=" + departmentName
				+ ", supervisorName=" + supervisorName + ", balance=" + balance + ", typeName=" + typeName + "]";
	}
	
	
}
