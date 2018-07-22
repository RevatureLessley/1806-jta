package com.revature.dal;

import com.revature.beans.ReimbursementBean;

import java.util.ArrayList;

/**
 * Interface for accessing the database on behalf of Reimbursement Requests
 */
public interface ReimbursementDao {
    ArrayList<ReimbursementBean> retrieveReimbursementsByEmployee(int employeeId);
    ReimbursementBean retrieveReimbursementFormById(int id);
    boolean insertReimbursementForm(ReimbursementBean bean, int supervisorId);
    boolean updateReimbursementStatus(int id, int status);
    boolean deleteReimbursementFormById(int id);
}
