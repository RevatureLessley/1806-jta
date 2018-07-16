package com.trms.beans;

import java.sql.Date;

public class Event {
//	eventID number(6),
//    eventName varchar(100),
//    eventStartDate date,
//    eventType varchar2(100),
//    location varchar2(500),
//    description varchar2(2000),
	
	private int ID;
	private String eventType, eventName, location, description;

	private Date startDate;
	
	public Event() {
		super();
	}

	public Event(int iD, String eventName, Date startDate, String eventType, 
			String location, String description) {
		super();
		ID = iD;
		this.eventName = eventName;
		this.eventType = eventType;
		this.location = location;
		this.description = description;
		this.startDate = startDate;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String getEventName() {
		return eventName;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Event [ID=" + ID + ", eventType=" + eventType + ", eventName=" + eventName + ", location=" + location
				+ ", description=" + description + ", startDate=" + startDate + "]";
	}	
}
