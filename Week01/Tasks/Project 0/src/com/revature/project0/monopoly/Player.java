package com.revature.project0.monopoly;

import com.revature.project0.monopoly.Board.BoardPiece;

import java.awt.*;
import java.util.*;

public class Player {

    private String name;
    private BoardPiece piece;
    private int money;
    private int location;

    private HashMap<Board.BoardSquare, Boolean> properties;     //BoardSquare, Boolean <true = mortgaged, false = not mortgaged>

    public boolean isInJail;
    public int jailTurnCount = 0;

    /**
     * Constructor of a Player object.
     * @param name  the name of the Player
     * @param piece the Board.BoardPiece the player would like to play with
     */
    public Player(String name, BoardPiece piece){
        this.name = name;
        this.piece = piece;
        this.money = 1500;
        this.location = 0;
        this.isInJail = false;
        properties = new HashMap<>();
    }

    /**
     * This method will move this player around the Monopoly board.
     * If this player happens to move past 'Go' they will automatically gain $200
     * @param distance the amount of squares the player moves
     */
    public void move(int distance){
        //NOTE: Hardcoded 40 squares on board.
        if ((this.location + distance) / 40 > 0) {  //NOTE: This doesn't belong in Player, but its easy to implement here.
            this.money += 200;
            System.out.println(this.name + " passed Go, collecting $200.");
        }
        this.location = (this.location + distance) % 40;
    }

    public void printInfo(){
        System.out.printf("%-20s %s\n", "Player Name:", name);
        System.out.printf("%-20s %s\n", "Playing Piece:", piece.toString());
        System.out.printf("%-20s $%d\n", "Money:", money);
        System.out.printf("%-20s %s\n", "Owned Properties:", propertiesToString());
    }

