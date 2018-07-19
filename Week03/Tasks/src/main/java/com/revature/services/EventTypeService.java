package com.revature.services;

import com.revature.beans.EventType;
import com.revature.dao.EventTypeDaoImpl;

public class EventTypeService {
	public static EventType eventtypes;
	public static void getEventTypes() {
		eventtypes = new EventTypeDaoImpl().getEventTypes();
	}
}
