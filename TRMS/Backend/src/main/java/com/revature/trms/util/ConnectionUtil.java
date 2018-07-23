package com.revature.trms.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
  private static Connection conn = null;

  public static Connection getConnection() {
    try {
      String props[] = System.getenv("DB_TRMS").split(";");
      Class.forName(props[0]);
      conn = DriverManager.getConnection(props[1],props[2],props[3]);

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return conn;
  }

  public static void close(Statement resource) {
    if (resource != null) {
      try {
        resource.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

  public static void close(ResultSet resource) {
    if (resource != null) {
      try {
        resource.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }
}
