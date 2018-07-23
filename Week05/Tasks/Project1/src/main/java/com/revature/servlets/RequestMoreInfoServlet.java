package com.revature.servlets;

import com.revature.bll.NotificationService;
import com.revature.utils.LogWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestMoreInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean result = NotificationService.updateNotification(Integer.parseInt(req.getParameter("notificationId")), 3, req.getParameter("information"));
        LogWrapper.log(this.getClass(), "Updating of Notification returned: " + result, LogWrapper.Severity.DEBUG);
    }
}
