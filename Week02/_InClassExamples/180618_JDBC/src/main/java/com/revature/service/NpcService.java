package com.revature.service;

import java.util.List;

import com.revature.beans.JobClass;
import com.revature.beans.Npc;
import com.revature.dao.JobClassDao;
import com.revature.dao.JobClassDaoImpl;
import com.revature.dao.NpcDao;
import com.revature.dao.NpcDaoImpl;

public class NpcService {
	public List<Npc> getAllNpcsWithClass(){
		NpcDao nd = new NpcDaoImpl();
		JobClassDao jcd = new JobClassDaoImpl();
		
		List<Npc> npcs = nd.selectAllNpc();
		List<JobClass> jcs = jcd.getAllJobs();
		
		
		
		for(Npc n: npcs){
			for(JobClass jc: jcs){
				if(n.getJobClass().equals(jc.getId())){
					n.setJobClassString(jc.getName());
				}
			}
		}
		
		
		
		return npcs;
		
		//getAllNpc (Which wouldnt have jobStringClass set
		//getAllClasses
		//SetAllJobStringClasses in this method
	}
	
	public Npc getNpcById(Integer id){
		NpcDao nd = new NpcDaoImpl();
		JobClassDao jcd = new JobClassDaoImpl();
		List<JobClass> jcs = jcd.getAllJobs();
		Npc npc = nd.selectNpcById(id);
		
		if(npc != null){
			for(JobClass jc: jcs){
				if(npc.getJobClass().equals(jc.getId())){
					npc.setJobClassString(jc.getName());
				}
			}			
		}
		
		return npc;
	}
	
	public boolean updateLvlById(Integer id, Integer lvl){
		NpcDaoImpl nd = new NpcDaoImpl();
		Npc npc = nd.selectNpcById(id);
		
		if(npc != null){
			npc.setLvl(lvl);
			if(nd.updateNpc(npc) > 0){
				return true;
			}
		}
		
		return false;
	}
}
