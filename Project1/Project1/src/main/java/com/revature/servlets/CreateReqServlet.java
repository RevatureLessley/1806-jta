package com.revature.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.ReimbursementReq;
import com.revature.dao.ReimbursementReqDaoImpl;

/**
 * Servlet implementation class CreateReq
 */
public class CreateReqServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReqServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = null;
		session = request.getSession();
		
		int eventtype = Integer.parseInt(request.getParameter("eventtype"));
		Date eventDate = Date.valueOf(request.getParameter("eventdate"));
		float amount = (float)Integer.parseInt(request.getParameter("amount"));
		int empId = (Integer) session.getAttribute("username");
		ReimbursementReq req = new ReimbursementReq(empId, 100100, eventtype, eventDate, amount, false, false, false, false);
		ReimbursementReqDaoImpl rd = new ReimbursementReqDaoImpl();
		rd.insertNewReq(req);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
