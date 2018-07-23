package com.revature.services;

import com.revature.dao.EventDao;


/**
 * Service class that makes calls to the EventDao.
 * @author Logan Brewer
 *
 */
public class EventService 
{

	/**
	 * Return an integer representing the amount an employee will have left in
	 * their account after a reimbursement has been requested or declined.
	 * @param amount
	 * @param eventId
	 * @return
	 */
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
