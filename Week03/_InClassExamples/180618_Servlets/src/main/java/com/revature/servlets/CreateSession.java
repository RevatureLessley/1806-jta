package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class CreateSession
 */
public class CreateSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//Provides the created session for whatever user accessed the site last.
		//HOWEVER, if it is the first time this computer accessed the site, it will
		//create a new session for this computer.
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(session.isNew()){
			session.setAttribute("username", request.getParameter("username"));
			
			out.println("<h3 style='color:green'>SESSION CREATED FOR " 
						+ request.getParameter("username").toUpperCase()
						+ "!!!</h3>");
		}else{
			out.println("<h3 style='color:red'>BAD COLOR! SESSION ALREADY EXISTS FOR " + 
					((String)session.getAttribute("username")).toUpperCase() + "!!!</h3>");
		}
		HtmlTemplates.goBackButton(out);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
