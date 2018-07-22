package com.revature.dao;

import com.revature.beans.Employee;
import com.revature.beans.ReimbursementForm;
import java.util.List;

public interface ReimbursementFormDao {
	public void insertReimbursementForm(ReimbursementForm rf);
	public void updateReimbursementForm(ReimbursementForm urf);
	public void updateReimbursementForm2(ReimbursementForm urf);
	public void updateReimbursementForm3(ReimbursementForm urf);
	public void deleteReimbursementForm(ReimbursementForm drf);
	public void attachment(ReimbursementForm rf, String file);
	public List<ReimbursementForm> selectAllReimbursementForm();
}

