package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.UserService;
import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();	
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String role = request.getParameter("role");
		int supVId = Integer.parseInt(request.getParameter("supVId"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		int phone = Integer.parseInt(request.getParameter("phone"));
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String location = request.getParameter("location");
		String username = request.getParameter("username");
		String password = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (!password.equals(password2)) {
			out.println("<h3 style='color:red'>PASSWORD DO NOT MATCH</h3>");
			return;
		}
		if(UserService.createUser(role, supVId, fname, lname, phone, email, address, location, username, 
				password)){
			out.println("<h3 style='color:green'>USER: " + username + " CREATED!</h3>");
		}else{
			response.sendError(418);
		}
		HtmlTemplates.goBackButton(out);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}