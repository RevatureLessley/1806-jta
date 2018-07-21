package Project1.Servlets;

import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Project1.Beans.*;
import Project1.Service.*;

/**
 * Servlet implementation class AttachmentServlet
 */
public class EventAttachmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException,
															  IOException {
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("employee");
		
		if(null != employee) {
			String f = request.getParameter("paramf");
			String r = request.getParameter("paramr");
			BigInteger indexf = new BigInteger(f);
			BigInteger indexr = new BigInteger(r);
			HashMap<BigInteger, Attachment> eventAttachments = 
					employee.getReimbursements().get(indexr).getEvent()
							.getAttachments();
			Attachment attachment = eventAttachments.get(indexf);
			response.setContentType(attachment.getMime());
			InputStream in = null;
			ServletOutputStream out = null;

			try {
				in = attachment.getFile().getBinaryStream();
				out = response.getOutputStream();
				byte[] buffer = new byte[attachment.getFilesize().intValue()];
		         
		        if ((in.read(buffer)) != -1) {
		            out.write(buffer);
		        }
			} 
			
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			finally {
				in.close();
		        out.close(); 
			}
		}
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
