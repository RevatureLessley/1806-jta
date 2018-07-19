package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FrontController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public FrontController() 
    {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String url = request.getRequestURI(); //Stores the url in a string (minues the http jargon)
		System.out.println("url: " + url);			  //localhost:8085/something.do
		
		//response.setContentType("text");
		RequestDispatcher rd;
		
		System.out.println("servletName: " + this.getServletName());
		
		String[] tokens = url.split("/");
		String action = (tokens[tokens.length-1]); //something.do
		
		action = action.substring(0, action.length()-3).toLowerCase();//something
		System.out.println("action: " + action);
		
		System.out.println("response before switch: " + response);
		switch(action){
		case "login":
			System.out.println("inside login action switch");
			rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
			break;
		case "reimbursement":
			System.out.println("inside reimbursement action switch");
			//response.sendRedirect("SubmitReimbursement");
			rd = request.getRequestDispatcher("../SubmitReimbursement");
			rd.forward(request, response);
			break;
		default:
			System.out.println("inside login default switch");
			response.sendError(404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
