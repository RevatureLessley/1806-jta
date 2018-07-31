package com.revature.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.revature.beans.Npc;

/*
 * Path annotation denotes what url will be used to access THIS class's REST methods.
 * So typical path includes the ip & port for the server.
 * After that you provide the name of the project (Which is either the artificat ID as 
 * indicated in the pom file, or 
 * <build>
 * 	<finalName>PROJECTNAME</finalName>
 * </build>
 * 
 * Followed by whatever URL pattern provided in the web.xml under the dispatcher servlet
 * for Jersey. (In this case we wrote /rest/*)
 * Convention suggests you have at least SOMETHING to separate rest endpoints
 * from actual server url mappings.
 */
//http://localhost:8085/180618_Week07/rest/

@Path("/npc")
public class NpcRest {
	//http://localhost:8085/180618_Week07/rest/npc/
	public static List<Npc> npcs = new ArrayList<>();
	

	
	public NpcRest() {
		npcs.add(new Npc(1, "Bobbert", "Professional Bob", 999999));
	}

	@GET
	@Path("/get/all")
	@Produces(MediaType.APPLICATION_JSON)
	//http://localhost:8085/180618_Week07/rest/npc/get/all
	public List<Npc> getAllNpcs(){
		
		
		return npcs;
	}
	
	@GET
	@Path("/get/{arrayListIndex}")
	@Produces(MediaType.APPLICATION_JSON)
	public Npc getNpcById(
			@PathParam("arrayListIndex")
			int index
			){
		if(npcs.size()<1 || index >= npcs.size()){
			return null;
		}
		else{
			return npcs.get(index);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/post")
	public String insertNpc(Npc npc){
		System.out.println(npc);
		npcs.add(npc);
		return "success!";
	}
}
