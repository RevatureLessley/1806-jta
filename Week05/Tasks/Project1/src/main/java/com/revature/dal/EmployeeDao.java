package com.revature.dal;

import com.revature.beans.EmployeeBean;

import java.util.ArrayList;

/**
 * Interface for accessing the database on behalf of Employees
 */
public interface EmployeeDao {
    EmployeeBean retrieveEmployeeById(int id);
    boolean insertEmployee(EmployeeBean bean);
    boolean updateReimbursementValues(EmployeeBean bean);
    boolean deleteEmployeeById(int id);
    ArrayList<EmployeeBean> retrieveEmployeesByEmail(String email);
    ArrayList<EmployeeBean> retrieveAllEmployees();
}
