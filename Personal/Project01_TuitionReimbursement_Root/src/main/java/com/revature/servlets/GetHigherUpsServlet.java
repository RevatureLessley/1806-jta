package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Employee;
import com.revature.services.UserService;

/**
 * Servlet implementation class GetHigherUpsServlet
 */
public class GetHigherUpsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetHigherUpsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("======"+this.getServletName()+"======");
		PrintWriter out = response.getWriter();
		
		String role = request.getParameter("role");
		List<Employee> emps = new ArrayList<>();
		switch(role) {
		case "employee":
			emps = UserService.getAllEmployeesByRole("supervisor");
			break;
		case "supervisor":
			emps = UserService.getAllEmployeesByRole("head");
			break;
		case "head":
			emps = UserService.getAllEmployeesByRole("benco");
			break;
		}
		
		for(Employee emp : emps) {
			out.println("<option value='"+emp.getId()+"'>"+emp.getFirstName() +" "+emp.getLastName()+"</option>");
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
