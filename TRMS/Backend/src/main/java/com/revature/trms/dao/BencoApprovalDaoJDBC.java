package com.revature.trms.dao;

import static com.revature.trms.util.ConnectionUtil.close;

import com.revature.trms.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BencoApprovalDaoJDBC implements BencoApprovalDao {

  @Override
  public void insertApprovalStatus(Integer id, Integer requestId, Integer approved) {
    PreparedStatement ps = null;
    String sql = "INSERT INTO BENCO_APPROVAL_APPROVAL VALUES (?,?,?,?)";
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
}
