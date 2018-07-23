package com.revature.trms.dao;

import com.revature.trms.bean.Request;
import java.sql.Timestamp;
import java.util.List;

public interface RequestDao {

  List<Request> selectRequestsById(int id);

  List<Request> selectRequestsByEmail(String email);

  List<Request> selectRequestsByDepartment(String department);

  List<Request> selectAllRequests();

  List<Request> selectAllActiveRequestsById(Integer id);

  List<Request> selectAllClosedRequestsById(Integer id);

  List<Request> selectAllPendingRequestsBySupervisor(Integer id);

  List<Request> selectAllPendingRequestsByDepartment(Integer dept);

  List<Request> selectAllPendingRequests();

  Integer insertRequest(Integer employeeId, String name, String location, Timestamp time,
      Double cost, Integer eventType, Integer gradeFormat,
      String justification);

  Request selectRequestById(Integer id);
}
