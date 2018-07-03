package com.revature.project0.monopoly.database.dal;

import com.revature.project0.monopoly.core.Player;

import java.util.ArrayList;

public interface PlayerDao {
    boolean addPlayer(Player player, int gameId);
    ArrayList<Player> getAllPlayersofGame(int gameId);
    void deletePlayers(int gameId);
}
