package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class Headers
 */
public class Headers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Headers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/*
	 * When using HttpServlets in your application. For the most part, DO NOT EVER
	 * OVERRIDE THE SERVICE METHOD!
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}

	//doGet is called by service, if a GET request is sent.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> headers = request.getHeaderNames();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String header = "";
		out.println("<table border=2px><tr><th>HEADER NAME</th><th>HEADER VALUE</th></tr>");
		while(headers.hasMoreElements()){
			header = (String)headers.nextElement();
			out.println("<tr><td>" + header + "</td><td>" + request.getHeader(header) + "</td></tr>");
		}
		out.println("</table>");
		HtmlTemplates.goBackButton(out);
	}
	
	/*
	 * REQUEST HEADERS
	 * -Both requests and responses have key/value pairs that represent meta data about each
	 *  one. These are called the request/response headers.
	 *  For request, typical headers can include:
	 *  -accept: shows which file types are allowed to be returned/ received
	 *  -Authorization: header used to identify itself
	 *  -Connection: Header for determining how long a connection lasts.
	 *  -Cookie: Storing cookies previously received by browser.
	 *  -Host: Specifies a host and port
	 *  -User Agent: Identify browser that is being used.
	 *  	-can have special reactions to specific browsers.
	 * 	 
	 * RESPONSE HEADERS
	 * -Allow: specifices which HTTP mehtods are supported by the server.
	 * -Connection: Determining whether or not to persist connections to the server.
	 * -Expires: Dictates when page should no longer be cached.
	 * -Refresh: set a timer for when the page re-loads
	 * -Set-Cookie: Specifices a cookie with a page.
	 */

	//doPost is called by service, if a POST request is sent.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
