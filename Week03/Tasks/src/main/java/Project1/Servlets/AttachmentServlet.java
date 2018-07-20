package Project1.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import Project1.Beans.Employee;

/**
 * Servlet implementation class AttachmentServlet
 */
public class AttachmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttachmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
						 HttpServletResponse response) throws ServletException,
															  IOException {
		response.setContentType("text");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		Employee employee = (Employee)session.getAttribute("employee");
		if(null != employee) {
			ObjectMapper om = new ObjectMapper();
			out.println(om.writeValueAsString(employee));
//			System.out.println(employee.toString());
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
