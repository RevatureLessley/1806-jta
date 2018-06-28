package com.revature.main;

import java.sql.Connection;
import java.util.List;

import com.revature.beans.Npc;
import com.revature.service.NpcService;
import com.revature.util.Connections;

public class Driver {

	public static void main(String[] args) throws Exception{
		Connection conn = Connections.getConnection();
		
		NpcService ns = new NpcService();
		
		List<Npc> npcs = ns.getAllNpcsWithClass();
		
		for(Npc npc: npcs){
			System.out.println(npc.getName() +
							", who is the class: " +
								npc.getJobClassString() +
								", who currently has " +
								npc.getCurrency() +
								" monies....");
		}
		Npc npc = null;
		System.out.println("===================");
		System.out.println(npc = ns.getNpcById(3));
		ns.updateLvlById(3, npc.getLvl() + 1);
		System.out.println(npc = ns.getNpcById(3));
		
		if(conn!=null){
			conn.close();
		}
		
	}

}