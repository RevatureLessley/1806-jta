package com.revature.trms.dao;

import static com.revature.trms.util.ConnectionUtil.close;

import com.revature.trms.bean.Employee;
import com.revature.trms.bean.EventType;
import com.revature.trms.bean.GradeFormat;
import com.revature.trms.bean.Request;
import com.revature.trms.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RequestDaoJDBC implements RequestDao {

  @Override
  public List<Request> selectRequestsById(int id) {
    return null;
  }

  @Override
  public List<Request> selectRequestsByEmail(String email) {
    return null;
  }

  @Override
  public List<Request> selectRequestsByDepartment(String department) {
    return null;
  }

  @Override
  public List<Request> selectAllRequests() {
    return null;
  }

  @Override
  public List<Request> selectAllActiveRequestsById(Integer id) {
    List<Request> requests = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM Request WHERE amount IS NULL AND employee_id = ?";
    try (Connection conn = ConnectionUtil.getConnection()) {
      ps = conn.prepareStatement(sql);
      ps.setInt(1, id);
      rs = ps.executeQuery();
      while (rs.next()) {
        requests.add(new Request(
            rs.getInt("request_id"),
            rs.getInt("employee_id"),
            rs.getString("event_name"),
            rs.getString("event_location"),
            rs.getTimestamp("event_time"),
            rs.getDouble("event_cost"),
            rs.getInt("event_type_id"),
            rs.getInt("grade_format_id"),
            rs.getTimestamp("request_time"),
            rs.getString("justification"),
            rs.getDouble("amount")
        ));
      }

    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      close(rs);
      close(ps);
    }
    return requests;
  }

  @Override
  public List<Request> selectAllClosedRequestsById(Integer id) {
    List<Request> requests = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM Request WHERE amount IS NOT NULL AND employee_id = ?";
    try (Connection conn = ConnectionUtil.getConnection()) {
      ps = conn.prepareStatement(sql);
      ps.setInt(1, id);
      rs = ps.executeQuery();
      while (rs.next()) {
        requests.add(new Request(
            rs.getInt("request_id"),
            rs.getInt("employee_id"),
            rs.getString("event_name"),
            rs.getString("event_location"),
            rs.getTimestamp("event_time"),
            rs.getDouble("event_cost"),
            rs.getInt("event_type_id"),
            rs.getInt("grade_format_id"),
            rs.getTimestamp("request_time"),
            rs.getString("justification"),
            rs.getDouble("amount")
        ));
      }

    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      close(rs);
      close(ps);
    }
    return requests;
  }

  @Override
  public List<Request> selectAllPendingRequestsBySupervisor(Integer id) {
    List<Request> requests = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM Request r INNER JOIN EMPLOYEE e ON r.employee_id = e.employee_id "
        + "LEFT JOIN SUPERVISOR_APPROVAL sa ON r.request_id = sa.request_id WHERE e.boss_id = ? AND sa.approved IS NULL";
    try (Connection conn = ConnectionUtil.getConnection()) {
      ps = conn.prepareStatement(sql);
      ps.setInt(1, id);
      rs = ps.executeQuery();
      while (rs.next()) {
        requests.add(new Request(
            rs.getInt("request_id"),
            rs.getInt("employee_id"),
            rs.getString("event_name"),
            rs.getString("event_location"),
            rs.getTimestamp("event_time"),
            rs.getDouble("event_cost"),
            rs.getInt("event_type_id"),
            rs.getInt("grade_format_id"),
            rs.getTimestamp("request_time"),
            rs.getString("justification"),
            rs.getDouble("amount")
        ));
      }

    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      close(rs);
      close(ps);
    }
    return requests;
  }

  @Override
  public List<Request> selectAllPendingRequestsByDepartment(Integer dept) {
    List<Request> requests = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM Request r INNER JOIN EMPLOYEE e ON r.employee_id = e.employee_id "
        + "LEFT JOIN DEPTHEAD_APPROVAL da ON r.request_id = da.request_id WHERE e.dept_id = ? AND da.APPROVED IS NULL";
    try (Connection conn = ConnectionUtil.getConnection()) {
      ps = conn.prepareStatement(sql);
      ps.setInt(1, dept);
      rs = ps.executeQuery();
      while (rs.next()) {
        requests.add(new Request(
            rs.getInt("request_id"),
            rs.getInt("employee_id"),
            rs.getString("event_name"),
            rs.getString("event_location"),
            rs.getTimestamp("event_time"),
            rs.getDouble("event_cost"),
            rs.getInt("event_type_id"),
            rs.getInt("grade_format_id"),
            rs.getTimestamp("request_time"),
            rs.getString("justification"),
            rs.getDouble("amount")
        ));
      }

    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      close(rs);
      close(ps);
    }
    return requests;
  }

  @Override
  public List<Request> selectAllPendingRequests() {
    List<Request> requests = new ArrayList<>();
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM Request r INNER JOIN EMPLOYEE e ON r.employee_id = e.employee_id "
        + "LEFT JOIN BENCO_APPROVAL ba ON r.request_id = ba.request_id WHERE ba.approved IS NULL";
    try (Connection conn = ConnectionUtil.getConnection()) {
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        requests.add(new Request(
            rs.getInt("request_id"),
            rs.getInt("employee_id"),
            rs.getString("event_name"),
            rs.getString("event_location"),
            rs.getTimestamp("event_time"),
            rs.getDouble("event_cost"),
            rs.getInt("event_type_id"),
            rs.getInt("grade_format_id"),
            rs.getTimestamp("request_time"),
            rs.getString("justification"),
            rs.getDouble("amount")
        ));
      }

    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      close(rs);
      close(ps);
    }
    return requests;
  }

  @Override
  public Integer insertRequest(Integer employeeId, String name, String location, Timestamp time, Double cost,
      Integer eventType, Integer gradeFormat, String justification) {

    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = "INSERT INTO Request(EMPLOYEE_ID, EVENT_NAME, EVENT_LOCATION,EVENT_TIME, EVENT_COST, EVENT_TYPE_ID, GRADE_FORMAT_ID, REQUEST_TIME, JUSTIFICATION) VALUES (?,?,?,?,?,?,?,?,?)";

    try (Connection conn = ConnectionUtil.getConnection()) {
      stmt = conn.prepareStatement(sql);
      stmt.setInt(1, employeeId);
      stmt.setString(2, name);
      stmt.setString(3, location);
      stmt.setTimestamp(4, time);
      stmt.setDouble(5, cost);
      stmt.setInt(6, eventType);
      stmt.setInt(7, gradeFormat);
      stmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
      stmt.setString(9,justification);
      rs = stmt.executeQuery();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      close(rs);
      close(stmt);
    }

    return null;
  }

  @Override
  public Request selectRequestById(Integer id) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM Request WHERE request_id = ?";

    try(Connection conn = ConnectionUtil.getConnection()) {
      stmt = conn.prepareStatement(sql);
      stmt.setInt(1, id);
      rs = stmt.executeQuery();
      if (rs.next()) {
        return new Request(            rs.getInt("request_id"),
            rs.getInt("employee_id"),
            rs.getString("event_name"),
            rs.getString("event_location"),
            rs.getTimestamp("event_time"),
            rs.getDouble("event_cost"),
            rs.getInt("event_type_id"),
            rs.getInt("grade_format_id"),
            rs.getTimestamp("request_time"),
            rs.getString("justification"),
            rs.getDouble("amount"));
      }
    }catch (SQLException ex) {
      ex.printStackTrace();
    }finally {
      close(rs);
      close(stmt);
    }
    return null;
  }
}
