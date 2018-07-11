package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EventRequestServlet
 */
public class EventRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * 
     */
    public EventRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String s;
		
		System.out.println("at event request servlet");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String values = "";
		
		
		
		//EventRequestServlet 
		Map<String, String[]> inputs = request.getParameterMap();
		out.println("<h3>GET PARAMETERS</h3>");
		out.println("<table border=2px><tr><th>PARAMETER</th><th>VALUE</th></tr>");

		for (String key : inputs.keySet()) {
			out.println("<tr><td>" + key + "</td><td>");
			values = "";

			for (String val : inputs.get(key)) {
				values += (val + " ");
			}
			out.println(values + "</td></tr>");
		}

		out.println("</table>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
