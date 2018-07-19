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

/**
 * Servlet implementation class NewFormServlet
 */
public class NewFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewFormServlet() {
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
		String date = request.getParameter("date");
		String place = request.getParameter("place");
		String info = request.getParameter("info");
		String lname = request.getParameter("lname");
		int propreim = Integer.parseInt(request.getParameter("propreim"));
		String justification = request.getParameter("justification");
		int timemissed = Integer.parseInt(request.getParameter("timemissed"));
		String gradeformat = request.getParameter("gradeformat");
		int cutoff = Integer.parseInt(request.getParameter("cutoff"));
		String eventtype = request.getParameter("eventtype");
		String eventcost = request.getParameter("eventcost");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		HttpSession session = null;
		if(EmployeeService.registerEmployee(username, password, fname, lname, dirsup, dep, pos)){
			session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			RequestDispatcher rd = request.getRequestDispatcher("user/emphome.html");
			rd.forward(request, response);
		}else{
			response.sendError(418);
		}
	}

}
