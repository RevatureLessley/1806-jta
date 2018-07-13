package com.revature.beans;

import java.util.HashMap;

public class Department {
	private HashMap<Integer,String> depNameMap;

	public Department() {
		depNameMap = new HashMap<>();
	}
	public void insertDep(int depid, String depname) {
		depNameMap.put(depid, depname);
	}

	/**
	 * @return the depNameMap
	 */
	public HashMap<Integer, String> getDepNameMap() {
		return depNameMap;
	}

	/**
	 * @param depNameMap the depNameMap to set
	 */
	public void setDepNameMap(HashMap<Integer, String> depNameMap) {
		this.depNameMap = depNameMap;
	}

	
}
