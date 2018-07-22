package Project1.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
												 IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		String datetime = request.getParameter("datetime");
		String location = request.getParameter("location");
		String cost = request.getParameter("cost");
		String description = request.getParameter("description");
		String justification = request.getParameter("justification");
		String grading = request.getParameter("grading");
		String cutoff = request.getParameter("cutoff");
		String timeMissed = request.getParameter("timeMissed");
		String eventAttachments = request.getParameter("eventAttachments");
		String directSupervisor = request.getParameter("directSupervisor");
		String departmentHead = request.getParameter("departmentHead");
		String benefitsCoordinator = request.getParameter("benefitsCoordinator");
		String approvalAttachments = request.getParameter("approvalAttachments");
		
		System.out.println(type);
		System.out.println(datetime);
		System.out.println(location);
		System.out.println(cost);
		System.out.println(description);
		System.out.println(justification);
		System.out.println(grading);
		System.out.println(cutoff);
		System.out.println(timeMissed);
		System.out.println(eventAttachments);
		System.out.println(directSupervisor);
		System.out.println(departmentHead);
		System.out.println(benefitsCoordinator);
		System.out.println(approvalAttachments);
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
