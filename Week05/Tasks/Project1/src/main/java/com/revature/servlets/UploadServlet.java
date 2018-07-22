package com.revature.servlets;

import com.revature.beans.ReimbursementBean;
import com.revature.dal.ReimbursementDaoImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet that handles the uploading of a file
 */
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int val = Integer.parseInt(request.getParameter("value"));
        ReimbursementBean bean = new ReimbursementDaoImpl().retrieveReimbursementFormById(val);

        response.setContentType("text");
        PrintWriter out = response.getWriter();
        out.println(bean.getFileUrl());
    }

}
