package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.services.ReimbursementService;


public class DeclinedReimbursements extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public DeclinedReimbursements() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text");
		PrintWriter out = response.getWriter();
		ReimbursementService rs = new ReimbursementService();
		HttpSession session = request.getSession();
		String accountName = "";
		
		accountName = (String) session.getAttribute("accountname");
		
		out.println(rs.getDeclinedReimbursementWithJSON(accountName));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
