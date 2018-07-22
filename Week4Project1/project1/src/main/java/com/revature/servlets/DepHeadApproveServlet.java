package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.services.ReimbursementService;


public class DepHeadApproveServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public DepHeadApproveServlet() 
    {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String accountName = "";
		
		ReimbursementService rs = new ReimbursementService();
		
		rs.updateApprovalToLevel3();
		
		response.sendRedirect("./departmenthead/approveOrDenyReimbursements.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
