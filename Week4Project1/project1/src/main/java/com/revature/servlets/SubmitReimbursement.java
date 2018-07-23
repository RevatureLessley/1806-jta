package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Employee;
import com.revature.services.EmployeeService;
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
		ReimbursementService rs = new ReimbursementService();
		EmployeeService es = new EmployeeService();
		Integer availableAmount = 0;
		Employee employee = null;
		
		String eventDate = request.getParameter("eventdate");
		String eventTime = request.getParameter("eventtime");
		String eventLocation = request.getParameter("eventlocation");
		String eventDesc = request.getParameter("eventdesc");
		Integer eventCost = Integer.parseInt(request.getParameter("eventcost"));
		String justification = request.getParameter("justification");
		Integer gradeCutoff = Integer.parseInt(request.getParameter("gradecutoff"));
		Integer empId = Integer.parseInt(request.getParameter("empid"));
		Integer eventId = Integer.parseInt(request.getParameter("eventid"));
		Integer gradingFormatId = Integer.parseInt(request.getParameter("gradingformatid"));
		
		rs.insertReimbursement(eventDate, eventTime, eventLocation, eventDesc, 
							   eventCost, justification, gradeCutoff, empId, 
							   eventId, gradingFormatId);
		
		availableAmount = es.getCurrencyByEmpId(empId);
		availableAmount -= eventCost;
		employee = es.getEmployeeUsingEmpId(empId);
		
		es.updateCurrencyById(employee, empId, availableAmount);
		
		response.sendRedirect("./index.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
