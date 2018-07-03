package com.revature.project0.monopoly.database.beans;

/**
 * This class represents the data gleaned from the database. It follows the bean design pattern
 */
public class BoardSquarePartial {
    private int squareId;
    private int board_id;
    private String name;
    private int buyPrice;
    private int ownerId;
    private int expansionCount;
    private int mortgageValue;

    public BoardSquarePartial(int squareId, int board_id, String name, int buyPrice, int ownerId, int expansionCount, int mortgageValue) {
        this.squareId = squareId;
        this.board_id = board_id;
        this.name = name;
        this.buyPrice = buyPrice;
        this.ownerId = ownerId;
        this.expansionCount = expansionCount;
        this.mortgageValue = mortgageValue;
    }

    public int getSquareId() {
        return squareId;
    }

    public void setSquareId(int squareId) {
        this.squareId = squareId;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getExpansionCount() {
        return expansionCount;
    }

    public void setExpansionCount(int expansionCount) {
        this.expansionCount = expansionCount;
    }

    public int getMortgageValue() {
        return mortgageValue;
    }

    public void setMortgageValue(int mortgageValue) {
        this.mortgageValue = mortgageValue;
    }

}
