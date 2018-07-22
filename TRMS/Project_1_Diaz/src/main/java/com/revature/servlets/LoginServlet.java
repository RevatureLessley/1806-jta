package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.services.EmployeeService;
import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Test: ").append(request.getContextPath());
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("Employee_UserName1");
		String password = request.getParameter("Employee_Password1");	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = null;

		if(EmployeeService.employeeLogin( userName, password)){
			session = request.getSession();
			session.setAttribute("Employee_UserName1", userName);
			System.out.println("LOGIN STARTED: " + (String)session.getAttribute("Employee_UserName1"));
			RequestDispatcher rd = request.getRequestDispatcher("emp.html");
			rd.forward(request, response);
		}else{
			out.println("<h3 style='color:red'>Invalid Names and Password!!</h3>");
			HtmlTemplates.goBackButton(out);
		}
			

	}

}
