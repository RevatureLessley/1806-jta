package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.bean.Event;
import com.revature.dao.EventDaoImpl;
import com.revature.displaywrapper.EventDisplay;
import com.revature.utils.StringManip;

public class EventService {

	public static EventDisplay getEventWrapper(Event event) {
		return new EventDisplay(event);
	}

	public static String selectUserEvents(Integer userId) {
		List<Event> events = new EventDaoImpl().selectUserEvents(userId);
		List<EventDisplay> wrappedEvents = new ArrayList<>();

		for (Event ev : events)
			wrappedEvents.add(new EventDisplay(ev));

		return StringManip.getJSONString(wrappedEvents);
	}

	public static String selectUserEvent(Integer userId, Integer eventId) {
		Event event = new EventDaoImpl().selectById(eventId);

		EventDisplay evD = new EventDisplay(event);

		return StringManip.getJSONString(evD);
	}

	public static String selectSubordinateEvents(Integer userId) {
		List<Event> events = new EventDaoImpl().selectSubordinateEvents(userId);
		List<EventDisplay> wrappedEvents = new ArrayList<>();

		for (Event ev : events)
			wrappedEvents.add(new EventDisplay(ev));

		return StringManip.getJSONString(wrappedEvents);
	}
}
