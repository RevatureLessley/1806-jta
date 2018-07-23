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

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int username = Integer.parseInt( request.getParameter("userid"));
		String password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//ensures that we are starting fresh with a new session
		HttpSession session = null;
		
		// Checks to see if the login process was successful
		if(EmployeeService.employeeLogin(username, password) != null){
			session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("role", EmployeeService.employeeLogin(username, password).getEmpRole());
			System.out.println("LOGIN STARTED: " + session.getAttribute("role"));
			if(session.getAttribute("role").equals(" "))
			{
				RequestDispatcher rd = request.getRequestDispatcher("user/index.html");
				rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("user/superindex.html");
				rd.forward(request, response);
			}
			
		}else{
			out.println("<h3 style='color:red'>GET OOOOOUTTA HERE!</h3>");
			HtmlTemplates.goBackButton(out);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}