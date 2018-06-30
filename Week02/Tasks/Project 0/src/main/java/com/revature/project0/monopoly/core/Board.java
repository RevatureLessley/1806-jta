package com.revature.project0.monopoly.core;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import static com.revature.project0.monopoly.core.LogWrapper.Severity.DEBUG;
import static com.revature.project0.monopoly.core.LogWrapper.Severity.WARN;


/**
 * This class represents the Monopoly Board and all the information that the Board should care about
 */
public class Board implements Serializable {

    private static final long serialVersionUID = 3114123156991142900L;
    private final Color PURPLE = new Color(87, 0, 127);
    private final Color CYAN = Color.CYAN;
    private final Color MAHOGANY = new Color(127,35,63);
    private final Color ORANGE = new Color(255,106,0);
    private final Color RED = Color.RED;
    private final Color YELLOW = Color.YELLOW;
    private final Color GREEN = new Color(0,181,98);
    private final Color BLUE = new Color(0,38,133);

    private BoardSquare[] squares;

    public enum BoardPiece{
        TOPHAT, THIMBLE, IRON, BOOT, BATTLESHIP, RACECAR, DOG, WHEELBARROW
    }

    /**
     * This method will return the enum type representation of the string passed in, provided it matches
     * @param piece the String representation of the enum type BoardPiece
     * @return the enum type itself (BoardPiece) or null if @param piece is not one of the enum values
     */
    BoardPiece selectPiece(String piece){
        switch (piece.toLowerCase()){
            case "tophat": return BoardPiece.TOPHAT;
            case "thimble": return BoardPiece.THIMBLE;
            case "iron": return BoardPiece.IRON;
            case "boot": return BoardPiece.BOOT;
            case "battleship": return BoardPiece.BATTLESHIP;
            case "racecar": return BoardPiece.RACECAR;
            case "dog": return BoardPiece.DOG;
            case "wheelbarrow": return BoardPiece.WHEELBARROW;
            default: return null;
        }
    }

    /**
     * This function will initialize the Monopoly GameState Board. It creates BoardSquare Objects for each square on a typical
     * Monopoly board and places them in an array for indexing.
     * Each BoardSquare contains data such as name, color, buying price, etc.
     */
    void initBoard() {
        squares = new BoardSquare[]{
                new BoardSquare("GO", null, -1, null),
                new BoardSquare("Mediterranean Avenue", PURPLE, 60, new int[]{2,10,30,90,160,250}),
                new BoardSquare("Community Chest", null, -1, null),
                new BoardSquare("Baltic Avenue", PURPLE, 60, new int[]{4,20,60,180,320,450}),
                new BoardSquare("Income Tax", null, -1, null),
                new BoardSquare("Reading Railroad", null, 200, new int[]{25,50,100,200}),
                new BoardSquare("Oriental Avenue", CYAN, 100, new int[]{6,30,90,270,400,550}),
                new BoardSquare("Chance", null, -1, null),
                new BoardSquare("Vermont Avenue", CYAN, 100, new int[]{6,30,90,270,400,550}),
                new BoardSquare("Connecticut Avenue", CYAN, 120, new int[]{8,40,100,300,450,600}),
                new BoardSquare("Jail", null, -1, null),
                new BoardSquare("St. Charles Avenue", MAHOGANY, 140, new int[]{10,50,150,450,625,750}),
                new BoardSquare("Electric Company", null, 150, null),
                new BoardSquare("States Avenue", MAHOGANY, 140, new int[]{10,50,150,450,625,750}),
                new BoardSquare("Virginia Avenue", MAHOGANY, 160, new int[]{12,60,180,500,700,900}),
                new BoardSquare("Pennsylvania Railroad", null, 200, new int[]{25,50,100,200}),
                new BoardSquare("St. James Place", ORANGE, 180, new int[]{14,70,200,550,750,950}),
                new BoardSquare("Community Chest", null, -1, null),
                new BoardSquare("Tennessee Avenue", ORANGE, 180, new int[]{14,70,200,550,750, 950}),
                new BoardSquare("New York Avenue", ORANGE, 200, new int[]{16,80,220,600,800,1000}),
                new BoardSquare("Free Parking", null, -1, null),
                new BoardSquare("Kentucky Avenue", RED, 220, new int[]{18,90,250,700,875,1050}),
                new BoardSquare("Chance", null, -1, null),
                new BoardSquare("Indiana Avenue", RED, 220, new int[]{18,90,250,700,875,1050}),
                new BoardSquare("Illinois Avenue", RED, 240, new int[]{20,100,300,750,925,1100}),
                new BoardSquare("B & O Railroad", null, 200, new int[]{25,50,100,200}),
                new BoardSquare("Atlantic Avenue", YELLOW, 260, new int[]{22,110,330,800,975,1150}),
                new BoardSquare("Ventor Avenue", YELLOW, 260, new int[]{22,110,330,800,975,1150}),
                new BoardSquare("Water Works", null, 150, null),
                new BoardSquare("Marvin Gardens", YELLOW, 280, new int[]{24,120,360,850,1025,1200}),
                new BoardSquare("Go To Jail", null, -1, null),
                new BoardSquare("Pacific Avenue", GREEN, 300, new int[]{26,130,390,900,1100,1275}),
                new BoardSquare("North Carolina Avenue", GREEN, 300, new int[]{26,130,390,900,1100,1275}),
                new BoardSquare("Community Chest", null, -1, null),
                new BoardSquare("Pennsylvania Avenue", GREEN, 320, new int[]{28,150,450,1000,1200,1400}),
                new BoardSquare("Short Line", null, 200, new int[]{25,50,100,200}),
                new BoardSquare("Chance", null, -1, null),
                new BoardSquare("Park Place", BLUE, 350, new int[]{35,175,500,1100,1300,1500}),
                new BoardSquare("Luxury Tax", null, -1, null),
                new BoardSquare("Boardwalk", BLUE, 400, new int[]{50,200,600,1400,1700,2000})
        };
        LogWrapper.log(this.getClass(), "Game board initialized");
    }

