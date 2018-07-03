package Servlets.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * A servlet is a Java application or program that serves to take incoming http
 * requests and handle appropriately, then send back proper http responses.
 */
public class HelloWorld extends HttpServlet{
	public void init() {
		/*
		 * Servlets have 3 key methosds in their lifecycle.
		 * When a servlet is first interacted with, the web container will check
		 * if the servlet has been instantiated yet. If it hasn't then it will call the
		 * init() method for the servlet. However if the servlet is already instantiated,
		 * it will use the current instance. This effectively makkes the servlet a Singleton
		 */
		System.out.println(this.getServletName() + ": INIT:");
	}
		/*
		 * Once the serlet is instantiated, the web container calls the servlet's 
		 * service() while passing it the user request and response objects. It is at this
		 * point that the servlet will use the information however it needs to,
		 * as well as modify the response to be sent back
		 */
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println(this.getServletName()+ ": SERVICE");
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		out.println("<h3>HELLO WORLD! </h3>");
		out.println(""
				+"<hr><input type='button' value='GO BACK' onclick ='goback()'>"
				+"<script>function goBack(){window.history.back(): } </script>");
	}
	
	/*
	 * After a certain amount of inactivity the web container will call the servlets
	 * destroy() method before ultimately deleting and taking back th ememory
	 */
	public void destroy() {
		System.out.println(this.getServletName() + ": DESTROY");
	}
}
