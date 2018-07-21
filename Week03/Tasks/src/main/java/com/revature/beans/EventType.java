package com.revature.beans;

import java.util.ArrayList;
import java.util.HashMap;

public class EventType {
	private HashMap<String,ArrayList<Integer>> eventTypeMap;
	private HashMap<Integer,String> eventTypeNameMap;
	public EventType() {
		eventTypeMap = new HashMap<>();
		eventTypeNameMap = new HashMap<>();
	}
	
	public void insertType(String typename,int typeid,int preimb) {
		ArrayList<Integer> al = new ArrayList<>();
		al.add(typeid);
		al.add(preimb);
		eventTypeMap.put(typename,al);
		eventTypeNameMap.put(typeid, typename);
	}
	
	/**
	 * @return the eventTypeNameMap
	 */
	public HashMap<Integer, String> getEventTypeNameMap() {
		return eventTypeNameMap;
	}

	/**
	 * @param eventTypeNameMap the eventTypeNameMap to set
	 */
	public void setEventTypeNameMap(HashMap<Integer, String> eventTypeNameMap) {
		this.eventTypeNameMap = eventTypeNameMap;
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
