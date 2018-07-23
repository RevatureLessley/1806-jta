package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.request;
import services.RequestService;


public class UpdateRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public UpdateRequest() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("x"));
		
//		int id = Integer.parseInt(request.getAttribute("id").toString());
//		double fullAmount = Double.parseDouble(request.getAttribute("x[fullAmount]").toString());
//		double cooperateAmount = Double.parseDouble(request.getAttribute("x[cooperateAmount]").toString());
//		int status = Integer.parseInt(request.getAttribute("x[status]").toString());
//		Date approvalDate = null;
//		Date creationDate = null;
//		Date eventDate = null;
//		String gradingFormat = request.getAttribute("x[gradingFormat]").toString();
//		String eventDescription = request.getAttribute("x[eventDescription]").toString();
//		String eventJustification = request.getAttribute("x[eventJustification]").toString();
//		String eventLocation = request.getAttribute("x[eventLocation]").toString();
//		double typeValue = Double.parseDouble(request.getAttribute("x[typeValue]").toString());
//		String typeName = request.getAttribute("x[typeName]").toString();
//		int typeId = Integer.parseInt(request.getAttribute("x[typeId]").toString());
		
//		request r = new request(id, fullAmount, cooperateAmount, status, approvalDate, creationDate, eventDate, gradingFormat,
//				eventDescription, eventJustification, eventLocation, typeValue, typeName, typeId );
//		
//		RequestService rs = new RequestService();
//		
//		System.out.println(r.getId());
//		
		//rs.updateRequest(r);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}