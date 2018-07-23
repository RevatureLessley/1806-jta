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
		String url = request.getRequestURI();
		System.out.println("url: " + url);

		RequestDispatcher rd;
		
		String[] tokens = url.split("/");
		String action = (tokens[tokens.length-1]);
		
		action = action.substring(0, action.length()-3).toLowerCase();

		switch(action)
		{
			case "login":
				rd = request.getRequestDispatcher("LoginServlet");
				rd.forward(request, response);
				break;
			case "reimbursement":
				rd = request.getRequestDispatcher("../SubmitReimbursement");
				rd.forward(request, response);
				break;
			default:
				response.sendError(404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
