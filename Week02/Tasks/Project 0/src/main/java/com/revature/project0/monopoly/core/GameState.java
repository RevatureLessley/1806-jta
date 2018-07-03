package com.revature.project0.monopoly.core;

import java.util.ArrayList;

import static com.revature.project0.monopoly.core.LogWrapper.Severity.DEBUG;

/**
 *  This POJO class keeps track of the game state when the game is being suspended
 *
 */
public class GameState {

    private ArrayList<Player> playerList;
    private Board board;
    private int jackpotValue;
    private int playerTurn;
    private int idNum;

    private static int idIncrementor = 0;

    public GameState(boolean uniqueGame){
        if (uniqueGame) this.idNum = ++idIncrementor;   //prefix operation to start idNum at 0.
    }

    public void setGameState(ArrayList<Player> playersList, Board board, int playerTurn, int jackpotValue){
        this.playerList = playersList;
        this.board = board;
        this.playerTurn = playerTurn;
        this.jackpotValue = jackpotValue;
        LogWrapper.log(this.getClass(), "GameState set.", DEBUG);
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

    public int getPlayerTurn(){
        return playerTurn;
    }

    public int getIdNum() {
        return idNum;
    }
}
