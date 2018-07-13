package com.revature.dal;

import com.revature.beans.ReimbursementBean;

public interface ReimbursementDao {
    ReimbursementBean retrieveReimbursementFormById(int id);
    boolean insertReimbursementForm(ReimbursementBean bean);
    boolean deleteReimbursementFormById(int id);
}
