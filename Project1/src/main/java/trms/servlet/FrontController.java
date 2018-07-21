package trms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		System.out.println(url);
		RequestDispatcher requestDispatcher;
		
		String[] tokens = url.split("/");
		String action = tokens[tokens.length-1]; 
		
		action = action.substring(0, action.length()-3).toLowerCase();

		switch(action) {
		case "login":
			requestDispatcher = request.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(request, response);
			break;
		case "logout":
			requestDispatcher = request.getRequestDispatcher("/Logout");
			requestDispatcher.forward(request, response);
			break;
		case "register":
			requestDispatcher = request.getRequestDispatcher("register.jsp");
			requestDispatcher.forward(request, response);
			break;
		case "applicationform":
			requestDispatcher = request.getRequestDispatcher("/ApplicationForm");
			requestDispatcher.forward(request, response);
			break;
		default:
			response.sendError(404);
		}
		
	}
}
