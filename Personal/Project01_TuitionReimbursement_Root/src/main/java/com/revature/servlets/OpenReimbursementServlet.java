package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Reimbursement;
import com.revature.services.UserService;

/**
 * Servlet implementation class OpenReimbursement
 */
public class OpenReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("======"+this.getServletName()+"======");
		
		int id = Integer.parseInt(request.getParameter("reimId"));
		Reimbursement reim = UserService.getReimbursementById(id);
		
		response.sendRedirect("/Project01_TuitionReimbursement/user/editReimbursement.html?"
				+ "type="+reim.getType()+"&"
				+ "format="+reim.getGradingFormat()+"&"
				+ "date="+reim.getDate()+"&"
				+ "location="+reim.getLocation()+"&"
				+ "desc="+reim.getDescription()+"&"
				+ "just="+reim.getJustification()+"&"
				+ "cost="+reim.getCost()+"&"
				+ "id="+reim.getId());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
