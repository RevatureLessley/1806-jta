package com.revature.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

/**
 * Servlet that handles the retrieval of the Session
 */
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text");
        resp.getWriter().println(sessionToJson(req.getSession(false)));
    }

    private String sessionToJson(HttpSession session){
        StringBuilder sb = new StringBuilder("{");
        //header information about session
        sb.append("\"SessionId\":\"").append(session.getId()).append("\",");    //SessionId is a String

        //get attribute key-value pairs
        sb.append("\"data\":[");
        Iterator<String> it = session.getAttributeNames().asIterator();
        boolean once = false;
        sb.append("{");
        while(it.hasNext()){
            once = true;
            String key = it.next();
            sb.append("\"").append(key).append("\":");
            if (session.getAttribute(key) instanceof String) sb.append("\"").append(session.getAttribute(key)).append("\"");
            else sb.append(session.getAttribute(key));
            sb.append(",");
        }
        if (once) sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        //loop
        sb.append("]");

        sb.append("}");


        return sb.toString();
    }
}
