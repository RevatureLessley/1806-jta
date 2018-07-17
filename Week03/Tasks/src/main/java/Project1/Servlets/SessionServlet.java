package Project1.Servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Project1.Beans.*;

/**
 * Servlet implementation class SessionServlet
 */
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException,
															  IOException {
		response.setContentType("text");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Employee employee = (Employee)session.getAttribute("employee");
		if(null != employee) out.println(employee.getFirstname());
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
