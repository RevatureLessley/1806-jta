package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.ReimbursementService;


public class BothDeclineServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public BothDeclineServlet() 
    {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		ReimbursementService rs = new ReimbursementService();
		
		rs.updateApprovalToLevel0();
		
		response.sendRedirect("./supandhead/approveOrDenyReimbursements.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
