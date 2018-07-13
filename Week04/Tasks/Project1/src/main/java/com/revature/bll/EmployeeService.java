package com.revature.bll;

import com.revature.beans.EmployeeBean;
import com.revature.dal.EmployeeDaoImpl;

import java.util.ArrayList;

public class EmployeeService {

    public static EmployeeBean[] getEmployeesByEmail(String email){
        ArrayList<EmployeeBean> beanList = new EmployeeDaoImpl().retrieveEmployeesByEmail(email);
        return beanList.toArray(new EmployeeBean[0]);   //code inspection told me to pass an empty array
    }
}
