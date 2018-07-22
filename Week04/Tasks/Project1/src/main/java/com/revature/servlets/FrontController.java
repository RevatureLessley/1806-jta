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
    private String[] servlets = {
            "Authentication",
            "Employment",
            "GetEmployees",
            "GetReimbursements",
            "GetReimbursementTypes",
            "Logout",
            "Reimbursement",
            "Session",
            "Upload",
            "Notification",
            "ApprovalRequest",
            "DenialRequest"
    };

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

//        if (!url.equals("/GetEmployees.Servlet") && !url.equals("/Employment.Servlet") &&  req.getSession(false) == null){ //TODO: Hardcode
//            System.out.println("escape clause");
//            resp.sendRedirect("index.html");
//            //rd = req.getRequestDispatcher("index.html");
//            //rd.forward(req, resp);
//            return;
//        }

        int begin = url.lastIndexOf("/")+1;
        int end = url.indexOf(".");
        String action = "";
        if (!(begin < 0 || end < 0)) action = url.substring(begin, end);
        boolean contained = false;
        for (String s : servlets) if (s.equals(action)) contained = true;
        if (!action.equals("") && contained){
            rd = req.getRequestDispatcher(action + EXTENSION);
            rd.forward(req, resp);
        }
        else{
            LogWrapper.log(this.getClass(), "Unrecognized Servlet Name: " + action, LogWrapper.Severity.ERROR);
            resp.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
