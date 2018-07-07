package com.revature.daos;

import java.util.List;

import com.revature.beans.BranchManager;

public interface BranchManagerDao {
	public BranchManager selectBranchManagerById(Integer id);
	public List<BranchManager> selectAllBranchManager();
	public Integer deleteBranchManagerById(Integer id);
	public Integer updateBranchManager(BranchManager bm);
	public Boolean insertBranchManagerViaSp(BranchManager bm);
	public BranchManager selectBranchManagerByName(String name);
}
