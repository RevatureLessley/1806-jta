package trms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplicationForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ApplicationForm() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// provide employee UUID
		// generate form UUID
		// for each attachment, 
		Map params = request.getParameterMap();
		Enumeration paramNames = request.getParameterNames();
		
		String title = "Reading Data";
		StringBuilder stringBuilder = new StringBuilder();
		
	    stringBuilder.append("<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n");

	    stringBuilder.append("<html>\n" +
		            "<head><title>" + title + "</title></head>\n" +
		            "<body bgcolor = \"#f0f0f0\">\n" +
		               "<h1 align = \"center\">" + title + "</h1>\n" +
		               "<ul>\n");
	    while(paramNames.hasMoreElements()) {
	    	String name = (String) paramNames.nextElement();
	    	stringBuilder.append("<li><b>" + name + " value: </b>");
	    	stringBuilder.append(request.getParameter(name) + "</li>");
	    }
	    stringBuilder.append("</ul>\n" +    "</body>" + "</html>");
	    out.println(stringBuilder);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
