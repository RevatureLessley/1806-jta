package Project1.Servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Project1.Beans.*;
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
		String username = request.getParameter("lusername");
		String password = request.getParameter("lpassword");
		Employee employee = EmployeeService.employeeLogin(username, password);
		HttpSession session = null;
		RequestDispatcher rd = null;
		PrintWriter out = response.getWriter();

		if(null != employee){
			session = request.getSession();
			session.setAttribute("employee", employee);
			rd = request.getRequestDispatcher("user/index.html");
			rd.forward(request, response);
		}
		
		else {
			rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
			out.println("<script>" + 
							"document.getElementById(\"beforeLogin\")" + 
									".innerHTML=" + 
										"\"Username or Password incorrect.\"" + 
						"</script>");
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
