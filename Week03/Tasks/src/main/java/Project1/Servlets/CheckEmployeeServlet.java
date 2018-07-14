package Project1.Servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import Project1.Service.EmployeeService;

/**
 * Servlet implementation class CheckEmployeeServlet
 */
public class CheckEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) throws ServletException,
															  IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		response.setContentType("text");
		PrintWriter out = response.getWriter();
		out.println(EmployeeService.employeeLogin(username, password));
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
