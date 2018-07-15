package com.revature.dao;

import com.revature.beans.Employee;
import com.revature.beans.ReimbursementForm;
import java.util.List;

public interface ReimbursementFormDao {
	public Boolean insertReimbursementForm(ReimbursementForm rf);
	public Employee selectReimbursementForm(String rfId);
}
