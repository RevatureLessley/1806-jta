package com.revature.services;

import java.sql.Date;

import com.revature.dao.ReimbursementDao;

public class ReimbursementService 
{
	
	public boolean insertReimbursement(String eventDate, String eventTime, String eventLocation,
									   String eventDesc, Integer eventCost, String justification,
									   Integer gradeCutoff, Integer empId, Integer eventId,
									   Integer gradingFormatId)
	{
		ReimbursementDao rd = new ReimbursementDao();
		return rd.insertReimbursementViaSp(eventDate, eventTime, eventLocation, 
										   eventDesc, eventCost, justification, 
										   gradeCutoff, empId, eventId, gradingFormatId);
	}
	
}
