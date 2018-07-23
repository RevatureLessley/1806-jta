package com.revature.trms.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

  public FrontController() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String url = request.getRequestURI();
    RequestDispatcher rd;
    String[] tokens = url.split("/");
    String action = tokens[tokens.length -1].toLowerCase();

    switch (action) {
      case"login.do":
        rd = request.getRequestDispatcher("LoginServlet");
        rd.forward(request, response);
        break;
      case "dashboard.do":
        rd = request.getRequestDispatcher("UserServlet");
        rd.forward(request, response);
        break;
      case "new-request.do":
        rd = request.getRequestDispatcher("NewRequestServlet");
        rd.forward(request, response);
        break;
      case "active-requests.do":
        rd = request.getRequestDispatcher("ActiveRequestsServlet");
        rd.forward(request, response);
        break;
      case "closed-requests.do":
        rd = request.getRequestDispatcher("ClosedRequestsServlet");
        rd.forward(request, response);
        break;
      case "pending-requests.do":
        rd = request.getRequestDispatcher("PendingRequestsServlet");
        rd.forward(request, response);
        break;
      case "request-details.do":
        rd = request.getRequestDispatcher("RequestDetailsServlet");
        rd.forward(request, response);
        break;
      default:
        response.sendError(404);
    }

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
