package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.bean.Event;
import com.revature.dao.EventDaoImpl;
import com.revature.displaywrapper.EventDisplay;

public class EventService {
	
	public static EventDisplay getEventWrapper(Event event) {
		return new EventDisplay(event);
	}

	public static String selectUserEvents(Integer userId) {
		List<Event> events = new EventDaoImpl().selectUserEvents(userId);
		List<EventDisplay> wrappedEvents = new ArrayList<>();
		
		for(Event ev : events) 
			wrappedEvents.add(new EventDisplay(ev));
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = null;
		
		try {
			json = mapper.writeValueAsString(wrappedEvents);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}
}
