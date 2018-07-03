package com.revature.project0.monopoly.database.beans;

/**
 * This class represents the data gleaned from the database. It follows the bean design pattern
 */
public class GameStatePartial {

    private int gameStateId;
    private int playerTurn;
    private int jackpotValue;

    public GameStatePartial(int gameStateId, int playerTurn, int jackpotValue) {
        this.gameStateId = gameStateId;
        this.playerTurn = playerTurn;
        this.jackpotValue = jackpotValue;
    }

    public int getGameStateId() {
        return gameStateId;
    }

    public void setGameStateId(int gameStateId) {
        this.gameStateId = gameStateId;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getJackpotValue() {
        return jackpotValue;
    }

    public void setJackpotValue(int jackpotValue) {
        this.jackpotValue = jackpotValue;
    }
}
