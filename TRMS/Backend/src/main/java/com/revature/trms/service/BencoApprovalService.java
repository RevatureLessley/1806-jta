package com.revature.trms.service;

import com.revature.trms.dao.BencoApprovalDao;
import com.revature.trms.dao.BencoApprovalDaoJDBC;

public class BencoApprovalService {

  public static void setApprovalStatus(Integer id, Integer requestId, Integer approved) {
    BencoApprovalDao bad = new BencoApprovalDaoJDBC();
    bad.insertApprovalStatus(id, requestId, approved);
  }
}
