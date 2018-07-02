package p0.service;

import p0.PlayerAccount;
import p0.dao.PlayerDaoImpl;

public class PlayerService {
	PlayerDaoImpl pdi = new PlayerDaoImpl();
	
	public boolean updatePlayer(PlayerAccount player) {
		if(pdi.updatePlayer(player) >0) {
			return true;
		}
		return false;
	}
}
