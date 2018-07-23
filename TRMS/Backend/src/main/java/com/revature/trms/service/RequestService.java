package com.revature.trms.service;

import com.revature.trms.bean.Request;
import com.revature.trms.dao.RequestDao;
import com.revature.trms.dao.RequestDaoJDBC;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

public class RequestService {

  public static String getActiveRequestsById(Integer id) {
    List<Request> requests = getAllActiveRequestsById(id);
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(requests);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public static String getClosedRequestsById(Integer id) {
    List<Request> requests = getAllClosedRequestsById(id);
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(requests);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public static String getSupervisorPendingRequests(Integer id) {
    List<Request> requests = getAllPendingRequestsBySupervisorId(id);
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(requests);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }



  public static String getDepartmentPendingRequests(Integer dept) {
    List<Request> requests = getAllPendingRequestsByDepartmentId(dept);
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(requests);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }



  public static String getBencoPendingRequests() {
    List<Request> requests = getAllPendingRequests();
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(requests);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public static String getRequestByIdJSON(Integer id) {
    Request request = getRequestById(id);
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(request);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  private static List<Request> getAllActiveRequestsById(Integer id) {
    RequestDao rd = new RequestDaoJDBC();
    return rd.selectAllActiveRequestsById(id);
  }

  private static List<Request> getAllClosedRequestsById(Integer id) {
    RequestDao rd = new RequestDaoJDBC();
    return rd.selectAllClosedRequestsById(id);
  }

  private static List<Request> getAllPendingRequestsBySupervisorId(Integer id) {
    RequestDao rd = new RequestDaoJDBC();
    return rd.selectAllPendingRequestsBySupervisor(id);
  }
  private static List<Request> getAllPendingRequestsByDepartmentId(Integer dept) {
    RequestDao rd = new RequestDaoJDBC();
    return rd.selectAllPendingRequestsByDepartment(dept);
  }

  private static List<Request> getAllPendingRequests() {
    RequestDao rd = new RequestDaoJDBC();
    return rd.selectAllPendingRequests();
  }

  public static Request getRequestById(Integer id) {
    RequestDao rd = new RequestDaoJDBC();
    return rd.selectRequestById(id);
  }
}
