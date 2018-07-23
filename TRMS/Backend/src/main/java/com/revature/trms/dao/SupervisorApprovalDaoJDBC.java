package com.revature.trms.dao;

import static com.revature.trms.util.ConnectionUtil.close;

import com.revature.trms.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupervisorApprovalDaoJDBC implements SupervisorApprovalDao {

  @Override
  public void insertApprovalStatus(Integer id, Integer requestId, Integer approved) {

    if (selectApprovalByRequestId(requestId) > 0) return;

    PreparedStatement ps = null;
    String sql = "INSERT INTO SUPERVISOR_APPROVAL VALUES (?,?,?,?)";
    try (Connection conn = ConnectionUtil.getConnection()) {
      ps = conn.prepareStatement(sql);
      ps.setInt(1, requestId);
      ps.setInt(2, id);
      ps.setInt(3, approved);
      ps.setString(4, "");
      ps.executeQuery();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      close(ps);
    }
  }

  public Integer selectApprovalByRequestId(Integer requestId) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT COUNT(*) FROM Supervisor_Approval WHERE request_id = ?";
    try(Connection conn = ConnectionUtil.getConnection()) {
      stmt = conn.prepareStatement(sql);
      stmt.setInt(1, requestId);
      rs = stmt.executeQuery();
      if (rs.next()) return rs.getInt(1);
    }catch (SQLException ex) {
      ex.printStackTrace();
    }
    return 0;
  }
}
