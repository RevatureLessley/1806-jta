package com.revature.trms.dao;

public interface SupervisorApprovalDao {

  void insertApprovalStatus(Integer id, Integer requestId, Integer approved);
}
