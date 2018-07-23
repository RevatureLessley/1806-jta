package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.services.EmployeeService;
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
		int currempid = Integer.parseInt(request.getParameter("currempid"));
		int currfinalperc = Integer.parseInt(request.getParameter("currfinalperc"));
		int curreventcost = Integer.parseInt(request.getParameter("curreventcost"));
		HttpSession session = request.getSession(false);
		Employee employee = (Employee)session.getAttribute("employee");
		if(currapplvl >= 4) {
			RFormService.approveRForm(6, currFormId);
			Employee emp = EmployeeService.getEmpById(currempid);
			System.out.println(emp.getPending());
			System.out.println(currfinalperc);
			System.out.println(curreventcost);
			emp.setPending(emp.getPending() - 0.01*currfinalperc*curreventcost);
			emp.setAwarded(emp.getAwarded() + 0.01*currfinalperc*curreventcost);
			EmployeeService.updatePendingReim(emp.getPending(),currempid);
			EmployeeService.updateAwardedReim(emp.getAwarded(), currempid);
			
		}else if(employee.getDepId() == 1) {
			RFormService.approveRForm(4, currFormId);
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
