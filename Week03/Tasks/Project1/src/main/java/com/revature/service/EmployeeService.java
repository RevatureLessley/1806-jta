package com.revature.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.revature.bean.Employee;
import com.revature.dao.EmployeeDao;
import com.revature.displaywrapper.EmployeeDisplay;
import com.revature.utils.StringManip;

public class EmployeeService {

	private static Map<Integer, String> employeeNameMap;

	private static Map<Integer, String> getNameMap() {
		if (employeeNameMap == null)
			employeeNameMap = new HashMap<Integer, String>();

		return employeeNameMap;
	}

	public static String getEmployeeName(Integer employeeId) {

		getNameMap();

		if (employeeNameMap.containsKey(employeeId))
			return employeeNameMap.get(employeeId);

		Employee employee = new EmployeeDao().selectById(employeeId);

		if (employee == null)
			return "none";

		String name = employee.getFname() + " " + employee.getLname();

		employeeNameMap.put(employeeId, name);

		return name;
	}

//	public static String getEmployeeBalance(Integer userId) {
//		EmployeeDao employeeDao = new EmployeeDao();
//
//		return StringManip.formatCurrency(employeeDao.selectById(userId).getBalance());
//	}
	
	public static double getEmployeeReimbursemntAvailable(Integer userId) {
		EmployeeDao employeeDao = new EmployeeDao();

		return employeeDao.selectById(userId).getReimbursementAvailable();
	}

	public static String getEmployeeDisplay(Integer userId) {
		Employee employee = new EmployeeDao().selectById(userId);
		EmployeeDisplay employeeDisplay = new EmployeeDisplay(employee);

		return StringManip.getJSONString(employeeDisplay);
	}

	public static String getEmployeeRedirect(Integer userId) {
		Employee employee = new EmployeeDao().selectById(userId);

		switch (employee.getType()) {
		case 1:
		case 2:
		case 3:
			return "./manage/";
		default:
			return "./user/";
		}
	}
}
