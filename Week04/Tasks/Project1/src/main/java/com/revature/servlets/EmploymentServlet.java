package com.revature.servlets;

import com.revature.bll.EmployeeService;
import com.revature.utils.LogWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmploymentServlet extends HttpServlet {

    public EmploymentServlet(){
        super();
        LogWrapper.log(this.getClass(), "EmploymentServlet created.", LogWrapper.Severity.DEBUG);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        //check password match
        if (!password.equals(confirmPassword)){
            RequestDispatcher rd = req.getRequestDispatcher("create-employee.html");
            rd.forward(req, resp);
        }
        else{
            //find a valid id num to assign
            int id = EmployeeService.getUnusedId();

            long phoneNum = -1L;
            try{
                phoneNum = Long.parseLong(phone);
            }catch(NumberFormatException e){
                LogWrapper.log(this.getClass(), e);
                resp.sendError(400);
            }

            if (EmployeeService.addEmployee(id, firstname, lastname, password, phoneNum, email, 0,0, getSupervisor(), getDeptHead(), getBenCo())){
                resp.sendRedirect("/user/home.html"); //sendRedirect to invalidate sensitive information
            }
            else{
                resp.sendError(400);    //Employee insertion failed.
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogWrapper.log(this.getClass(), "EmploymentServlet received POST request.", LogWrapper.Severity.DEBUG);
        doGet(req, resp);
    }

    //NOTE: The following three methods are for expanding this project. For now, they only return the "default" employee's ID

    private int getSupervisor(){
        return 4;   //TODO: Hardcoded from Database
    }

    private int getDeptHead(){
        return 3;   //TODO: Hardcoded from Database
    }

    private int getBenCo(){
        return 2;   //TODO: Hardcoded from Database
    }
}
