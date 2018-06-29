package com.revature.main;

import java.sql.Connection;
import java.util.List;

import com.revature.beans.Npc;
import com.revature.service.NpcService;
import com.revature.utils.CloseStreams;
import com.revature.utils.Connections;

public class Driver {

	public static void main(String[] args) {
		Connection con = Connections.getConnection();

		NpcService ns = new NpcService();
		List<Npc> npcs = ns.getAllNpcsWithClass();

		for (Npc n : npcs) {
			System.out.println(n.getName() + " is a " + n.getJobClassString() + " and has " + n.getCurrency() + "g");
		}

		Npc n = ns.getNpc(1);
		System.out.println("ALSO, " + n.getName() + " is level " + n.getLvl());
		
		n = ns.levelUpNpc(1,10);
		System.out.println("ALSO, " + n.getName() + " is level " + n.getLvl());
		
		CloseStreams.close(con);

	}
}
