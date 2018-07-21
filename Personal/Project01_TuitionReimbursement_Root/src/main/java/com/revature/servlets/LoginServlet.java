package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.main.Driver;
import com.revature.services.UserService;
import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("======"+this.getServletName()+"======");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = null;
		
		Employee emp = UserService.userLogin(username, password);
		Driver.loggedIn = emp;
		if(emp != null){
			session = request.getSession();
			session.setAttribute("username", emp.getUsername());
			session.setAttribute("password", emp.getPassword());
			session.setAttribute("id", emp.getId());
			System.out.println("LOGIN STARTED: " + (String)session.getAttribute("username"));
			RequestDispatcher rd;
			switch(emp.getRole()) {
			case "supervisor":
				System.out.println("supervisor logged in...");
				rd = request.getRequestDispatcher("supervisor/index.html");
				rd.forward(request, response);
				break;
			case "head":
				System.out.println("head logged in...");
				rd = request.getRequestDispatcher("head/index.html");
				rd.forward(request, response);
				break;
			case "benco":
				System.out.println("benco logged in...");
				rd = request.getRequestDispatcher("benco/index.html");
				rd.forward(request, response);
				break;
			default:
				System.out.println("default logged in...");
				rd = request.getRequestDispatcher("user/index.html");
				rd.forward(request, response);
				break;
			}
		}else{
			out.println("<h3 style='color:red'>password username combination does not exist</h3>");
			HtmlTemplates.goBackButton(out);
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
