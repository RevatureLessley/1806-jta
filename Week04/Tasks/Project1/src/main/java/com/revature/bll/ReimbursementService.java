package com.revature.bll;

import com.revature.beans.EmployeeBean;
import com.revature.beans.ReimbursementBean;
import com.revature.dal.EmployeeDaoImpl;
import com.revature.dal.ReimbursementDaoImpl;

import java.time.LocalDate;

public class ReimbursementService {
    public static boolean insertRequest(int id, LocalDate date, String location, String description, double amount, int type){
        //TODO: Do error checking *somehwere* that the employee exists before being able to submit a reimbursement form
        ReimbursementDaoImpl rdao = new ReimbursementDaoImpl();
        EmployeeDaoImpl edao = new EmployeeDaoImpl();

        return rdao.insertReimbursementForm(
                new ReimbursementBean(
                    id,
                    edao.retrieveEmployeeById(id),  //pulls from the database
                    date, location, description, amount, "Format?", //TODO: Figure out 'Format?'
                    type, false
                )
        );
    }
}
