package com.revature.bll;

import com.revature.beans.EmployeeBean;
import com.revature.beans.EventTypeBean;
import com.revature.beans.NotificationBean;
import com.revature.beans.NotificationBeanProxy;
import com.revature.dal.EmployeeDaoImpl;
import com.revature.dal.NotificationDaoImpl;
import com.revature.dal.ReimbursementDaoImpl;
import com.revature.dal.ReimbursementTypeDaoImpl;
import com.revature.utils.LogWrapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class NotificationService {

    public static boolean updateNotification(int notificationId, int status){
        NotificationBeanProxy beanProxy = new NotificationDaoImpl().retrieveNotificationsById(notificationId);
        NotificationBean bean = new NotificationBean(beanProxy.getId(),
                new ReimbursementDaoImpl().retrieveReimbursementFormById(beanProxy.getReimbursementId()),
                new EmployeeDaoImpl().retrieveEmployeeById(beanProxy.getNotifieeId()),
                beanProxy.isAtSupervisor(),
                beanProxy.isAtDeptHead(),
                beanProxy.isAtBenCo(),
                beanProxy.getApprovalCount());

        if (status == 2){
            //Hacks the system so that the notification gets deleted and the status updated to denied.
            bean.setAtBenCo(true);
            bean.setAtDeptHead(false);
            bean.setAtSupervisor(false);
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
            if (!new NotificationDaoImpl().deleteNotificationById(bean.getId())) success = false;
            if(!new ReimbursementDaoImpl().updateReimbursementStatus(bean.getReimbursement().getId(), status)) success = false;
            return success;

        }
        LogWrapper.log(NotificationService.class, "Something fishy happened.", LogWrapper.Severity.WARN); //TODO: Edit this log.
        return false;

    }

    public static  ArrayList<NotificationBean> getAllNotifications(int id){
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
                    proxy.getApprovalCount()
            ));
        }
        return beanList;
    }

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

    private static String eventTypeToJson(EventTypeBean bean){
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":").append(bean.getId()).append(",");
        sb.append("\"description\":").append("\"").append(bean.getDescription()).append("\"").append(",");
        sb.append("\"percent\":").append(bean.getPercent());
        sb.append("}");

        return sb.toString();
    }

}
