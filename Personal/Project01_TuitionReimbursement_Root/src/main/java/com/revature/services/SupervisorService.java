package com.revature.services;

import com.revature.beans.Supervisor;
import com.revature.daos.SupervisorDaoImpl;

public class SupervisorService {
	public boolean insertSupervisor(Supervisor supv) {
		SupervisorDaoImpl supvd = new SupervisorDaoImpl();
		return supvd.insertSupervisorViaSp(supv);
	}
}
