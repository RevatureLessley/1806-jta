package com.revature.service;

import java.util.List;

import com.revature.bean.Notificaiton;
import com.revature.dao.NotificationDao;
import com.revature.utils.StringManip;

public class NotificationService {

	public static void eventAddNotification(Integer eventId, Integer userId, String message) {
		new NotificationDao().eventAddNotification(eventId, userId, message);
	}
	
	public static void employeeAddNotification(Integer targetId, Integer sourceId, String message) {
		new NotificationDao().eventAddNotification(targetId, sourceId, message);
	}
	
	public static String selectUserNotifications(Integer empId){
		List<Notificaiton> notifications = new NotificationDao().selectUserNotifications(empId);
		return StringManip.getJSONString(notifications);
	}

	public static String selectEventNotifications(Integer eventId){
		List<Notificaiton> notifications = new NotificationDao().selectEventNotifications(eventId);
		return StringManip.getJSONString(notifications);
	}
}
