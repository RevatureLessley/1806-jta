package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trms.beans.Employee;
import com.trms.services.UserService;
import com.trms.util.HtmlTemplates;

//import com.revature.services.UserService;
//import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class Login
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public LoginServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if (UserService.userLogin(username, password)) {

			System.out.println("Login Successful for " + username);
			Employee builtUser = UserService.buildUser(username);
			request.setAttribute("employee", builtUser);
			System.out.println("LoginServlet:USER BUILT: " + request.getAttribute("employee"));
			if (builtUser.getTitle().toLowerCase().equals("employee")) {
				RequestDispatcher rd = request.getRequestDispatcher("frontEnd/userhome/userhome.html");
				rd.forward(request, response);
			} else if (builtUser.getTitle().toLowerCase().equals("benco")) {
				RequestDispatcher rd = request.getRequestDispatcher("frontEnd/bencohome/bencohome.html");
				rd.forward(request, response);
			}

		} else {
			out.println("<h3 style='color:red'>LOGIN FAILED</h3>");
			HtmlTemplates.goBackButton(out);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
