package com.revature.beans;

import java.util.HashMap;

public class EmployeeType {
	private HashMap<String,Integer> empTypeMap;
	private HashMap<Integer,String> empTypeIdMap;

	public EmployeeType() {
		empTypeMap = new HashMap<>();
		empTypeIdMap = new HashMap<>();
	}
	
	public void insertType(String typename,int typeid) {
		empTypeMap.put(typename,typeid);
		empTypeIdMap.put(typeid,typename);
	}
	
	/**
	 * @return the empTypeIdMap
	 */
	public HashMap<Integer, String> getEmpTypeIdMap() {
		return empTypeIdMap;
	}

	/**
	 * @param empTypeIdMap the empTypeIdMap to set
	 */
	public void setEmpTypeIdMap(HashMap<Integer, String> empTypeIdMap) {
		this.empTypeIdMap = empTypeIdMap;
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
