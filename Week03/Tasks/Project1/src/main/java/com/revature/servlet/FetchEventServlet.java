package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.service.EventService;

/**
 * Servlet implementation class FetchEventServlet
 */
public class FetchEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchEventServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		String filter = request.getParameter("filter");

		boolean isManager = Boolean.parseBoolean(request.getParameter("manager"));

		if (isManager)
			out.write(EventService.selectSubordinateEvents(userId, filter));
		else
			out.write(EventService.selectUserEvents(userId, filter));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
