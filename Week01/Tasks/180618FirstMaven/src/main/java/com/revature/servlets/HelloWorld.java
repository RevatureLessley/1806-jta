package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * A Servlet is a Java application or program that serves to take incoming http
 * requests and handle appropriately, then send back proper http responses.
 */
public class HelloWorld extends HttpServlet{
	/*
	 * Servlets have 3 key methods in their lifecycle.
	 * When a servlet is first interacted with, the web container will check
	 * if the servlet has been instantiated yet. If it hasn't, then it will call the
	 * init() method for that servlet. However, if the servlet is already instantiated,
	 * it will use the current instance. This effectively makes the servlet a Singleton.
	 */
	
	public void init(){
		System.out.println(this.getServletName() + ": INIT");
	}
	
	
	/*
	 * Once the servlet is instantiated, the web container calls the servlet's 
	 * service() while passing it the user request and response objects. It is at this
	 * point that the servlet will use the information however it needs to, as well as
	 * modify the response to be sent back.
	 */
	public void service(HttpServletRequest req, HttpServletResponse res)
					throws ServletException, IOException{
		System.out.println(this.getServletName() + ": SERVICE");
		
		//You must always tell the server what kind of content we are sending back to
		//the user. In this case, an html file
		res.setContentType("text/html");
		//The printwriter is actually used to write the resposne back dynamically.
		PrintWriter out = res.getWriter();
		
		//Using the actual writer
		out.println(
				"<h3>HELLO WORLD!</h3>"
				);
		
		out.println(""
				+ "<hr><input type='button' value='GO BACK' onclick='goBack()'>"
				+ "<script>function goBack(){ window.history.back(); } </script>");
	}
	
	/*
	 * After a certain amount of inactivity the web container will call the servlets
	 * destroy() method before ultimately deleting and taking back the memory.	 
	 */
	public void destroy(){
		System.out.println(this.getServletName() + ": DESTROY");
	}
}