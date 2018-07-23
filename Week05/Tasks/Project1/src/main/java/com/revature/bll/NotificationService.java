package com.revature.bll;

import com.revature.beans.*;
import com.revature.dal.EmployeeDaoImpl;
import com.revature.dal.NotificationDaoImpl;
import com.revature.dal.ReimbursementDaoImpl;
import com.revature.dal.ReimbursementTypeDaoImpl;
import com.revature.utils.LogWrapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class serves as the Business Logic Layer (BLL) for the Notification data.
 */
public class NotificationService {

    /**
     * Updates the current Notification with the new approval status
     * @param notificationId the unique ID of the Notification
     * @param status the approval status of the Notification. 0 = Pending, 1 = Approved, 2 = Denied, 3 = Sent Back
     * @return true if the update is successful, false if otherwise
     */
    public static boolean updateNotification(int notificationId, int status, String text){
        NotificationBeanProxy beanProxy = new NotificationDaoImpl().retrieveNotificationById(notificationId);
        NotificationBean bean = new NotificationBean(beanProxy.getId(),
                new ReimbursementDaoImpl().retrieveReimbursementFormById(beanProxy.getReimbursementId()),
                new EmployeeDaoImpl().retrieveEmployeeById(beanProxy.getNotifieeId()),
                beanProxy.isAtSupervisor(),
                beanProxy.isAtDeptHead(),
                beanProxy.isAtBenCo(),
                beanProxy.getApprovalCount(),
                beanProxy.getAdditionalInfo());
        boolean decline = false;
        if (status == 2){
            //Hacks the system so that the notification gets deleted and the status updated to denied.
            bean.setAtBenCo(true);
            bean.setAtDeptHead(false);
            bean.setAtSupervisor(false);
            decline = true;
        }
        else if (status == 3){
            bean.setAtSupervisor(false);
            bean.setAtDeptHead(false);
            bean.setAtBenCo(false);
            bean.setNotifiee(bean.getReimbursement().getEmployee());
            bean.setApprovalCount(-1);  //will become 0 prior to insertion
            bean.setAdditionalInfo(text);
            return new NotificationDaoImpl().updateNotifiee(bean);
        }

        if (bean.isAtSupervisor()) {
            bean.setAtSupervisor(false);
            bean.setAtDeptHead(true);
            bean.setNotifiee(new EmployeeDaoImpl().retrieveEmployeeById(bean.getReimbursement().getEmployee().getDeptHeadId()));
            return new NotificationDaoImpl().updateNotifiee(bean);
        }
        else if (bean.isAtDeptHead()) {
            bean.setAtDeptHead(false);
            bean.setAtBenCo(true);
            bean.setNotifiee(new EmployeeDaoImpl().retrieveEmployeeById(bean.getReimbursement().getEmployee().getBenCoId()));
            return new NotificationDaoImpl().updateNotifiee(bean);
        }
        else if (bean.isAtBenCo()) {
            bean.setAtBenCo(false);
            boolean success = true;
            EmployeeBean ebean = bean.getReimbursement().getEmployee();
            ebean.setPendingReimbursements(ebean.getPendingReimbursements() - bean.getReimbursement().getReimbursement());

            if (!decline) ebean.setAwardedReimbursements(ebean.getAwardedReimbursements() + bean.getReimbursement().getReimbursement());

            if (!new NotificationDaoImpl().deleteNotificationById(bean.getId())) success = false;
            if(!new ReimbursementDaoImpl().updateReimbursementStatus(bean.getReimbursement().getId(), status)) success = false;
            if (!new EmployeeDaoImpl().updateReimbursementValues(ebean)) success = false;
            return success;

        }
        else{
            System.out.println("Checkpoint");
            bean.setAtSupervisor(true);
            bean.setAtDeptHead(false);
            bean.setAtBenCo(false);
            bean.setAdditionalInfo(text);
            EmployeeBean supervisor = null;
            if ((supervisor = new EmployeeDaoImpl().retrieveEmployeeById(bean.getReimbursement().getEmployee().getSupervisorId())) == null) return false;
            bean.setNotifiee(supervisor);
            return new NotificationDaoImpl().updateNotifiee(bean);
        }

    }

