package com.revature.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SecurityFilter implements Filter {



	public void destroy() 
	{
		System.out.println("FILTER DESTROYED"); // just a debug to make sure that it is destroyed properly
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		HttpSession session = request.getSession(false);
		System.out.println("session");
		if(session == null)
		{
			response.sendRedirect("../index.html"); // if the session is null, send them straight back to the index page
		}
		else
		{
			//Forward to the proper servlet.
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException 
	{
		System.out.println("FILTER INSTANTIATED"); // Just a check to see if it did indeed instantiate
	}

}