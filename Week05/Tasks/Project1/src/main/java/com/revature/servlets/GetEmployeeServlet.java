package com.revature.servlets;

import com.revature.bll.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that handles the retrieval of a single Employee
 */
public class GetEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text");
        resp.getWriter().println(EmployeeService.getEmployeeByIdJson((Integer) req.getSession(false).getAttribute("employeeId")));
    }
}
