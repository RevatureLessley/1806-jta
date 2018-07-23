package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.main.Driver;
import com.revature.services.UserService;

/**
 * Servlet implementation class ApproveReimbursement
 */
public class ApproveReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("===="+this.getServletName()+"====");
		
		int reimId = Integer.parseInt(request.getParameter("reimId"));
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		switch(Driver.loggedIn.getRole().toLowerCase()) {
		case "supervisor":
			if (UserService.updateReimbursementSupervisorApprovalById(reimId)) {
				out.println("<h3 style='color:green'>Reimbursement: " + reimId + " Approval Updated!</h3>");
			} else {
				out.println("<h3 style='color:red'>Reimbursement: " + reimId + " Approval Failed!</h3>");
			}
			break;
		case "head":
			if (UserService.updateReimbursementHeadApprovalById(reimId)) {
				out.println("<h3 style='color:green'>Reimbursement: " + reimId + " Approval Updated!</h3>");
			} else {
				out.println("<h3 style='color:red'>Reimbursement: " + reimId + " Approval Failed!</h3>");
			}
			break;
		case "benco":
			if (UserService.updateReimbursementBencoApprovalById(reimId)) {
				out.println("<h3 style='color:green'>Reimbursement: " + reimId + " Approval Updated!</h3>");
			} else {
				out.println("<h3 style='color:red'>Reimbursement: " + reimId + " Approval Failed!</h3>");
			}
			break;
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
