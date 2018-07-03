package com.revature.project0.monopoly.database.dal;

import com.revature.project0.monopoly.core.GameState;
import com.revature.project0.monopoly.database.beans.GameStatePartial;

public interface GameStateDao {

    boolean addGameState(GameState state);
    boolean setGameState(GameState state);
    GameStatePartial getGameStateData();
    void deleteGameState(int gameId);
}
