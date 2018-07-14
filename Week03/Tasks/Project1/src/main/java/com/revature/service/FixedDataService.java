package com.revature.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.bean.Department;
import com.revature.bean.Employee;
import com.revature.bean.EmployeeType;
import com.revature.bean.Event;
import com.revature.bean.EventType;
import com.revature.bean.GradeScale;
import com.revature.dao.FixedDataDao;

public class FixedDataService {

	private static Map<Integer, EmployeeType> employeeTypeMap;
	private static Map<Integer, EventType> eventTypeMap;
	private static Map<Integer, Department> departmentMap;
	private static Map<Integer, GradeScale> gradeScaleMap;

	private static Map<Integer, EmployeeType> getEmployeeTypeMap() {
		if (employeeTypeMap == null) {
			List<EmployeeType> list = new FixedDataDao().selectAllEmployeeTypes();
			employeeTypeMap = new HashMap<>();
			for (EmployeeType i : list)
				employeeTypeMap.put(i.getId(), i);
		}
		return employeeTypeMap;
	}

	private static Map<Integer, EventType> getEventTypeMap() {
		if (eventTypeMap == null) {
			List<EventType> list = new FixedDataDao().selectAllEventTypes();
			eventTypeMap = new HashMap<>();
			for (EventType i : list)
				eventTypeMap.put(i.getId(), i);
		}
		return eventTypeMap;
	}

	private static Map<Integer, Department> getDepartmentMap() {
		if (departmentMap == null) {
			List<Department> list = new FixedDataDao().selectAllDepartments();
			departmentMap = new HashMap<>();
			for (Department i : list)
				departmentMap.put(i.getId(), i);
		}
		return departmentMap;
	}
	
	private static Map<Integer, GradeScale> getGradeScaleMap() {
		if (gradeScaleMap == null) {
			List<GradeScale> list = new FixedDataDao().selectAllGradeScales();
			gradeScaleMap = new HashMap<>();
			for (GradeScale i : list)
				gradeScaleMap.put(i.getId(), i);
		}
		return gradeScaleMap;
	}

	public static EventType getEventType(Integer id) {
		return getEventTypeMap().get(id);
	}

	public static EventType getEventType(Event event) {
		return getEventType(event.getType());
	}

	public static EmployeeType getEmployeeType(Integer id) {
		return getEmployeeTypeMap().get(id);
	}

	public static EmployeeType getEmployeeType(Employee employee) {
		return getEmployeeType(employee.getType());
	}

	public static Department getDepartment(Integer id) {
		return getDepartmentMap().get(id);
	}

	public static Department getDepartment(Employee employee) {
		return getDepartment(employee.getDepartment());
	}

	public static GradeScale getGradeScale(Integer id) {
		return getGradeScaleMap().get(id);
	}

	
}
