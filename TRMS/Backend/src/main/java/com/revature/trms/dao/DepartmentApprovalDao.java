package com.revature.trms.dao;

public interface DepartmentApprovalDao {

  void insertApprovalStatus(Integer id, Integer requestId, Integer approved);
}
