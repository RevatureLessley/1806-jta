package demo_project.dao;

import java.util.List;

import demo_project.beans.Npc;

public interface NpcDao {
	public void insertNpc(Npc npc);
	public Npc selectNpcById(Integer id);
	public List<Npc> selectAllNpc();
	public Integer deleteNpcById(Integer id);
	public Integer updateNpc(Npc npc);
	public boolean insertNpcViaSp(Npc npc);
}
