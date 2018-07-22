package trms.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import trms.beans.User;
import trms.dao.ApplicationFormDAO;
import trms.dao.ApplicationFormDAOImpl;
import trms.dao.UserDAO;
import trms.dao.UserDAOImpl;

/**
 * Servlet implementation class Pending
 */
public class Pending extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pending() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*UserDAO userDAO = new UserDAOImpl();
		ApplicationFormDAO applicationFormDAO = new ApplicationFormDAOImpl();
		List<User> users = userDAO.getAllUsers();
		List<trms.beans.ApplicationForm> applicationForms = applicationFormDAO.getAllForms();
		request.setAttribute("users", users);
		RequestDispatcher dispatcher = request.getRequestDispatcher("pending.jsp");
		dispatcher.forward(request, response);*/
		listContent(request, response);
	}
	
	private void listContent (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userDAO = new UserDAOImpl();
		List<User> users = userDAO.getAllUsers();/*
		String json = new Gson().toJson(users);
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");*/
		request.setAttribute("users", users);
		RequestDispatcher dispatcher = null;
		if (request.getAttribute("formData") != null) {
			System.out.println("Form data is not null.");
			dispatcher = request.getRequestDispatcher("review.jsp");
		} else {
			dispatcher = request.getRequestDispatcher("pending.jsp");
		}
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		trms.beans.ApplicationForm formData = null;
		UserDAO userDAO = new UserDAOImpl();
		ApplicationFormDAO applicationFormDAO = new ApplicationFormDAOImpl();
		List<User> users = userDAO.getAllUsers();
		List<trms.beans.ApplicationForm> applicationForms = applicationFormDAO.getAllForms();
		String userUUID = request.getParameter("user");
		String formUUID = request.getParameter("form");
		String selectedUserUUID = request.getParameter("selectedId");
		String selectedFormUUID = request.getParameter("selectedForm");
		
		if (selectedFormUUID != null) {
			formData = applicationFormDAO.getApplicationForm(selectedFormUUID);
		} 
		
		if (formUUID != null){
			formData = applicationFormDAO.getApplicationForm(formUUID);
		}
		
		if (selectedUserUUID != null) {
			applicationForms = applicationFormDAO.getUserApplicationForms(selectedUserUUID);
		} 
		
		if (userUUID != null) {
			applicationForms = applicationFormDAO.getUserApplicationForms(userUUID);
		}
		
		System.out.println(applicationForms.size() + " forms found.");
		if (formData != null) {
			System.out.println(formData.getFormUUID());
		}
		if (userUUID != null ) {
		request.setAttribute("selectedId", userUUID);
		}
		if (applicationForms != null) {
		request.setAttribute("forms", applicationForms);
		}
		if (formUUID != null) {
			request.setAttribute("selectedForm", formUUID);
		}
		if (formData != null) {
			System.out.println(formData.toString());
			request.setAttribute("formData", formData);
		}
		
		listContent(request, response);
	}

}
