package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.EmployeeService;
import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class employee
 */
public class employeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public employeeServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] rfId = request.getParameterValues("rfId");
		String[] empTypeId = request.getParameterValues("empTypeId");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String password = request.getParameter("password");
		String empPhone = request.getParameter("empPhone");
		String empEmail = request.getParameter("empEmail");
		
		System.out.println(fname + lname);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		EmployeeService.createEmployee(rfId, empTypeId, fname, lname, password, empPhone, empEmail);
			out.println("<h3 style='color:green'>USER: " + fname+ " " + lname + " CREATED!</h3>");
		
		HtmlTemplates.goBackButton(out);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
