package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeeDaoImpl;
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
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String password = request.getParameter("password");	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = null;

		if(EmployeeService.employeeLogin(first, last, password)){
			session = request.getSession();
			session.setAttribute("first", first);
			session.setAttribute("last", last);
			System.out.println("LOGIN STARTED: " + (String)session.getAttribute("fisrt")+(String)session.getAttribute("last"));
			RequestDispatcher rd = request.getRequestDispatcher("emp.html");
			rd.forward(request, response);
		}else{
			out.println("<h3 style='color:red'>Invalid Names and Password!!</h3>");
			HtmlTemplates.goBackButton(out);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
