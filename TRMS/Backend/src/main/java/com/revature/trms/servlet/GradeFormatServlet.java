package com.revature.trms.servlet;

import com.revature.trms.bean.GradeFormat;
import com.revature.trms.service.EmployeeService;
import com.revature.trms.service.GradeFormatService;
import com.revature.trms.service.RequestService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GradeFormatServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/json");
    PrintWriter out = response.getWriter();
    out.println(GradeFormatService.getAllGradeFormats());
  }
}
