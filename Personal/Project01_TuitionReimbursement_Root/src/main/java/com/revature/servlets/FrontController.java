package com.revature.servlets;

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

	
    /*
     * Front Controller (A design pattern!)
     * -The front controller is built to designate a single servlet
     * for handling all requests and dispatching to proper servlets for handling.
     * The goal is to ensure that one servlet acts as the gatekeepr for th rest of 
     * application.
     * (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    
    @Override
    public void init() throws ServletException{
    	super.init();
    	System.out.println("FRONT CONTROLLER STARTED");
    }
    
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
		case "reimburse":
			rd = request.getRequestDispatcher("ReimbursementServlet");
			rd.forward(request, response);
			break;
		case "getreimbursements":
			rd = request.getRequestDispatcher("GetReimbursementsServlet");
			rd.forward(request, response);
			break;
		case "killsession":
			rd = request.getRequestDispatcher("KillSessionServlet");
			rd.forward(request, response);
			break;
		case "openreimbursement":
			rd = request.getRequestDispatcher("OpenReimbursementServlet");
			rd.forward(request, response);
			break;
		case "updatereimbursement":
			rd = request.getRequestDispatcher("UpdateReimbursementServlet");
			rd.forward(request, response);
			break;
		case "displayreimbursement":
			rd = request.getRequestDispatcher("DisplayReimbursementServlet");
			rd.forward(request, response);
			break;
		case "approvereimbursement":
			rd = request.getRequestDispatcher("ApproveReimbursementServlet");
			rd.forward(request, response);
			break;
		case "gethigherups":
			rd = request.getRequestDispatcher("GetHigherUpsServlet");
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
