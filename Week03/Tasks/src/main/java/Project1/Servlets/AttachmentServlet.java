package Project1.Servlets;

import java.io.*;
import java.math.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import Project1.Beans.*;
import Project1.Service.*;

/**
 * Servlet implementation class AttachmentServlet
 */
public class AttachmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException,
															  IOException {
//		HttpSession session = request.getSession();
		response.setContentType("application/pdf");
		ServletOutputStream out = response.getOutputStream();
		HashMap<BigInteger, Attachment> attachments = 
				AttachmentService.getAttachments("Event", "eve_att_event", 
												 new BigInteger("1"));
		InputStream in = attachments.get(new BigInteger("1")).getFile();
		byte[] buffer = new byte[970411];
         
        if ((in.read(buffer)) != -1) {
            out.write(buffer);
        }
//		Employee employee = (Employee)session.getAttribute("employee");
//		if(null != employee) {
//			ObjectMapper om = new ObjectMapper();
//			out.println(om.writeValueAsString(employee));
//			System.out.println(employee.toString());
//		}
        in.close();
        out.close(); 
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
