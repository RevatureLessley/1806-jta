package Project1.Filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet Filter implementation class SecurityFilter
 */
public class SecurityFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, 
						 FilterChain chain) throws IOException,
												   ServletException {
		RequestDispatcher rd = null;
		String[] url = ((HttpServletRequest) request).getRequestURI()
													 .split("/");
		String action = (url[url.length - 1]).toLowerCase();

		switch(action){
			case "login.do":
				rd = request.getRequestDispatcher("LoginServlet");
				rd.forward(request, response);
				break;
			case "register.do":
				rd = request.getRequestDispatcher("RegisterServlet");
				rd.forward(request, response);
				break;
			default:
				rd = request.getRequestDispatcher("index.html");
				rd.forward(request, response);
				break;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {}

}
