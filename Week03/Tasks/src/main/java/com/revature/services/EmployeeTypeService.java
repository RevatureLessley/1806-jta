package com.revature.services;

import com.revature.beans.EmployeeType;
import com.revature.dao.EmployeeTypeDaoImpl;

public class EmployeeTypeService {
	public static EmployeeType emptypes;
	public static void getEmployeeTypes() {
		emptypes = new EmployeeTypeDaoImpl().getEmployeeTypes();
	}
}
