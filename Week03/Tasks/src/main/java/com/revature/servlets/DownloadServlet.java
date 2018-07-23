package com.revature.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withRegion("us-east-2")
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .build();
		String bucketname = "revature-tuition-reimbusement";
		response.setContentType("text");
		String home = System.getProperty("user.home");
		String directory = home+"/Downloads/";
		File dest = new File(directory + request.getParameter("rformidFUpload"));
		s3client.getObject(new GetObjectRequest(bucketname,request.getParameter("rformidFUpload")),dest);
		RequestDispatcher rd = request.getRequestDispatcher("user/emphome.html");
		rd.forward(request, response);
	}

}
