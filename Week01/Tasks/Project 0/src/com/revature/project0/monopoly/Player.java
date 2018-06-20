package com.revature.project0.monopoly;

import com.revature.project0.monopoly.Board.BoardPiece;

public class Player {

    private String name;
    private BoardPiece piece;
    private int money;
    private int location;

    public Player(String name, BoardPiece piece){
        this.name = name;
        this.piece = piece;
        this.money = 1500;
        this.location = 0;
    }


    /* Getters and Setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoardPiece getPiece() {
        return piece;
    }

    public void setPiece(BoardPiece piece) {
        this.piece = piece;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
