package com.revature.trms.dao;

import com.revature.trms.bean.Employee;

public interface EmployeeDao {
  Employee selectEmployeeById(int id);
  Employee selectEmployeeByEmail(String email);
  Integer selectEmployeeIdByEmail(String email);
  Integer selectDepartmentIdByEmail(String email);
  Integer selectTitleIdByEmail(String email);
}
