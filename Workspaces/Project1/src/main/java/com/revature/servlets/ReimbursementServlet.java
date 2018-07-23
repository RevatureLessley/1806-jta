package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.bean.Employee;
import com.revature.bean.Reimbursement;
import com.revature.service.EmployeeService;
import com.revature.service.ReimbursementService;
import com.revature.util.DateConversions;
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int empId = Integer.parseInt(request.getParameter("empid"));
		Date tdate = DateConversions.stringToDate(request.getParameter("date"));
		Date sdate = DateConversions.stringToDate(request.getParameter("startdate"));
		String loc = request.getParameter("location");
		String desc = request.getParameter("description");
		int cost = Integer.parseInt(request.getParameter("cost"));
		int gradeForm = Integer.parseInt(request.getParameter("grade"));		
		int train = Integer.parseInt(request.getParameter("train"));
		
		int grade;
		if(request.getParameter("gradepass") != null) {
			grade = Integer.parseInt(request.getParameter("gradepass"));
		}
		else {
			grade = 0;
		}
		
		
		String just = request.getParameter("justification");
					
		Reimbursement r = new Reimbursement(empId, 
											tdate, 
											sdate, 
											loc, 
											desc, 
											cost, 
											gradeForm,
											train, 
											grade, 
											just);
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		int id = (int)session.getAttribute("id");
		Employee e = EmployeeService.getEmployeeById(id);
		
		if(ReimbursementService.insertReimbursement(r)){
			if(e.getRole() == 1) {
				response.sendRedirect("employee.html");
			}
			else if(e.getRole() == 2) {
				response.sendRedirect("supervisor.html");
			}
			else if(e.getRole() == 3) {
				response.sendRedirect("depthead.html");
			}
			else if(e.getRole() == 4) {
				response.sendRedirect("benco.html");
			}
			
		}else{
			request.getRequestDispatcher("badReimbursement.html").include(request, response);
			HtmlTemplates.goBackButton(out);
		}                             	
	}

}
