package com.revature.beans;

import java.util.HashMap;

public class Department {
	private HashMap<String,Integer> depNameMap;

	public Department() {
		depNameMap = new HashMap<>();
	}
	
	public void insertDep(String depname,int depid) {
		depNameMap.put(depname, depid);
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