    /**
     * This function draws the Monopoly Board, using the System.out.print() family.
     * It uses the player list to decide how many and where to place the players.
     * @param pList the list of players playing on this board.
     */
    void drawBoard(ArrayList<Player> pList) {
        //Clear Screen  //TODO: When you uncomment this, make sure that *nothing* gets erased before the user sees it.
//        try {
//            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//        }
//        catch(IOException e){e.printStackTrace();}
//        catch(InterruptedException e) {e.printStackTrace();}

        //Draw
        String[] cells = new String[40];
        for (int i = 0; i < 40; i++) {
            if ( (i >= 10 && i <= 20) || i == 1 || i == 29 ) cells[i] = "_";
            else cells[i] = " ";
        }
        int spaces = 1;
        String[] extraTopLines = new String[pList.size()-1];
        String[] extraBottomLines = new String[pList.size()-1];
        for (int i = 0; i < extraTopLines.length; i++) extraTopLines[i] = String.format("%-21s", "");  //String of 21 spaces
        for (int i = 0; i < extraBottomLines.length; i++) extraBottomLines[i] = String.format("%-21s", "");  //String of 21 spaces
        for (int i = 0; i < pList.size(); i++){
            int index = pList.get(i).getLocation();
            if (cells[index].equals(" ") || cells[index].equals("_")) cells[index] = i+1 + "";
            else {
                if (index < 11){
                    cells[index] += ("," + (i+1));
                    if (spaces < cells[index].length()) spaces = cells[index].length();     //only possible if >5 players
                }
                else if (index < 21){   //NOTE: lazy if
                    int j = 0;
                    while (extraTopLines[j].charAt(convert1(index)) != ' ') j++; //loop until you find an extraline not already occupied at that location
                    extraTopLines[j] = extraTopLines[j].substring(0,convert1(index)) + (i+1) + extraTopLines[j].substring(convert1(index)+1);
                }
                else if (index < 31){   //NOTE: lazy if
                    cells[index] += ("," + (i+1));
                }
                else{   //NOTE: lazy else
                    int j = 0;
                    while (extraBottomLines[j].charAt(convert2(index)) != ' ') j++; //loop until you find an extraline not already occupied at that location
                    extraBottomLines[j] = extraBottomLines[j].substring(0,convert2(index)) + (i+1) + extraBottomLines[j].substring(convert2(index)+1);
                }
            }
        }   //end for
        for(int i = extraTopLines.length-1; i >= 0; i--){
            if (!extraTopLines[i].equals(String.format("%-21s", ""))) System.out.println(extraTopLines[i]);
        }
        System.out.printf("%"+spaces+"s|%s %s %s %s %s %s %s %s %s|%s\n", cells[10], cells[11], cells[12], cells[13], cells[14], cells[15], cells[16], cells[17], cells[18], cells[19], cells[20]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[9], cells[21]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[8], cells[22]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[7], cells[23]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[6], cells[24]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[5], cells[25]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[4], cells[26]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[3], cells[27]);
        System.out.printf("%"+spaces+"s|                 |%s\n", cells[2], cells[28]);
        System.out.printf("%"+spaces+"s|_ _ _ _ _ _ _ _ _|%s\n", cells[1], cells[29]);
        System.out.printf("%"+spaces+"s|%s %s %s %s %s %s %s %s %s|%s\n", cells[0], cells[39], cells[38], cells[37], cells[36], cells[35], cells[34], cells[33], cells[32], cells[31], cells[30]);

        for(String s : extraBottomLines) {
            if (!s.equals(String.format("%-21s", ""))) System.out.println(s);
        }
        LogWrapper.log(this.getClass(), "Board drawn");
    }

