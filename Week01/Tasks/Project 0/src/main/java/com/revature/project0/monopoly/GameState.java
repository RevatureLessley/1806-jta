package com.revature.project0.monopoly;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  This POJO class keeps track of the game state when the game is being suspended, and is able to serialize that info.
 *
 */
public class GameState implements Serializable {

    private static final long serialVersionUID = 5079595583497469998L;
    private ArrayList<Player> playerList;
    private Board board;
    private int jackpotValue;
    private int playerTurn;

    void setGameState(ArrayList<Player> playersList, Board board, int playerTurn, int jackpotValue){
        this.playerList = playersList;
        this.board = board;
        this.playerTurn = playerTurn;
        this.jackpotValue = jackpotValue;
    }

    ArrayList<Player> getPlayers(){
        return playerList;
    }

    Board getBoard(){
        return board;
    }

    int getJackpotValue(){
        return jackpotValue;
    }

    int getPlayerTurn(){
        return playerTurn;
    }

}
