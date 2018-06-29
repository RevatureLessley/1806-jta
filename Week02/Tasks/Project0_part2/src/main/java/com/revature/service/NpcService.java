package com.revature.service;

import java.util.List;

import com.revature.beans.Npc;
import com.revature.dao.NpcDao;
import com.revature.dao.NpcDaoImpl;

public class NpcService {

	public List<Npc> getAllNpcsWithClass() {
		
		NpcDao nd = new NpcDaoImpl();
		return nd.selectAllNpc();
	}
	
	public Npc getNpc(int id) {
		NpcDao nd = new NpcDaoImpl();
		return nd.selectNpcById(id);
	}
	

	public Npc levelUpNpc(Integer id, int amt) {
		Npc npc = getNpc(id);
		
		NpcDao nd = new NpcDaoImpl();	
		
		npc.setLvl(npc.getLvl()+amt);
		
		nd.updateNpc(npc);
		
		return npc;
	}
}