    /**
     * Finds all BoardSquares of a single Color
     * @param color the color of the BoardSquare's
     * @return a BoardSquare[3], which may or may not contain null at index [2].
     */
    BoardSquare[] getAllSquaresOfColor(Color color){
        if (color == null) return null; //NOTE: null *is* a valid Color on the board, but it would generate ArrayIndexOutOfBoundsException
        BoardSquare[] array = new BoardSquare[3];
        int i = 0;
        for(BoardSquare square : squares){
            if (square.getColor() == color) array[i++] = square;
        }
        LogWrapper.log(this.getClass(), "Returning BoardSquare[]:" + Arrays.toString(array), DEBUG);
        return array;
    }

    private int convert1(int index){
        return (index - 10) * 2;
    }
    private int convert2(int index){
        return (40 - index) * 2;
    }

    BoardSquare getBoardSquare(int index){
        return squares[index];
    }

    /**
     * This class represents a single tile on a standard Monopoly Board, containing information about:
     * Property Color
     * Tile Name
     * Buy Price (variable based on how developed the property is)
     * Visit Price (ditto)
     * Property Owner
     * Number of Exansions (Houses 1,2,3,4 or Hotel)
     */
    class BoardSquare implements Serializable{
        private static final long serialVersionUID = 6340183481971574039L;
        private final int MAX_EXPANSIONS = 5;

        private Color color;    //null if no color
        private String name;
        private int buyPrice;   // -1 if cannot be bought
        private int[] visitPrices;    //{nothing, 1 house, 2, 3, 4, hotel}
        private Player owner;
        private int expansions;
        private int mortgageValue;
        private int mortgageCost;

        private BoardSquare(String name, Color color, int buyPrice, int[] visitPrices) {
            this.name = name;
            this.color = color;
            this.buyPrice = buyPrice;
            this.visitPrices = visitPrices;
            this.owner = null;
            this.expansions = 0;
            this.mortgageValue = (int)(buyPrice / 2.0f);
            this.mortgageCost = mortgageValue;
            LogWrapper.log(this.getClass(), "BoardSquare created.", DEBUG);
        }

        //TODO: refactor the logic in this method, get rid of hardcode, get rid of Board reference
        int calculateRent(Board board, int diceSum){
            if (this.name.contains("Railroad") || this.name.equals("Short Line")){  //is a railroad
                return visitPrices[owner.getRailRoadCount()];
            }
            else if (this.name.equals("Water Works") || this.name.equals("Electric Company")){  //it a Utility
                int count = owner.getUtilityCount();
                return (count == 1) ? 4 * diceSum : 10 * diceSum;   //Guarenteed that count is 1 or 2
            }
            int rent = visitPrices[expansions];
            if (expansions > 0) return rent;
            else {
                if (owner.ownsBlock(board, this)) rent *= 2;
                LogWrapper.log(this.getClass(), "Returning rent value: "+ rent, DEBUG);
                return rent;
            }
        }

        Color getColor() {
            return color;
        }

        String getName() {
            return name;
        }

        /**
         * This function gets the price of buying the base property or an expansion thereof.
         * @return the base cost of the property if no one owns it, or the cost of expanding one time if it is owned.
         */
        int getBuyPrice() {
            int price;
            if (owner == null) price = buyPrice;
            else{   //expansion prices
                if (color == PURPLE || color == CYAN) price = 50;
                else if (color == ORANGE || color == MAHOGANY) price = 100;
                else if (color == RED || color == YELLOW) price = 150;
                else if (color == GREEN || color == BLUE) price = 200;
                else{
                    String s = (color == null) ? "null" : color.toString();
                    LogWrapper.log(this.getClass(), "Board.getBuyPrice() encountered something unexpected: Color: " + s, WARN);
                    //System.err.println("Board.getBuyPrice() encountered something unexpected");
                    //System.err.println("Color: " + color.toString());
                    price = -1;
                }
            }
            LogWrapper.log(this.getClass(), "Returning price value: " + price, DEBUG);
            return price;
        }

        int getOriginalBuyPrice(){
            return buyPrice;
        }

        int getBuyBackPrice(){
            return mortgageCost;
        }

        void addInterest(){
            mortgageCost = (int)(mortgageCost / 10.0f) + mortgageCost;
        }

        int getVisitPrice() {
            return visitPrices[expansions];
        }

        Player getOwner() {
            return owner;
        }

        void setOwner(Player owner){
            this.owner = owner;
        }

        int getExpansions(){
            return this.expansions;
        }

        void setExpansions(int value){
            this.expansions = value;
        }

        int getExpansionsRemaining(){
            return MAX_EXPANSIONS - expansions;
        }

        int getMortgageValue(){
            return this.mortgageValue + (int)((getBuyPrice() * expansions) / 2.0f);
        }
    }
}
