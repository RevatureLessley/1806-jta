package project01Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project01Services.UsernameAndPasswordService;

/**
 * Servlet implementation class UsernameAndPasswordService
 */
public class UsernameAndPasswordServvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UsernameAndPasswordServvlet() {
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernameAndPasswordService servicer = new UsernameAndPasswordService();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(servicer.createUsernameAndPassword(username, password)){
			out.println("<h3 style='color:green'>USER: " + username + " CREATED!</h3><br><a href=\"index.html\">Return to Login</a>");
		}else{
			out.println("<h3 style='color:red'>USER: " + username + " ALREADY CREATED!</h3><br><a href=\\\"index.html\\\">Return to Login</a>");
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
