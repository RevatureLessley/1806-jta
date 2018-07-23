package com.revature.trms.service;

import com.revature.trms.dao.SupervisorApprovalDao;
import com.revature.trms.dao.SupervisorApprovalDaoJDBC;

public class SupervisorApprovalService {

  public static void setApprovalStatus(Integer id, Integer requestId, Integer approved) {
    SupervisorApprovalDao sad = new SupervisorApprovalDaoJDBC();
    sad.insertApprovalStatus(id, requestId, approved);
  }
}
