package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Reimbursement;
import com.revature.main.Driver;
import com.revature.services.UserService;
import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementServlet() {
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
		String evType = request.getParameter("eventType");
		double evCover = 0.0; // poll database for info
		String evJust = request.getParameter("eventJustification");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(UserService.createReimbursement(new Reimbursement(empId, evDate, evLoc, evDesc,
				evCost, evGForm, evType, evCover, evJust))){
			out.println("<h3 style='color:green'>Reimbursement " + evType + " CREATED!</h3>");
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
