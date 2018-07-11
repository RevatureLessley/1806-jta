package com.revature.utils;

import com.revature.bean.Employee;

public class HtmlTemplates {

	public static String getUserSummary(Employee employee) {

		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>Name: ");
		sb.append(employee.getFname() + " " + employee.getLname());
		
		sb.append("</p><p>Email: ");
		sb.append(employee.getEmail());
		
		sb.append("</p><p>Department: ");
		sb.append(employee.getDepartment());
		
		sb.append("</p><p>Direct Supervisor: ");
		sb.append(employee.getSupervisedBy());
		
		sb.append("</p>");

		return sb.toString();
	}
}
