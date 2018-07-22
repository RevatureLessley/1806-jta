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
		response.getWriter().append(" Test: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(EmployeeService.createEmployee(request)) {
			out.println("<h3 style='color:green'>USER:  CREATED!</h3>");
		}else{
			out.println("Employee already exist please try again ");
		}
		HtmlTemplates.goBackButton(out);
	}
}
