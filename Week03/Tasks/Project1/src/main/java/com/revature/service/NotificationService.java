package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Notificaiton;
import com.revature.dao.NotificationDao;
import com.revature.displaywrapper.NotificationDisplay;
import com.revature.utils.StringManip;

public class NotificationService {

	/**
	 * Adds a notification from a given user to a user that owns the given event.
	 * The notification is accompanied by a message and associated with an event
	 */
	public static void eventAddNotification(Integer eventId, Integer userId, String message) {
		new NotificationDao().eventAddNotification(eventId, userId, message);
	}

	/**
	 * Adds a notification from a given user to another user. The notification is
	 * accompanied by a message
	 */
	public static void employeeAddNotification(Integer targetId, Integer sourceId, String message) {
		new NotificationDao().eventAddNotification(targetId, sourceId, message);
	}

	/**
	 * Select all notifications that belong to a specific user
	 * 
	 * @param empId
	 * @return
	 */
	public static String selectUserNotifications(Integer empId) {
		List<Notificaiton> notifications = new NotificationDao().selectUserNotifications(empId);
		List<NotificationDisplay> wrappedNotificaitons = new ArrayList<>();

		for (Notificaiton n : notifications) {
			wrappedNotificaitons.add(new NotificationDisplay(n));
		}

		return StringManip.getJSONString(wrappedNotificaitons);
	}

	/**
	 * select all notifications that belong to a specific event
	 * 
	 * @param eventId
	 * @return
	 */
	public static String selectEventNotifications(Integer eventId) {
		List<Notificaiton> notifications = new NotificationDao().selectEventNotifications(eventId);
		List<NotificationDisplay> wrappedNotificaitons = new ArrayList<>();

		for (Notificaiton n : notifications) {
			wrappedNotificaitons.add(new NotificationDisplay(n));
		}

		return StringManip.getJSONString(wrappedNotificaitons);
	}

	/**
	 * marks a notification as read by a specific user
	 * 
	 * @param ntId
	 * @param userId
	 */
	public static void notificationMarkAsRead(Integer ntId, Integer userId) {

		new NotificationDao().notificationMarkAsRead(ntId, userId);
	}
}
