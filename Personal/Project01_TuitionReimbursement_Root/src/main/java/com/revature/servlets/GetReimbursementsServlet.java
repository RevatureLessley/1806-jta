package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Reimbursement;
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
		HttpSession session = request.getSession();
		int id = (Integer)session.getAttribute("id");
		List<Reimbursement> reims = UserService.getAllReimbursementsById(id);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<table>");
		out.println("<tr><td>Reimbursement ID</td><td>Employee ID</td><td>Type</td>"
				+ "<td>Description</td><td>Justification</td></tr>");
		for(Reimbursement reim : reims) {
			out.println("<tr><td>"+reim.getId()+"</td><td>"+reim.getEmpId()+"</td><td>"+reim.getType()+"</td>"
					+ "<td>"+reim.getDescription()+"</td><td>"+reim.getJustification()+"</td></tr>");
		}
		out.print("</table>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
