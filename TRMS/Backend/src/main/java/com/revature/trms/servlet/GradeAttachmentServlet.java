package com.revature.trms.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class GradeAttachmentServlet extends HttpServlet {

  private static final String ATTACHMENT_DIRECTORY = "attachments";
  private static final int THRESHOLD = 1024 * 1024 * 3;
  private static final int FILE_SIZE = 1024 * 1024 * 10;
  private static final int REQUEST_SIZE = 1024 * 1024 * 15;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/json");
    PrintWriter out = response.getWriter();

    DiskFileItemFactory dfif = new DiskFileItemFactory();
    dfif.setSizeThreshold(THRESHOLD);
    dfif.setRepository(new File(System.getProperty("java.io.tmpdir")));
    ServletFileUpload attachment = new ServletFileUpload(dfif);
    attachment.setFileSizeMax(FILE_SIZE);
    attachment.setSizeMax(REQUEST_SIZE);
    String path = getServletContext().getRealPath("") + ATTACHMENT_DIRECTORY;

    File directory = new File(path);
    if (!directory.exists()) {
      directory.mkdir();
    }

    try {
      List<FileItem> fileItems = attachment.parseRequest(request);
      if (fileItems != null && fileItems.size() > 0) {
        for (FileItem fileItem : fileItems) {
          if (!fileItem.isFormField()) {
            String fileName = new File(fileItem.getName()).getName();
            String filePath = path + File.separator + fileName;
            File storeFile = new File(filePath);
            fileItem.write(storeFile);
            System.out.println(filePath);
            out.println("{ \"status\": \"success\" }");
          } else {
            System.out.println(fileItem.toString());
          }
        }
      }
    } catch (Exception ex) {
      out.println("{ \"status\": \"failure\" }");
    }

  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

  }
}
