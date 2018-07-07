package com.revature.daos;

import java.util.List;

import com.revature.beans.BenefitsCoordinator;

public interface BenefitsCoordinatorDao {
	public BenefitsCoordinator selectBenefitsCoordinatorById(Integer id);
	public List<BenefitsCoordinator> selectAllBenefitsCoordinator();
	public Integer deleteBenefitsCoordinatorById(Integer id);
	public Integer updateBenefitsCoordinator(BenefitsCoordinator benco);
	public Boolean insertBenefitsCoordinatorViaSp(BenefitsCoordinator benco);
	public BenefitsCoordinator selectBenefitsCoordinatorByName(String name);
}
