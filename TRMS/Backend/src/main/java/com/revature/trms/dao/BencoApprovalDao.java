package com.revature.trms.dao;

public interface BencoApprovalDao {

  void insertApprovalStatus(Integer id, Integer requestId, Integer approved);
}
