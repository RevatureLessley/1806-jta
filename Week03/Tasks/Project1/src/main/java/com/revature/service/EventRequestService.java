package com.revature.service;

import java.time.LocalDateTime;

import com.revature.dao.EventDaoImpl;
import com.revature.utils.MasterLogger;

public class EventRequestService {

	/**
	 * Submit a new event request with the given parameters.
	 * 
	 * @param empId
	 * @param eventName
	 * @param type
	 * @param gradeScale
	 * @param date
	 * @param cost
	 * @param location
	 * @param description
	 * @param justification
	 * @return
	 */
	public static Integer submitEvent(Integer empId, String eventName, Integer type, Integer gradeScale, LocalDateTime date,
			Double cost, String location, String description, String justification) {

		EventDaoImpl evDao = new EventDaoImpl();
		
		MasterLogger.info(EventRequestService.class, "empId " + empId + " submitted event " + eventName);

		return evDao.addNewEvent(empId, type, gradeScale, eventName, date, location, description, justification, cost);
	}

}
