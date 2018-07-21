package trms.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trms.beans.User;
import trms.dao.UserDAO;
import trms.dao.UserDAOImpl;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = null;
		String signin = request.getParameter("signin");
		String password = request.getParameter("password");
		UserDAO userDAO = new UserDAOImpl();
		User user = null;
		
		if (signin.contains("@")) {
			user = userDAO.userLoginWithEmail(signin, password);
		} else {
			user = userDAO.userLoginWithUsername(signin, password);
		}
		
		if (user != null) {
			User isLoggedIn = userDAO.getUserByUsername(user.getUsername());
			String name = isLoggedIn.getFirstName();
			response.addCookie(new Cookie("username", isLoggedIn.getUsername()));
			response.addCookie(new Cookie("name", isLoggedIn.getFirstName()));
			response.addCookie(new Cookie("last", isLoggedIn.getLastName()));
			response.addCookie(new Cookie("email", isLoggedIn.getEmail()));
			
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			rs.forward(request, response);
		} else {
			out.println("Username or password is incorrect.");
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			rs.include(request, response);
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
