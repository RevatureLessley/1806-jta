package com.revature.beans;

import java.util.ArrayList;
import java.util.HashMap;

public class EventType {
	private HashMap<String,ArrayList<Integer>> eventTypeMap;

	public EventType() {
		eventTypeMap = new HashMap<>();
	}
	public void insertType(String typename,int typeid,int preimb) {
		ArrayList<Integer> al = new ArrayList<>();
		al.add(typeid);
		al.add(preimb);
		eventTypeMap.put(typename,al);
	}
	
	/**
	 * @return the eventTypeMap
	 */
	public HashMap<String, ArrayList<Integer>> getEventTypeMap() {
		return eventTypeMap;
	}

	/**
	 * @param eventTypeMap the eventTypeMap to set
	 */
	public void setEventTypeMap(HashMap<String, ArrayList<Integer>> eventTypeMap) {
		this.eventTypeMap = eventTypeMap;
	}
	
}
