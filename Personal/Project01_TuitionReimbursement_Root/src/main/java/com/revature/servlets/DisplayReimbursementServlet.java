package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.main.Driver;
import com.revature.services.UserService;
import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class DisplayReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("===="+this.getServletName()+"====");
		
		System.out.println(request.getParameter("reimId"));
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int reimId = Integer.parseInt(request.getParameter("reimId"));
		Reimbursement reim = UserService.getReimbursementById(reimId);
		
		out.println("<h4>Reimbursement Id: "+reim.getId()+"</h4><br>");
		out.println("<h4>Employee Id: "+reim.getEmpId()+"</h4><br>");
		out.println("<h4>Location: "+reim.getLocation()+"</h4><br>");
		out.println("<h4>Type: "+reim.getType()+"</h4><br>");
		out.println("<h4>Date: "+reim.getDate()+"</h4><br>");
		out.println("<h4>Description: "+reim.getDescription()+"</h4><br>");
		out.println("<h4>Justification: "+reim.getJustification()+"</h4><br>");
		out.println("<h4>Cost: "+reim.getCost()+"</h4><br>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
