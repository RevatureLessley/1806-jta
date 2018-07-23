package com.revature.trms.dao;

import com.revature.trms.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDaoJDBC implements DepartmentDao {

  @Override
  public Integer selectDepartmentHeadByDeptId(Integer dept) {
    PreparedStatement ps;
    ResultSet rs;
    String sql = "SELECT dept_head FROM Department WHERE dept_id = ?";
    try (Connection conn = ConnectionUtil.getConnection()) {
      ps = conn.prepareStatement(sql);
      ps.setInt(1, dept);
      rs = ps.executeQuery();
      if (rs.next()) {
        return rs.getInt(1);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
