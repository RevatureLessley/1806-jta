package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.services.RFormService;

/**
 * Servlet implementation class ApproveRFormServlet
 */
public class ApproveRFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveRFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text");
		int currFormId = Integer.parseInt(request.getParameter("currFormId"));
		int currapplvl = Integer.parseInt(request.getParameter("currapplvl"));
		HttpSession session = request.getSession(false);
		if(((Employee)session.getAttribute("employee")).getDepId() == 1) {
			RFormService.approveRForm(currapplvl + 2, currFormId);
		}else {
			RFormService.approveRForm(currapplvl + 1, currFormId);
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
