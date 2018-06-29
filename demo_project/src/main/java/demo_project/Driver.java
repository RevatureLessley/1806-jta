package demo_project;

import java.util.List;
import java.util.Scanner;

import demo_project.beans.Npc;
import demo_project.service.NpcService;

public class Driver 
{
	public static void main(String[] args) throws Exception{
		//Connection conn = Connections.getConnection();
		
		NpcService ns = new NpcService();
		
		List<Npc> npcs = ns.getAllNpcesWithClass();
		
		displayNpcGroup(npcs);
		Npc npc = null;
		System.out.println("===========");
		System.out.println(npc = ns.getNpcById(2));
		ns.updateLvlById(2,  npc.getLvl()+1);

		Scanner in = new Scanner(System.in);
		
		Npc temp = new Npc("StoredProcPerson", (int)(Math.random() *100), (int)(Math.random()*999999),(int)(((Math.random()) *3) +1));
		ns.insertNpc(temp);
		npcs = ns.getAllNpcesWithClass();
		displayNpcGroup(npcs);
					
//		if(conn!=null) {
//			conn.close();
//		}
	}
	
	public static void displayNpcGroup(List<Npc> npcs)
	{
		for(Npc npc: npcs) {
			System.out.println(npc.getName() +
					", who is the class: "+
					npc.getJobClassString() +
					", who currently has " +
					npc.getCurrency() +
					" monies and is level: " +
					npc.getLvl());
		}
	}
}
