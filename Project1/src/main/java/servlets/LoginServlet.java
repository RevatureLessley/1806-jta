package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.person;
import services.PersonService;
import util.HtmlTemplates;

/**
 * Servlet implementation class loginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		int uId = PersonService.userLogin(username, password);
		if(uId != -1){
			PersonService ps = new PersonService();
			person p = ps.buildPersonByID(uId);
			getServletContext().setAttribute("personID", uId);
			
			System.out.println(getServletContext().getAttribute("personID"));
			int sId = p.getSecLvl();

			switch (sId) {
			case 1: 
				response.sendRedirect("/employee.html");
//				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/employee.html");
//				dispatcher.forward(request, response);
					break;
			case 2: break;
			case 3: break;
			case 4: break;
			case 5: break;
			}
		}else{
			out.println("<h3 style='color:red'>Login Failed, go back and try again</h3>");
			HtmlTemplates.goBackButton(out);
		}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
