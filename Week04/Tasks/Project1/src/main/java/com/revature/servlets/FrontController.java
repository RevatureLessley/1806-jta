package com.revature.servlets;

import com.revature.utils.LogWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    private final String EXTENSION = "Servlet";
    public FrontController() {
        super();
        LogWrapper.log(this.getClass(), "FrontController created", LogWrapper.Severity.DEBUG);
    }

    @Override
    public void destroy() {
        super.destroy();
        LogWrapper.log(this.getClass(), "FrontController destroyed", LogWrapper.Severity.DEBUG);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        RequestDispatcher rd;
        String[] tokens = url.split("/");
        String action = tokens[tokens.length-1];    //gets the servlet name

        int extLength = EXTENSION.length();
        action = action.substring(0, action.length()-extLength).toLowerCase();

        switch(action){
            case "reimbursement":
                rd = req.getRequestDispatcher("ReimbursementServlet");
                rd.forward(req, resp);
            break;
            default:
                resp.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
