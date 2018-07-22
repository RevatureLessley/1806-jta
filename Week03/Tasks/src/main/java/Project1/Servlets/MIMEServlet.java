package Project1.Servlets;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.codehaus.jackson.map.*;
import Project1.Service.*;

/**
 * Servlet implementation class FileExtensionServlet
 */
public class MIMEServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException,
															  IOException {
		response.setContentType("text");
		PrintWriter out = response.getWriter();
		HashMap<Integer, String[]> mimetypes = AttachmentService.getMIMETypes();
		ObjectMapper om = new ObjectMapper();
		out.println(om.writeValueAsString(mimetypes));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) 
								  throws ServletException, IOException {
		doGet(request, response);
	}

}
