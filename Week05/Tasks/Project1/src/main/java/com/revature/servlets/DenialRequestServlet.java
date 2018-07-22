package com.revature.servlets;

import com.revature.bll.NotificationService;
import com.revature.utils.LogWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that handles the updating of a Notification if a user denies a reimbursement request
 */
public class DenialRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean result = NotificationService.updateNotification(Integer.parseInt(req.getParameter("notificationId")), 2);
        LogWrapper.log(this.getClass(), "Updated of Notification: Denial returned: " + result, LogWrapper.Severity.DEBUG);
    }
}
