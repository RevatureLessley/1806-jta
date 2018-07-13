package com.revature.services;

import com.revature.beans.Department;
import com.revature.dao.DepartmentDaoImpl;

public class DepartmentService {
	public static Department department;
	public static void getDepartments() {
		department = new DepartmentDaoImpl().getDepartments();
	}
}
