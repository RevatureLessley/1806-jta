package com.revature.servlets;

import com.revature.bll.ReimbursementService;
import com.revature.utils.LogWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetReimbursementTypesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventTypeIndex = req.getParameter("type");
        int index = -1;
        try{
            index = Integer.parseInt(eventTypeIndex);
        }catch(NumberFormatException e){LogWrapper.log(this.getClass(), e);}
        String json = ReimbursementService.retrieveEventType(++index);  //convert from 0-based(html) to 1-based(database)
        resp.setContentType("text");
        PrintWriter out = resp.getWriter();
        out.println(json);
    }
}
