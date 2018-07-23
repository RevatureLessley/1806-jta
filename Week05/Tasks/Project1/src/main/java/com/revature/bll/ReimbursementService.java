package com.revature.bll;

import com.revature.beans.EmployeeBean;
import com.revature.beans.EventTypeBean;
import com.revature.beans.NotificationBean;
import com.revature.beans.ReimbursementBean;
import com.revature.dal.EmployeeDaoImpl;
import com.revature.dal.NotificationDaoImpl;
import com.revature.dal.ReimbursementDaoImpl;
import com.revature.dal.ReimbursementTypeDaoImpl;
import com.revature.utils.LogWrapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class serves as the Business Logic Layer (BLL) for the Reimbursement data.
 */
public class ReimbursementService {

    private static final int MAX_AWARD = 1000;

    /**
     * Passes to the DAL a insert request, given a Reimbursement Request' metadata
     * @param id the unique ID of the Reimbursement (This isn't important, as the database triggers a unique one)
     * @param date the Date of the Event
     * @param location the location of the Event
     * @param description a description of the event
     * @param amount the amount of reimbursement being requested
     * @param type the type of Event
     * @param format the Format for Evaluation
     * @param url the URL of an attached file
     * @return true if insertion was successful, false otherwise
     */
    public static boolean insertRequest(int id, LocalDate date, String location, String description, double amount, int type, String format, String url, String fileName){
        //TODO: Do error checking *somehwere* that the employee exists before being able to submit a reimbursement form
        ReimbursementDaoImpl rdao = new ReimbursementDaoImpl();
        EmployeeDaoImpl edao = new EmployeeDaoImpl();
        EmployeeBean ebean = edao.retrieveEmployeeById(id);  //pulls from the database

        EventTypeBean typeBean = retrieveEventType(type);
        amount = (amount * typeBean.getPercent()) / (double)100;

        double cap = MAX_AWARD - (ebean.getPendingReimbursements() + ebean.getAwardedReimbursements());
        if (cap == 0) return false; //Reject Employee from submitting
        if (amount > cap) amount = cap; //Checks if employee is overdrawing
        ebean.setPendingReimbursements(ebean.getPendingReimbursements() + amount);

        ReimbursementBean rbean = new ReimbursementBean(
                id, //this value isn't actually inserted (And that's okay)
                ebean,
                date, location, description, amount, format, //TODO: Figure out 'Format?'
                type, 0, url, fileName
        );
        boolean success = true;
        if (!rdao.insertReimbursementForm(rbean, ebean.getSupervisorId(), "")) success = false;
        if (!edao.updateReimbursementValues(ebean)) success = false;
        return success;
    }

    /**
     * Sends a request to the DAL to look up the Reimbursement Requests of an Employee
     * @param employeeId the ID of the Employee
     * @return An ArrayList of Reimbursements
     */
    private static ArrayList<ReimbursementBean> selectRequests(int employeeId){
        ReimbursementDaoImpl rdao = new ReimbursementDaoImpl();
        return rdao.retrieveReimbursementsByEmployee(employeeId);
    }

    /**
     * Uses a helper method to get the Reimbursements of an Employee, then converts it to a JSON String
     * @param employeeId the ID of the Employee
     * @return a String representing the JSON Object of Reimbursements
     */
    public static String retrieveRequestsAsJSON(int employeeId){
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try{
            json = mapper.writeValueAsString(selectRequests(employeeId));
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
     * Passes a request to the DAL to retrieve the Event Type
     * @param eventId the ID of the Event Type
     * @return a String containing the JSON Object of the Event (uses a helper method for the conversion)
     */
    public static String retrieveEventTypeJSON(int eventId){
        EventTypeBean bean = new ReimbursementTypeDaoImpl().selectEventType(eventId);
        return eventTypeToJson(bean);
    }

    /**
     * Asks the DAL for an EventType Bean of the supplied eventID
     * @param eventId the Event Type requested
     * @return the Event Type Bean of the requested ID
     */
    public static EventTypeBean retrieveEventType(int eventId){
        return new ReimbursementTypeDaoImpl().selectEventType(eventId);
    }


    /**
     * A helper method to convert an Event Type to JSON String
     * @param bean The Event Type Bean
     * @return A String containing the JSON-formatted EventType
     */
    private static String eventTypeToJson(EventTypeBean bean){
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":").append(bean.getId()).append(",");
        sb.append("\"description\":").append("\"").append(bean.getDescription()).append("\"").append(",");
        sb.append("\"percent\":").append(bean.getPercent());
        sb.append("}");

        return sb.toString();
    }
}
