package com.revature.servlets;

import com.revature.beans.EmployeeBean;
import com.revature.bll.EmployeeService;
import com.revature.utils.HtmlTemplates;
import com.revature.utils.LogWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class AuthenticationServlet extends HttpServlet {

    public AuthenticationServlet(){
        super();
        LogWrapper.log(this.getClass(), "AuthenticationServlet created.", LogWrapper.Severity.DEBUG);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogWrapper.log(this.getClass(), "AuthenticationServlet received GET request.", LogWrapper.Severity.DEBUG);

        String userEmail = req.getParameter("username");
        String password = req.getParameter("password");

        //check password
        EmployeeBean[] emps = EmployeeService.getEmployeesByEmail(userEmail);
        //TODO: the above comment
        for(EmployeeBean e :emps) System.out.println(e.toString());


        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


        HtmlTemplates.goBackButton(out);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        //TODO: change this to doGet()? Superclass will spit an errorcode: (400, 405?)
        LogWrapper.log(this.getClass(), "AuthenticationServlet received POST request.", LogWrapper.Severity.DEBUG);
    }
}