    private String propertiesToString(){
        if (getUnMortgagedProperties().size() > 0) {
            StringBuilder builder = new StringBuilder("[");
            for (Board.BoardSquare square : getUnMortgagedProperties()) builder.append(square.getName() + ", ");
            builder.delete(builder.length() - 2, builder.length());
            builder.append("]");
            return builder.toString();
        }
        else return "";
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

    public Set<Board.BoardSquare> getOwnedProperties(){
        return properties.keySet();
    }

    public HashSet<Board.BoardSquare> getUnMortgagedProperties(){
        HashSet<Board.BoardSquare> set = new HashSet<>();
        for(Board.BoardSquare s : properties.keySet()){
            if (!properties.get(s)) set.add(s);
        }
        return set;
    }

    public HashSet<Board.BoardSquare> getMortgagedProperties(){
        HashSet<Board.BoardSquare> set = new HashSet<>();
        for(Board.BoardSquare s : properties.keySet()){
            if (properties.get(s)) set.add(s);
        }
        return set;
    }

    /**
     * This function changes the BoardSquare's Mortgage flag to true and pays the player its value
     * @param property the property being mortgaged.
     */
    public void mortgageProperty(Board.BoardSquare property){
        properties.replace(property, true);
        money += property.getMortgageValue();
    }

    /**
     * This method changes the BoardSquare's Mortgage flag to false and tries to deduct money from player
     * @param property the property the player is trying to unmortgage
     * @return true if the player can afford the transaction, false if they cannot
     */
    public boolean unMortgageProperty(Board.BoardSquare property){
        if (money < (int)(property.getBuyBackPrice() * 1.1f)) return false;
        else {
            properties.replace(property, false);
            money -= (int)(property.getBuyBackPrice() * 1.1f);
            return true;
        }
    }

    public void boughtProperty(Board.BoardSquare property, boolean isMortgaged){
        properties.put(property, isMortgaged);
    }

    public int getRailRoadCount(){
        int count = 0;
        for (Board.BoardSquare property : properties.keySet()){
            if (property.getName().contains("Railroad") || property.getName().equals("Short Line")) count++;
        }
        return count;
    }

    public int getUtilityCount(){
        int count = 0;
        for (Board.BoardSquare property : properties.keySet()){
            if (property.getName().equals("Water Works") || property.getName().equals("Electric Company")) count++;
        }
        return count;
    }

    public boolean ownsBlock(Board board, Board.BoardSquare square){
        Color squareColor = square.getColor();
        Board.BoardSquare[] squares = board.getAllSquaresOfColor(squareColor);
        for(Board.BoardSquare tile : squares){
            if (tile != null && tile.getOwner() != this) return false;  //check for null only matters if there are two properties in the Color family
        }
        return true;

    }

    public boolean owesMoney(int debt, Player otherPlayer){
        if (!mortgage(debt)){
            //Player is out of money and must leave the game.
            System.out.println(name + " is declaring bankruptcy!");
            //otherPlayer.setMoney(otherPlayer.getMoney() + debt);
            for (Board.BoardSquare property : properties.keySet()) {
                property.setOwner(otherPlayer);    //null = the Bank
                property.setExpansions(0);
            }
            return false;
        }
        else return true;
    }

    /**
     * This method steps the player through the process of mortgaging one or more of their properties
     * If they have no properties to mortgage, mortgage is unsuccessful and returns false. True, otherwise
     * @param amount The value the player must have in money to leave this method.
     * @return true if mortgage is successful, false if no mortgage is performed
     */
    boolean mortgage(int amount){
        HashSet<Board.BoardSquare> unMortgagedProperties = this.getUnMortgagedProperties();
        if (unMortgagedProperties.size() == 0) {
            System.out.println("You don't have anything to mortgage.");
            return false;
        }
        do {
            System.out.printf("You have $%d, and you owe $%d. Select a property to mortgage:\n", this.getMoney(), amount);
            int count = 0;
            StringBuilder sb = new StringBuilder();
            for (Board.BoardSquare square : unMortgagedProperties){
                sb.append(square.getName() + " (" +(++count)+ "), ");
            }
            String[] validInputs = new String[count];
            for (Board.BoardSquare square : unMortgagedProperties) validInputs[count-1] = "" + (count--);
            sb.delete(sb.length() - 2, sb.length());    //delete ', '
            System.out.println(sb.toString());
            String line = Monopoly.waitForValidInput(sb.toString(), validInputs);
            Board.BoardSquare square = null;
            Iterator it = unMortgagedProperties.iterator();
            for (int i = 0; i < Integer.parseInt(line); i++) square = (Board.BoardSquare)it.next();
            if (square.getExpansions() > 0) {
                String msg = square.getName() + " has "+square.getExpansions()+" expansions. These will all be sold for half the price to build them. Continue with the mortgage? Yes (Y), No (N)";
                System.out.println(msg);
                line = Monopoly.waitForValidInput(msg, "y", "n");
                if (line.equals("n")) continue;
            }
            this.mortgageProperty(square);  //this method pays the player
            System.out.println("The bank gave you $"+square.getMortgageValue()+" to mortgage "+square.getName() + ".");

            unMortgagedProperties = this.getUnMortgagedProperties();
        } while(unMortgagedProperties.size() > 0 && this.getMoney() < amount);
        //if you leave this loop, then either: you've run out of properties and need to declare bankruptcy, or
        //      you now have enough money to pay your debts.
        if (this.getMoney() >= amount) return true;
        else return false;  //else you still dont have the money AND you have nothing else to mortgage.
    }


    public void unMortgage(){
        HashSet<Board.BoardSquare> set = this.getMortgagedProperties();
        if (set.size() == 0) System.out.println("The bank doesn't own any of your properties.");
        else{
            System.out.println("You have $" + this.getMoney() + ".");
            System.out.println("Select a property to buy back from the bank.");
            int count = 0;
            StringBuilder sb = new StringBuilder();
            for (Board.BoardSquare square : set){
                sb.append(square.getName() + ": $"+square.getBuyBackPrice()+" (" +(++count)+ "), ");
            }
            String[] validInputs = new String[count];
            for (Board.BoardSquare square : set) validInputs[count-1] = "" + (count--);
            sb.delete(sb.length() - 2, sb.length());    //delete ', '
            System.out.println(sb.toString());
            String line = Monopoly.waitForValidInput(sb.toString(), validInputs);
            Board.BoardSquare square = null;
            Iterator it = set.iterator();
            for (int i = 0; i < Integer.parseInt(line); i++) square = (Board.BoardSquare)it.next();
            if (this.unMortgageProperty(square)) System.out.printf("You bought %s back from the bank.\n", square.getName());
            else System.out.println("You don't have enough money to complete this purchase.");
        }
    }



    public int getTotalWorth(){
        int sum = 0;
        sum += money;
        for (Board.BoardSquare property : properties.keySet()) {
            sum += (property.getBuyPrice() * property.getExpansions()); //cost of expansions
            sum += property.getOriginalBuyPrice();  //cost of property
        }
        return sum;
    }

}
