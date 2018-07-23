package com.revature.trms.servlet;

import com.revature.trms.service.LoginService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    Integer id = LoginService.validateLogin(email, password);
    HttpSession session;
    response.setContentType("text/json");
    PrintWriter out = response.getWriter();

    if (id > 0) {
      session = request.getSession(true);
      session.setAttribute("email", email);
      //response.sendRedirect("/user");
      out.println("{ \"status\": \"success\" }");
    } else {
      out.println("{ \"status\": \"failure\" }");
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }
}
