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
import com.revature.services.EmployeeTypeService;
import com.revature.services.EventTypeService;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		String username = request.getParameter("username1");
		String password = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		int dirsup = Integer.parseInt(request.getParameter("dirsup"));
		int dep = Integer.parseInt(request.getParameter("dep"));
		int pos = Integer.parseInt(request.getParameter("pos"));
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		DepartmentService.getDepartments();
		EmployeeTypeService.getEmployeeTypes();
		EventTypeService.getEventTypes();

		HttpSession session = null;
		if (!password.equals(password2)) {
			request.getRequestDispatcher("index.html").include(request, response);
			out.println("<script>document.getElementById('unmatchedpass')"
					+ ".innerHTML='Invalid username or password'; </script>");
		}else if(EmployeeService.registerEmployee(username, password, fname, lname, dirsup, dep, pos)){
			session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			session.setAttribute("employee", EmployeeService.getEmp(username, password));
			RequestDispatcher rd = request.getRequestDispatcher("user/emphome.html");
			rd.forward(request, response);
		}else{
			request.getRequestDispatcher("index.html").include(request, response);
			out.println("<script>document.getElementById('invalidentry')"
					+ ".innerHTML='Invalid username or password'; </script>");
		}
	}

}
