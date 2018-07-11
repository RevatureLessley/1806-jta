package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.HtmlTemplates;

/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public loginServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		
		String values = "";
		String pass= "password"; //TODO: Replace with password retrieved via username
		if(confirmPass(request.getParameter("password1"), pass)) {
			
		}
		else {
			out.println("<h1 style ='color:red'> USERNAME/PASSWORD COMBO INCORRECT!</h1>"); //TODO: Redirect to login with error message.
		}
	}
	
	public boolean confirmPass(String pass1, String pass2) {
		return pass1.equals(pass2);
	}
}
