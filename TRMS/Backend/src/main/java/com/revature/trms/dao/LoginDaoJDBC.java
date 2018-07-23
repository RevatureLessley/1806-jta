package com.revature.trms.dao;

import com.revature.trms.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoJDBC implements LoginDao {

  @Override
  public String selectPasswordById(Integer id) {
    //TODO write the query to get the password from the database
    PreparedStatement ps;
    ResultSet rs;
    String sql = "SELECT password_hash FROM PASSWORD WHERE employee_id = ?";
    try (Connection conn = ConnectionUtil.getConnection()) {
      ps = conn.prepareStatement(sql);
      ps.setInt(1, id);
      rs = ps.executeQuery();
      if (rs.next()) {
        return rs.getString(1);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
