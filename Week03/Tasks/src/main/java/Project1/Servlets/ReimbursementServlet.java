package Project1.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Project1.Beans.Employee;
import Project1.Beans.Reimbursement;
import Project1.Service.ReimbursementService;

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
		RequestDispatcher rd  = 
				request.getRequestDispatcher("user/index.html");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("employee");
		String datetime = request.getParameter("datetime");
		
		if(datetime == null) {
			rd.include(request, response);
			out.println("<script>" + 
							"document.getElementById(\"beforeDatetime\")" + 
									".innerHTML=" + 
										"\"Datetime cannot be empty.\"" + 
						"</script>");
		}
		
		else {
			SimpleDateFormat sdf = null;
			Timestamp eventTime = null;
			
			try {
			    sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				eventTime = new Timestamp(sdf.parse(datetime).getTime());
			} 
			
			catch (ParseException chrome) {
				
				try {
					sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
					eventTime = new Timestamp(sdf.parse(datetime).getTime());
				} 
				
				catch (ParseException pe) {
					pe.printStackTrace();
				}
			}
			
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			Calendar cal = Calendar.getInstance();
		    cal.setTimeInMillis(currentTime.getTime());
		    cal.add(Calendar.DAY_OF_MONTH, 7);
		    Timestamp futureTime = new Timestamp(cal.getTime().getTime());
	
		    if (futureTime.compareTo(eventTime) > 0) {
		    	rd.include(request, response);
				out.println("<script>" + 
								"document.getElementById(\"beforeDatetime\")" + 
									".innerHTML=" + 
										"\"You must submit this form at" + 
										"least 1 week before the event.\"" + 
							"</script>");
		    }
		    
		    else {
		    	String s = request.getParameter("cutoff");
		    	Double cutoff = (Double) null;
		    	
		    	if(s != null) cutoff = Double.valueOf(s);
		    	
	//	    	String grading = request.getParameter("grading");
				String username = employee.getUsername();
				String type = request.getParameter("type");
				String location = request.getParameter("location");
				BigDecimal cost = new BigDecimal(request.getParameter("cost"));
				String description = request.getParameter("description");
				String justification = request.getParameter("justification");
				String timeMissed = request.getParameter("timeMissed") + 
									" 0:0:0.0";
				
				Reimbursement reimbursement = 
						ReimbursementService.submitReimbursement(username, 
																 type, 
																 cost, 
																 eventTime, 
																 location, 
																 timeMissed, 
																 cutoff, 
																 description, 
																 justification
																 );
				
				employee.insertReimbursement(reimbursement.getId(), 
											 reimbursement);
				session.setAttribute("employee", employee);
				rd = request.getRequestDispatcher("user/index.html");
				rd.forward(request, response);
				System.out.println("Please");
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
