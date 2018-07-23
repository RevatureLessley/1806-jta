package com.revature.trms.dao;

import static com.revature.trms.util.ConnectionUtil.close;

import com.revature.trms.bean.GradeFormat;
import com.revature.trms.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GradeFormatDaoJDBC implements GradeFormatDao {

  @Override
  public List<GradeFormat> selectAllGradeFormats() {
    List<GradeFormat> formats = new ArrayList<>();
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM GradeFormat";
    try (Connection conn = ConnectionUtil.getConnection()) {
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        formats.add(new GradeFormat(
            rs.getInt("grade_format_id"),
            rs.getString("grade_format_desc")
        ));
      }

    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      close(rs);
      close(stmt);
    }
    return formats;
  }
}
