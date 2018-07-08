package com.revature.main;

import com.revature.services.BenefitsCoordinatorService;
import com.revature.services.BranchManagerService;
import com.revature.services.DepartmentHeadService;
import com.revature.services.EmployeeService;


public class Driver {

	public static void main(String[] args) {
		BranchManagerService bms = new BranchManagerService();
		BenefitsCoordinatorService bencos = new BenefitsCoordinatorService();
		DepartmentHeadService heads = new DepartmentHeadService();
		SupervisorService sups = new SupervisorService();
		EmployeeService emps = new EmployeeService();
		sups.insertSupervisor(new Supervisor(100, "Eli", "Laster", 5555555, 
				"elast@email.com", "100 way dr", "hamptons"));
	}

}
