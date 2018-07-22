package com.revature.dal;

import com.revature.beans.NotificationBean;
import com.revature.beans.NotificationBeanProxy;

import java.util.ArrayList;

/**
 * Interface for accessing the database on behalf of Notifications
 */
public interface NotificationDao {
    ArrayList<NotificationBeanProxy> retrieveNotificationsByEmployeeId(int id);
    NotificationBeanProxy retrieveNotificationsById(int id);
    boolean insertNotification(NotificationBean bean);
    boolean updateNotifiee(NotificationBean bean);
    boolean deleteNotificationById(int id);
}
