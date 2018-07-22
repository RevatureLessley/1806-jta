package com.revature.servlets;

import com.revature.bll.NotificationService;
import com.revature.utils.LogWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that handles the updating of a notification when a user approves a reimbursement request
 */
public class ApprovalRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean result = NotificationService.updateNotification(Integer.parseInt(req.getParameter("notificationId")), 1);
        LogWrapper.log(this.getClass(), "Updating of the Notification: Approval returned: " + result, LogWrapper.Severity.DEBUG);
    }
}
