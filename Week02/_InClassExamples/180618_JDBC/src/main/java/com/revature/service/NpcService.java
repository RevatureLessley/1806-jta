package com.revature.service;

import java.util.List;

import com.revature.beans.Npc;
import com.revature.dao.NpcDao;
import com.revature.dao.NpcDaoImpl;

public class NpcService {
	public List<Npc> getAllNpcsWithClass(){
		NpcDao nd = new NpcDaoImpl();
		return nd.selectAllNpc();
		
		//getAllNpc (Which wouldnt have jobStringClass set
		//getAllClasses
		//SetAllJobStringClasses in this method
	}
}
