package com.revature.bll;

import com.revature.beans.EmployeeBean;
import com.revature.beans.EventTypeBean;
import com.revature.beans.ReimbursementBean;
import com.revature.dal.EmployeeDaoImpl;
import com.revature.dal.ReimbursementDaoImpl;
import com.revature.dal.ReimbursementTypeDaoImpl;
import com.revature.utils.LogWrapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReimbursementService {
    public static boolean insertRequest(int id, LocalDate date, String location, String description, double amount, int type, String url){
        //TODO: Do error checking *somehwere* that the employee exists before being able to submit a reimbursement form
        ReimbursementDaoImpl rdao = new ReimbursementDaoImpl();
        EmployeeDaoImpl edao = new EmployeeDaoImpl();

        return rdao.insertReimbursementForm(
                new ReimbursementBean(
                    id, //this value isn't actually inserted (And that's okay)
                    edao.retrieveEmployeeById(id),  //pulls from the database
                    date, location, description, amount, "Format?", //TODO: Figure out 'Format?'
                    type, false, url
                )
        );
    }

    public static ArrayList<ReimbursementBean> selectRequests(int employeeId){
        ReimbursementDaoImpl rdao = new ReimbursementDaoImpl();
        return rdao.retrieveReimbursementsByEmployee(employeeId);
    }

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

    public static String retrieveEventType(int eventId){
        EventTypeBean bean = new ReimbursementTypeDaoImpl().selectEventType(eventId);
        return eventTypeToJson(bean);
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
