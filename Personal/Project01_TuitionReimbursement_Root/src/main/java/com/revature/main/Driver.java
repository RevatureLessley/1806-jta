package com.revature.main;

import com.revature.beans.BranchManager;
import com.revature.services.BranchManagerService;

public class Driver {

	public static void main(String[] args) {
		BranchManagerService bms = new BranchManagerService();
		bms.insertBranchManager(new BranchManager(100, 99, "Jared", "Belford", 5555555, 
				"jbel@email.com", "100 way dr", "hamptons"));
	}

}
