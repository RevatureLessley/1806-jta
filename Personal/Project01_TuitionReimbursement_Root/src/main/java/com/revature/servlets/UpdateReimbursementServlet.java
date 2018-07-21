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
public class UpdateReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("===="+this.getServletName()+"====");
		int empId = Driver.loggedIn.getId();
		String evDate = request.getParameter("eventDate");
		String evLoc = request.getParameter("locations");
		String evDesc = request.getParameter("eventDescription");
		double evCost = Double.parseDouble(request.getParameter("eventCost"));
		String evGForm = request.getParameter("formats");;
		String evType = request.getParameter("eventTypes");
		double evCover = 0.0; // poll database for info
		String evJust = request.getParameter("eventJustification");
		int id = Integer.parseInt(request.getParameter("id"));
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Employee loggedIn = Driver.loggedIn;
		
		if(UserService.updateReimbursement(new Reimbursement(id, empId, loggedIn.getSupVId(), evDate, evLoc, evDesc,
				evCost, evGForm, evType, evCover, evJust))){
			out.println("<h3 style='color:green'>Reimbursement " + evType + " UPDATED!</h3>");
		}else{
			response.sendError(418);
		}
		HtmlTemplates.goBackButton(out);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
