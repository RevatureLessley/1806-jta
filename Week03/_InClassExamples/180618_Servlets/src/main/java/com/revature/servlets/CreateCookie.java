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
 * Servlet implementation class CreateCookie
 */
public class CreateCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println(request.getParameter("cookieName").equals(null));
		if(request.getParameter("cookieName").equals(null) || request.getParameter("cookieValue")==null){
		
			response.sendError(400); //BAD REQUEST!
			return;
		}
		Cookie cookie = new Cookie(request.getParameter("cookieName"), 
				request.getParameter("cookieValue"));
		
		Cookie[] cookies = request.getCookies();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//Check if cookie already exists
		if(cookies != null){
			for(Cookie c: cookies){
				if(c.getName().equals(cookie.getName())){
					out.println("<h3 style='color:red'>COOKIE ALREADY EXISTS!</h3>");
					HtmlTemplates.goBackButton(out);
					return;
				}
			}
		}
		
		//otherwise we create a cookie with a lifespan of 3 years
		cookie.setMaxAge(20); //20 seconds
		cookie.setComment("Just a cookie... ya know... existin...");
		response.addCookie(cookie);
		out.println("<h3 style='color:green'>COOKIE CREATED!</h3>");
		HtmlTemplates.goBackButton(out);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
