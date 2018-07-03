package com.revature.project0.monopoly.database.beans;

/**
 *  This class contains the data gleaned from the database. It follows the Bean design pattern
 */
public class BoardPartial {

    private int boardid;
    private int gameStateId;

    public BoardPartial(int boardid, int gameStateId) {
        this.boardid = boardid;
        this.gameStateId = gameStateId;
    }

    public int getBoardid() {
        return boardid;
    }

    public void setBoardid(int boardid) {
        this.boardid = boardid;
    }

    public int getGameStateId() {
        return gameStateId;
    }

    public void setGameStateId(int gameStateId) {
        this.gameStateId = gameStateId;
    }
}
