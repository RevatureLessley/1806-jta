package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.services.RFormService;

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
		System.out.println(date);
		Date d = java.sql.Date.valueOf(date);
		System.out.println(d);
		String place = request.getParameter("place");
		String info = request.getParameter("info");
		int propreim = Integer.parseInt(request.getParameter("propreim"));
		String justification = request.getParameter("justification");
		int timemissed = Integer.parseInt(request.getParameter("timemissed"));
		int gradeformat =  Integer.parseInt(request.getParameter("gradeformat"));
		int cutoff = Integer.parseInt(request.getParameter("cutoff"));
		int eventtype =  Integer.parseInt(request.getParameter("eventtype"));
		int eventcost =  Integer.parseInt(request.getParameter("eventcost"));
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = null;
		session = request.getSession();
		if(RFormService.registerRForm(((Employee)session.getAttribute("employee")).getEmpid(), d,
									place, info, propreim, justification, timemissed, gradeformat, cutoff,
									eventtype, eventcost)){
			session = request.getSession();
			RequestDispatcher rd = request.getRequestDispatcher("user/emphome.html");
			rd.forward(request, response);
		}else{
			response.sendError(418);
		}
	}

}
