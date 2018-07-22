package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.main.Driver;
import com.revature.services.UserService;

/**
 * Servlet implementation class GetReimbursementsServlet
 */
public class GetReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReimbursementsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("======"+this.getServletName()+"======");
		Employee loggedIn = Driver.loggedIn;
		int id = loggedIn.getId();
		
		List<Reimbursement> reims = UserService.getAllReimbursementsByEmpId(id);
		Driver.loggedIn.setReims(reims);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (!reims.isEmpty()) {	
			out.println("<h3>Recent Reimbursements</h3><table class='table table-hover'>"+
					"<thead><tr><th>Reimbursement ID</th><th>Employee Name</th><th>Type</th>"
					+ "<th>Description</th><th>Justification</th></tr></thead><tbody>");
			for(Reimbursement reim : reims) {
				out.println("<tr onclick='openReimbursement("+reim.getId()+")'><td>"+reim.getId()
				+"</td><td>"+reim.getEmpId()+"</td><td>"+reim.getType()+"</td>"+ "<td>"+reim.getDescription()
				+"</td><td>"+reim.getJustification()+"</td></tr>");
			}
			out.print("</body></table>");
		}
		
		if (loggedIn.getRole() != "employee") {
			reims = UserService.getAllReimbursementsByApproverId(id);
				if (!reims.isEmpty()) {
					
					out.println("<h3>All Unapproved Reimbursements</h3><table class='table table-hover'>"+
					"<thead><tr><td>Reimbursement ID</td><td>Employee ID</td><td>Type</td>"
							+ "<td>Description</td><td>Justification</td></tr></thead><tbody>");
					for(Reimbursement reim : reims) {
						out.println("<tr onclick='goToDisplayReimPage("+reim.getId()+")'><td>"+reim.getId() +"</td><td>"+reim.getEmpId()+"</td><td>"+reim.getType()+"</td>"
					+ "<td>"+reim.getDescription()+"</td><td>"+reim.getJustification()+"</td></tr>");
					}
					out.print("</body></table>");
				}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
