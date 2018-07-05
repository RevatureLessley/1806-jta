package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class RetrieveCookies
 */
public class RetrieveCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveCookies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cookie[] cookies = request.getCookies();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<table border=2px><tr><th>COOKIE NAME</th><th>COOKIE VALUE</th>"
				+ "<th>DOMAIN</th><th>PATH</th><th>MAX AGE</th><th>COMMENT</th></tr>");
		
		for(Cookie cookie : cookies){
			out.print("<tr><td>" + cookie.getName() + "</td><td>"
					+ cookie.getValue() + "</td><td>"
					+ cookie.getDomain() + "</td><td>"
					+ cookie.getPath() + "</td><td>"
					+ cookie.getMaxAge() + "</td><td>"
					+ cookie.getComment() + "</td></tr>");
		}
		out.println("</table>");
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
