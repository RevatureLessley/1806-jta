package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.services.UserService;
import com.trms.util.HtmlTemplates;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String address = request.getParameter("address");
		Integer postalCode = Integer.valueOf(request.getParameter("postalCode"));
		Date birthDate = Date.valueOf(request.getParameter("birthDate"));
		Date hireDate = Date.valueOf(request.getParameter("hireDate"));
		String sp = request.getParameter("supervisor");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(password1.equals(password2)) {
			System.out.println(sp);
			if(!sp.equals(null) && !sp.equals("")) {
				Integer supervisorID = Integer.valueOf(request.getParameter("supervisor"));
				if(UserService.newUser(username, password1, fName, lName, address, postalCode, birthDate, hireDate, supervisorID)) {
					out.println("<h3>Account, " + username + " with supervisor, successfully created.</h3>");
				}else { 
					out.println("<h3>Account creation failed.</h3>");
				}
			}else {
				if(UserService.newUser(username, password1, fName, lName, address, postalCode, birthDate, hireDate)) {
					out.println("<h3>Account, " + username + " supervisor, successfully create.</h3>");
				}else {
					out.println("<h3>Account creation failed.</h3>");
				}
			}
		}else { 
			out.println("<h3>Passwords do not match.</h3>");
		}
		HtmlTemplates.goBackButton(out);
	}

}
