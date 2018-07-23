package trms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

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
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String uuid = String.valueOf(UUID.randomUUID());
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phoneNumber = request.getParameter("phoneNumber");
		
		User newUser = new User();
		newUser.setUuid(uuid);
		newUser.setUsername(username);
		newUser.setLoginPassword(password);
		newUser.setEmail(email);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setPhoneNumber(phoneNumber);
		UserDAO userDAO = new UserDAOImpl();
		userDAO.registerUser(newUser);
		
		User creationSuccess = userDAO.getUserByUsername(newUser.getUsername());
		
		if (creationSuccess != null) {
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			response.addCookie(new Cookie("username", creationSuccess.getUsername()));
			response.addCookie(new Cookie("name", creationSuccess.getFirstName()));
			response.addCookie(new Cookie("last", creationSuccess.getLastName()));
			response.addCookie(new Cookie("email", creationSuccess.getEmail()));
			rs.forward(request, response);
		} else {
			out.println("An error occurred while creating the user account.");
			RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
			rs.include(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
