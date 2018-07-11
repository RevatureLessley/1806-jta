package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.services.EmployeeService;
import com.revature.util.HtmlTemplates;


public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String accountname = request.getParameter("accountname");
		String password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = null;
		
		
		if ( EmployeeService.employeeLogin(accountname, password) )
		{
			session = request.getSession();
			session.setAttribute("accountname", accountname);
			out.println("<h3 style='color:green'>LOGIN SUCCESS</h3>");
			out.println("<button href='user/index.html'>OKAY!</button>");
		}
		else
		{
			out.println("<h3 style='color:red'>GET OUT</h3>");
			HtmlTemplates.goBackButton(out);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
