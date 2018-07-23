package com.revature.service;

import java.util.HashMap;
import java.util.Map;

import com.revature.bean.Employee;
import com.revature.dao.EmployeeDao;
import com.revature.displaywrapper.EmployeeDisplay;
import com.revature.utils.StringManip;

public class EmployeeService {

	private static Map<Integer, String> employeeNameMap;

	/**
	 * Gets a map containing employee ids as keys and employee names as values
	 * 
	 * @return
	 */
	private static Map<Integer, String> getNameMap() {
		if (employeeNameMap == null)
			employeeNameMap = new HashMap<Integer, String>();

		return employeeNameMap;
	}

	/**
	 * Gets the name of an employee by id. The key value pair is cached for future
	 * use
	 * 
	 * @param employeeId
	 * @return
	 */
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

	/**
	 * Gets the amount of reimbursements still available to a given employee
	 * 
	 * @param userId
	 * @return
	 */
	public static double getEmployeeReimbursemntAvailable(Integer userId) {
		EmployeeDao employeeDao = new EmployeeDao();

		return employeeDao.selectById(userId).getReimbursementAvailable();
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
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
