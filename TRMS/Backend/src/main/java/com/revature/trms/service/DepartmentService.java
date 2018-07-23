package com.revature.trms.service;

import com.revature.trms.dao.DepartmentDao;
import com.revature.trms.dao.DepartmentDaoJDBC;

public class DepartmentService {

  public static Integer getDepartmentHeadId(Integer dept) {
    DepartmentDao dd = new DepartmentDaoJDBC();
    return dd.selectDepartmentHeadByDeptId(dept);
  }
}
