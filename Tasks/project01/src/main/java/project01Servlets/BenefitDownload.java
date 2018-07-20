package project01Servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BenefitDownload
 */
public class BenefitDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BenefitDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AHHHHHHHH-sd=-fa=sd-f");
		System.out.println(request.getParameter("file"));
 		File file = new File("C:\\temp\\"+request.getParameter("file"));
		FileInputStream fileIn = new FileInputStream(file);
		ServletOutputStream out = response.getOutputStream();

		byte[] outputByte = new byte[4096];
		//copy binary conect to output stream
		while(fileIn.read(outputByte, 0, 4096) != -1)
		{
			out.write(outputByte, 0, 4096);
		}
		fileIn.close();
		out.flush();
		out.close();  

}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}




/*
 * 		File file = new File("C:\\temp\\kim_27_107");
		FileInputStream fileIn = new FileInputStream(file);
		ServletOutputStream out = response.getOutputStream();

		byte[] outputByte = new byte[4096];
		//copy binary conect to output stream
		while(fileIn.read(outputByte, 0, 4096) != -1)
		{
			out.write(outputByte, 0, 4096);
		}
		fileIn.close();
		out.flush();
		out.close();  
		FILE DOWNLOAD!!!!!!!!!!!!!!!!!!!!!
		
		
		
				File file = new File("C:\\temp\\kim_27_107.jpg");
		FileInputStream fileIn = new FileInputStream(file);
		ServletOutputStream out = response.getOutputStream();

		byte[] outputByte = new byte[4096];
		//copy binary conect to output stream
		while(fileIn.read(outputByte, 0, 4096) != -1)
		{
			out.write(outputByte, 0, 4096);
		}
		fileIn.close();
		out.flush();
		out.close();  
 * */
