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

public class PendingRequestsServlet extends HttpServlet {

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
      Integer dept = EmployeeService.getDepartmentIdByEmail(email);
      Integer role = EmployeeService.getTileIdByEmail(email);
      switch (role) {
        case 1:
          break;
        case 2:
          out.println(RequestService.getSupervisorPendingRequests(id));
          break;
        case 3:
          out.println(RequestService.getDepartmentPendingRequests(dept));
          break;
        case 4:
          out.println(RequestService.getBencoPendingRequests());
          break;
      }
    } else {
      System.out.println("No session");
    }

  }
}
