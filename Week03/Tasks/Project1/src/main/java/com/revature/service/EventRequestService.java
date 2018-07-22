package com.revature.service;

import java.io.InputStream;
import java.time.LocalDateTime;

import com.revature.dao.EventDaoImpl;

public class EventRequestService {

	public static Integer submitEvent(Integer empId, String eventName, Integer type, Integer gradeScale, LocalDateTime date,
			Double cost, String location, String description, String justification) {

		EventDaoImpl evDao = new EventDaoImpl();

		return evDao.addNewEvent(empId, type, gradeScale, eventName, date, location, description, justification, cost);
	}

	public static void submitImage(Integer eventId, String docName, InputStream fileData, Integer type) {
		EventDaoImpl evDao = new EventDaoImpl();

		evDao.addDocument(eventId, docName, fileData, type);
	}

}
