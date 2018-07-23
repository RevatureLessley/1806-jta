package project01Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import project01Services.InfoService;

/**
 * Servlet implementation class fileNamesServlet
 */
public class fileNamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(fileNamesServlet.class);

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fileNamesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Servlet to get names of the files with request ");
		response.setContentType("text");
		PrintWriter out = response.getWriter();
		InfoService ns = new InfoService();
		System.out.println(request.getParameter("id"));
		out.println(ns.getAllFileNames(request.getParameter("id")));	
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
