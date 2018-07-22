package com.revature.servlets;

import com.revature.bll.NotificationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DenialRequestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NotificationService.updateNotification(Integer.parseInt(req.getParameter("notificationId")), 2);
        //TODO: do something with the return value of the above.

    }
}
