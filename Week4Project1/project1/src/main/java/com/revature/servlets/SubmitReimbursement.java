package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.ReimbursementService;


public class SubmitReimbursement extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public SubmitReimbursement() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ReimbursementService rs = new ReimbursementService();
		
		String eventDate = request.getParameter("eventdate");
		String eventTime = request.getParameter("eventtime");
		String eventLocation = request.getParameter("eventlocation");
		String eventDesc = request.getParameter("eventDesc");
		Integer eventCost = Integer.parseInt(request.getParameter("eventcost"));
		String justification = request.getParameter("justification");
		Integer gradeCutoff = Integer.parseInt(request.getParameter("gradecutoff"));
		Integer empId = Integer.parseInt(request.getParameter("empid"));
		Integer eventId = Integer.parseInt(request.getParameter("eventid"));
		Integer gradingFormatId = Integer.parseInt(request.getParameter("gradingformatid"));
		
		rs.insertReimbursement(eventDate, eventTime, eventLocation, eventDesc, 
							   eventCost, justification, gradeCutoff, empId, 
							   eventId, gradingFormatId);
		
		response.sendRedirect("./employee/index.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
