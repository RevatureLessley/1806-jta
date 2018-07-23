package com.revature.trms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>\n"
        + "<html lang=\"en\">\n"
        + "<head>\n"
        + "  <meta charset=\"UTF-8\">\n"
        + "  <title>Tuition Reimbursement Management System</title>\n"
        + "\n"
        + "  <link rel=\"stylesheet\" type=\"text/css\"\n"
        + "        href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\">\n"
        + "  <link rel=\"stylesheet\" type=\"text/css\" href=\"/resources/css/main.css\">\n"
        + "</head>\n"
        + "<body>\n"
        + "<div class='container'>\n"
        + "  <h1 id='title'>Tuition Reimbursement Management System</h1>\n"
        + "\n"
        + "  <div class='row' id=\"data\" style=\"margin-top:60px\">\n"
        + "    <div class=\"col-sm-8 offset-md-2\" >\n"
        + "          <h2>Please Login</h2>\n"
        + "          <hr>\n"
        + "          <p id=\"login_message\" style=\"color: red\"></p>\n"
        + "          <div class=\"form-group\">\n"
        + "            <input type=\"email\" name=\"email\" id=\"email\" class=\"form-control input-lg\" placeholder=\"Email Address\">\n"
        + "          </div>\n"
        + "          <div class=\"form-group\">\n"
        + "            <input type=\"password\" name=\"password\" id=\"password\" class=\"form-control input-lg\" placeholder=\"Password\">\n"
        + "          </div>\n"
        + "          <hr>\n"
        + "          <div class=\"row\">\n"
        + "            <div class=\"col-sm-6 offset-sm-3\">\n"
        + "              <button class=\"btn btn-lg btn-success btn-block\" onclick=\"auth()\">Login</button>\n"
        + "            </div>\n"
        + "          </div>\n"
        + "    </div>\n"
        + "  </div>\n"
        + "\n"
        + "</div>\n"
        + "\n"
        + "<script src=\"http://code.jquery.com/jquery-3.3.1.min.js\"\n"
        + "        integrity=\"sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=\"\n"
        + "        crossorigin=\"anonymous\"></script>\n"
        + "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\"></script>\n"
        + "<script src=\"/resources/js/ajax.js\"></script>\n"
        + "</body>\n"
        + "</html>\n");
  }

}

