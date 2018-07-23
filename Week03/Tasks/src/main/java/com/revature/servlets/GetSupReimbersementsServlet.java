package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.services.EmployeeService;

/**
 * Servlet implementation class GetSupReimbersementsServlet
 */
public class GetSupReimbersementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSupReimbersementsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		Employee employee = (Employee)session.getAttribute("employee");
		if(employee.getEmpid() == 1) {
			out.println(EmployeeService.getBenHeadRFormsJSON(employee.getEmpid()));
		}else if(employee.getDepId() == 1){
			out.println(EmployeeService.getBenRFormsJSON(employee.getEmpid()));
		}else if(employee.getEmpType() >= 2){
			out.println(EmployeeService.getHeadRFormsJSON(employee.getEmpid(),
					employee.getDepId()));
		}else if(employee.getEmpType() >= 1) {
			out.println(EmployeeService.getSupRFormsJSON(employee.getEmpid()));
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
