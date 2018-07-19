package com.revature.daos;

import java.util.List;

import com.revature.beans.Reimbursement;

public interface ReimbursementDao {
	public Reimbursement selectReimbursementById(Integer id);
	public List<Reimbursement> selectAllReimbursementById(Integer id);
	public Integer deleteReimbursementById(Integer id);
	public Integer updateReimbursement(Reimbursement reim);
	public Boolean insertReimbursementViaSp(Reimbursement reim);
	public Reimbursement selectReimbursementByName(String name);
}
