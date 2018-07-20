package com.revature.beans;

import java.util.HashMap;

public class Department {
	private HashMap<String,Integer> depNameMap;
	private HashMap<Integer,String> depIdMap;

	public Department() {
		depNameMap = new HashMap<>();
		depIdMap = new HashMap<>();
	}
	
	public void insertDep(String depname,int depid) {
		depNameMap.put(depname, depid);
		depIdMap.put(depid, depname);
	}

	/**
	 * @return the depIdMap
	 */
	public HashMap<Integer, String> getDepIdMap() {
		return depIdMap;
	}

	/**
	 * @param depIdMap the depIdMap to set
	 */
	public void setDepIdMap(HashMap<Integer, String> depIdMap) {
		this.depIdMap = depIdMap;
	}

	/**
	 * @return the depNameMap
	 */
	public HashMap<String, Integer> getDepNameMap() {
		return depNameMap;
	}

	/**
	 * @param depNameMap the depNameMap to set
	 */
	public void setDepNameMap(HashMap<String, Integer> depNameMap) {
		this.depNameMap = depNameMap;
	}

	
}
