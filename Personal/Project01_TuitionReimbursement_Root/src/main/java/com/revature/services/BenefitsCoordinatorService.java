package com.revature.services;

import com.revature.beans.BenefitsCoordinator;
import com.revature.daos.BenefitsCoordinatorDaoImpl;

public class BenefitsCoordinatorService {
	public boolean insertBenefitsCoordinator(BenefitsCoordinator benco) {
		BenefitsCoordinatorDaoImpl bencod = new BenefitsCoordinatorDaoImpl();
		return bencod.insertBenefitsCoordinatorViaSp(benco);
	}
}
