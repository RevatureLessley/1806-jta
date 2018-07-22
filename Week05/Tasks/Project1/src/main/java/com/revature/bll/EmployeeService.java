package com.revature.bll;

import com.revature.beans.EmployeeBean;
import com.revature.dal.EmployeeDaoImpl;

import com.revature.utils.LogWrapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class serves as the Business Logic Layer (BLL) for the Employee data.
 */
public class EmployeeService {

    /**
     * Uses this.getAllEmployees() to grab all the Employees, then converts them into a JSON format.
     * @return a String pf the JSON-formatted data containing all the Employees
     */
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

    /**
     * Grabs a single employee from the database by it's unique ID and converts it to a JSON String
     * @param id The ID of the Employee being grabbed from the database
     * @return The String JSON representation of the Employee bean.
     */
    public static String getEmployeeByIdJson(int id){
        EmployeeBean bean = new EmployeeDaoImpl().retrieveEmployeeById(id);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        try{
            json = mapper.writeValueAsString(bean);
        }catch(IOException e){
            LogWrapper.log(EmployeeService.class, e);
        }

        return json;
    }

    /**
     * Helper method to grab all the Employees from the database.
     * @return An ArrayList of EmployeeBean Objects; all the Employees in the database
     */
    private static ArrayList<EmployeeBean> getAllEmployees(){
        return new EmployeeDaoImpl().retrieveAllEmployees();
    }

    /**
     * This method gets all Employees of an email, but since email is unique in the database, it really just
     * returns a single Employee, wrapped in an ArrayList. Sorry.
     * @param email the email to index the Employee by
     * @return An Array containing an Employee Bean
     */
    public static EmployeeBean[] getEmployeesByEmail(String email){
        ArrayList<EmployeeBean> beanList = new EmployeeDaoImpl().retrieveEmployeesByEmail(email);
        return beanList.toArray(new EmployeeBean[0]);   //code inspection told me to pass an empty array
    }

    /**
     * This method searches for an ID number that hasn't been assigned to an Employee, and grabs it.
     * @return a unique ID for the Employee
     */
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

    /**
     * Passes on a call to the database to insert the Employee with the following metadata into the database
     * @param id The unique ID of the Employee
     * @param fname The Employee's first name
     * @param lname The Employee's last name
     * @param password The Employee's password
     * @param phone The Employee's phone number
     * @param email The Employee's email address
     * @param pending The Employee's amount of pending reimbursements
     * @param awarded The Employee's amount of awarded reimbursements
     * @param superId The Employee's supervisor's ID
     * @param deptId The Employee's Department Head's ID
     * @param benCoId The Employee's Benefits Coordinator's ID
     * @return true if the insertion was successful, false otherwise
     */
    public static boolean addEmployee(int id, String fname, String lname, String password, long phone, String email, double pending, double awarded, int superId, int deptId, int benCoId){
        return new EmployeeDaoImpl().insertEmployee(new EmployeeBean(
                id, fname, lname, password, phone, email, pending, awarded, superId, deptId, benCoId));
    }
}
