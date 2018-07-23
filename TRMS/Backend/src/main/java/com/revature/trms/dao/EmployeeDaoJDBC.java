package com.revature.trms.dao;

import com.revature.trms.bean.Employee;
import com.revature.trms.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoJDBC implements EmployeeDao {

  @Override
  public Employee selectEmployeeById(int id) {
    PreparedStatement ps;
    ResultSet rs;
    String sql = "SELECT * FROM Employee WHERE EMPLOYEE_ID = ?";
    try (Connection conn = ConnectionUtil.getConnection()) {
      ps = conn.prepareStatement(sql);
      ps.setInt(1, id);
      rs = ps.executeQuery();
      if (rs.next()) {
        return new Employee(
            rs.getInt("employee_id"),
            rs.getString("fname"),
            rs.getString("lname"),
            rs.getString("email"),
            rs.getInt("title_id"),
            rs.getInt("dept_id"),
            rs.getInt("boss_id"));
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return null;
  }

  @Override
  public Employee selectEmployeeByEmail(String email) {
    PreparedStatement ps;
    ResultSet rs;
    String sql = "SELECT * FROM Employee WHERE EMAIL = ?";
    try (Connection conn = ConnectionUtil.getConnection()) {
      ps = conn.prepareStatement(sql);
      ps.setString(1, email);
      rs = ps.executeQuery();
      if (rs.next()) {
        return new Employee(
            rs.getInt("employee_id"),
            rs.getString("fname"),
            rs.getString("lname"),
            rs.getString("email"),
            rs.getInt("title_id"),
            rs.getInt("dept_id"),
            rs.getInt("boss_id"));
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return null;
  }

  @Override
  public Integer selectEmployeeIdByEmail(String email) {
    PreparedStatement ps;
    ResultSet rs;
    String sql = "SELECT employee_id FROM Employee WHERE email = ?";
    try (Connection conn = ConnectionUtil.getConnection()) {
      ps = conn.prepareStatement(sql);
      ps.setString(1, email);
      rs = ps.executeQuery();
      if (rs.next()) {
        return rs.getInt(1);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  @Override
  public Integer selectDepartmentIdByEmail(String email) {
    PreparedStatement ps;
    ResultSet rs;
    String sql = "SELECT dept_id FROM Employee WHERE email = ?";
    try (Connection conn = ConnectionUtil.getConnection()) {
      ps = conn.prepareStatement(sql);
      ps.setString(1, email);
      rs = ps.executeQuery();
      if (rs.next()) {
        return rs.getInt(1);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  @Override
  public Integer selectTitleIdByEmail(String email) {
    PreparedStatement ps;
    ResultSet rs;
    String sql = "SELECT title_id FROM Employee WHERE email = ?";
    try (Connection conn = ConnectionUtil.getConnection()) {
      ps = conn.prepareStatement(sql);
      ps.setString(1, email);
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
