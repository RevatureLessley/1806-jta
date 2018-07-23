package com.revature.trms.dao;

import static com.revature.trms.util.ConnectionUtil.close;

import com.revature.trms.bean.EventType;
import com.revature.trms.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventTypeDaoJDBC implements EventTypeDao {

  @Override
  public List<EventType> selectAllEventTypes() {
    List<EventType> eventTypes = new ArrayList<>();
    Statement stmt = null;
    ResultSet rs = null;
    String sql = "SELECT * FROM EventType";
    try (Connection conn = ConnectionUtil.getConnection()) {
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        eventTypes.add(new EventType(
            rs.getInt("event_type_id"),
            rs.getString("event_type_name"),
            rs.getInt("reimbursement_rate")
        ));
      }

    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      close(rs);
      close(stmt);
    }
    return eventTypes;
  }
}
