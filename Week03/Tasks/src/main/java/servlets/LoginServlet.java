package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.services.EmployeeService;
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = null;
		if(EmployeeService.employeeLogin(username, password)){
			session = request.getSession();
			session.setAttribute("username", username);
			System.out.println("LOGIN STARTED: " + (String)session.getAttribute("username"));
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.forward(request, response);
			out.println("<h3 style='color:green'>Success?</h3>");
			HtmlTemplates.goBackButton(out);
		}else{
			out.println("<h3 style='color:red'>GET OOOOOUTTA HERE?</h3>");
			HtmlTemplates.goBackButton(out);
		}
	}

}
