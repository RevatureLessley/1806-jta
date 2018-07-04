package com.revature.main;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Npc;
import com.revature.service.NpcService;
import com.revature.util.Connections;

public class Driver {

	public static void main(String[] args) throws Exception{
		Connection conn = Connections.getConnection();
		if(conn!=null){
			conn.close();
		}

		NpcService ns = new NpcService();

		List<Npc> npcs = ns.getAllNpcsWithClass();

		displayNpcGroup(npcs);

		Npc npc = null;
		System.out.println("===================");
		System.out.println(npc = ns.getNpcById(3));
		ns.updateLvlById(3, npc.getLvl() + 1);
		System
		.out
		.println(npc = ns.getNpcById(3));

		Scanner in = new Scanner(System.in);

		Npc newNpc = new Npc("StoredProcPerson",
				(int)(Math.random()*100),
				(int)(Math.random()*999999),
				(int)(((Math.random())*3) + 1));

		if(ns.insertNpc(newNpc)){
			npcs = ns.getAllNpcsWithClass();
			displayNpcGroup(npcs);
		}else{
			System.err.println("STORED PROCEDURE FAILURE!");
		}



	}

	public static void displayNpcGroup(List<Npc> npcs){
		for(Npc npc: npcs){
			System.out.println(npc.getName() +
							", who is the class: " +
								npc.getJobClassString() +
								", who currently has " +
								npc.getCurrency() +
								" monies...." +
								" who is also lvl: " +
								npc.getLvl());
		}
	}

}
