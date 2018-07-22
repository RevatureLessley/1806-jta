package com.revature.servlets;

import com.revature.bll.NotificationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet that handles retrieving all Notifications of an Employee
 */
public class NotificationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = NotificationService.getAllNotificationsJson((Integer)req.getSession().getAttribute("employeeId"));

        resp.setContentType("text");
        PrintWriter out = resp.getWriter();
        out.println(json);
    }
}
