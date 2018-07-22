package com.revature.servlet;

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
	 * Default constructor.
	 */
	public FrontController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd;

		String url = request.getRequestURI();

		//System.out.println(url);

		String[] tokens = url.split("/");
		String action = tokens[tokens.length - 1];

		action = action.substring(0, action.length() - 3).toLowerCase();
		System.out.println("front controller: " + action);
		System.out.println(request.getParameterMap().keySet());
		
		switch (action) {
		case "login":
			rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
			break;
		case "logout":
			rd = request.getRequestDispatcher("../LogoutServlet");
			rd.forward(request, response);
			break;
		case "eventrequest":
			rd = request.getRequestDispatcher("EventRequestServlet");
			rd.forward(request, response);
			break;
		case "eventcomment":
			rd = request.getRequestDispatcher("EventCommentServlet");
			rd.forward(request, response);
			break;
		case "eventapprove":
			rd = request.getRequestDispatcher("EventApproveServlet");
			rd.forward(request, response);
			break;
		case "eventchangeaward":
			rd = request.getRequestDispatcher("EventChangeAwardServlet");
			rd.forward(request, response);
			break;
		case "eventgrade":
			rd = request.getRequestDispatcher("EventGradeServlet");
			rd.forward(request, response);
			break;
		case "eventconfirm":
			rd = request.getRequestDispatcher("EventConfirmServlet");
			rd.forward(request, response);
			break;
		default:
			response.sendError(404);
		}

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
