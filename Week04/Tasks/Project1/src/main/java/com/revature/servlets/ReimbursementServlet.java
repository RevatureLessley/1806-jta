package com.revature.servlets;

import com.revature.bll.ReimbursementService;
import com.revature.utils.LogWrapper;
import com.revature.utils.HtmlTemplates;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;


public class ReimbursementServlet extends HttpServlet {

    public ReimbursementServlet(){
        super();
        LogWrapper.log(this.getClass(), "ReimbursementServlet created.", LogWrapper.Severity.DEBUG);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        LogWrapper.log(this.getClass(), "ReimbursementServlet received GET request.", LogWrapper.Severity.DEBUG);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogWrapper.log(this.getClass(), "ReimbursementServlet received POST request.", LogWrapper.Severity.DEBUG);

        String location = req.getParameter("location");
        String description = req.getParameter("description");
        String url = req.getParameter("picUrl");
        url = url.replace(' ', '+');  //What an unusual bug, when transferring from JavaScript to here, that this should be necessary.

        int idNum = 1, type = -1;  //this should trigger SQLException constraint violation if left unaltered - primary key uniqueness
        double cost = 0;
        LocalDate date = null;
        try {
            idNum = (Integer)req.getSession().getAttribute("employeeId");
            date = LocalDate.parse(req.getParameter("date"));
            cost = Double.parseDouble(req.getParameter("cost"));
            type = Integer.parseInt(req.getParameter("type")) + 1;  //convert from 0-based to 1-based
        }catch(NumberFormatException | DateTimeParseException e){
            LogWrapper.log(this.getClass(), e);
            resp.sendError(400);
            //TODO: customize this error page
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        //TODO: Refactor this if statement, unduplicate code.
        if(ReimbursementService.insertRequest(idNum, date, location, description, cost, type, url)){
            out.println(
                    "<head>" +
                            "<link rel='stylesheet' type='text/css' href= '../resources/css/main.css' />" +
                            "</head>" +
                            "<body>" +
                            "<div class='col-sm-4'></div>" +
                            "<div class='col-sm-4'>" +
                            "<center>" +
                            "<h2 style='color:green'>Request Submitted.</h2>" +
                            "<form action='/user/home.html'>" +
                            "<input type='submit' value='Go Home'>" +
                            "</form>" +
                            "</center>" +
                            "</div>" +
                            "</body>"
            );

        }
        else{
            out.println(
                    "<head>" +
                            "<link rel='stylesheet' type='text/css' href= '../resources/css/main.css' />" +
                            "</head>" +
                            "<body>" +
                            "<div class='col-sm-4'></div>" +
                            "<div class='col-sm-4'>" +
                            "<center>" +
                            "<h2 style='color:red'>There was a problem submitting your request.</h2>" +
                            "<form action='/user/home.html'>" +
                            "<input type='submit' value='Go Home'>" +
                            "</form>" +
                            "</center>" +
                            "</div>" +
                            "</body>"
            );
        }

    }
}
