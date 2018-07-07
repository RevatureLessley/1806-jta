package com.revature.services;

import com.revature.beans.BranchManager;
import com.revature.daos.BranchManagerDaoImpl;

public class BranchManagerService {
	public boolean insertBranchManager(BranchManager bm) {
		BranchManagerDaoImpl bmd = new BranchManagerDaoImpl();
		return bmd.insertBranchManagerViaSp(bm);
	}
}
