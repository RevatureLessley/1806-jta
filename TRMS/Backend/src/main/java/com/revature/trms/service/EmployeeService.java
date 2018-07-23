package com.revature.trms.service;

import com.revature.trms.bean.Employee;
import com.revature.trms.dao.EmployeeDao;
import com.revature.trms.dao.EmployeeDaoJDBC;

public class EmployeeService {

  public static Employee getEmployeeById(Integer id) {
    EmployeeDao ed = new EmployeeDaoJDBC();
    return ed.selectEmployeeById(id);
  }

  public static String getEmployeeJSON(Integer id) {
    return getEmployeeById(id).toString();
  }

  public static Integer getEmployeeIdByEmail(String email) {
    EmployeeDao ed = new EmployeeDaoJDBC();
    return ed.selectEmployeeIdByEmail(email);
  }

  public static Integer getDepartmentIdByEmail(String email) {
    EmployeeDao ed = new EmployeeDaoJDBC();
    return ed.selectDepartmentIdByEmail(email);
  }

  public static Integer getTileIdByEmail(String email) {
    EmployeeDao ed = new EmployeeDaoJDBC();
    return ed.selectTitleIdByEmail(email);
  }

}
