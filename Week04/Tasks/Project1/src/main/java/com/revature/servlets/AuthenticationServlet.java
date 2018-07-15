package com.revature.servlets;

import com.revature.beans.EmployeeBean;
import com.revature.bll.EmployeeService;
import com.revature.utils.HtmlTemplates;
import com.revature.utils.LogWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;


public class AuthenticationServlet extends HttpServlet {

    public AuthenticationServlet(){
        super();
        LogWrapper.log(this.getClass(), "AuthenticationServlet created.", LogWrapper.Severity.DEBUG);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        LogWrapper.log(this.getClass(), "AuthenticationServlet received GET request.", LogWrapper.Severity.DEBUG);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogWrapper.log(this.getClass(), "AuthenticationServlet received POST request.", LogWrapper.Severity.DEBUG);
        String userEmail = req.getParameter("username");
        String password = req.getParameter("password");

        boolean validUser = false;
        int id = 0;
        //check password
        EmployeeBean[] emps = EmployeeService.getEmployeesByEmail(userEmail);
        if (emps.length == 0){
            //by this point, we've already left /index.html and are in /Authentification.Servlet
            LogWrapper.log(this.getClass(), "Username \'"+userEmail+"\' does not exist.", LogWrapper.Severity.INFO);
        }
        else {
            for (EmployeeBean bean : emps){
                if (password.equals(bean.getPassword())){
                    validUser = true;
                    id = bean.getId();
                    break;
                }
            }
        }

        HttpSession session = null;
        if (validUser) {
            session = req.getSession();
            LogWrapper.log(this.getClass(), "Session created.");
            session.setAttribute("employeeId", id);
            resp.sendRedirect("user/home.html");
        }
        else {
            //if not a employee, or if their password's wrong:
            RequestDispatcher rd = req.getRequestDispatcher("index.html");
            rd.forward(req, resp);
        }
    }
}
