package com.revature.trms.util;

import java.io.PrintWriter;

public class HttpTemplates {

  public static void RegularNavbar(PrintWriter out) {
    out.println("  <nav class=\"navbar navbar-expand-sm bg-primary navbar-dark\">\n"
        + "    <ul class=\"navbar-nav mr-auto\" id=\"nav-div\">\n"
        + "      <li class=\"nav-item\">\n"
        + "        <a class=\"btn btn-md btn-primary nav-link active\" href=\"/user\" id=\"new-request\">New Request</a>\n"
        + "      </li>\n"
        + "      <li class=\"nav-item\">\n"
        + "        <a class=\"btn btn-md btn-primary nav-link\" href=\"/user/active\" id=\"active-requests\">Active Requests</a>\n"
        + "      </li>\n"
        + "      <li class=\"nav-item\">\n"
        + "        <a class=\"btn btn-md btn-primary nav-link\" href=\"/user/closed\" id=\"closed-requests\">Closed Requests</a>\n"
        + "      </li>\n"
        + "    </ul>\n"
        + "    <ul class=\"navbar-nav ml-auto\">\n"
        + "      <li class=\"nav-item\">\n"
        + "        <a class=\"btn btn-md btn-primary nav-link\" onclick=\"logout()\" id=\"logout\">Logout</a>\n"
        + "      </li>\n"
        + "    </ul>\n"
        + "  </nav>");
  }

  public static void AdministrativeNavbar(PrintWriter out) {
    out.println("  <nav class=\"navbar navbar-expand-sm bg-primary navbar-dark\">\n"
        + "    <ul class=\"navbar-nav mr-auto\" id=\"nav-div\">\n"
        + "      <li class=\"nav-item\">\n"
        + "        <a class=\"btn btn-md btn-primary nav-link active\" href=\"/user\" id=\"new-request\">New Request</a>\n"
        + "      </li>\n"
        + "      <li class=\"nav-item\">\n"
        + "        <a class=\"btn btn-md btn-primary nav-link\" href=\"/user/pending\" id=\"pending-requests\">Pending\n"
        + "          Requests</a>\n"
        + "      </li>\n"
        + "      <li class=\"nav-item\">\n"
        + "        <a class=\"btn btn-md btn-primary nav-link\" href=\"/user/active\" id=\"active-requests\">Active Requests</a>\n"
        + "      </li>\n"
        + "      <li class=\"nav-item\">\n"
        + "        <a class=\"btn btn-md btn-primary nav-link\" href=\"/user/closed\" id=\"closed-requests\">Closed Requests</a>\n"
        + "      </li>\n"
        + "    </ul>\n"
        + "    <ul class=\"navbar-nav ml-auto\">\n"
        + "      <li class=\"nav-item\">\n"
        + "        <a class=\"btn btn-md btn-primary nav-link\" onclick=\"logout()\" id=\"logout\">Logout</a>\n"
        + "      </li>\n"
        + "    </ul>\n"
        + "  </nav>");
  }

}
