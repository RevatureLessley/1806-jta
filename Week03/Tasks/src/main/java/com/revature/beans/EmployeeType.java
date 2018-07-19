package com.revature.beans;

import java.util.HashMap;

public class EmployeeType {
	private HashMap<String,Integer> empTypeMap;

	public EmployeeType() {
		empTypeMap = new HashMap<>();
	}
	
	public void insertType(String typename,int typeid) {
		empTypeMap.put(typename,typeid);
	}
	
	/**
	 * @return the empTypeMap
	 */
	public HashMap<String, Integer> getEmpTypeMap() {
		return empTypeMap;
	}

	/**
	 * @param empTypeMap the empTypeMap to set
	 */
	public void setEmpTypeMap(HashMap<String, Integer> empTypeMap) {
		this.empTypeMap = empTypeMap;
	}
	
	
}
