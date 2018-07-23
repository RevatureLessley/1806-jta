package com.revature.beans;

/**
 * Event Bean class with the following attributes:
 * <br>-Integer eventId
 * <br>-String  eventName
 * <br>-Integer reimbursementPercent
 * <br>-Integer gradingFormatId
 * <br>
 * @author Logan Brewer
 *
 */
public class Event 
{
	private Integer eventId;
	private String eventName;
	private Integer reimbursementPercent;
	private Integer gradingFormatId;
	
	public Event(Integer eventId, String eventName, Integer reimbursementPercent, Integer gradingFormatId) 
	{
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.reimbursementPercent = reimbursementPercent;
		this.gradingFormatId = gradingFormatId;
	}
	
	public Event(Integer eventId, String eventName, Integer reimbursementPercent) 
	{
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.reimbursementPercent = reimbursementPercent;
	}
	
	public Event(String eventName, Integer reimbursementPercent) 
	{
		super();
		this.eventName = eventName;
		this.reimbursementPercent = reimbursementPercent;
	}
	
	public Event() 
	{
		super();
	}
	
	public Integer getEventId() 
	{
		return eventId;
	}
	
	public void setEventId(Integer eventId) 
	{
		this.eventId = eventId;
	}
	
	public String getEventName() 
	{
		return eventName;
	}
	
	public void setEventName(String eventName) 
	{
		this.eventName = eventName;
	}
	
	public Integer getReimbursementPercent() 
	{
		return reimbursementPercent;
	}
	
	public void setReimbursementPercent(Integer reimbursementPercent) 
	{
		this.reimbursementPercent = reimbursementPercent;
	}
	
	public Integer getGradingFormatId() 
	{
		return gradingFormatId;
	}
	
	public void setGradingFormatId(Integer gradingFormatId) 
	{
		this.gradingFormatId = gradingFormatId;
	}
	
	@Override
	public String toString() 
	{
		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", reimbursementPercent="
				+ reimbursementPercent + ", gradingFormatId=" + gradingFormatId + "]";
	}
	
}
