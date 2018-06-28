package demo_project;

import java.sql.Connection;
import java.util.List;

import demo_project.beans.Npc;
import demo_project.service.NpcService;

public class Driver 
{
	public static void main(String[] args) throws Exception{
		//Connection conn = Connections.getConnection();
		
		NpcService ns = new NpcService();
		
		List<Npc> npcs = ns.getAllNpcesWithClass();
		
		for(Npc npc: npcs) {
			System.out.println(npc.getName() +
					", who is the class: "+
					npc.getJobClassString() +
					", who currently has " +
					npc.getCurrency() +
					" monies.");
		}
//		if(conn!=null) {
//			conn.close();
//		}
	}
}
