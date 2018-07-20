package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.bean.Employee;
import com.revature.service.EmployeeService;
import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = null;
		
		if(EmployeeService.userLogin(email, password)) {
			session = request.getSession();
			session.setAttribute("email", email);
			Employee e = EmployeeService.getEmployeeByEmail(email);
			session.setAttribute("id", e.getId());
			
			if(e.getRole() == 1) {
				response.sendRedirect("employee.html");
			}
			else if(e.getRole() == 2) {
				response.sendRedirect("supervisor.html");
			}
			else if (e.getRole() == 3) {
				response.sendRedirect("depthead.html");
			}
			else if(e.getRole() == 4) {
				response.sendRedirect("benco.html");
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("test.html");
				rd.include(request, response);
				
				out.println("<h3>Login was correct but didn't send to the right page</h3>");
				HtmlTemplates.goBackButton(out);
				
			}
			
		}
		else {
			out.println("<h3 style='color:red'>Incorrect login</h3>");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
			
		}
	}

}
