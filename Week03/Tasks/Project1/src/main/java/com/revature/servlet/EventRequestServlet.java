package com.revature.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.revature.service.DocumentService;
import com.revature.service.EventRequestService;
import com.revature.utils.StringManip;

/**
 * Servlet implementation class EventRequestServlet
 */
@MultipartConfig
public class EventRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 * 
	 */
	public EventRequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("at event request servlet");
		response.setContentType("text/html");

		List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName()))
				.collect(Collectors.toList());
		List<Part> fileParts2 = request.getParts().stream().filter(part -> "file2".equals(part.getName()))
				.collect(Collectors.toList());

		System.out.println(fileParts);

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		System.out.println("userId: " + userId);

		String eventName = request.getParameter("eventName");
		Integer eventType = Integer.parseInt(request.getParameter("eventType"));
		Integer gradeFormat = Integer.parseInt(request.getParameter("gradeFormat"));
		LocalDateTime date = StringManip.getLocalDateTimeForm(request.getParameter("date"));
		System.out.println("date: " + date);
		Double cost = Double.parseDouble(request.getParameter("cost"));
		String location = request.getParameter("location");
		String description = request.getParameter("description");
		String justification = request.getParameter("justification");

		Integer event = EventRequestService.submitEvent(userId, eventName, eventType, gradeFormat, date, cost, location,
				description, justification);

		// upload files
		for (Part filePart : fileParts) {

			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			InputStream fileContent = filePart.getInputStream();

			if (fileName.trim().length() < 1)
				continue;

			DocumentService.submitDocument(event, fileName, fileContent, 0);
		}
		// upload files2
		for (Part filePart : fileParts2) {

			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			InputStream fileContent = filePart.getInputStream();

			if (fileName.trim().length() < 1)
				continue;

			DocumentService.submitDocument(event, fileName, fileContent, 1);
		}

		response.sendRedirect("./requestSuccess.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
