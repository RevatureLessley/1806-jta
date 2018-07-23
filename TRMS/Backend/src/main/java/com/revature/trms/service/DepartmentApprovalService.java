package com.revature.trms.service;

import com.revature.trms.dao.DepartmentApprovalDao;
import com.revature.trms.dao.DepartmentApprovalDaoJDBC;

public class DepartmentApprovalService {

  public static void setApprovalStatus(Integer id, Integer requestId, Integer approved) {
    DepartmentApprovalDao dad = new DepartmentApprovalDaoJDBC();
    dad.insertApprovalStatus(id, requestId, approved);
  }
}
