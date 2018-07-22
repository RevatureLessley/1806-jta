package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Notificaiton;
import com.revature.dao.NotificationDao;
import com.revature.displaywrapper.NotificationDisplay;
import com.revature.utils.StringManip;

public class NotificationService {

	public static void eventAddNotification(Integer eventId, Integer userId, String message) {
		new NotificationDao().eventAddNotification(eventId, userId, message);
	}

	public static void employeeAddNotification(Integer targetId, Integer sourceId, String message) {
		new NotificationDao().eventAddNotification(targetId, sourceId, message);
	}

	public static String selectUserNotifications(Integer empId) {
		List<Notificaiton> notifications = new NotificationDao().selectUserNotifications(empId);
		List<NotificationDisplay> wrappedNotificaitons = new ArrayList<>();

		for (Notificaiton n : notifications) {
			wrappedNotificaitons.add(new NotificationDisplay(n));
		}

		return StringManip.getJSONString(wrappedNotificaitons);
	}

	public static String selectEventNotifications(Integer eventId) {
		List<Notificaiton> notifications = new NotificationDao().selectEventNotifications(eventId);
		List<NotificationDisplay> wrappedNotificaitons = new ArrayList<>();

		for (Notificaiton n : notifications) {
			wrappedNotificaitons.add(new NotificationDisplay(n));
		}

		return StringManip.getJSONString(wrappedNotificaitons);
	}

	public static void notificationMarkAsRead(Integer ntId) {

		new NotificationDao().notificationMarkAsRead(ntId);
	}
}
