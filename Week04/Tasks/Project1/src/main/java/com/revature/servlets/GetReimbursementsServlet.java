package com.revature.servlets;

import com.revature.bll.ReimbursementService;
import com.revature.utils.LogWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetReimbursementsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text");
        PrintWriter out = resp.getWriter();

        try{
            out.println(ReimbursementService.retrieveRequestsAsJSON((Integer) req.getSession(false).getAttribute("employeeId")));
        }catch(ClassCastException e)
        {
            LogWrapper.log(this.getClass(), e);
            out.println("");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
