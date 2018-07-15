package com.revature.services;

import java.util.List;

import com.revature.beans.Npc;
import com.revature.beans.User;
import com.revature.dao.NpcDao;
import com.revature.dao.NpcDaoImpl;
import com.revature.dao.UsersDaoImpl;

public class UserService {
	public static boolean createUserWithNpc(
				String username,
				String password,
				String fname,
				String lname
			){
		
		User user = null;
		NpcDao nd = new NpcDaoImpl();
		UsersDaoImpl ud = new UsersDaoImpl();
		Npc npc = new Npc(username,3,0,3);
		
		if(!(nd.selectNpcByName(username) == null)){
			return false;
		}
		
		nd.insertNpcViaSp(npc);
		

		
		List<Npc> npcs = nd.selectAllNpc();
		System.out.println(username + "in service");
		for(Npc n: npcs){
			if(n.getName().equals(username)){
				user = new User(n.getId(), username, password);
				ud.insertUserViaSp(user);
				return true;
			}
		}
		return false;
	}
	
	public static boolean userLogin(String username, String password){
		UsersDaoImpl ud = new UsersDaoImpl();
		User user = null;
		if((user = ud.selectUserByUsername(username)) == null){
			return false;
		}
		if(!user.getPassword().equals(password)){
			return false;
		}
		
		
		return true;
	}
}
