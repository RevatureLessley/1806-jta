package com.revature.trms.servlet;

import com.revature.trms.bean.Employee;
import com.revature.trms.bean.Request;
import com.revature.trms.dao.RequestDao;
import com.revature.trms.dao.RequestDaoJDBC;
import com.revature.trms.service.EmployeeService;
import com.revature.trms.service.RequestService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import oracle.sql.TIMESTAMP;

public class NewRequestServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    //http://localhost:8081/NewRequestServlet?event_name=asdasd&event_location=asdas&event_date=2018-07-22&event_time=01%3A01&event_cost=12&event_type=1&grade_format=1&justification=asdasas
    String name = request.getParameter("event_name");
    String location = request.getParameter("event_location");
    System.out.println(request.getParameter("event_date") + " " + request.getParameter("event_time"));

    Timestamp time = Timestamp.valueOf(request.getParameter("event_date") + " " + request.getParameter("event_time")+ ":00");
    Double cost = Double.parseDouble(request.getParameter("event_cost"));
    Integer eventType = Integer.parseInt(request.getParameter("event_type"));
    Integer gradeFormat = Integer.parseInt(request.getParameter("grade_format"));
    String justification = request.getParameter("justification");

    HttpSession session = request.getSession(false);
    if (session != null) {
      Integer id = EmployeeService.getEmployeeIdByEmail((String)session.getAttribute("email"));
      RequestDao rd = new RequestDaoJDBC();
      rd.insertRequest(id, name, location, time, cost, eventType, gradeFormat, justification);
    }
    response.sendRedirect("/user");
  }
}
