package com.revature.services;

import com.revature.dao.EventDao;

public class EventService 
{

	public Integer calculateAmountLeft(Integer amount, Integer eventId)
	{
		EventDao ed = new EventDao();
		Integer amountLeft = 0;
		Integer initialPercent = 0;
		Double percent = 0.0;
		
		initialPercent = ed.getReimbursementPercent(eventId);
		percent = (double)initialPercent/100;
		amountLeft = (int) Math.round(amount * percent);
		
		return amountLeft;
	}
	
}
