package com.revature.main;

import com.revature.beans.DepartmentHead;
import com.revature.services.BenefitsCoordinatorService;
import com.revature.services.BranchManagerService;
import com.revature.services.DepartmentHeadService;

public class Driver {

	public static void main(String[] args) {
		BranchManagerService bms = new BranchManagerService();
		BenefitsCoordinatorService bencos = new BenefitsCoordinatorService();
		DepartmentHeadService heads = new DepartmentHeadService();
		heads.insertDepartmentHead(new DepartmentHead(100, 100, "Fitra", "Khan", 5555555, 
				"Fitra@email.com", "100 way dr", "hamptons"));
	}

}
