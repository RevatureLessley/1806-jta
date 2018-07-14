package Project1.Servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Project1.Service.*;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) throws ServletException,
															  IOException {
		HttpSession session = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher rd = null;

		if(EmployeeService.employeeLogin(username, password)){
			session = request.getSession();
			session.setAttribute("username", username);
			rd = request.getRequestDispatcher("user/index.html");
			rd.forward(request, response);
		}
		
		else {
			rd = request.getRequestDispatcher("index.html");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) 
								  throws ServletException, IOException {
		doGet(request, response);
	}
}
