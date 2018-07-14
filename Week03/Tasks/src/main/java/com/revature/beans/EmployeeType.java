package com.revature.beans;

import java.util.HashMap;

public class EmployeeType {
	private HashMap<Integer,String> empTypeMap;

	public EmployeeType() {
		empTypeMap = new HashMap<>();
	}
	
	public void insertType(int typeid, String typename) {
		empTypeMap.put(typeid, typename);
	}
	
	/**
	 * @return the empTypeMap
	 */
	public HashMap<Integer, String> getEmpTypeMap() {
		return empTypeMap;
	}

	/**
	 * @param empTypeMap the empTypeMap to set
	 */
	public void setEmpTypeMap(HashMap<Integer, String> empTypeMap) {
		this.empTypeMap = empTypeMap;
	}
	
	
}
