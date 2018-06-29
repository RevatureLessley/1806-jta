package demo_project.service;

import java.util.List;

import demo_project.beans.JobClass;
import demo_project.beans.Npc;
import demo_project.dao.JobClassDao;
import demo_project.dao.JobClassDaoImpl;
import demo_project.dao.NpcDao;
import demo_project.dao.NpcDaoImpl;

public class NpcService {
	public List<Npc> getAllNpcesWithClass() {
		NpcDao nd = new NpcDaoImpl();
		JobClassDao jcd = new JobClassDaoImpl();
		
		List<Npc> npcs = nd.selectAllNpc();
		List<JobClass> jcs = jcd.getAllJobs();
		
		for(Npc n: npcs)
		{
			for(JobClass jc: jcs) {
				if(n.getJobClass().getClass().equals(jc.getId())) {
					n.setJobClassString(jc.getName());
				}
			}
		}
		
		return nd.selectAllNpc();
		
		//getAllNpc (Which wouldn't have jobStringClass set
		//getAllClasses
		//SetAllJobStringClasses in this method
	}
	
	public Npc getNpcById(Integer id) {
		NpcDaoImpl ndi = new NpcDaoImpl();
		JobClassDao jcd = new JobClassDaoImpl();
		List<JobClass> jcs = jcd.getAllJobs();
		Npc npc = ndi.selectNpcById(id);
		if(npc != null) {
			for(JobClass jc: jcs) {
				if(npc.getJobClass().getClass().equals(jc.getId())) {
					npc.setJobClassString(jc.getName());
				}
			}
		}
		return npc;
	}
	
	public boolean updateLvlById(Integer id, Integer lvl)
	{
		NpcDaoImpl nd = new NpcDaoImpl();
		Npc npc = nd. selectNpcById(id);
		
		if(npc != null) {
			npc.setLvl(lvl);
			if(nd.updateNpc(npc) > 0) {
				return true;
			}
		}
		return false;
	}
	public boolean insertNpc(Npc npc) {
		NpcDao nd = new NpcDaoImpl();
		return nd.insertNpcViaSp(npc);
	}
}
