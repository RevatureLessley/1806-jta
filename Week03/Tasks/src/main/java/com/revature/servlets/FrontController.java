package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.services.DepartmentService;

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

	
    /*
     * Front Controller (A design pattern!)
     * -The front controller is built to designate a single servlet
     * for handling all requests and dispatching to proper servlets for handling.
     * The goal is to ensure that one servlet acts as the gatekeepr for th rest of 
     * application.
     * (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI(); //Stores the url in a string (minues the http jargon)
		System.out.println(url);			  //localhost:8085/something.do
		
		RequestDispatcher rd;
		
		System.out.println("====" + this.getServletName() + "====");
		
		String[] tokens = url.split("/");
		String action = (tokens[tokens.length-1]); //something.do
		
		action = action.substring(0, action.length()-3).toLowerCase();//something
		switch(action){
		case "login":
			rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
			break;
		case "register":
			rd = request.getRequestDispatcher("RegisterServlet");
			rd.forward(request, response);
			break;
		case "upload":
			rd = request.getRequestDispatcher("UploadServlet");
			rd.forward(request, response);
			break;
		case "selectemployee":
			rd = request.getRequestDispatcher("SelectEmployeeServlet");
			rd.forward(request, response);
			break;
		case "logout":
			rd = request.getRequestDispatcher("LogoutServlet");
			rd.forward(request, response);
			break;
		case "reimbursementpage":
			rd = request.getRequestDispatcher("ReimbursementPageServlet");
			rd.forward(request, response);
			break;
		case "homepage":
			rd = request.getRequestDispatcher("users/emphome.html");
			rd.forward(request, response);
			break;
		case "newform":
			rd = request.getRequestDispatcher("NewFormServlet");
			rd.forward(request, response);
			break;
		case "getreimbursements":
			rd = request.getRequestDispatcher("GetReimbursementsServlet");
			rd.forward(request, response);
			break;
		default:
			response.sendError(404);
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
