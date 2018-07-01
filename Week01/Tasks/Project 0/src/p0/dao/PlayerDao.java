package p0.dao;

import java.util.ArrayList;

import p0.Player;

public interface PlayerDao {
	public ArrayList<Player> GetPlayerById(Integer id);
}
