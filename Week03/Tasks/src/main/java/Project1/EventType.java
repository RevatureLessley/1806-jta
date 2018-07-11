package Project1;

import java.util.*;

public class EventType {
	private static HashMap<Integer, String> eventTypes = new HashMap<>();
	
	public static String insertType(Integer index, String type) {
		return eventTypes.put(index, type);
	}
	
	public static String retrieveType(Integer index) {
		return eventTypes.get(index);
	}
}