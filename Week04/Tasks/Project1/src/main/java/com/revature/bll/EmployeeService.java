package com.revature.bll;

import com.revature.beans.EmployeeBean;
import com.revature.dal.EmployeeDaoImpl;

import com.revature.utils.LogWrapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;


public class EmployeeService {

    public static String getAllEmployeesJSON(){
        ArrayList<EmployeeBean> beanList = getAllEmployees();
        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        try{
            json = mapper.writeValueAsString(beanList);
        }catch(IOException e){
            LogWrapper.log(EmployeeService.class, e);
        }

        return json;
    }

    public static ArrayList<EmployeeBean> getAllEmployees(){
        return new EmployeeDaoImpl().retrieveAllEmployees();
    }

    public static EmployeeBean[] getEmployeesByEmail(String email){
        ArrayList<EmployeeBean> beanList = new EmployeeDaoImpl().retrieveEmployeesByEmail(email);
        return beanList.toArray(new EmployeeBean[0]);   //code inspection told me to pass an empty array
    }

    public static int getUnusedId(){
        //TODO: efficiency: remove usedId once algorithm declares that its been used and incremented
        ArrayList<EmployeeBean> employees = new EmployeeDaoImpl().retrieveAllEmployees();
        int[] usedIds = new int[employees.size()];
        for (int i = 0; i < usedIds.length; i++) usedIds[i] = employees.get(i).getId();
        for (int i = 1; true; i++){
            boolean isUsed = false;
            for (int j = 0; j < usedIds.length; j++){
                if (i == usedIds[j]) {
                    isUsed = true;
                    break;      //no need to continue this loop
                }
            }
            if (!isUsed) return i;
        }
    }

    public static boolean addEmployee(int id, String fname, String lname, String password, long phone, String email, double pending, double awarded, int superId, int deptId, int benCoId){
        return new EmployeeDaoImpl().insertEmployee(new EmployeeBean(
                id, fname, lname, password, phone, email, pending, awarded, superId, deptId, benCoId));
    }
}
