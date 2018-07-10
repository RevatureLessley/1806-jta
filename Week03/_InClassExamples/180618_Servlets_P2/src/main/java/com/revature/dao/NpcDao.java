package com.revature.dao;

import java.util.List;

import com.revature.beans.Npc;

public interface NpcDao {
	public void insertNpc(Npc npc);
	public Npc selectNpcById(Integer id);
	public List<Npc> selectAllNpc();
	public Integer deleteNpcById(Integer id);
	public Integer updateNpc(Npc npc);
	public Boolean insertNpcViaSp(Npc npc);
	public Npc selectNpcByName(String name);
}
