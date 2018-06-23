package com.revature.project0.monopoly;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  This POJO class keeps track of the game state when the game is being suspended, and is able to serialize that info.
 *
 */
public class GameState implements Serializable {
    private static final long serialVersionUID = -3534440685637555427L;
    private ArrayList<Player> playerList;
    private Board board;
    private int jackpotValue;

    public void setGameState(ArrayList<Player> playersList, Board board, int jackpotValue){
        this.playerList = playersList;
        this.board = board;
        this.jackpotValue = jackpotValue;
    }

    public ArrayList<Player> getPlayers(){
        return playerList;
    }

    public Board getBoard(){
        return board;
    }

    public int getJackpotValue(){
        return jackpotValue;
    }

}
