package com.revature.project0.monopoly.database.dal;

import com.revature.project0.monopoly.core.Board;

import java.util.HashMap;

public interface PlayerPropertyDao {
    boolean addProperty(int playerId, int propertyId, boolean isMortgaged);
    HashMap<Board.BoardSquare, Boolean> getAllPropertiesOfPlayer(int playerId);
    void deleteMetaDataFromGame(int gameId);
}
