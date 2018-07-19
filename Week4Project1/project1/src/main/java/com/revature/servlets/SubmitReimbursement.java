package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		PrintWriter out = response.getWriter();
		ReimbursementService rs = new ReimbursementService();
		EmployeeService es = new EmployeeService();
		HttpSession session = null;
		Integer availableAmount = 0;
		Employee employee = null;
		
		//System.out.println("+++++++++++INSIDE SubmitReimbursement.java doGet()++++++++++++++++");
		String eventDate = request.getParameter("eventdate");
		//System.out.println("eventDate: " + eventDate);
		String eventTime = request.getParameter("eventtime");
		//System.out.println("eventTime: " + eventTime);
		String eventLocation = request.getParameter("eventlocation");
		//System.out.println("eventLocation: " + eventLocation);
		String eventDesc = request.getParameter("eventdesc");
		//System.out.println("eventDesc: " + eventDesc);
		Integer eventCost = Integer.parseInt(request.getParameter("eventcost"));
		//System.out.println("eventCost: " + eventCost);
		String justification = request.getParameter("justification");
		//System.out.println("justification: " + justification);
		Integer gradeCutoff = Integer.parseInt(request.getParameter("gradecutoff"));
		//System.out.println("gradeCutoff: " + gradeCutoff);
		Integer empId = Integer.parseInt(request.getParameter("empid"));
		//System.out.println("empId: " + empId);
		Integer eventId = Integer.parseInt(request.getParameter("eventid"));
		//System.out.println("eventId: " + eventId);
		Integer gradingFormatId = Integer.parseInt(request.getParameter("gradingformatid"));
		//System.out.println("gradingFormatId: " + gradingFormatId);
		
		session = request.getSession();
		
		rs.insertReimbursement(eventDate, eventTime, eventLocation, eventDesc, 
							   eventCost, justification, gradeCutoff, empId, 
							   eventId, gradingFormatId);
		
		//System.out.println("availableAmount before getCurrencyByEmpId: " + availableAmount);
		availableAmount = es.getCurrencyByEmpId(empId);
		//System.out.println("availableAmount after getCurrencyByEmpId: " + availableAmount);
		availableAmount -= eventCost;
		//System.out.println("availableAmount after -= eventCost: " + availableAmount);
		employee = es.getEmployeeUsingEmpId(empId);
		
		System.out.println("employee.getCurrencyById before updateCurrencyById: " + es.getCurrencyByEmpId(empId));
		es.updateCurrencyById(employee, empId, availableAmount);
		System.out.println("employee.getCurrencyById after updateCurrencyById: " + es.getCurrencyByEmpId(empId));
		
		response.sendRedirect("./index.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
