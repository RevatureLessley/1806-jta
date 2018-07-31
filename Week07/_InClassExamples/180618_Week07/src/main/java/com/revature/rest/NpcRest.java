package com.revature.rest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
	List<Npc> npcs = Arrays.asList(
			new Npc(1, "Bobbert", "Professional Bob", 999999)
	);
	
	@GET
	@Path("/get/all")
	@Produces(MediaType.APPLICATION_JSON)
	//http://localhost:8085/180618_Week07/rest/npc/get/all
	public List<Npc> getAllNpcs(){
		
		
		return npcs;
	}
}
