package com.revature.trms.service;

import com.revature.trms.bean.Employee;
import com.revature.trms.dao.EmployeeDao;
import com.revature.trms.dao.EmployeeDaoJDBC;
import com.revature.trms.dao.LoginDao;
import com.revature.trms.dao.LoginDaoJDBC;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginService {

  public static Integer validateLogin(String email, String password) {
    if (email == null || password == null) return 0;

    String hash = hashPassword(password);
    EmployeeDao ed = new EmployeeDaoJDBC();
    Employee employee;
    if ((employee = ed.selectEmployeeByEmail(email)) == null) {
      return 0;
    }
    LoginDao ld = new LoginDaoJDBC();
    password = ld.selectPasswordById(employee.getId());

    return (hash != null && hash.equals(password) ? employee.getId() : 0);
  }

  private static String hashPassword(String password) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      byte[] digest = messageDigest.digest(password.getBytes());
      StringBuilder hex = new StringBuilder();
      for (byte b : digest) {
        String s = Integer.toHexString(b & 0xFF);
        hex.append((s.length() == 1 ? '0' + s : s));
      }
      return hex.toString();
    } catch (NoSuchAlgorithmException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
