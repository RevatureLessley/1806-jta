package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonDaoImpl;
import util.HtmlTemplates;

/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public loginServlet() {
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		PersonDaoImpl PDI = new PersonDaoImpl();
		int test = PDI.checkPassword(request.getParameter("name"), "pWord");
		
		if(test != -1) {
			
		}
		else {
			out.println("<h1 style ='color:red'> USERNAME/PASSWORD COMBO INCORRECT!</h1>"); //TODO: Redirect to login with error message.
		}
	}
}
