package com.trms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.trms.dao.ReimbursementRequestDaoImpl;
import com.trms.dao.UserHistoryDaoImpl;
import com.trms.dao.UserInfoDaoImpl;

/**
 * Servlet implementation class LoadUserInfoServlet
 */
public class RecordLoadingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordLoadingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("loading servlet hit");
		response.setContentType("text");
		PrintWriter out = response.getWriter();
		System.out.println(request.getParameter("type").toString());
		switch(request.getParameter("type").toString()) {
		case "user":
			System.out.println("user loading attempted");
			out.println(getJSON((new UserInfoDaoImpl()).selectAll()));
			break;
		case "request":
			System.out.println("reimbursement request loading attempted");
			out.println(getJSON((new ReimbursementRequestDaoImpl()).selectAll()));
			break;
		case "history":
			System.out.println("event history loading attempted");
			out.println(getJSON((new UserHistoryDaoImpl()).selectAll()));
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private <T> String getJSON(List<T> t){
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(t);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
}
