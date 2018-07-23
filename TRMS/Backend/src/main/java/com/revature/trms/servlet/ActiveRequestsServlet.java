package com.revature.trms.servlet;

import com.revature.trms.service.EmployeeService;
import com.revature.trms.service.RequestService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ActiveRequestsServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (session != null) {
      String email = (String)session.getAttribute("email");
      response.setContentType("text/json");
      PrintWriter out = response.getWriter();
      Integer id = EmployeeService.getEmployeeIdByEmail(email);
      out.println(RequestService.getActiveRequestsById(id));
    }
  }
}
