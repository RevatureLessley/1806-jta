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
		
		EmployeeService es = new EmployeeService();
		
		if ( EmployeeService.employeeLogin(accountname, password) )
		{
			session = request.getSession();
			session.setAttribute("accountname", accountname);
			if ( es.checkJobTypeId(accountname) == 5 )
			{
				response.sendRedirect("./benefitscoordinator/index.html");
			}
			else if ( es.checkJobTypeId(accountname) == 4 )
			{
				response.sendRedirect("./supandhead/index.html");
			}
			else if ( es.checkJobTypeId(accountname) == 3 )
			{
				response.sendRedirect("./departmenthead/index.html");
			}
			else if ( es.checkJobTypeId(accountname) == 2 )
			{
				response.sendRedirect("./supervisor/index.html");
			}
			else if ( es.checkJobTypeId(accountname) == 1 )
			{
				response.sendRedirect("./employee/index.html");
			}
			
		}
		else
		{
			out.println("<h3 style='color:red'>Invalid Login</h3>");
			HtmlTemplates.goBackButton(out);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
