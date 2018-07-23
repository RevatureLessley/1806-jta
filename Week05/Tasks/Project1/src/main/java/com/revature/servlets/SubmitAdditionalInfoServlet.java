package com.revature.servlets;

import com.revature.beans.NotificationBean;
import com.revature.bll.NotificationService;
import com.revature.utils.LogWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubmitAdditionalInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NotificationBean bean = NotificationService.getNotificationById(Integer.parseInt(req.getParameter("notificationId")));
        bean.setAtSupervisor(false);
        bean.setAtDeptHead(false);
        bean.setAtBenCo(false);

        boolean result = NotificationService.updateNotification(Integer.parseInt(req.getParameter("notificationId")), 0, req.getParameter("information"));
        LogWrapper.log(this.getClass(), "Update result was: " + result);
    }
}
