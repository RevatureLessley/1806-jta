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
		
		System.out.println("accountname: " + accountname);
		System.out.println("password: " + password);
		
		EmployeeService es = new EmployeeService();
		
		if ( EmployeeService.employeeLogin(accountname, password) )
		{
			session = request.getSession();
			session.setAttribute("accountname", accountname);
			if ( es.checkEmpId(accountname) == 1 )
			{
				response.sendRedirect("./benefitscoordinator/index.html");
			}
			else if ( es.checkEmpId(accountname) == 2 )
			{
				response.sendRedirect("./supandhead/index.html");
			}
			else if ( es.checkEmpId(accountname) == 3 )
			{
				response.sendRedirect("./departmenthead/index.html");
			}
			else if ( es.checkEmpId(accountname) == 4 )
			{
				response.sendRedirect("./supervisor/index.html");
			}
			else if ( es.checkEmpId(accountname) == 5 )
			{
				response.sendRedirect("./employee/index.html");
			}
			
//			out.println("<h3 style='color:green'>LOGIN SUCCESS</h3>");
//			out.println("<a href=\"./employee/index.html\" class=\"btn btn-info\" role=\"button\">Employee login</a>");
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
