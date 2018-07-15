package com.revature.dao;

import com.revature.beans.ReimbursementForm;
import java.util.List;

public interface ReimbursementFormDao {
	public void insertNpc(ReimbursementForm rf);
	public ReimbursementForm selectRFById(Integer id);
	public List<ReimbursementForm> selectAllRF();
	public Integer deleteRFById(Integer id);
	public Integer updateRF(ReimbursementForm rf);
	public Boolean insertRFViaSp(ReimbursementForm rf);
	public ReimbursementForm selectRFByName(String first, String last);
}
