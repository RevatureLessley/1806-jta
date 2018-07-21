package trms.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

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
		List<FileItem> fileItems = null;
		
		String title = "Reading Data";
		StringBuilder stringBuilder = new StringBuilder();
		
	    stringBuilder.append("<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n");

	    stringBuilder.append("<html>\n" +
		            "<head><title>" + title + "</title></head>\n" +
		            "<body bgcolor = \"#f0f0f0\">\n" +
		               "<h1 align = \"center\">" + title + "</h1>\n" +
		               "<ul>\n");
/*	    while(paramNames.hasMoreElements()) {
	    	String name = (String) paramNames.nextElement();
	    	stringBuilder.append("<li><b>" + name + " value: </b>");
	    	stringBuilder.append(request.getParameter(name) + "</li>");
	    }*/
		
		try {
			fileItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : fileItems) {
				if (!item.isFormField()) {
					String fieldName = item.getFieldName();
					String fileName = FilenameUtils.getName(item.getName());
					InputStream fileContent = item.getInputStream();
			    	stringBuilder.append("<li><b>" + fieldName + " value: </b>");
			    	stringBuilder.append(fileName + "</li>");
				} else {
					String fieldName = item.getFieldName();
					String fieldValue = item.getString();
					stringBuilder.append("<li><b>" + fieldName + "</b>");
					stringBuilder.append(fieldValue.toString() + "</li>");
				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse the multiparse request.", e);
		} catch (IOException io) {
			io.printStackTrace();
		}
		
	    stringBuilder.append("</ul>\n" +    "</body>" + "</html>");
	    out.println(stringBuilder);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
