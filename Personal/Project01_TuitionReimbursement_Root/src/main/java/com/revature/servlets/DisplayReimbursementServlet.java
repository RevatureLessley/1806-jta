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
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		int reimId = Integer.parseInt(request.getParameter("reimId"));
		Reimbursement reim = UserService.getReimbursementById(reimId);
		Driver.loggedIn.setSelectedReimbursement(reim);
		
		out.println("<h4>Reimbursement Id: </h4><h6 id='reimId'>"+reim.getId()+"</h6><br>");
		out.println("<h4>Employee Id: </h4><h6>"+reim.getEmpId()+"</h6><br>");
		out.println("<h4>Location: </h4><h6>"+reim.getLocation()+"</h6><br>");
		out.println("<h4>Type: </h4><h6>"+reim.getType()+"</h6><br>");
		out.println("<h4>Date: </h4><h6>"+reim.getDate()+"</h6><br>");
		out.println("<h4>Description: </h4><h6>"+reim.getDescription()+"</h6><br>");
		out.println("<h4>Justification: </h4><h6>"+reim.getJustification()+"</h6><br>");
		out.println("<h4>Cost: </h4><h6>"+reim.getCost()+"</h6><br>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
