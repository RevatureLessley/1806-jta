package com.revature.displaywrapper;

import com.revature.bean.Notificaiton;
import com.revature.service.EmployeeService;
import com.revature.utils.StringManip;

public class NotificationDisplay {

	private Notificaiton notification;
	private String dateString;
	

	private String sourceName; 

	public NotificationDisplay(Notificaiton notificaiton) {
		
		this.notification = notificaiton;
		sourceName = EmployeeService.getEmployeeName(notificaiton.getEmpSource());
		dateString = StringManip.formatDate(notificaiton.getTimestamp());
	}

	@Override
	public String toString() {
		return "NotificationDisplay [notifiction=" + notification + ", dateString=" + dateString + ", sourceName="
				+ sourceName + "]";
	}

	public Notificaiton getNotification() {
		return notification;
	}

	public void setNotification(Notificaiton notifiction) {
		this.notification = notifiction;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
}
