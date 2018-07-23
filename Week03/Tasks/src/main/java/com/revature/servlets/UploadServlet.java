package com.revature.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.revature.beans.Employee;
import com.revature.services.RFormService;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withRegion("us-east-2")
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .build();
		String bucketname = "revature-tuition-reimbusement";
		response.setContentType("text");
		String rformid = "";
		int rfid = 0;
		try {
			List<FileItem> files = sf.parseRequest(request);
			for(FileItem item: files) {
				if (item.isFormField()) {
					String fieldname = item.getFieldName();
			        String fieldvalue = item.getString();
			        if(fieldname.equals("rformidFUpload")) {
			        	rformid = fieldvalue;
			        	rfid = Integer.parseInt(fieldvalue);
			        }

				}
				if(rformid != null && item.getName() != "null" && item.getName() != null) {
					RFormService.setFileKeyRForm(rfid, rformid+item.getName());
					InputStream is = item.getInputStream();
					s3client.putObject(new PutObjectRequest(bucketname, rformid+item.getName(),is,new ObjectMetadata())
											.withCannedAcl(CannedAccessControlList.PublicRead));
					is.close();
				}
				//For downloading file from as3
				
//				String home = System.getProperty("user.home");
//				String directory = home+"/Downloads/";
//				File dest = new File(directory + item.getName());
//				s3client.getObject(new GetObjectRequest(bucketname,item.getName()),dest);
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("user/emphome.html");
		rd.forward(request, response);
	}

}
