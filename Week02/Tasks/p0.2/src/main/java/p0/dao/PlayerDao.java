package p0.dao;

import p0.PlayerAccount;

public interface PlayerDao {
	public Integer updatePlayer(PlayerAccount player);
	public void createPlayer(String name, String Uname, String pword, Integer count);
}

