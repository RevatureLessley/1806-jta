package com.revature.servlets;

import com.revature.utils.LogWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that handles a user logging out
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();

        LogWrapper.log(this.getClass(), "Session Invalidated.");

        resp.sendRedirect("index.html");
    }
}
