package com.revature.servlets;

import com.revature.bll.ReimbursementService;
import com.revature.utils.LogWrapper;
import com.revature.utils.HtmlTemplates;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class ReimbursementServlet extends HttpServlet {

    public ReimbursementServlet(){
        super();
        LogWrapper.log(this.getClass(), "ReimbursementServlet created.", LogWrapper.Severity.DEBUG);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogWrapper.log(this.getClass(), "ReimbursementServlet received GET request.", LogWrapper.Severity.DEBUG);

        String location = req.getParameter("location");
        String description = req.getParameter("description");

        int idNum = 1;  //this should trigger SQLException constraint violation - primary key uniqueness
        double cost = 0;
        LocalDate date = null;
        try {
            idNum = Integer.parseInt(req.getParameter("idnum"));
            date = LocalDate.parse(req.getParameter("date"));
            cost = Integer.parseInt(req.getParameter("cost"));
        }catch(NumberFormatException | DateTimeParseException e){
            LogWrapper.log(this.getClass(), e);
            resp.sendError(400);
            //TODO: customize this error page
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        //System.out.println(firstName+"\n"+lastName+"\n"+email+"\n"+type+"\n"+phone+"\n"+idNum);
        if(ReimbursementService.insertRequest(idNum, date, location, description, cost, 1)){     //TODO: HARDCODE (Wrong) 1?
            out.println("<h2 style='color:green'>Request Submitted.</h2>");
        }
        else resp.sendError(400);

        HtmlTemplates.goBackButton(out);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        //TODO: change this to doGet()? Superclass will spit an errorcode: (400, 405?)
        LogWrapper.log(this.getClass(), "ReimbursementServlet received POST request.", LogWrapper.Severity.DEBUG);
    }
}