    /**
     * Grabs all the Notifications stored in the database.
     * @param id The Employee's ID to which the Notifications are to pop up for.
     * @return An ArrayList of Notification Beans
     */
    private static  ArrayList<NotificationBean> getAllNotifications(int id){
        ArrayList<NotificationBeanProxy> beanProxyList = new NotificationDaoImpl().retrieveNotificationsByEmployeeId(id);
        ArrayList<NotificationBean> beanList = new ArrayList<>();
        for(NotificationBeanProxy proxy : beanProxyList){
            beanList.add(new NotificationBean(
                    proxy.getId(),
                    new ReimbursementDaoImpl().retrieveReimbursementFormById(proxy.getReimbursementId()),
                    new EmployeeDaoImpl().retrieveEmployeeById(proxy.getNotifieeId()),
                    proxy.isAtSupervisor(),
                    proxy.isAtDeptHead(),
                    proxy.isAtBenCo(),
                    proxy.getApprovalCount(),
                    proxy.getAdditionalInfo()
            ));
        }
        return beanList;
    }

    /**
     * Uses a helper method to grab all the Notifications of an Employee, and then converts it to a JSON String
     * @param employeeId the ID of the Employee receiving the Notifications
     * @return a String of JSON-formatted Notification Beans
     */
    public static String getAllNotificationsJson(int employeeId){
        ArrayList<NotificationBean> beanList = getAllNotifications(employeeId);

        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try{
            json = mapper.writeValueAsString(beanList);
        }
        catch (IOException e){
            LogWrapper.log(EmployeeService.class, e);
        }
        String[] splits = json.split(",");
        for(String s : splits){
            final String TARGET = "\"eventType\":";
            if(s.contains(TARGET)){
                String s1,s2;
                s1 = s.substring(0,TARGET.length());
                s2 = s.substring(TARGET.length(), s.length());
                String eventJson = eventTypeToJson(new ReimbursementTypeDaoImpl().selectEventType(Integer.parseInt(s2)));
                json = json.replace(s, s1 + eventJson);
            }
        }
        return json;
    }

    /**
     * Helper method to convert an Event Type to JSON
     * NOTE: If you are a developer, please refactor the code so that this method is a Utility method (Hint: it's duplicate code)
     * @param bean The Event Type Bean being converted
     * @return a String containing the JSON representation of the Event Type Bean
     */
    private static String eventTypeToJson(EventTypeBean bean){
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":").append(bean.getId()).append(",");
        sb.append("\"description\":").append("\"").append(bean.getDescription()).append("\"").append(",");
        sb.append("\"percent\":").append(bean.getPercent());
        sb.append("}");

        return sb.toString();
    }

    /**
     * Calls the DAL to retrieve a Notification from the database
     * @param id the ID of the requested Notification
     * @return the Notification of the ID
     */
    public static NotificationBean getNotificationById(int id){
        NotificationBeanProxy proxy = new NotificationDaoImpl().retrieveNotificationById(id);
        if (proxy == null) return null;
        ReimbursementBean rbean = new ReimbursementDaoImpl().retrieveReimbursementFormById(proxy.getReimbursementId());
        EmployeeBean ebean = new EmployeeDaoImpl().retrieveEmployeeById(proxy.getNotifieeId());
        NotificationBean bean = new NotificationBean(
                proxy.getId(),
                rbean,
                ebean,
                proxy.isAtSupervisor(),
                proxy.isAtDeptHead(),
                proxy.isAtBenCo(),
                proxy.getApprovalCount(),
                proxy.getAdditionalInfo());
        return bean;
    }

}
