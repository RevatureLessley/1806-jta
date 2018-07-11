package com.revature.daos;

import java.util.List;

import com.revature.beans.Supervisor;

public interface SupervisorDao {
	public Supervisor selectSupervisorById(Integer id);
	public List<Supervisor> selectAllSupervisor();
	public Integer deleteSupervisorById(Integer id);
	public Integer updateSupervisor(Supervisor supv);
	public Boolean insertSupervisorViaSp(Supervisor supv);
	public Supervisor selectSupervisorByName(String name);
}
