package demo_project.service;

import java.util.ArrayList;
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
}
