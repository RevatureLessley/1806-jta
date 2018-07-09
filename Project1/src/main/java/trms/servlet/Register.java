package trms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
		Boolean creationSuccess = userDAO.registerUser(newUser);
		
		if (creationSuccess) {
			RequestDispatcher rs = request.getRequestDispatcher("index.html");
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			rs.forward(request, response);
		} else {
			out.println("An error occurred while creating the user account.");
			RequestDispatcher rs = request.getRequestDispatcher("login.html");
			rs.include(request, response);
		}
	}

}