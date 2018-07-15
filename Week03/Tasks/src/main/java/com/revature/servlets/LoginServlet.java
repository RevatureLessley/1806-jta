package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.services.DepartmentService;
import com.revature.services.EmployeeService;
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = null;
		if(EmployeeService.employeeLogin(username, password)){
			DepartmentService.getDepartments();
			session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			RequestDispatcher rd = request.getRequestDispatcher("user/emphome.html");
			rd.forward(request, response);

		}else{
			request.getRequestDispatcher("index.html").include(request, response);
			out.println("<script>document.getElementById('invalidpass')"
					+ ".innerHTML='Invalid username or password'; </script>");
		}
	}

}
