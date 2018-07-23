package com.revature.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.bean.Department;
import com.revature.bean.Employee;
import com.revature.bean.EmployeeType;
import com.revature.bean.Event;
import com.revature.bean.EventType;
import com.revature.bean.GradeScale;
import com.revature.bean.GradeValue;
import com.revature.dao.FixedDataDao;

public class FixedDataService {

	private static Map<Integer, EmployeeType> employeeTypeMap;
	private static Map<Integer, EventType> eventTypeMap;
	private static Map<Integer, Department> departmentMap;
	private static Map<Integer, GradeScale> gradeScaleMap;
	private static Map<Integer, GradeValue> gradeValueMap;

	/**
	 * Obtains a singleton hash map of (employeeType id, employeeType bean)
	 * key-value pairs
	 * 
	 * @return
	 */
	private static Map<Integer, EmployeeType> getEmployeeTypeMap() {
		if (employeeTypeMap == null) {
			List<EmployeeType> list = new FixedDataDao().selectAllEmployeeTypes();
			employeeTypeMap = new HashMap<>();
			for (EmployeeType i : list)
				employeeTypeMap.put(i.getId(), i);
		}
		return employeeTypeMap;
	}

	/**
	 * Obtains a singleton hash map of (eventType id, eventType bean) key-value
	 * pairs
	 * 
	 * @return
	 */
	private static Map<Integer, EventType> getEventTypeMap() {
		if (eventTypeMap == null) {
			List<EventType> list = new FixedDataDao().selectAllEventTypes();
			eventTypeMap = new HashMap<>();
			for (EventType i : list)
				eventTypeMap.put(i.getId(), i);
		}
		return eventTypeMap;
	}

	/**
	 * Obtains a singleton hash map of (department id, department bean) key-value
	 * pairs
	 * 
	 * @return
	 */
	private static Map<Integer, Department> getDepartmentMap() {
		if (departmentMap == null) {
			List<Department> list = new FixedDataDao().selectAllDepartments();
			departmentMap = new HashMap<>();
			for (Department i : list)
				departmentMap.put(i.getId(), i);
		}
		return departmentMap;
	}

	/**
	 * Obtains a singleton hash map of (gradeScale id, gradeScale bean) key-value
	 * pairs
	 * 
	 * @return
	 */
	private static Map<Integer, GradeScale> getGradeScaleMap() {
		if (gradeScaleMap == null) {
			List<GradeScale> list = new FixedDataDao().selectAllGradeScales();
			gradeScaleMap = new HashMap<>();
			for (GradeScale i : list)
				gradeScaleMap.put(i.getId(), i);
		}
		return gradeScaleMap;
	}

	/**
	 * Obtains a singleton hash map of (gradeValue id, gradeValue bean) key-value
	 * pairs
	 * 
	 * @return
	 */
	private static Map<Integer, GradeValue> getGradeValueMap() {
		if (gradeValueMap == null) {
			List<GradeValue> list = new FixedDataDao().selectAllGradeValues();
			gradeValueMap = new HashMap<>();
			for (GradeValue i : list)
				gradeValueMap.put(i.getId(), i);
		}
		return gradeValueMap;
	}

	/**
	 * Gets an event type by id
	 * 
	 * @param id
	 * @return
	 */
	public static EventType getEventType(Integer id) {
		return getEventTypeMap().get(id);
	}

	/**
	 * Gets an event type by event
	 * 
	 * @param event
	 * @return
	 */
	public static EventType getEventType(Event event) {
		return getEventType(event.getType());
	}

	/**
	 * Gets an employee type by id
	 * 
	 * @param id
	 * @return
	 */
	public static EmployeeType getEmployeeType(Integer id) {
		return getEmployeeTypeMap().get(id);
	}

	/**
	 * Gets an employee type by employee
	 * 
	 * @param employee
	 * @return
	 */
	public static EmployeeType getEmployeeType(Employee employee) {
		return getEmployeeType(employee.getType());
	}

	/**
	 * Gets a department by id
	 * 
	 * @param id
	 * @return
	 */
	public static Department getDepartment(Integer id) {
		return getDepartmentMap().get(id);
	}

	/**
	 * Gets a department by employee
	 * 
	 * @param employee
	 * @return
	 */
	public static Department getDepartment(Employee employee) {
		return getDepartment(employee.getDepartment());
	}

	/**
	 * Gets a gradeScale by id
	 * 
	 * @param id
	 * @return
	 */
	public static GradeScale getGradeScale(Integer id) {
		return getGradeScaleMap().get(id);
	}

	/**
	 * Gets a grade value by id
	 * 
	 * @param id
	 * @return
	 */
	public static GradeValue getGradeValue(Integer id) {
		return getGradeValueMap().get(id);
	}

	/**
	 * Gets grade values associated with a given grade scale
	 * 
	 * @param gsId
	 * @return
	 */
	public static List<GradeValue> gradeScaleGetValues(Integer gsId) {
		List<GradeValue> ls = new ArrayList<>();
		getGradeValueMap();

		for (GradeValue v : gradeValueMap.values()) {
			if (v.getScale() == gsId)
				ls.add(v);
		}

		return ls;
	}

}
