package com.revature.trms.servlet;

import com.revature.trms.service.BencoApprovalService;
import com.revature.trms.service.DepartmentApprovalService;
import com.revature.trms.service.DepartmentService;
import com.revature.trms.service.EmployeeService;
import com.revature.trms.service.RequestService;
import com.revature.trms.service.SupervisorApprovalService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApprovalServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession();
    if (session != null) {
      String email = (String)session.getAttribute("email");
      response.setContentType("text/json");
      PrintWriter out = response.getWriter();

      Integer requestId = Integer.parseInt(request.getParameter("id"));
      Integer approved = Integer.parseInt(request.getParameter("approval"));

      Integer id = EmployeeService.getEmployeeIdByEmail(email);
      Integer role = EmployeeService.getTileIdByEmail(email);

      //Integer dept = EmployeeService.getEmployeeById(RequestService.getRequestById(requestId).getEmployee()).getDepartment();
      //Integer bossId = EmployeeService.getEmployeeById(RequestService.getRequestById(requestId).getEmployee()).getSupervisor();
      if (approved == 0) {
        if (role > 1) {
          SupervisorApprovalService.setApprovalStatus(id, requestId, approved);
          DepartmentApprovalService.setApprovalStatus(id, requestId, approved);
          BencoApprovalService.setApprovalStatus(id, requestId, approved);
        }
      } else {
        switch (role) {
          case 1:
            break;
          case 2:
            SupervisorApprovalService.setApprovalStatus(id, requestId, approved);
            break;
          case 3:
            SupervisorApprovalService.setApprovalStatus(id, requestId, approved);
            DepartmentApprovalService.setApprovalStatus(id, requestId, approved);
            break;
          case 4:
            SupervisorApprovalService.setApprovalStatus(id, requestId, approved);
            DepartmentApprovalService.setApprovalStatus(id, requestId, approved);
            BencoApprovalService.setApprovalStatus(id, requestId, approved);
            break;
        }
      }
    } else {
      System.out.println("No session");
    }
  }

}
