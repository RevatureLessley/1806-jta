package com.revature.dal;

import com.revature.beans.EmployeeBean;

public interface EmployeeDao {
    EmployeeBean retrieveEmployeeById(int id);
    boolean insertEmployee(EmployeeBean bean);
    boolean deleteEmployeeById(int id);
}
